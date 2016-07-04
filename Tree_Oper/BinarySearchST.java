package ClassicAlgorithm;

public class BinarySearchST<Key extends Comparable<Key>, Value> {

	private Key[] keys;
	private Value[] values;
	private int N;

	public BinarySearchST(int capacity) {
		keys = (Key[]) new Comparable[capacity];
		values = (Value[]) new Object[capacity];
	}

	private void resize(int max){
		Key[]  tempKeys = (Key[]) new Comparable[max];
		Value[] tempValues = (Value[]) new Object[max];
		
		for(int i =0; i< N; i++){
			tempKeys[i] = keys[i];
			tempValues[i] = values[i];
		}
		
		keys = tempKeys;
		values = tempValues;	
	}
	
	public int size() {
		return N;
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public int search_binary(Key key){
		int low = 0;
		int hight = N-1;
		
		while(low<= hight){
			int mid = low + (hight-low)/2;
			int cmp = keys[mid].compareTo(key);
			
			if(cmp < 0){
				low = mid+1;
			}else if(cmp > 0){
				hight = mid-1;
			}else{
				return mid;
			}
		}
		return low;
	}

	public Value get(Key key) {
		if (isEmpty())
			return null;
		int i = search_binary(key);
		if (i < N && keys[i].compareTo(key) == 0) {
			return values[i];
		} else {
			return null;
		}
	}
	
	public void push(Key key, Value value){
		int i = search_binary(key);
		
		//if found , update the key's value
		if(i < N  && keys[i].compareTo(key)==0){
			values[i] = value;
			return ;
		}
		
		if(keys.length == N){
			resize(2*keys.length);
		}
		
		//if not found, insert the key&value
		for(int j = N; j>i ; j--){
			keys[j]= keys[j-1];
			values[j] = values[j-1];
		}
		
		keys[i] = key;
		values[i] = value;
		N++;
		
	}

}
