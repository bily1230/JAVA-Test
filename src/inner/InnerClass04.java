package inner;

/**
 * Created by ning on 2017/1/3.
 */
public class InnerClass04 {
   // 匿名内部类使用外部定义对象时，对参数引用是fianl。
    public Food food(final String  color){
        return new  Food(){
            private String foodcolor = color;
            @Override
            public String value() {
                return foodcolor;
            }
        };
    }

    public static void main(String[] args) {
       InnerClass04 inner = new InnerClass04();
       Food food = inner.food("PPP");
       System.out.println(food.value());
    }
}
