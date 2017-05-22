package thread;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by ning on 2017/5/13.
 */
public class Toast {
    public enum Status{DRY,BUTTERED,JAMMED}
    private Status status = Status.DRY;
    private final int id;
    public Toast(int id){this.id = id;}
    public void butter(){status = Status.BUTTERED;}
    public void jam(){status = Status.JAMMED;}
    public Status getStatus(){return status;}

    public int getId() {
        return id;
    }
    public String toString(){
        return "Toast"+id+":"+status;
    }
}

class ToastQueue extends LinkedBlockingQueue<Toast>{}

class Toaster implements Runnable{
    private ToastQueue toastQueue;
    private int count = 0;
    private Random random = new Random(23);
    public Toaster(ToastQueue toastQueue1){this.toastQueue = toastQueue1;}
    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                TimeUnit.MILLISECONDS.sleep(100+random.nextInt(500));
                Toast toast = new Toast(count++);
                System.out.println(toast);
                toastQueue.put(toast);
            }
        } catch (InterruptedException e) {
            System.out.println("Toaster interrupt");
        }
        System.out.println("Toaster off");
    }
}

class Butterer implements Runnable{
    private ToastQueue dryQueue;
    private ToastQueue butterredQueue;
    public Butterer(ToastQueue dryQueue,ToastQueue butterredQueue){
        this.dryQueue = dryQueue;
        this.butterredQueue = butterredQueue;
    }
    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                Toast t = dryQueue.take();
                t.butter();
                System.out.println(t);
                butterredQueue.put(t);
            }
        }catch (InterruptedException e){
            System.out.println("Butterer interrupted");
        }
        System.out.println("Butterer off");
    }
}
class Jammer implements Runnable{
    private ToastQueue butteredQueue;
    private ToastQueue finishedQueue;
    public Jammer(ToastQueue butteredQueue,ToastQueue finishedQueue){
        this.butteredQueue = butteredQueue;
        this.finishedQueue = finishedQueue;
    }
    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                Toast t = butteredQueue.take();
                t.jam();
                System.out.println(t);
                finishedQueue.put(t);
            }
        }catch (InterruptedException e){
            System.out.println("Jammer interrupted");
        }
        System.out.println("Jammer off");
    }
}

class Eater implements Runnable{
    private ToastQueue finishedQueue;
    private int counter = 0;
    public Eater(ToastQueue finishedQueue){
        this.finishedQueue = finishedQueue;
    }
    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                Toast t = finishedQueue.take();
                if(t.getId()!=counter++||t.getStatus()!=Toast.Status.JAMMED){
                    System.out.println(">>>Error:"+t);
                    System.exit(1);
                }else{
                    System.out.println("Chomp!"+t);
                }
            }
        }catch (InterruptedException e){
            System.out.println("Eater interrupted");
        }
        System.out.println("Eater off");
    }
}

class ToasterMatic{
    public static void main(String[] args) throws InterruptedException {
        ToastQueue dryQueue = new ToastQueue();
        ToastQueue butterQueue = new ToastQueue();
        ToastQueue finishQueue = new ToastQueue();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Toaster(dryQueue));
        executorService.execute(new Butterer(dryQueue,butterQueue));
        executorService.execute(new Jammer(butterQueue,finishQueue));
        executorService.execute(new Eater(finishQueue));
        TimeUnit.SECONDS.sleep(5);
        executorService.shutdownNow();
    }
}