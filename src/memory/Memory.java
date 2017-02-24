package memory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Memory {

   
	/**
	 * 传地址 ,编程中多注意
	 * @param args
	 */
	
	
    
	public static void main(String[] args) {
	
		Map<String , List<String>> map = new HashMap<String, List<String>>();
	    
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		list.add("3");
		map.put("1", list);
		
		List<String> listTest = map.get("1");
		
		listTest.add("4");
		listTest.add("5");
		
		System.out.println(list.size());
		System.out.println(map.get("1").size());
		
		
	}

}
