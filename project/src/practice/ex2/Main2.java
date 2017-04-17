package practice.ex2;

import java.util.ArrayList;

/**
 * Created by gnidoc327 on 2017. 4. 7..
 */
public class Main2 {
    private static final int NUM_END = 10000;
    private static final int NUM_THREAD = 4; // assume NUM_END is divisible by NUM_THREAD

    public static void main(String[] args) {
        int[] int_arr = new int [NUM_END];
        int i,s;
        for (i=0;i<NUM_END;i++) int_arr[i]=i+1;
        s=sum(int_arr);
        System.out.println("sum=" + s) ;
    }

    static int sum(int[] arr) {
        // insert your code here
        ArrayList<SumThread> sumThreads = new ArrayList<>();

        int unit = NUM_END/NUM_THREAD;

        for(int i=0;i<NUM_THREAD;i++){
            SumThread sumThread = new SumThread(arr, i*unit, (i+1)*unit);
            sumThreads.add(sumThread);
            sumThread.start();
        }

        int result=0;

        for(SumThread sumThread : sumThreads){
            try {
                sumThread.join();
                result += sumThread.getResult();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    //
    static int sum_for_dc(int[] arr){
        int n = arr[arr.length];
        return n*(n+1)/2;
    }
}
