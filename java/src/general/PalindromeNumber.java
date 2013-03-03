package general;

/**
 * Find if a given integer is a palindrome.
 */
public class PalindromeNumber {
	public static boolean isPalindrome(int number) {
		if (number < 0) {
			number *= -1;
		}

		int temp = number;
		int leftDigitFinder = 1;
		while (temp >= 10) {
			leftDigitFinder *= 10;
			temp /= 10;
		}

		while (leftDigitFinder >= 10) {
			int left = (number / leftDigitFinder) % 10;
			int right = number % 10;
			if (left != right) {
				return false;
			}

			leftDigitFinder /= 100;
			number /= 10;
		}

		return true;
	}
}
