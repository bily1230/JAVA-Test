package thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by ning on 2017/4/26.
 */

public class SimpleDaemons implements Runnable {
    @Override
    public void run() {
        try{
            TimeUnit.MILLISECONDS.sleep(100);
            System.out.println(Thread.currentThread()+" " +this);
        }catch(InterruptedException e){
            System.out.println("sleep interrupted");
        }
    }
    public static void main(String[] args){
        for(int i=0;i<10;i++){
            Thread daemon = new Thread(new SimpleDaemons());
            daemon.setDaemon(true);
            daemon.start();
        }
        System.out.println("all daemon started");
        try {
            TimeUnit.MILLISECONDS.sleep(175);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
