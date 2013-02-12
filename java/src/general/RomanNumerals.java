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
		int thousands = number / 1000;
		while (thousands > 0) {
			roman.append("M");
			thousands -= 1;
			number -= 1000;
		}

		char[] numerals = { 'I', 'V', 'X', 'L', 'C', 'D', 'M' };
		int[] values = { 1, 5, 10, 50, 100, 500, 1000 };

		int low = 4;
		while (low >= 0) {
			int quotient = number / values[low];
			if (quotient > 0) {
				if (quotient < 4) {
					append(roman, numerals[low], quotient);
				} else if (quotient == 4) {
					roman.append(numerals[low]);
					roman.append(numerals[low + 1]);
				} else if (quotient < 9) {
					roman.append(numerals[low + 1]);
					append(roman, numerals[low], quotient - 5);
				} else {
					roman.append(numerals[low]);
					roman.append(numerals[low + 2]);
				}
			}

			number -= quotient * values[low];
			low -= 2;
		}

		return roman.toString();
	}

	private static void append(StringBuffer buffer, char numeral, int times) {
		while (times > 0) {
			buffer.append(numeral);
			times--;
		}
	}
}
