import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Driver {
    public static void main(String[] args){
        partA();
        partB();
    }
    private static void partA(){
        printHeader("PartA");
        ExecutorService ES = Executors.newFixedThreadPool(7);
        List<Future<?>> myFutures = new ArrayList<Future<?>>();
        for(int i = 0; i < 50; i++){
            SampleThread temp = new SampleThread();
            Future<?> f = ES.submit(temp);
            myFutures.add(f);
        }

        for (Future<?> future : myFutures) {
            try {
                future.get();
            } catch (Exception e){
                System.out.printf("[Driver][%s]\n", e.toString());
            }
        }
        ES.shutdown();
        printFooter();
    }
    private static void partB(){
        printHeader("PartB");
        ExecutorService ES = Executors.newCachedThreadPool();
        Future<Integer>[] futures = new Future[200];


        for (int i = 0; i < futures.length; i++){
            SampleCallable temp = new SampleCallable(i);
            futures[i] = ES.submit(temp);
        }
        for (int i = 0; i < futures.length; i++) {
            try {
                Integer result = futures[i].get();
                printLine(String.format("CT%d", i), String.format("Finished calculating %d", result));
            } catch (Exception e){
                System.out.printf("[Driver][%s]\n", e.toString());
            }
        }

        ES.shutdown();
        printFooter();
    }

    static void printLine(String id, String msg){
        System.out.printf("║ %-5s ║ %-32s ║\n", id, msg);
    }
    private static void printHeader(String s){
        System.out.printf("╔{%5s}╦══════════════════════════════════╗\n", s);
    }
    private static void printFooter(){
        System.out.println("╚═══════╩══════════════════════════════════╝");
    }

}
