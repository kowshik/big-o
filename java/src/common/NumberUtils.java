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
}
