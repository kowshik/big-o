package common;

public class StringUtils {
	private static int getLength(int number) {
		if (number == 0) {
			return 1;
		}

		int length = 0;
		if (number < 0) {
			number *= -1;
			length++;
		}

		while (number > 0) {
			number /= 10;
			length++;
		}

		return length;
	}

	public static String itoa(int number) {
		String digits = "0123456789";
		int asciiLength = getLength(number);
		char[] ascii = new char[asciiLength];
		int endIndex = 0;
		if (number < 0) {
			ascii[0] = '-';
			endIndex = 1;
			number *= -1;
		}

		for (int index = ascii.length - 1; index >= endIndex; index--) {
			ascii[index] = digits.charAt(number % 10);
			number /= 10;
		}

		return new String(ascii);
	}
}
