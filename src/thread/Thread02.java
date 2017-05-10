package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static jdk.nashorn.internal.objects.Global.print;

/**
 * Created by ning on 2017/3/21.
 */
public class Thread02 implements Runnable{

    public static void main(String[] args){
        for(int i =0 ;i<10;i++) {
            Thread thread = new Thread(new Thread02());
            thread.setDaemon(true);
            thread.start();
        }
        System.out.println("all statted");
    }

    @Override
    public void run() {
        try{
            TimeUnit.MILLISECONDS.sleep(1000);
            System.out.println(Thread.currentThread()+"99 " + this);

        }catch(InterruptedException e){

        }
    }
}
