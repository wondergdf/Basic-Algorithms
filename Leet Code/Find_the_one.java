
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class Find_the_one {

	/***
	 * Given the array that contains integer members, these numbers are all odd
	 * or even except one that different with the rest. find the different one.
	 * 
	 * example: { 2, 4, 6, 1, 8, 10 } should return 1.
	 */
	public  static int find_different_one(int[] target_array) {

		int oddNum = 0;
		int evenNum = 0;

		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

		for (int i = 0; i < target_array.length; i++) {
			if (target_array[i] % 2 == 0) {
				evenNum++;
				map.put(0, target_array[i]);
			} else {
				oddNum++;
				map.put(1, target_array[i]);
			}

			// optimizing: if the target_array is too big, once we get the
			// one,we break loop to save time.
			if (map.size() > 1) {
				break;
			}
		}

		if (evenNum == 1) {
			return map.get(0);
		} else if (oddNum == 1) {
			return map.get(1);
		}

		return 0;
	}

	/**
	 * Given the string to find the first repeat character
	 * 
	 * example: "stress" should return 's'
	 * 
	 */
	public static Character find_first_repeat_char(String target_string) {
		Character result = null;

		char[] target_array = target_string.toCharArray();

		HashSet<Character> set = new HashSet<Character>();

		for (int i = 0; i < target_array.length; i++) {
			if (set.contains(target_array[i])) {
				return target_array[i];
			} else {
				set.add(target_array[i]);
			}
		}
		return result;

	}

	/**
	 * 
	 * Given the string to find out the first non-repeat character
	 * 
	 * example : "teeter" should return 'r' "stress" should return 't'
	 */
	public static Character find_first_non_repeat_char(String target_string) {

		char[] target_array = target_string.toCharArray();

		//use the LinkedHashMap which will keep the inserted order.
		LinkedHashMap<Character, Integer> map = new LinkedHashMap<Character, Integer>();

		for (int i = 0; i < target_array.length; i++) {
			if (map.containsKey(target_array[i])) {
				map.put(target_array[i], map.get(target_array[i]++));
			} else {
				map.put(target_array[i], 1);
			}
		}

		Iterator<Character> keys = map.keySet().iterator();
		while (keys.hasNext()) {
			Character key = keys.next();
			if (map.get(key).equals(1)) {
				return key;
			}
		}

		return null;
	}

	public static void main(String[] args) {

		int[] test_case1 = { 2, 4, 6, 1, 8, 10 };
		int[] test_case2 = { 1, 4, 6, 2, 8, 10 };
		int[] test_case3 = { 2, 1, 12, 4, 8, 10 };
		int[] test_case4 = { 3, 1, 2, 1, 7, 5 };

		System.out.println(Find_the_one.find_different_one(test_case1));
		System.out.println(Find_the_one.find_different_one(test_case2));
		System.out.println(Find_the_one.find_different_one(test_case3));
		System.out.println(Find_the_one.find_different_one(test_case4));

		System.out.println(Find_the_one.find_first_repeat_char("stress"));
		System.out.println(Find_the_one.find_first_non_repeat_char("teetergk"));

	}

}
