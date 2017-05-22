package thread.concurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by ning on 2017/5/22.
 */
public class CountDownLatchDemo {

    static final int SIZE = 100;
    public static void main(String[] args){
        ExecutorService service = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(SIZE);
        for(int i = 0;i<10;i++){
            service.execute(new WaitingTask(latch));
        }
        for(int i = 0;i<90;i++){
            service.execute(new TaskPortion(latch));
        }
        System.out.println("latch all task");
        service.shutdown();
    }
}
class TaskPortion implements Runnable{
    private static int counter = 0;
    private final int id = counter++;
    private static Random rand = new Random(47);
    private final CountDownLatch latch;

    TaskPortion(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try{
            doWork();
            latch.countDown();
        }catch (InterruptedException e){

        }
    }

    private void doWork() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(2000);
        System.out.println(this + "completed");
    }
    public String toString(){
        return Thread.currentThread().getName()+"----"+id;
    }
}
class WaitingTask implements Runnable{
    private static int counter = 0;
    private final int id = counter++;
    private CountDownLatch latch;

    WaitingTask(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            latch.countDown();
            latch.await();
            System.out.println("Latch passed for" + this);
        }catch (InterruptedException e){
            System.out.println(this + "interrupted");
        }
    }
    public String toString(){
        return Thread.currentThread().getName()+"----"+id;
    }
}

