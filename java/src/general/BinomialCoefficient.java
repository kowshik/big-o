package general;

/**
 * Find n C r.
 * n C r = n! / r! x (n - r)!
 */
public class BinomialCoefficient {
	// n C r = n! / r! x (n - r)!
	public static long findNumCombinations(int n, int r) {
		if (n <= 0 || r < 0) {
			throw new IllegalArgumentException(
					String.format(
							"Values for n should be > 0 and r should be >= 0. You passed n: %d and r: %d.",
							n, r));
		}

		if (r > n - r) {
			r = n - r;
		}

		long result = 1;
		for (int i = 0; i < r; i++) {
			result *= (n - i);
			result /= (i + 1);
		}

		return result;
	}
}
