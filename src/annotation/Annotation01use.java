package annotation;

import java.lang.reflect.Method;

/**
 * Created by ning on 2017/3/6.
 */
public class Annotation01use {

    @Annotation01(id = 47 ,description = "Password must contain numeric")
    public String validatePassword(String password){
        return new StringBuilder(password).reverse().toString();

    }
    public static void main(String[] args){
        for(Method m : Annotation01use.class.getDeclaredMethods()){
            Annotation01 uc = m.getAnnotation(Annotation01.class);
            if(uc!=null){
                System.out.print(uc.id()+uc.description());
            }

           // System.out.println("000");

        }
    }
}
