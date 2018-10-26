/**
  * @author  Monde Mcongwana 
  * @version 0.0.1
  * Creates a Terrain Grid to install att the sunlight values for each
  *
**/
import java.util.*;
import java.io.*;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;



public class ParallelTerrain {

        //private static float [][] grid ;

        //private static int [][] trees;

        private String filePath;

        private int gridSize;

//private static ForkJoinPool fjPool = new ForkJoinPool();
       public static final int SEQUENTIAL_CUTOFF = 10;
      /**
       * The number of runs to do. The first two will always be warm-ups.
       */
       private static final int NUM_RUNS = 12;
       private static ForkJoinPool fjPool = new ForkJoinPool(2);


       public static int [][] getTrees(){
                 int [][] trees;

            	     Scanner fileScanner = null;
                    // Opens the file
                    try {
                        fileScanner = new Scanner(new File("newFile.txt"));
                    } catch (FileNotFoundException fileError) {
                        System.out.println("Files not found. Make sure you provided the correct paths.");
                        System.exit(0);
                    }

            	      List<String> fileForm = new ArrayList<String>();
                  	while(fileScanner.hasNextLine()){
                           fileForm.add(fileScanner.nextLine());

            	     }

                  //Trees are defined here
                  int numTrees =Integer.parseInt(fileForm.get(2));
                  trees = new int [numTrees][3];
                  for(int i =0; i < numTrees;i++){
                      for(int m = 0;m<3;m++){
                        trees[i][m] = Integer.parseInt(((fileForm.get(i+3)).split(" "))[m]);
                        //System.out.println(Arrays.deepToString(trees));
                      }
                  }

                 return trees;
       }

       public static float[][] getDataPoints(){
                  float [][] grid ;

            	     Scanner fileScanner = null;
                    // Opens the file
                    try {
                        fileScanner = new Scanner(new File("newFile.txt"));
                    } catch (FileNotFoundException fileError) {
                        System.out.println("Files not found. Make sure you provided the correct paths.");
                        System.exit(0);
                    }

            	      List<String> fileForm = new ArrayList<String>();
                  	while(fileScanner.hasNextLine()){
                           fileForm.add(fileScanner.nextLine());

            	     }

                   //Grid is defined here
                    String umz = fileForm.get(1);
                    String [] k = umz.split(" "); /*
                   //Grid is defined here
                    String umz = fileForm.get(1);
                    String [] k = umz.split(" ");
                    int size = Integer.parseInt(((fileForm.get(0)).split(" "))[0]);
                    grid = new float[size][size];
                    for(int y=0;y< size;y++){
                       for(int x = 0;x<size;x++){
                          grid[y][x] = Float.parseFloat(k[x]);
                       }

                    }*/

                    int size = Integer.parseInt(((fileForm.get(0)).split(" "))[0]);
                    grid = new float[size][size];
                    for(int y=0;y< size;y++){
                       for(int x = 0;x<size;x++){
                          grid[y][x] = Float.parseFloat(k[x]);
                       }

                    }
                  return grid;

       }

