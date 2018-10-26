/**
 * @author Monde Mcongwana   
 * @version 0.0.1
 * Does the actual calculating of the correlation of the two data sets. New threads are made
 * until the sequential cutoff is reached after which it does the sequential algorithm.
 */

import java.util.concurrent.RecursiveTask;
import java.util.*;
import java.util.concurrent.ForkJoinPool;

/**
 * CorrelateArray extends RecursiveAction because it doesn't need to return anything.
 * It's used to calculate the correlation of the two given data sets. They results are stored in
 * correlationArray. correlationArray's mutability is exploited to remove the need for joining or
 * returning arrays.
 */
public class FillTerrain extends RecursiveTask<Float> {
    /**
     * The end of the indices that have to be covered by a thread.
     */
    private int endIndex;
    /**
     * The start of the indices that have to be covered by a thread.
     */
    private int startIndex;
    /**
     * Transmitted data set.
     */
    private float[][] terrain;
    /**
     * Received data set.
     */
    private int[][] trees;
    /**
     * The data set that will hold the answers.
     */
    public float[] treeTotalArray;
    public static float zum;

    /**
     * Parameterized constructor. Initializes all the necessary variables.
     * @param transmittedDataPoints    Transmitted data set.
     * @param receivedDataPoints       Received data set.
     * @param crossCorrelatedData      The data set that will hold the answers.
     * @param startIndex               The start of the indices that have to be covered by a thread.
     * @param endIndex                 The end of the indices that have to be covered by a thread.
     */
    public FillTerrain(float[][] terrainDataPoints, int[][] treeDataPoints, int startIndex, int endIndex) {

        this.endIndex = endIndex;
        this.startIndex = startIndex;
        this.terrain = terrainDataPoints;
        this.trees = treeDataPoints;
        treeTotalArray = new float[treeDataPoints.length];

    }


    @Override
    protected Float compute() {
        if ((endIndex - startIndex) < ParallelTerrain.SEQUENTIAL_CUTOFF) {
            //If the sequential cutoff is met, switch to the sequential task.
            for (int i = startIndex; i < endIndex; i++) {


                float sum = 0;
                int x = trees[i][0];
                int y = trees[i][1];
                int canopy = trees[i][2];
                int rx;
                int ry;
                /**
                  * Border checks
                */

                if (x + canopy >terrain.length){
                   rx = terrain.length;
                } else{
                  rx = x+canopy;
                }

                if (y + canopy >terrain.length){
                   ry = terrain.length;
                } else{
                  ry = y+canopy;
                }
                float zum = 0;
                for(int k = y; y <=ry;y++){
                    for(int l = x;x<=rx;x++){
                       zum += terrain[k][l];
                    //   System.out.println(sum);

                    }
                  //  System.out.println();
                }

                //treeTotalArray[i] = sum;
                //System.out.println("sq");
              //  System.out.println(Arrays.toString(treeTotalArray));


            }
              return  zum;

          //  return treeTotalArray;
        } else {
            // If the sequential cutoff isn't met, make more threads.
            FillTerrain leftBranch = new FillTerrain(terrain, trees,
                                                            startIndex,
                                                           (startIndex + endIndex)/2);
            FillTerrain rightBranch = new FillTerrain(terrain, trees,

                                                            (startIndex + endIndex)/2, endIndex);
            //Make a new thread.
           leftBranch.fork();
            //Do the work of a new thread in this thread to save time
            float right =   rightBranch.compute();
            //Wait for the left branch to finish running before continuing.
            float left =  leftBranch.join();
            return left + right;
            //System.out.println("Pa");
        }

        //return treeTotalArray;
    }
}
