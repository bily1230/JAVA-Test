package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ning on 2017/5/1.
 */
public class SyncEvenChecker implements Runnable{
    private SyncInitGenerator generator;
    private final int id;

    public SyncEvenChecker(SyncInitGenerator syncInitGenerator,int ident) {
        id = ident;
        generator = syncInitGenerator;
    }
    @Override
    public void run() {
        while(!generator.isCancaled()){
            int val = generator.next();
            if(val%2!=0){
                System.out.println(val+"not even!");
                generator.cancel();
            }
        }
        System.out.println(id+"awayls----!");
        generator.setCancaled(Boolean.FALSE);
    }
    public static void test(SyncInitGenerator syncInitGenerator,int count){
        System.out.println("Press Contrl-C to exit");
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i = 0;i< count;i++){
            executorService.execute(new SyncEvenChecker(syncInitGenerator,i));
        }
        executorService.shutdown();
    }
    public static void test(SyncInitGenerator syncInitGenerator){
        test(syncInitGenerator , 10);
    }
}