       public static float[] correlateData(float[][] grid,int[][] trees) {
         return fjPool.invoke(new FillTerrain(grid, trees, 0,trees.length));
    }
}


    /*public FillTerrain(String filePath){
        this.filePath = filePath;
    }*/
      /* public static float[][] getDataPoints(){
          Scanner fileScanner = null;
          // Opens the file
          try {
              fileScanner = new Scanner(new File("newFile.txt"));
          } catch (FileNotFoundException fileError) {
              System.out.println("Files not found. Make sure you provided the correct paths.");
              System.exit(0);
          }

          int numLines = fileScanner.nextInt();
          //gridSize = numLines;
          float[][] dataPoints = new float[numLines][numLines];
          int dataPointIndex = 0;

          float[] terrainPoints = new float[numLines*numLines];
          while (fileScanner.hasNextFloat()) {
                terrainPoints[dataPointIndex] = fileScanner.nextFloat() ;
                dataPointIndex++;
          }
          int countIndex =0;
          for(int y =0;y<numLines;y++){
              for(int x =0;x<numLines;x++){
                dataPoints[y][x] = terrainPoints[countIndex];
                countIndex++;
              }
          }
          //System.out.println(terrainPoints.length);

        //  fileScanner.close();
         return dataPoints;
      }




        public static int [][] getTrees(){
             Scanner fileScanner= null;
            Scanner fileScannerClone =null;
            int numTrees=0;
            try {
             fileScanner = new Scanner(new File("newFile.txt"));
            //Scanner fileScannerClone;
             fileScannerClone = fileScanner;
            } catch (FileNotFoundException fileError) {
                System.out.println("Files not found. Make sure you provided the correct paths.");
                System.exit(0);
            }
            int count = 0;
            String[] dummy = new String[3];
            while (fileScannerClone.hasNextLine()) {
              if (count<3){int k =0;}

              else if (count==3){
                  String nt  = (fileScannerClone.nextLine()).replaceAll("\\s+","");
                //  numTrees = Integer.parseInt(nt);
              }else if ( count >3){
              System.out.println(fileScannerClone.nextLine()+" "+count);
              }
                //long num = 0;

              /*String [] line = (fileScannerClone.nextLine()).split(" ");
                if(count==2){
                  numTrees = Integer.parseInt(fileScannerClone.nextLine());
                  System.out.println("Monde");

                }else{
                  System.out.println(count);
                  System.out.println(line);
                   System.out.println(numTrees);
                }*/
                //if(count < 2){int k =1;}


                //String line = fileScannerClone.nextLine();
              /*  count++;

            }//System.out.println(numTrees);

            //int numLines = fileScanner.nextInt();
            int [][] dataTree = new int[numTrees][3];
            //String StringTrees = file
            int dataTreeIndex = 0;
            int dataIndex = 0;
            int dataPointIndex =0;
                }
                //if(count < 2){int k =1;}


            while (dataIndex < numTrees) {

                if (dataTreeIndex==2){
                //dataTree = new int [Integer.parseInt(fileScanner.nextLine())][3];
                }else if(dataPointIndex>2){
                    String [] treePoints = (fileScanner.nextLine()).split(" ");
                    for(int i =0; i<3;i++){
                        dataTree[dataIndex][i] =Integer.parseInt(treePoints[i]);
                    }
                    //dataPoints[dataIndex] = fileScanner.nexLine();
                    dataIndex++;
                  //  System.out.println(dataTree);
                }
              dataPointIndex++;


            //fileScanner.close();

        }
        return dataTree;

  }

  /*
public static void getTrees1(){
    Scanner fileScanner = null;//[Intger.parseInt(fileScanner.hasNextLine())]
    // Opens the file[Intger.parseInt(fileScanner.hasNextLine())]
    try {
        fileScanner = new Scanner(new File("sample_input.txt"));
    } catch (FileNotFoundException fileError) {
        System.out.println("Files not found. Make sure you provided the correct paths.");
        System.exit(0);
    }

    //int numLines = fileScanner.nextInt();

    int dataTreeIndex = 0;
    int dataIndex = 0;
    int dataPointIndex =0;
    while (fileScanner.hasNextLine()) {
        if (dataPointIndex == 0) {
        int  k = 1;
        }
        if (dataTreeIndex==2){
        long [][] dataTree = new long [Integer.parseInt(fileScanner.nextLine())][3];
        }else if(dataPointIndex>2){
            long [][] dataTree = new long [Integer.parseInt(fileScanner.nextLine())][3];
            String [] treePoints = (fileScanner.nextLine()).split(" ");
            System.out.println(dataTree.length);
            for(int i =0; i<3;i++){
        //        dataTree[dataIndex][i] =Integer.parseInt(treePoints[i]);
            //    System.out.println(dataTree.length);
            }
            //dataPoints[dataIndex] = fileScanner.nexLine();
            dataIndex++;
          //  System.out.println(dataTree);
        }
      dataPointIndex++;


    //fileScanner.close();

}
*/
//return dataTree;
