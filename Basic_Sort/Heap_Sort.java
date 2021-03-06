
import java.util.Comparator;

public class Heap_Sort<Key extends Comparable<Key>> {

	
	private static boolean less(Comparable[] data ,int i, int j) {
		return data[i].compareTo(data[j]) < 0;
	}

	private static void exchange(Comparable[] data,int i, int j) {
		Key temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}

	
	private static void sink(Comparable[] data, int root,int end) {
		while ((2 * root + 1) <= end) {
			int child = 2 * root +1;// the left child 
			if (child +1<= end && less(child, j + 1)) {
				child++; // find the bigeer child
			}
			if (!less(root, child)) {
				break;
			}
			exchange(root, child);
			root = child;
		}
	}

	
	public static void heap_Sort(Comparable[] data){

		int length = data.length;
		int last_patrent = (data.length-2)/2; 

		for(int k = last_patrent ; k >=0 ; k--){
                  sink(data,k,length-1);// step 1 : heapify 
		}
         
                int end = length -1;
		while(end > 0){
			exchange(data,0, end);// step 2: exchange the root node(max value) with the last leaf node ,the biggest leaf save to the data[end] position.
			sink(data,0,end -1);//step 3: heapify the rest nodes (every time find the max value from the rest nodes {M top K problem})
		    end--;		
		}
	}
	
	@Deprecated
	public static void print(Comparable[] data) {
		for (int i = 1; i<= N; i++) {
			System.out.print(data[i].toString()+" ");
		}
	}
}
