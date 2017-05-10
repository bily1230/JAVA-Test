package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by ning on 2017/5/10.
 */
public class ProductMeal {
    private final int orderNum;
    public ProductMeal(int orderNum){
        this.orderNum = orderNum;
    }
    public String toString(){
        return "Meal"  + orderNum;
    }
}

class CleanPerson implements  Runnable{
    private ProductMeal meal;
    Restaurant restaurant;
    CleanPerson(Restaurant restaurant){
        this.restaurant = restaurant;
    }
    @Override
    public void run() {
        try{
            while(!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.meal == null) {
                        wait();
                    }
                }
                System.out.println("CleanPerson to" + restaurant.meal);
                synchronized (restaurant.chef) {
                    restaurant.meal = null;
                    restaurant.chef.notifyAll();
                }
            }
        }catch (InterruptedException e){
            System.out.println("CleanPerson interrupt");
        }
    }
}

class WaitPerson implements Runnable{
    private Restaurant restaurant;
    public WaitPerson(Restaurant restaurant){
        this.restaurant = restaurant;
    }
    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                synchronized (this){
                    while (restaurant.meal ==null)
                        wait();
                }
                System.out.println("WaitPerson got"+restaurant.meal);
                synchronized (restaurant.cleanPerson){
                    restaurant.cleanPerson.notifyAll();

                }
            }

        }catch (InterruptedException e){
            System.out.println("WaitPerson interrupted");
        }
    }
}
class Chef implements Runnable{
    private  Restaurant restaurant;
    private int count = 0;
    Chef(Restaurant restaurant){
        this.restaurant = restaurant;
    }
    @Override
    public void run() {
        try{
            while(!Thread.interrupted()){
                synchronized (this){
                    while(restaurant.meal!=null)
                        wait();
                }
                if(++count == 10){
                    System.out.println("Out of food ,closing");
                    restaurant.executorService.shutdownNow();
                }
                System.out.println("Order up!");
                synchronized (restaurant.waitPerson){
                    restaurant.meal = new ProductMeal(count);
                    restaurant.waitPerson.notifyAll();
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        }catch(InterruptedException e){
            System.out.println("chef interrupt");
        }
    }
}
class Restaurant{
    ProductMeal meal;
    ExecutorService executorService = Executors.newCachedThreadPool();
    CleanPerson cleanPerson = new CleanPerson(this);
    WaitPerson waitPerson = new WaitPerson(this);
    Chef chef = new Chef(this);
    public Restaurant(){
        executorService.execute(chef);
        executorService.execute(waitPerson);
        executorService.execute(cleanPerson);
    }
    public static void main(String[] args){
        new Restaurant();
    }
}