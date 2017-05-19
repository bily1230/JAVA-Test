package fun;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Fun01 {

	

	public static void main(String[] args) {
		
		List features = Arrays.asList("Lambdas", "Default Method", "Stream API",
				 "Date and Time API");
		//features.forEach(n -> System.out.println(n));
		//features.forEach(System.out::println);
		
		String[] newWay = "Improving code with Lambda expressions in Java 8".split(" ");
	/*		Arrays.sort(newWay, (s1, s2) -> {
		    return s1.toLowerCase().compareTo(s2.toLowerCase());
		});
		System.out.println(String.join(", ", newWay));*/
		
		Arrays.sort(newWay,(s1,s2) ->{ return s1.toLowerCase().compareTo(s2.toLowerCase());});
		List list = Arrays.asList(newWay);
		list.forEach(n -> System.out.println(n));
		
		//System.out.println(String.join(", ", newWay));
	}

}
