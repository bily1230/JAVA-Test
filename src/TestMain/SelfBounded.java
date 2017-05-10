package TestMain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ning on 2017/4/19.
 */
public class SelfBounded<T extends SelfBounded<T>> {
   T element;
    T get(){
        return element;
    }
    public void set(T arg){
        element = arg;
    }
}
class A extends SelfBounded<A>{}
class B extends SelfBounded<A>{}

class D{
    public static void main(String[] args){

        List<Son> sonList = new ArrayList<Son>();
        sonList.add(new Son(33,"33"));
        List<? extends Person> list = sonList;

        Person son = list.get(0);
        System.out.print(son.getAge());


    }
}
//报错
//class F extends SelfBounded<D>{}


//强制要求正在定义的类当作参数传递给基类


