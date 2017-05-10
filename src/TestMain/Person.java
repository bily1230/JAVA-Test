package TestMain;

/**
 * Created by ning on 2017/4/10.
 */
public class Person implements Comparable<Person>{
    public Person(int age,String name){
        this.age = age;
        this.name = name;
    }
    private int age;
    private String name;

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int compareTo(Person o) {
       int result = 0;
       result = this.age - o.age;
       if(result==1){
           result = this.name.compareTo(o.name);
       }
       return result;
    }
}
