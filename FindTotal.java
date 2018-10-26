/**
 * @author Monde Mcongwana   
 * @version 0.0.1
 * Finds the maximum value in correlatedData. New threads are made until the sequential cutoff
 * is reached after which it does the sequential algorithm.
 */

import java.util.concurrent.RecursiveTask;

public class FindTotal extends RecursiveTask<Float> {
    /**
     * The start of the indices that have to be covered by a thread.
     */
    private int startIndex;
    /**
     * The end of the indices that have to be covered by a thread.
     */
    private int endIndex;
    /**
     * The data set that has all the correlated data points.
     */
    private float[] treeTotals;//correlatedData;

    /**
     * Paramterized constructor. Initializes all the necessary variables.
     * @param treeTotalArray         The data set that has all the correlated data points.
     * @param startIndex               The start of the indices that have to be covered by a thread.
     * @param endIndex                 The end of the indices that have to be covered by a thread.
     */
    public FindTotal(float[] treeTotalArray, int startIndex, int endIndex) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        treeTotals = treeTotalArray.clone();
    }


    /**
     * Compares two float[] by their second data item.
     * @param floatOne    A float[] to compare.
     * @param floatTwo    A float[] to compare.
     * @return            float[] with the biggest values at index 1.
     */
    /*public static float[] total(float[] floatOne, float[] floatTwo) {
        if (floatOne[1] > floatTwo[1]) {
            return floatOne;
        }
        return floatTwo;
    }*/

    @Override
    public Float compute() {
        if ((endIndex - startIndex) < ParallelTerrain.SEQUENTIAL_CUTOFF) {
            //If the sequential cutoff is met, switch to the sequential task.
            float max = 0;
            //float maxIndex = -1;
            for (int i = startIndex; i < endIndex; i++) {
                  //correlatedData[i] > max
                  max += treeTotals[i];
                    //maxIndex = i;

            }
            //return new float[] {maxIndex, max};

            return max;
        } else {
            // If the sequential cutoff isn't met, make more threads.
            FindTotal leftBranch = new FindTotal(treeTotals, startIndex, (endIndex + startIndex)/2);
            FindTotal rightBranch = new FindTotal(treeTotals, (endIndex + startIndex)/2, endIndex);

            //Make a new thread.
           rightBranch.fork();
            //Do the work of a new thread in this thread to save time
            float leftBranchAnswer = rightBranch.compute();
            //Wait for the left branch to finish running before continuing.
            float rightBranchAnswer = leftBranch.join();
            //Returns the biggest of the two branches.
          //  return leftBranchAnswer+rightBranchAnswer;
            return rightBranchAnswer;

        }
    }
}
