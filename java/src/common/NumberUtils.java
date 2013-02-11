package common;

public class NumberUtils {
	public static int atoi(String numberString) {
		if (numberString.length() == 0) {
			throw new java.lang.NumberFormatException(
					"The number string is empty.");
		}

		boolean isNegative = false;
		int startIndex = 0;
		if (numberString.charAt(0) == '-') {
			isNegative = true;
			startIndex = 1;
		} else if (numberString.charAt(0) == '+') {
			startIndex = 1;
		}

		if (startIndex == 1 && numberString.length() == 1) {
			throw new NumberFormatException("The string is malformed.");
		}

		int num = 0;
		for (int index = startIndex; index < numberString.length(); index++) {
			if (!Character.isDigit(numberString.charAt(index))) {
				throw new NumberFormatException("The string is malformed");
			}

			num = num * 10 + (numberString.charAt(index) - '0');
		}

		return isNegative ? num * -1 : num;
	}

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

	public static int length(int number) {
		if (number == 0) {
			return 1;
		}

		int length;
		for (length = 0; number != 0; number /= 10, length++)
			;

		return length;
	}

	public static int reverse(int x) {
		int given = x;
		int reversed = 0;

		while (x % 10 == 0 && x != 0) {
			x /= 10;
		}

		while (x != 0) {
			reversed = x % 10 + reversed * 10;
			x /= 10;
		}

		return given < 0 ? reversed * -1 : reversed;
	}

	public static double pow(int x, int y) {
		if (y < 0) {
			return 1.0F / pow(x + 0L, (y * -1) + 0L);
		}

		return pow(x + 0L, y + 0L);
	}

	private static long pow(long x, long y) {
		if (y < 0) {
			throw new IllegalArgumentException(String.format(
					" y can't be negative. you passed: %d", y));
		}

		if (y == 0) {
			if (x == 0) {
				return 0;
			}

			return 1;
		}

		if (y == 1) {
			return x;
		}

		long pow = pow(x, y / 2);
		if (y % 2 == 0) {
			return pow * pow;
		}

		return pow * pow * x;
	}
}
