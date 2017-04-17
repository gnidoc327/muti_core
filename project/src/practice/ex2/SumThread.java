package practice.ex2;

/**
 * Created by gnidoc327 on 2017. 4. 7..
 */
class SumThread extends Thread {
    int lo, hi; // fields for communicating inputs
    int[] arr;
    int ans = 0; // for communicating result
    SumThread(int[] a, int l, int h) {
        lo=l; hi=h; arr=a;
    }
    public void run() {
        // insert your code here
        for(int i=lo;i<hi;i++){
            ans += arr[i];
        }
    }

    public int getResult(){
        return ans;
    }
}
