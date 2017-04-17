package problem1;

import java.util.ArrayList;

import static java.lang.System.exit;

public class Main {
    static final int NUM_MAX = 200000;
    private static int thread_size;

    public static void main(String[] args) {
        if(args.length != 2){
            printHelp();
        }

        thread_size = Integer.parseInt(args[0]);
        int option = Integer.parseInt(args[1]);

        Main main = new Main();

        long start_time = System.currentTimeMillis();

        switch (option){
            case 0:
                main.run_static();
                System.out.println("Static Run");
                break;
            case 1:
                main.run_dynamic();
                System.out.println("Dynamic Run");
                break;
        }


        long end_time = System.currentTimeMillis();
        System.out.println("Thread Count is "+thread_size);
        System.out.println("Excute time is "+(end_time-start_time)+"ms");
    }

    public static void printHelp(){
        System.out.println("args - [Thread Num] [Option]\n[Option] is 0 or 1.\n0 is static, 1 is dynamic");
        exit(1);
    }

    public void run_static(){
        int unit = NUM_MAX / thread_size;
        int result_count = 0;

        ArrayList<PrimeStaticThread> primeStaticThreads = new ArrayList<>();

        for(int i=0;i<thread_size;i++){
            PrimeStaticThread primeStaticThread = new PrimeStaticThread(i* unit, (i+1)* unit);
            primeStaticThread.start();
            primeStaticThreads.add(primeStaticThread);
        }

        for(PrimeStaticThread primeStaticThread : primeStaticThreads){
            try {
                primeStaticThread.join();
                result_count += primeStaticThread.getCount();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Prime count is "+ result_count);
    }

    public void run_dynamic(){
        int result_count = 0;

        ArrayList<PrimeDynamicThread> primeDynamicThreads = new ArrayList<>();
        for(int i=0;i<thread_size;i++){
            PrimeDynamicThread primeDynamicThread = new PrimeDynamicThread();
            primeDynamicThreads.add(primeDynamicThread);
            primeDynamicThread.start();
        }

        for(PrimeDynamicThread primeDynamicThread : primeDynamicThreads){
            try {
                primeDynamicThread.join();
                result_count += primeDynamicThread.getCount();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Prime count is "+ result_count);
    }
}
