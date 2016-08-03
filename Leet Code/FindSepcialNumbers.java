package ClassicAlgorithm;

import java.util.Arrays;

public class FindSepcialNumbers {

	/***
	 * This function will find three numbers from the given integer array which
	 * its sum is zero.
	 * 
	 * @param data
	 * @version 1.0
	 * 
	 */
	public static void Find(int[] data) {

		ThreeWaysPartition_QuickSort.sort(data);

		for (int i = 0; i < data.length; i++) {
			for (int j = i + 1; j < data.length; j++) {
				int target = 0 - data[i] - data[j];
				if (target > 0 && Arrays.binarySearch(data, target) >= 0) {
					System.out.println("(" + data[i] + "," + data[j] + ","
							+ target + ")");
				} else {
					continue;
				}
			}
		}

	}

	public static void main(String[] args) {
		int[] data = { -1, 0, 1, 2, -1, 4 };
		FindSepcialNumbers.Find(data);
		
	}
}
