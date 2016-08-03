package ClassicAlgorithm;

/**
 * Reverse digits of an integer.
 * 
 * Example1: x = 123, return 321 Example2: x = -123, return -321
 * 
 * Have you thought about this?
 * 
 * Here are some good questions to ask before coding. Bonus points for you if
 * you have already thought through this!
 * 
 * If the integer's last digit is 0, what should the output be? ie, cases such
 * as 10, 100.
 * 
 * @author Lucifer
 * 
 */
public class Reverse_Integer {

	private static String eliminate_zreo(String data) {
		char[] buffer = data.toCharArray();
		int index = 0;

		while (index < buffer.length && buffer[index] == '0') {
			index++;
		}
		return data.substring(index);
	}

	public static String reverse_int(Integer value) {
		String sign = (value > 0) ? "" : "-";

		StringBuilder result = new StringBuilder();

		value = Math.abs(value);

		result.append(value.toString());

		return sign + eliminate_zreo(result.reverse().toString());

	}

}
