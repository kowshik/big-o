package general;

/**
 * Find number of zeros in the factorial of a number.
 */
public class NumberOfZerosInFactorial {
	public static int numFactorialZeros(int number) {
		if (number < 0) {
			throw new IllegalArgumentException(String.format(
					"Number should be >= 0. You passed: %d.", number));
		}

		int numZeros = 0;
		long divider = 5;
		long quotient;
		while ((quotient = number / divider) > 0) {
			numZeros += quotient;
			divider *= 5;
		}

		return numZeros;
	}
}
