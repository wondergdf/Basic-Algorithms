package ClassicAlgorithm;

/**
 * Given a sorted array, remove the duplicates in place such that each element
 * appear only once and return the new length.
 * 
 * Do not allocate extra space for another array, you must do this in place with
 * constant memory.
 * 
 * For example, Given input array nums = [1,1,2],
 * 
 * Your function should return length = 2, with the first two elements of nums
 * being 1 and 2 respectively.
 * 
 * It doesn't matter what you leave beyond the new length.
 * 
 * 
 */


public class Remove_Duplicate {

	private static void remove_preious(Comparable[] array, int start,
			int previous_num) {
		// step 1: data move to cover duplicate
		for (int i = start; i < array.length; i++) {
			array[i - previous_num] = array[i];
		}

		// step 2: set null to empty member
		for (int i = array.length - 1; i > array.length - 1 - previous_num; i--) {
			array[i] = null;
		}

	}

	public static int remove_duplicate_from_SortedArray(Comparable[] array) {

		// step 1: handle special case
		if (array == null) {
			return -1;
		}
		if (array.length == 0 || array.length == 1) {
			return array.length;
		}

		int length = array.length;
		int duplicate_num = 0;// record duplicate member's repeat number
		Comparable previous = array[0];

		for (int i = 1; i < array.length; i++) {

			if (array[i] != null && (previous.compareTo(array[i]) == 0)) {
				duplicate_num++;
			} else {
				if (duplicate_num != 0) {
					remove_preious(array, i - 1, duplicate_num);
					i = i - duplicate_num; // update i after remove duplicate member form array
					length = length - duplicate_num;// update actual length of array
					duplicate_num = 0;// reset counter
				}
			}
			 
			previous = array[i];
		}

		return length;
	}
	
	
	public static int remove_special_element(Comparable[] array,Comparable special){
		
		if(array == null || special == null) return -1;
		
		//step 1: remove the special element
		int length = array.length;
		
		for(int i=0,j=0; i<array.length; i++){
			if(array[i].compareTo(special)==0){
				length--;
				continue;
				
			}else{
				array[j]= array[i];
				j++;
			}
		}
		
		//step2 : reset the rest element to null( optional)
		for(int i= length ; i< array.length;i++){
               array[i]=null;			
		}
		
		return length;		
	}
	
	@Deprecated
	public static void print_array(Comparable[] data){
		for (int i = 0; i < data.length; i++) {
			System.out.println(data[i]);
		}		
	}

	public static void main(String[] args) {
		
		System.out.println("--------test:remove_duplicate_from_SortedArray----------------");

		Comparable[] data = { 1, 1, 1, 2, 2, 3, 4, 4, 5, 5 };

		int len = remove_duplicate_from_SortedArray(data);

		System.out.println("Original array length "+ data.length +" after removed deplicated members, the Length :"
				+ len);

		print_array(data);
		
		System.out.println("--------test:remove_special_element----------------");
		
		Comparable[] data2 = { 1, 2, 1, 3, 4, 1, 5, 1 };

		int len2 = remove_special_element(data2,data2[0]);

		System.out.println("Original array length "+ data2.length +" after removed deplicated members, the Length :"
				+ len2);

		print_array(data2);
	}

}
