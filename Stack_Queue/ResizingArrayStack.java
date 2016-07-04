package ClassicAlgorithm;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayStack<Item> implements Iterable<Item> {

	private Item[] a =(Item[]) new Object[1];
	private int size = 0;
  
	
	public boolean isEmpty(){return size == 0;}
	public int getSize() { return size;	}
	
// for debug 
	public void  printStackInfo(){
		System.out.println("Current Stack size  : "+ size );
		System.out.println("Current Space Size : "+ a.length);
	}
	
	private void  resize (int max){
		Item[] temp = (Item[]) new Object[max];
		for(int i=0;i<size;i++){
			temp[i] = a[i];
		}
		a = temp;
	}
	
	public void push(Item item){
		System.out.println("Current size before push Operation: "+ size );

		if(size == a.length){ resize(2*a.length);}
		a[size++] = item;
		
		System.out.println("Current size after push Operation: "+ size );
	}
	
	public Item pop() throws ArrayIndexOutOfBoundsException{
		if(size < 1){
			throw new ArrayIndexOutOfBoundsException();
		}
		Item item = a[--size];
		a[size] = null;//避免对象游离，可使Java虚拟机垃圾回收
		if(size > 0 && (a.length /4 )== size ){ resize( a.length/2);}
		return item;
	}
	
	public  Iterator<Item> iterator(){
		 return new ReverseArrayIterator();
	}
	
	private class ReverseArrayIterator implements Iterator<Item>{
		private int i = size;
		public boolean hasNext(){
		   return i> 0;
		}
		public Item next() throws NoSuchElementException {
			if(i<1){
				throw new NoSuchElementException();
			}
			return a[--i];
		}
		public void remove(){
			
		}
	}
	
	public static void main(String[] args){
		
		ResizingArrayStack<String> stack = new ResizingArrayStack<String>();
		
		stack.push("They");
		
		stack.push("Just ");
		
		stack.push("want ");
		stack.printStackInfo();
		
		stack.push("win ");
		
		stack.printStackInfo();
		
		for(String data : stack){
			System.out.println(data);
		}
		
		System.out.println("invoke  pop is : " + stack.pop());
		System.out.println("invoke  pop is : " + stack.pop());
		
		System.out.println("invoke  pop is : " + stack.pop());
		stack.printStackInfo();
		System.out.println("invoke  pop is : " + stack.pop());
		stack.printStackInfo();
		
	}
	
}
