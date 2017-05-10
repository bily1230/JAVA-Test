package thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by ning on 2017/3/21.
 */
public class Thread01 implements Runnable{

   protected int countDown = 10;
   private static int taskCount = 0;
   private final int id = taskCount++;
   public Thread01(){};
   public Thread01(int countDown){
       this.countDown = countDown;
   }

   public String status(){
       return "#" + id + "(" +(countDown>0?countDown:"Lifeoff!")+"),";

   }

    @Override
    public void run() {
       try {
           while (countDown-- > 0) {
               System.out.print(status());
               //  Thread.yield();
               TimeUnit.MILLISECONDS.sleep(1000);

           }
       }catch(Exception e){
           System.out.println("In");
       }
    }
}
