package inner;

/**
 * Created by ning on 2017/1/3.
 */
public class InnerClass {
    private   class InnerFood implements Food{
        public String value(){
            return "好吃！";
        }
    }
    public InnerFood inner() {
            return new InnerFood();
    }


    public static void main(String[] args) {
       InnerClass innerClass = new InnerClass();


    }
}

