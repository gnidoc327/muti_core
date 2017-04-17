package practice.ex4;

import java.util.ArrayList;

/**
 * Created by gnidoc327 on 2017. 4. 7..
 */
public class Main4 {
    private static final int NUM_END = 200000;
    public static void main(String[] args) {
        Main4 main4 = new Main4();
        main4.run();

        System.out.println("\n##################\n");

        main4.run_by_thread();
    }

    private void run(){
        int counter=0;
        int i;

        long startTime = System.currentTimeMillis();
        for (i=0;i<NUM_END;i++) {
            if (isPrime(i)) counter++;
        }
        long endTime = System.currentTimeMillis();
        long timeDiff = endTime - startTime;
        System.out.println("Execution Time : "+timeDiff+"ms");
        System.out.println("1..."+(NUM_END-1)+" prime# counter=" + counter +"\n");
    }

    private void run_by_thread(){
        int counter=0;
        long startTime = System.currentTimeMillis();

        //TODO count prime
        int NUM_THREAD = 4;
        int unit = NUM_END/NUM_THREAD;
        ArrayList<PrimeThread> primeThreads = new ArrayList<>();

        for(int i=0;i<NUM_THREAD;i++){
            PrimeThread primeThread = new PrimeThread(i*unit, (i+1)*unit);
            primeThread.start();
            primeThreads.add(primeThread);
        }

        for(PrimeThread primeThread : primeThreads){
            try {
                primeThread.join();
                counter += primeThread.getResult();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long endTime = System.currentTimeMillis();
        long timeDiff = endTime - startTime;
        System.out.println("Execution Time : "+timeDiff+"ms");
        System.out.println("1..."+(NUM_END-1)+" prime# counter=" + counter +"\n");
    }

    class PrimeThread extends Thread{
        int start_num;
        int end_num;
        int result=0;

        public PrimeThread(int start_num, int end_num) {
            this.start_num = start_num;
            this.end_num = end_num;
        }

        @Override
        public void run() {
            long current_time = System.currentTimeMillis();

            for(int i=start_num;i<end_num;i++){
                if(isPrime(i)){
                    result++;
                }
            }

            long end_time = System.currentTimeMillis();

            System.out.println("thread ID : "+this.getId()+" is finish\n" +
                    "start num is "+start_num+". Calculate time is "+(end_time-current_time)+"ms");
        }

        public int getResult(){
            return result;
        }
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
