package ClassicAlgorithm;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;



public class Linked_List<Item extends Comparable<Item>> implements Iterable<Item> {

	private Node head;
	private Node current;
	private int size = 0;

	private  static class Node<Item extends Comparable<Item>> {
		Item item;
		Node next;

		public Node(Item item, Node next) {
			super();
			this.item = item;
			this.next = next;
		}

		public Node(Item item) {
			super();
			this.item = item;
		}

	}

	public boolean push(Item item) {
		if (item == null)
			return false;
		Node newNode = new Node(item);
		if (head == null) {
			current = newNode;
			head = current;
		} else {
			current.next = newNode;
			current = newNode;
		}
		size++;
		return true;
	}

	public int getSize() {
		return size;
	}

	public boolean reverse_by_stack() {
		if (check_NULL_single())
			return false;

		Node current = head;
		Stack<Node> stores = new Stack<Node>();

		// step 1: put all the nodes into stack
		while (current != null) {
			stores.push(current);
			current = current.next;
		}

		// step 2: pop the node & reconstruct list
		head = stores.pop();
		current = head;
		while (!stores.isEmpty()) {
			Node temp = stores.pop();
			current.next = temp;
			current = temp;
		}

		// Reset the original head node's next not point to
		// the original second
		current.next = null;

		return true;
	}

	public boolean reverse_by_reinsert() {
		if (check_NULL_single())
			return false;

		// step 1 : keep the head node and break the link to the rest nodes
		Node previous = head;
		Node current = previous.next;

		previous.next = null;

		// step 2: scan the rest nodes and reinsert before the head node
		while (current != null) {
			Node next = current.next;

			current.next = previous;
			previous = current;
			current = next;
		}

		head = previous;

		return true;
	}
	
	public boolean reverse_previous_k(int k){
		if (check_NULL_single())
			return false;
		if(k <= 1 || k> this.size){
			return false;
		}
		if(k == this.size){
			reverse_by_reinsert();
		}else {
			
			int i = 2; // K >=2 , initial loop 
		    Node	pre_head = head;
		    Node previous = head;
		    Node current = previous.next;
		    
		    while (i <= k) {
		    	Node nextNode = current.next;
		    	current.next = previous;
		    	previous = current;
		    	current = nextNode;
		    	i++;
			}
		    
		    pre_head.next = current;
		    head = previous;
		    
		}
		return true;
		
	}
	public boolean reverse_special_range(int start, int end){
		if (check_NULL_single())
			return false;
		
		if(start< 1|| start>= end || end> this.size){
			return false;
		}
		if(start == 1 && end < size){
			reverse_previous_k(end);
		}else if(start == 1 && end == size){
			reverse_by_reinsert();
		}else{
			
			//Step 1: find the previous node before start node.			
			Node pre_start  = head;
			for(int i =2;i<start;i++){
				pre_start = pre_start.next;
			}
			
			//Step2: backup the start node to restore the next node.
			Node backup_start = pre_start.next;
			
			//step3: reverse the nodes in given range
			int i = start+1;
			
			 Node previous = backup_start;
			 Node current = previous.next;
			    
		    while (i <= end) {
		    	Node nextNode = current.next;
		    	current.next = previous;
		    	previous = current;
		    	current = nextNode; 
		    	i++;
			}
		    
		    //step4 : update the previous & next node
		    pre_start.next = previous;
		    
		    backup_start.next = current;  
			
		}
		
		return true;
	}

	public Node get_Reciprocal_K(int K) {
		if (size == 0 || size < K || K <= 0)
			return null;

		if (size == K) {
			return head;
		} else {
			Node fastPoint = head;
			Node slowPoint = head;

			while (K > 0) {
				fastPoint = fastPoint.next;
				K--;
			}

			while (fastPoint != null) {
				fastPoint = fastPoint.next;
				slowPoint = slowPoint.next;
			}
			return slowPoint;
		}

	}
	
	public boolean check_Circle(){
		
		boolean result = false;
		
		Node  fastPoint = head;
		Node  slowPoint = head;
		while(fastPoint.next != null && fastPoint.next.next != null){
			fastPoint = fastPoint.next.next;
			slowPoint = slowPoint.next;
			if(fastPoint == slowPoint){
				return true;
			}
		}	
		
		return result;		
	}
	
	public void sort_byArray(){
		
		Object[] arrayOfObject = this.toArray();		
		Arrays.sort(arrayOfObject);
		
		LinkedList_Iterator localListIterator = (LinkedList_Iterator) this.iterator();
	    for (int i = 0; i < arrayOfObject.length; ++i)
	    {
	      localListIterator.next();
	      localListIterator.set(arrayOfObject[i]);
	    }
	}
	
	
	public boolean exchange(int positionA, int positionB){
		if(check_NULL_single()){
			return false;
		}
		if(positionA<1 || positionB<1 || positionA> size || positionB>size || positionA == positionB){
			return false;
		}
		
	     // Search for A (keep track of prevA and CurrA)
		Node preA = null, curA = head;
		int indexA =1 ;
		while(curA!= null && indexA != positionA){
			preA = curA;
			curA = curA.next;
			indexA++;
		}
		
	    // Search for B (keep track of prevB and CurrB)
		Node preB = null, curB = head;
		int indexB =1 ;
		while(curB!= null && indexB != positionB){
			preB = curB;
			curB = curB.next;
			indexB++;
		}
		
		// If A is not head of linked list
		if(preA != null){
			preA.next = curB;
		}else {//make B the new head
			head = curB;
		}
		
		if(preB != null){
			preB.next = curA;
		}else{
			head = curA;
		}
		
		 // Swap next pointers
        Node temp = curA.next;
        curA.next = curB.next;
        curB.next = temp;
				
		return true;
	}
	
