package problem1;

/**
 * Created by gnidoc327 on 2017. 4. 15..
 */
class PrimeDynamicThread extends Thread{
    private int count;
    private static int wait_number = 0;

    @Override
    public void run() {
        int NUM_MAX = Main.NUM_MAX;
        while(wait_number< NUM_MAX){
            int working_num = getNextNumber();

            if(isPrime(working_num)){
                count++;
            }
        }
    }

    private boolean isPrime(int x) {
        int i;
        if (x<=1) return false;
        for (i=2;i<x;i++) {
            if ((x%i == 0) && (i!=x)) return false;
        }
        return true;
    }

    int getCount(){
        return count;
    }

    private static synchronized int getNextNumber(){
        int num = wait_number;
        wait_number += 1;
        return num;
    }
}
