package general;

import java.util.ArrayList;

/**
 * Given a number, find its prime factorization.
 */
public class PrimeFactors {
	public static ArrayList<Integer> getPrimeFactors(int number)
			throws InterruptedException {
		ArrayList<Integer> primeFactors = new ArrayList<Integer>();

		for (int divisor = 2; divisor <= number; divisor++) {
			// The trick here is that the divisor that divides the number below
			// will always be a prime number.
			while (number % divisor == 0) {
				primeFactors.add(divisor);
				number /= divisor;
			}
		}

		return primeFactors;
	}
}
