package arrays;

/**
 * Write a class that given an array of N integers with values in the range 0 to
 * K, pre-processes its input and then answers any query about how many of the N
 * integers fall into a range [A,B] in O(1) time (note: the range includes A and
 * B). Your algorithm can use no more than O(N + K) pre-preocessing time.
 * 
 * Your class: Answer0toKRangeQuery should implement a constructor with the
 * following signature. The constructor should take as input the array to be
 * processed, and the value K which determines the range 0 to K.
 * 
 * Answer0toKRangeQuery(int[] array, int k)
 * 
 * Your class should also implement the method:
 * 
 * int countNumIntegers(int rangeStart, int rangeEnd)
 * 
 * The above method answers the required queries in O(1) time.
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
