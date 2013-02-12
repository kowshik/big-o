package general;

import java.util.HashMap;
import java.util.Map;

public class RomanNumerals {
	public static long romanToDecimal(String romanNumber) {
		if (romanNumber == null || romanNumber.isEmpty()) {
			throw new IllegalArgumentException(
					"Null or empty roman number passed.");
		}

		final Map<Character, Integer> map = new HashMap<Character, Integer>();
		map.put('I', 1);
		map.put('V', 5);
		map.put('X', 10);
		map.put('L', 50);
		map.put('C', 100);
		map.put('D', 500);
		map.put('M', 1000);

		long decimal = 0L;
		for (int index = romanNumber.length() - 1; index >= 0;) {
			char romanDecimal = romanNumber.charAt(index);

			if (index > 0) {
				char previousRomanDecimal = romanNumber.charAt(index - 1);
				if (map.get(romanDecimal) > map.get(previousRomanDecimal)) {
					decimal += map.get(romanDecimal)
							- map.get(previousRomanDecimal);
					index -= 2;
					continue;
				}
			}

			decimal += map.get(romanDecimal);
			index--;
		}

		return decimal;
	}

	public static String decimalToRoman(int number) {
		if (number < 0) {
			throw new IllegalArgumentException(String.format(
					"Number should be >= 0. You passed: %d", number));
		}

		StringBuffer roman = new StringBuffer();
		String[] numerals = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X",
				"IX", "V", "IV", "I" };
		int[] values = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };

		int index = 0;
		int size = numerals.length;
		while (number > 0 && index < size) {

			if (values[index] <= number) {
				roman.append(numerals[index]);
				number = number - values[index];

			} else {
				index++;
			}
		}

		return roman.toString();
	}

	public static void main(String[] args) {
		System.out.println(decimalToRoman(999));
	}
}
