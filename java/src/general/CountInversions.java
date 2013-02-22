git apackage general;

/**
 * Count number of inversions in an array of integers. An inversion is a pair
 * (i,j) such that i < j and array[i] > array[j].
 */
public class CountInversions {
	public static long countInversions(int[] array) {
		return countInversions(array, 0, array.length - 1,
				new int[array.length], -1);
	}

	private static long countInversions(int[] array, int start, int end,
			int[] merged, int indent) {
		if (start == end) {
			merged[0] = array[start];
			return 0L;
		}

		int middle = (start + end) / 2;

		int[] left = new int[middle - start + 1];
		long leftInversions = countInversions(array, start, middle, left,
				indent);
		int[] right = new int[end - middle];
		long rightInversions = countInversions(array, middle + 1, end, right,
				indent);

		return leftInversions + rightInversions
				+ mergeAndCountInversions(left, right, merged);
	}

	private static long mergeAndCountInversions(int[] foo, int[] bar,
			int[] merged) {
		if (foo == null || bar == null || merged == null) {
			throw new NullPointerException(
					"One or more of foo/bar/merged is null.");
		}

		long numInversions = 0L;
		int fooIndex = 0, barIndex = 0;
		int sortedIndex = 0;
		while (fooIndex < foo.length && barIndex < bar.length) {
			if (foo[fooIndex] <= bar[barIndex]) {
				merged[sortedIndex] = foo[fooIndex];
				fooIndex++;
			} else {
				numInversions += foo.length - fooIndex;
				merged[sortedIndex] = bar[barIndex];
				barIndex++;
			}

			sortedIndex++;
		}

		while (fooIndex < foo.length) {
			merged[sortedIndex] = foo[fooIndex];
			fooIndex++;
			sortedIndex++;
		}

		while (barIndex < bar.length) {
			merged[sortedIndex] = bar[barIndex];
			barIndex++;
			sortedIndex++;
		}

		return numInversions;
	}
}
