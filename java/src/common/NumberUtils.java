package common;

public class NumberUtils {
	public static int atoi(String numberString) {
		if (numberString.length() == 0) {
			throw new java.lang.NumberFormatException(
					"The number string is empty.");
		}

		boolean isNegative = false;
		int endIndex = 0;
		if (numberString.charAt(0) == '-') {
			isNegative = true;
			endIndex = 1;
		} else if (numberString.charAt(0) == '+') {
			endIndex = 1;
		} else if (!Character.isDigit(numberString.charAt(0))) {
			throw new NumberFormatException("The string is malformed.");
		}

		if (endIndex == 1 && numberString.length() == 1) {
			throw new NumberFormatException("The string is malformed.");
		}

		int num = 0;
		int counter = 1;
		for (int index = numberString.length() - 1; index >= endIndex; index--) {
			if (!Character.isDigit(numberString.charAt(index))) {
				throw new NumberFormatException("The string is malformed");
			}

			num += (numberString.charAt(index) - '0') * counter;
			counter *= 10;
		}

		return isNegative ? num * -1 : num;
	}
}
