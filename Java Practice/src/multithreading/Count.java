package multithreading;

public class Count extends Thread {
    @Override
    public void run() {
//        super.run();
        for(int i=0; i<10_00_00_00_0; i++){
        }
        System.out.println(System.currentTimeMillis());
    }
}
