package general;

/**
 * Find sum of values in the sub array that has the maximal sum.
 */
public class MaximalSum {
	public static long findMaximalSum(int[] array) {
		long currentSum = 0L;
		long maxSum = 0L;
		int biggestNonPositive = Integer.MIN_VALUE;
		boolean seenPositive = false;

		for (int number : array) {
			currentSum += number;
			if (number > 0) {
				seenPositive = true;
			}

			if (currentSum > maxSum) {
				maxSum = currentSum;
			} else {
				currentSum = 0L;
				if (!seenPositive) {
					if (number > biggestNonPositive) {
						biggestNonPositive = number;
					}
				}
			}
		}

		if (!seenPositive) {
			return biggestNonPositive;
		}

		return maxSum;
	}
}
