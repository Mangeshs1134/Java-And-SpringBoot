package multithreading;

public class Main {
    public static void main(String[] args) {

        Sum sum = new Sum();
        Count count = new Count();
        sum.start();
        count.start();
        try {
            sum.join();
            count.join();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
