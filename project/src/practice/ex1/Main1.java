package practice.ex1;

public class Main1 {

    public static void main(String[] args) {
        System.out.println("main thread start!");
        for(int i=1; i <= 5; ++i) {
            practice.ex1.C c = new C(i);
            c.start();
        }
        System.out.println("main thread end!");
    }
}
