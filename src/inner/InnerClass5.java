package inner;

/**
 * Created by ning on 2017/2/22.
 */
public class InnerClass5 {
    public void value(){
        System.out.println("信息公开！");
    }
    public Food getFood(){
        return new Food(){
            public String value(){
                InnerClass5.this.value();
                return "33";
            }
        };
    }

    public static void main(String[] args){
        InnerClass5 inner = new InnerClass5();
        Food food = inner.getFood();
       System.out.print( food.value());

    }
}
