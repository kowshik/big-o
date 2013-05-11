package arrays;

/**
 * Write a program that given n integers in the range 0 to k, pre-processes its
 * input and then answers any query about how many of the n integers fall into a
 * range [a,b] in O(1) time (note: the range includes a and b).
 * Your algorithm can use no more than O(n + k) pre-preocessing time.
 */
public class Answer0toKRangeQuery {
	private final int[] counter;
	private final int k;

	public Answer0toKRangeQuery(int[] array, int k) {
		if (k < 0) {
			throw new IllegalArgumentException(String.format(
					"Value of 'K' can't be negative. You passed: %d:", k));
		}

		this.k = k;
		counter = new int[k + 1];
		for (int number : array) {
			counter[number]++;
		}

		for (int i = 1; i <= k; i++) {
			counter[i] += counter[i - 1];
		}
	}

	public int countNumIntegers(int rangeStart, int rangeEnd) {
		if (rangeStart < 0) {
			throw new IllegalArgumentException(String.format(
					"rangeStart can't be negative. You passed: %d", rangeStart));
		}

		if (rangeEnd > k) {
			throw new IllegalArgumentException(String.format(
					"rangeStart can't be negative. You passed: %d", rangeEnd));
		}

		return counter[rangeEnd]
				- (rangeStart == 0 ? 0 : counter[rangeStart - 1]);
	}
}
