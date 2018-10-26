import java.util.*;
import java.io.*;

public class printData{

    float [][] grid;
    int [][] trees;

    public  void terrain(){

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
        String [] k = umz.split(" ");
        int size = Integer.parseInt(((fileForm.get(0)).split(" "))[0]);
        grid = new float[size][size];
        for(int y=0;y< size;y++){
           for(int x = 0;x<size;x++){
              grid[y][x] = Float.parseFloat(k[x]);
           }

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




        //print grid
        //System.out.println(Arrays.deepToString(grid));
        //print Trees
        System.out.println(Arrays.deepToString(trees));


        //return umz;






    }



    public static void main(String args[]){

    //private void getDataPoints() {
        /*Scanner fileScanner = null;
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
            //if(dataPointIndex==1){
              terrainPoints[dataPointIndex] = fileScanner.nextFloat() ;
            //dataPoints[dataPointIndex] = fileScanner.nextFloat();
            dataPointIndex++;
        }
        int countIndex =0;
        for(int y =0;y<numLines;y++){
            for(int x =0;x<numLines;x++){
              dataPoints[y][x] = terrainPoints[countIndex];
              countIndex++;
            }
        }
        /*for(int i =0; i<dataPoints.length;i++){
          for(int k =0;k<dataPoints.length;i++){
            System.out.print(dataPoints[i][k] + " ");
          }
          System.out.println();

        }*/
        //System.out.println(Arrays.deepToString(dataPoints));



     	printData T = new printData();
	      T.terrain();//.split(" ");
         //System.out.println(Arrays.toString(k));
         /*for(int i =0;k.length;i++){
           System.out.println();
         }*/

        //System.out.println( T.terrain());

        //fileScanner.close();
       // return dataPoints;
    }
}