	public Object[] toArray(){	
		
		Object[] arrayOfObject = new Object[this.size];
		int i = 0;
		for (Node localNode = this.head; localNode != null; localNode = localNode.next)
			arrayOfObject[(i++)] =  localNode.item;
		
		return  arrayOfObject;
	}

	@Override
	public Iterator iterator() {
		return new LinkedList_Iterator();
	}

	private class LinkedList_Iterator implements Iterator<Node>{

		 int N =0;
		 Node current = head;
		 Node previous = head;
		@Override
		public boolean hasNext() {
			
			if(N <= size){
				return true;
			}else{
				return false;
			}
		}

		@Override
		public Node next() {
			if(N> size){
				throw new NoSuchElementException();
			}
		   Node result = current;
		   previous = current;
		   current = current.next;
		   N++;
			return result;
		}
        
		@Override
		public void remove() {
               throw new UnsupportedOperationException();
		}
        
		public void set(Object obj){
		  previous.item = (Comparable) obj;
		}
	}
	
	public boolean check_NULL_single(){
		if (head == null || head.next == null)
			return true;
		return false;
	}
	
	public void sort(){
		head = sort(head);
	}
	
	private Node sort(Node list){
		
		if(list == null || list.next == null) return list;
		
		Node fastPoint = list.next;
		Node slowPoint = list;
		 
	    while(fastPoint.next!= null && fastPoint.next.next != null){
	    		slowPoint = slowPoint.next;
	    		fastPoint = fastPoint.next.next;
	    }
	    
	    Node head2 = sort(slowPoint.next);
	    slowPoint.next = null;
	    Node head1 = sort(list);
	    
	    return merge(head1, head2);
		
	}
	private Node merge(Node linklist_head1, Node linklist_head2){
		
		Node head = null ;
		
		/**
		 * first , get the smaller one from linklist_head1 & linklist_head2,
		 * assign for the merged list head node as return head_node. 
		 */		
		if(linklist_head1.item.compareTo(linklist_head2.item) < 0){
			head = linklist_head1;
			linklist_head1 = linklist_head1.next;
		}else {
			head = linklist_head2;
			linklist_head2 = linklist_head2.next;
		}
		
		Node list = head;
		while(linklist_head1!= null && linklist_head2 != null){
			if(linklist_head1.item.compareTo(linklist_head2.item) < 0){
				list.next = linklist_head1;
				linklist_head1 = linklist_head1.next;
				
			}else {
				list.next = linklist_head2;
				linklist_head2 = linklist_head2.next;
			}
			
			list = list.next;
		}
		
		if(linklist_head1 == null){
			list.next = linklist_head2;
		}else if(linklist_head2 == null){
			list.next = linklist_head1;
		}
		return head;
	}

	/***
	 * Debugging
	 */
	@Deprecated
	public void print() {
		System.out.println("------------------------");

		Node firstNode = head;
		while (firstNode != null) {
			System.out.print("-" + firstNode.item + "-");
			firstNode = firstNode.next;
		}

		System.out.println();
	}
	
	

	public static void main(String[] args) {
		Linked_List<Integer> testLinked_List = new Linked_List<Integer>();

		testLinked_List.push(4);
		testLinked_List.push(3);
		testLinked_List.push(2);
		testLinked_List.push(1);
		

		testLinked_List.print();

		System.out.println("Reverse by stack:");
		testLinked_List.reverse_by_stack();
		testLinked_List.print();

		System.out.println("Reverse by reinsert:");
		testLinked_List.reverse_by_reinsert();
		testLinked_List.print();
		
		System.out.println("Reverse the previous K: 3" );
		testLinked_List.reverse_previous_k(3);
		testLinked_List.print();
		
		testLinked_List.reverse_previous_k(3);
		testLinked_List.print();
		
		System.out.println("Reverse the range [2,4]" );
		testLinked_List.reverse_special_range(2, 4);
		testLinked_List.print();

		int K = 3;
		System.out.println("Get the reciprocal K: K= " + K);
		System.out.println(testLinked_List.get_Reciprocal_K(K).item);
		
		System.out.println("Sort the Linked_list :" );
		testLinked_List.sort_byArray();
		testLinked_List.print();
		
		System.out.println("Exchange the Linked_list :[1,3]" );
		testLinked_List.exchange(1, 3);
		testLinked_List.print();
		
		testLinked_List.get_Reciprocal_K(1).next =  testLinked_List.get_Reciprocal_K(4) ;
		System.out.println("Check the circle : " + testLinked_List.check_Circle());
	} 
	
}
