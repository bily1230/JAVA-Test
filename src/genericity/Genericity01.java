package genericity;

/**
 * 泛型学习
 * 
 *<p>泛型类</p>
 * @author nb
 *
 * @param <T>
 */
public class Genericity01<T> {
	
	
	private static class Node<U>{
		
		U item;
		Node<U> next;
		Node(){item = null; next = null;}
		Node(U item ,Node<U> next){
			this.item = item ;
			this.next = next ;
		}
		boolean end(){return item==null && next== null;}
	}
	private Node<T> top = new Node<T>();
	
	public void push(T item){
		top = new Node<T>(item , top);
	}
	
	public T pop(){
		T result = top.item;
		if(!top.end()){
			top = top.next;
		}
		return result;
	}

	public static void main(String[] args) {
		Genericity01<String> gen = new Genericity01<String>();
		for(String s1 : "for every day with you".split(" ")){
			gen.push(s1);
		}
		
		String str ;
		while((str=gen.pop())!=null){
			System.out.println(str);
		}

	}

}
