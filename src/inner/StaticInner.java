package inner;

/**
 * Created by ning on 2017/2/23.
 */
public class StaticInner {
    public static class Inner{
        public void show(){
            System.out.println("221112");
        }
    }

    public static void main(String[] args){
        StaticInner.Inner inner = new StaticInner.Inner();
        inner.show();
    }
}
