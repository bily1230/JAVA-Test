package genericity;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型学习
 * 
 * <p>泛型方法</P>
 * @author nb
 *
 */
public class Genericity02 {
	public static <T> List<T> asArrayList(T... args){
		List<T> list = new ArrayList<T>();
		for(T str :args){
			list.add(str);
		}
		return list;
		
	}

	public static void main(String[] args) {
		
		List<String> list = asArrayList("for","every" ,"body");
		
		System.out.println(list.toString());

	}

}
