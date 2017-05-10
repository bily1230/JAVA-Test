package genericity;

import java.util.ArrayList;

/**
 * 泛型学习
 * 
 *<p>泛型类</p>
 * @author nb
 *
 *
 */
public class Genericity03 {
	
	
	public <T > void getClassName(T t){
		System.out.println(t.getClass().getName());
	}


	public static void main(String[] args) {
		Class c1 = new ArrayList<String>().getClass();
		Class c2 = new ArrayList<Integer>().getClass();
		System.out.print(c1==c2);

	}

}
