package multithreading;

public class Sum extends Thread {
    private int res;
    @Override
    public void run(){
    for(int i=0; i<10_00_00; i++){
        res += i;
    }
        System.out.println(res + "Time is : " + System.currentTimeMillis());
    }
}
