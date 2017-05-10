package thread;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ning on 2017/5/9.
 */
public class Reentrant {
    private Lock lock = new ReentrantLock();
    public Reentrant(){
        lock.lock();
    }
    public void f(){
        try{
            lock.lockInterruptibly();
        }catch(InterruptedException e){
            System.out.println("Interrupted from lock in f()");
        }
    }
}
class Block implements Runnable{
    Reentrant reentrant = new Reentrant();
    @Override
    public void run() {
        System.out.println("wating for f()");
        reentrant.f();
        System.out.println("block calla");
    }
}
class Interrupt{
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<?> future = executorService.submit(new Block());
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Thread .interrupt");
        future.cancel(true);

    }
}
