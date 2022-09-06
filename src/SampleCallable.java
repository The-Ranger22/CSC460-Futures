import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

public class SampleCallable implements Callable<Integer> {
    public final String id;


    SampleCallable(int id){
        this.id = String.format("CT%d", id);
    }


    @Override
    public Integer call() throws Exception {
        int delay = ThreadLocalRandom.current().nextInt(100) + 101;

        try{
            Thread.sleep(delay);
        } catch (InterruptedException e){
            System.out.printf("[%s][%s]\n", this.id, e.toString());
        }
        Integer randNum = ThreadLocalRandom.current().nextInt(30) + 1;
        Driver.printLine(this.id, String.format("Calculating square of %d", randNum));
        randNum *= randNum;
        return randNum;
    }
}
