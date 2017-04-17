package problem2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

public class Main {
    static int thread_size;

    public static void main(String[] args) {
        if(args.length != 2){
            printHelp();
            exit(0);
        }

        thread_size = Integer.parseInt(args[1]);

        File file = new File(args[0]);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

            ArrayList<String> arrayList = new ArrayList<>();

            String text;

            //get file text
            while((text = bufferedReader.readLine()) != null){
                arrayList.add(text);
            }

            // row x column = byby
            String[] byby = arrayList.get(0).split(" ");
            int row_num = Integer.parseInt(byby[0]);
            int column_num = Integer.parseInt(byby[1]);

            // get sub list for matrix
            List<String> arrayList1 = arrayList.subList(1, row_num+1);
            List<String> arrayList2 = arrayList.subList(row_num+2, row_num+column_num+2);

            int[][] matrix_1 = getMatrix(arrayList1, row_num, column_num);
            int[][] matrix_2 = getMatrix(arrayList2, column_num, row_num);

            // mul for matrix
            long startTime = System.currentTimeMillis();

            //single thread
            //int[][] result_mat = getMatrixMul(matrix_1, matrix_2);

            //multi thread
            Main main = new Main();
            int[][] result_mat = main.getMatrixMulByThread(matrix_1, matrix_2);

            long endTime = System.currentTimeMillis();

            // print result
            System.out.print("\nResult\n");
            for(int[] columns : result_mat){
                for(int column : columns){
                    System.out.print(column+" ");
                }
                System.out.print("\n");
            }

            System.out.println("\nThread Size is "+thread_size+"\nExecute Time is "+(endTime-startTime)+"ms");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int[][] getMatrix(List<String> list, int row_num, int column_num){
        System.out.print("\nGet Matrix\n");
        int[][] matrix = new int[row_num][column_num];
        for(int i=0;i<row_num;i++){
            String[] columns = list.get(i).split(" ");
            for(int j=0;j<column_num;j++){
                matrix[i][j] = Integer.parseInt(columns[j]);
                System.out.print(matrix[i][j]+" ");
            }
            System.out.print("\n");
        }

        return matrix;
    }

    //not using thread(single thread)
    public static int[][] getMatrixMul(int[][] mat_1, int[][] mat_2){
        int row_num = mat_1.length;
        int column_num = mat_2[0].length;

        int[][] result = new int[row_num][row_num];

        for(int i=0;i<row_num;i++){
            for(int j=0;j<column_num;j++){
                for(int k=0;k<mat_1[0].length;k++){
                    result[i][j] += mat_1[i][k] * mat_2[k][j];
                }
            }
        }

        return result;
    }

    public int[][] getMatrixMulByThread(int[][] mat_1, int[][] mat_2){
        MatrixMulThread.matrixA = mat_1;
        MatrixMulThread.matrixB = mat_2;
        MatrixMulThread.working_row_index = 0;
        MatrixMulThread.working_column_index = 0;
        MatrixMulThread.matrixResult = new int[mat_1.length][mat_2[0].length];

        ArrayList<MatrixMulThread> matrixMulThreads = new ArrayList<>();


        for(int i=0;i<thread_size;i++){
            MatrixMulThread matrixMulThread = new MatrixMulThread();
            matrixMulThreads.add(matrixMulThread);
            matrixMulThread.start();
        }

        for(MatrixMulThread matrixMulThread : matrixMulThreads){
            try {
                matrixMulThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return MatrixMulThread.getMatrix();
    }

    public static void printHelp(){
        System.out.println("args - [File] [Thread Num]");
        exit(1);
    }
}
