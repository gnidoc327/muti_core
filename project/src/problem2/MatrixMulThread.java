package problem2;

/**
 * Created by kimhs on 2017-04-17.
 */
public class MatrixMulThread extends Thread{
    static int[][] matrixA;
    static int[][] matrixB;
    static int[][] matrixResult;
    static int working_row_index;
    static int working_column_index;

    @Override
    public void run() {
        while(working_row_index < matrixResult.length
                && working_column_index < matrixResult[0].length){
            int[] index = getIndex();

            calcMulForIndex(index);
        }
    }

    public static int[][] getMatrix(){
        return matrixResult;
    }

    //index calculate
    public static synchronized int[] getIndex(){
        int i=working_row_index;
        int j=working_column_index;

        working_column_index += 1;

        if(working_column_index >= matrixResult[0].length){
            working_column_index = 0;
            working_row_index += 1;
        }

        int[] temp = new int[2];
        temp[0] = i;
        temp[1] = j;

        return temp;
    }

    public void calcMulForIndex(int[] index){
        int i=index[0];
        int j=index[1];

        if(i >= matrixResult.length || j >= matrixResult[0].length){
            return;
        }

        int temp=0;
        for(int k=0;k<matrixA[0].length;k++){
            temp += matrixA[i][k]*matrixB[k][j];
            matrixResult[i][j] = temp;
        }
    }
}
