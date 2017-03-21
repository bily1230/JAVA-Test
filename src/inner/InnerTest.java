package inner;

/**
 * Created by ning on 2017/2/22.
 */
public class InnerTest {
    public static void main(String[] args){
        InnerClass innerClass = new InnerClass();
        Food inner = innerClass.inner();
        System.out.println(inner.value());
    }
}
