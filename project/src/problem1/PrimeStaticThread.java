package problem1;

/**
 * Created by gnidoc327 on 2017. 4. 7..
 */
public class PrimeStaticThread extends Thread{
    private int start_num;
    private int end_num;
    private int count=0;

    public PrimeStaticThread(int start, int end) {
        this.start_num = start;
        this.end_num = end;
    }

    @Override
    public void run() {
        long start_time = System.currentTimeMillis();

        for(int i=start_num;i<end_num;i++){
            if(isPrime(i)){
                count++;
            }
        }

        long end_time = System.currentTimeMillis();
        //System.out.println("Thread id : "+this.getId()+". Excute time is "+(end_time-start_time)+"ms");
    }

    public int getCount(){
        return count;
    }

    private static boolean isPrime(int x) {
        int i;
        if (x<=1) return false;
        for (i=2;i<x;i++) {
            if ((x%i == 0) && (i!=x)) return false;
        }
        return true;
    }
}
