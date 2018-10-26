import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.util.*;

public class  main {

  public static void main(String[] args){
    ParallelTerrain terraini = new ParallelTerrain();
    float[][] terrain = terraini.getDataPoints();
    int trees[][] = terraini.getTrees();
    float[] treeTotalArray = new float[trees.length];
    FillTerrain fillTerrain = new FillTerrain(terrain,trees,0,trees.length);
    System.out.println(fillTerrain.zum);


    terraini.correlateData(terrain,trees);
    //FillTerrain fillTerrain = new FillTerrain(field,trees,0,trees.length);
    //fillTerrain.compute();
    //System.out.println(Arrays.toString(fillTerrain.compute()));
      //public FillTerrain filling = new FillTerrain();
      //public FindTotal finding = new FindTotal();

  //  System.out.println(Arrays.deepToString(trees));


/*    for (int i = 0; i < trees.length; i++) {


        float sum = 0;
        int x = trees[i][0];
        int y = trees[i][1];
        int canopy = trees[i][2];
        //int
        int rx;
        int ry;
        /**
          * Border checks
        */
/*
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

        for(int k = y; y <=ry;y++){
            for(int l = x;x<=rx;x++){
               sum += terrain[k][l];
               //System.out.println(rx);
              // System.out.println(x);
              // System.out.println(sum);


            }
          //  System.out.println();
        }
        treeTotalArray[i] = sum;
        //System.out.println("sq");
      //  System.out.println(Arrays.toString(treeTotalArray));


    }*/

  //  System.out.println(Arrays.toString(treeTotalArray));




    //int [][] trees = terrain.getTrees();
    //System.out.println(Arrays.toString((terrain.correlateData(field,trees))));
    //FillTerrain fillTerrain = new FillTerrain(field,trees,0,trees.length);
    //fillTerrain.compute();
    //System.out.println(Arrays.toString(fillTerrain.compute()));
      //public FillTerrain filling = new FillTerrain();
      //public FindTotal finding = new FindTotal();



      //System.out.println((trees.length));
      //int startPoint = 0;
      //int endPoint = trees.length;
      //System.out.println("Monde");

      /*FillTerrain filling = new FillTerrain(field,trees,startPoint,endPoint);
      float[] totals = filling.treeTotalArray;
      FindTotal finding = new FindTotal(totals,startPoint,totals.length);
      Float total = finding.compute();
      System.out.println(total);*/

      //  ParallelTerrain terrain = new ParallelTerrain();
        //System.out.println(e);
        //System.out.println(terrain.getTrees());








    /*try{ ParallelTerrain terrain = new ParallelTerrain();
      //public FillTerrain filling = new FillTerrain();
      //public FindTotal finding = new FindTotal();

      float[][] field = terrain.getDataPoints();
      int [][] trees = terrain.getTrees();
      int startPoint = 0;
      int endPoint = trees.length;
      FillTerrain filling = new FillTerrain(field,trees,startPoint,endPoint);
      float[] totals = filling.treeTotalArray;
      FindTotal finding = new FindTotal(totals,startPoint,totals.length);
      Float total = finding.compute();
    }catch (Exception e) {
        ParallelTerrain terrain = new ParallelTerrain();
        System.out.println(e);
        System.out.println(terrain.getTrees());

    }*/

    //  System.out.println(System.nanoTime());

  }
}

  /*public static void main(String[] args) {
    if (args.length < 2) {
        System.out.println("You need to specify the sent-data file and received-data file, " +
                "alternatively, use make run-seq or run-parallel.");
        System.exit(0);
    }
    String transmittedDataPath = args[0];
    String receivedDataPath = args[1];

    float[] transmittedDataPoints = getDataPoints(transmittedDataPath);
    float[] receivedDataPoints = getDataPoints(receivedDataPath);
    float[] crossCorrelatedData = new float[transmittedDataPoints.length];
    float[] maxIndexValue;
    long startTime;
    double totalTimeTakenb
    double[] timeRecord = new double[10];
    FileWriter fileO = null;
    try {
        fileO = new FileWriter("outputPar.out");
        fileO.append("");
    } catch (IOException e) {
        e.printStackTrace();
    }
    for (int i = 0; i < NUM_RUNS; i++) {
        startTime = System.nanoTime();
        //Timed Functions
        correlateData(transmittedDataPoints, receivedDataPoints, crossCorrelatedData);
        maxIndexValue= findMaxIndex(crossCorrelatedData);
        totalTimeTaken = (System.nanoTime() - startTime) / NS_TO_MS;

        if (i < 2) {
            System.out.println("\nWarm-up Run #" + (i + 1) + "\n===================");
            System.out.println("Total Time Taken:\t" + totalTimeTaken + "ms");

            try {
                if (fileO != null) {
                    fileO.append("\n\nWarm-up Run #").append(Integer.toString(i + 1)).append(
                            "\n===================");
                    fileO.append("\nTotal Time Taken:\t").append(Double.toString
                            (totalTimeTaken)).append("ms");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("\nParallel Run #" + (i - 1) + "\n===================");
            System.out.println("Total Time Taken:\t" + totalTimeTaken + "ms");
            System.out.println("Max Value:\t\t" + maxIndexValue[1] +
                    "\nReceived Time:\t\t" + maxIndexValue[0]);
            timeRecord[i - 2] = totalTimeTaken;
            try {
                if (fileO != null) {
                    fileO.append("\n\nParallel Run #").append(Integer.toString(i - 1)).append(
                            "\n===================");
                    fileO.append("\nTotal Time Taken:\t").append(Double.toString
                            (totalTimeTaken)).append("ms");
                    fileO.append("\nMax Value:\t\t").append(Float.toString(maxIndexValue[1]))
                            .append
                                    ("\nReceived Time:\t\t").append(Float.toString(maxIndexValue[0]));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    double sum = sumTimes(timeRecord);
    System.out.println("\n\nTime taken to do 10 runs: " + sumTimes(timeRecord) + "ms");
    System.out.println("Average Time taken to do a run: " + (sum / 10) + "ms");
    try {
        if (fileO != null) {
            fileO.append("\n\nTime taken to do 10 runs: ").append(Double.toString(sum)).append("ms");
            fileO.append("\nAverage Time taken to do a run: ").append(
                    Double.toString(sum / 10)).append("ms\n");
            fileO.close();
        }
    } catch (Exception ioe) {
        ioe.printStackTrace();
    }
  }*/
