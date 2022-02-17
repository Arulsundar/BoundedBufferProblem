import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        MyBlockingQueue q = new MyBlockingQueue(3) ;

        final Runnable producer = () -> {
            while(true) {
                try {
                    int r = (int) (Math.random() * 10);
                    System.out.println("Producing : " + r);
                    q.offer(r);
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                    break ;
                }
            }
        };

        new Thread(producer).start() ;

        final Runnable consumer = () -> {
            while(true) {
                try {
                    System.out.println("Consuming : " + q.poll()) ;
                    TimeUnit.SECONDS.sleep(1) ;
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                    break ;
                }
            }
        };

        new Thread(consumer).start();
    }
}