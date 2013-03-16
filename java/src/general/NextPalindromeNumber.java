package general;

public class NextPalindromeNumber {
	public static String nextPalindromeNumber(String num) {
		char[] number = num.toCharArray();
		boolean palindrome = true;
		boolean valid = true;
		int start, end;
		for (start = 0, end = number.length - 1; start < end; start++, end--) {
			int digitStart = number[start] - '0';
			int digitEnd = number[end] - '0';
			if (digitStart != digitEnd) {
				palindrome = false;
			}
			if (digitStart < digitEnd) {
				valid = false;
				break;
			}
		}

		if (palindrome || !valid) {
			number = incrementCenterDigit(number);
		}
		mirrorLeftToRight(number);

		return new String(number);
	}

	private static void mirrorLeftToRight(char[] number) {
		int start, end;
		for (start = 0, end = number.length - 1; start < end; start++, end--) {
			number[end] = number[start];
		}
	}

	private static char[] incrementCenterDigit(char[] num) {
		int middle;
		if (num.length % 2 == 0) {
			middle = num.length / 2 - 1;
		} else {
			middle = num.length / 2;
		}

		int index = middle;
		if (num[middle] == '9') {
			while (index >= 0 && num[index] == '9') {
				num[index] = '0';
				index--;
			}

			if (index == -1) {
				index = 0;
				char[] newArray = new char[num.length + 1];
				newArray[0] = '0';
				System.arraycopy(num, 0, newArray, 1, num.length);
				num = newArray;
			}
		}

		num[index]++;
		return num;
	}

	public static void main(String[] args) {
		String number = "0";
		System.out.printf("%s => %s\n", number, nextPalindromeNumber(number));

		number = "9";
		System.out.printf("%s => %s\n", number, nextPalindromeNumber(number));

		number = "899";
		System.out.printf("%s => %s\n", number, nextPalindromeNumber(number));

		number = "999";
		System.out.printf("%s => %s\n", number, nextPalindromeNumber(number));

		number = "8971";
		System.out.printf("%s => %s\n", number, nextPalindromeNumber(number));
	}
}
