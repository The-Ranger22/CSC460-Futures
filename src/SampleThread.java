import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SampleThread implements Runnable {

    private final String id;
    private static int total_created = 0;
    public SampleThread() {
        id = String.format("ST%d", total_created++);
    }

    @Override
    public void run() {
        int delay = ThreadLocalRandom.current().nextInt(1000) + 1;
        try{
            Driver.printLine(this.id, String.format("Delaying for %dms", delay));
            Thread.sleep(delay);
        } catch (InterruptedException e){
            System.out.printf("[%s][%s]\n", this.id, e.toString());
        }
    }
}
