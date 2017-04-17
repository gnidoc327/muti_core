package practice.ex1;

/**
 * Created by gnidoc327 on 2017. 4. 7..
 */
class C extends Thread {
    int i;
    C(int i) { this.i = i; }
    public void run() {
        System.out.println("Thread " + i + " says hi");
        System.out.println("Thread " + i + " says bye");
    }
}

