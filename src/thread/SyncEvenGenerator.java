package thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by ning on 2017/5/1.
 */
public class SyncEvenGenerator extends SyncInitGenerator{
    private int currentEvenValue = 0;
    private Lock lock = new ReentrantLock();
    @Override
    public int next() {
        lock.lock();
        try{
            ++currentEvenValue;
            Thread.yield();
            // ++currentEvenValue;
            return currentEvenValue;
        }finally {
            lock.unlock();
        }

    }
    public static void main(String[] args){
        SyncEvenChecker.test(new SyncEvenGenerator());
    }
}
