package thread;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ning on 2017/3/21.
 */
public class ExeTest {

    public static void main(String[] args){

       ExecutorService exec = Executors.newSingleThreadExecutor();

        for(int i= 0; i<5;i++) {
            exec.execute(new Thread01());
        }
        exec.shutdown();
       //new Thread(new Thread01()).start();
    }
}
