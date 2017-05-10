package TestMain;

/**
 * Created by ning on 2017/4/17.
 */
public class Son extends Person{
    public Son(int age,String name){
        super(age,name);
    }
    public String getNameAge(){
        return getName() + "----" +getAge();
    }
}
