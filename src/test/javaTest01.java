package test;

import java.util.ArrayList;
import java.util.List;

public class javaTest01 extends Thread{
	
	
	
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(2, 2);
		list.add(1, 4);
		for(int a :list){
			System.out.println(a);
		}

	}

}
