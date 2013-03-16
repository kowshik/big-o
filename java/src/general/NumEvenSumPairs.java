package general;

/**
 * Write a function that, given an array A consisting of N integers, returns the
 * number of pairs (P, Q) such that 0 ≤ P < Q < N and (A[P] + A[Q]) is even.
 * 
 * The function should return −1 if the number of such pairs exceeds
 * 1,000,000,000.
 * 
 * For example, given array A such that: A[0] = 2, A[1] = 1, A[2] = 5, A[3] =
 * −6, A[4] = 9.
 * 
 * The function should return 4, because there are four pairs that fulfill the
 * above condition, namely (0,3), (1,2), (1,4), (2,4). Assume that: N is an
 * integer within the range [0..1,000,000]; each element of array A is an
 * integer within the range [−2,147,483,648..2,147,483,647].
 */
public class NumEvenSumPairs {
	public static int numEvenSumPairs(int[] numbers) {
		int evenCount = 0;
		int oddCount = 0;

		for (int number : numbers) {
			if ((number & 1) == 0) {
				evenCount++;
			}
		}

		oddCount = numbers.length - evenCount;
		long temp = (evenCount * (evenCount - 1) / 2)
				+ (oddCount * (oddCount - 1) / 2);

		if (temp >= 1000000000) {
			return -1;
		}

		return (int) temp;
	}
}
