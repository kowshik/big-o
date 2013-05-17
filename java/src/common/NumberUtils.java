package common;

public class NumberUtils {

	/**
	 * Converts a string to an integer.
	 * 
	 * @throws NumberFormatException
	 *             if the string is not a valid integer.
	 * 
	 * @return the integer value
	 */
	public static int atoi(String s) {
		if (s == null) {
			throw new NullPointerException("You passed a null string.");
		}

		if (s.length() == 0) {
			throw new NumberFormatException("The number string is empty.");
		}

		boolean isNegative = false;
		int startIndex = 0;
		if (s.charAt(0) == '+' || s.charAt(0) == '-') {
			startIndex = 1;
			if (s.charAt(0) == '-') {
				isNegative = true;
			}

			if (s.length() == 1) {
				throw new NumberFormatException(String.format(
						"The input string: %s is not a valid integer.", s));
			}
		}

		long num = 0L;
		for (int index = startIndex; index < s.length(); index++) {
			char c = s.charAt(index);
			if (c < '0' || c > '9') {
				throw new NumberFormatException(String.format(
						"The input string: %s is not a valid integer.", s));
			}

			num = num * 10 + (c - '0');
			if (num > Integer.MAX_VALUE
					&& !(isNegative && num == Integer.MAX_VALUE + 1)) {
				throw new NumberFormatException(
						String.format(
								"The input string: %s is not a valid integer as it overflows the integer range.",
								s));
			}
		}

		int intNum = (int) num;
		return isNegative ? intNum * -1 : intNum;
	}

	/**
	 * Converts an integer to a string.
	 * 
	 * @return the string representation of the integer passed.
	 */
	public static String itoa(int number) {
		int length = length(number);
		if (number < 0) {
			length++;
		}

		char[] digits = new char[length];
		if (number == 0) {
			digits[0] = '0';
		}
		if (number < 0) {
			digits[0] = '-';
			number *= -1;
		}

		for (int index = length - 1; number != 0; index--, number /= 10) {
			digits[index] = (char) ('0' + number % 10);
		}

		return new String(digits);
	}

	/**
	 * Returns the length of an integer, ignoring its sign.
	 */
	public static int length(int number) {
		if (number == 0) {
			return 1;
		}

		int length;
		for (length = 0; number != 0; number /= 10, length++)
			;

		return length;
	}

	/**
	 * Reverses the digits of an integer.
	 */
	public static int reverse(int x) {
		int reversed = 0;

		while (x % 10 == 0 && x != 0) {
			x /= 10;
		}

		while (x != 0) {
			reversed *= 10;
			reversed += x % 10;
			x /= 10;
		}

		return reversed;
	}

	/**
	 * Returns true if the string is a valid integer, and false otherwise.
	 */
	public static boolean isValidInteger(String str) {
		if (str == null || str.length() == 0) {
			return false;
		}

		int index = 0;
		if (str.charAt(0) == '+' || str.charAt(0) == '-') {
			index++;
		}

		for (; index < str.length(); index++) {
			if (str.charAt(index) < '0' || str.charAt(index) > '9') {
				return false;
			}
		}

		return true;
	}
}
