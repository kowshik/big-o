package sorting;

import java.util.Comparator;

import common.Pair;

/**
 * Find the minimum length unsorted subarray, which when sorted makes the array
 * completely sorted.
 */
public class FindMinLengthUnsortedSubArray {

	public static Pair<Integer, Integer> findMinLengthUnsortedSubArray(
			int[] array, Comparator<Integer> comparator) {
		if (array == null || array.length == 1) {
			return null;
		}

		// Find first index where sorting order is disturbed.
		int startIndex;
		for (startIndex = 1; startIndex < array.length; startIndex++) {
			if (comparator.compare(array[startIndex], array[startIndex - 1]) < 0) {
				break;
			}
		}

		if (startIndex == array.length) {
			// Array is already sorted.
			return null;
		}

		int endIndex;
		for (endIndex = array.length - 1; endIndex > startIndex; endIndex--) {
			if (comparator.compare(array[endIndex], array[endIndex - 1]) < 0) {
				break;
			}
		}

		if (endIndex == -1) {
			// This should never happen, but being defensive.
			throw new AssertionError();
		}

		// Now, between start and end indices, find the min and max elements.
		int min = array[startIndex];
		int max = array[startIndex];

		for (int index = startIndex + 1; index < endIndex; index++) {
			if (array[index] < min) {
				min = array[index];
			}

			if (array[index] > max) {
				max = array[index];
			}
		}

		// In the input array, from locations 0 to startIndex - 1, find the
		// first element that is bigger than min;
		for (int index = 0; index < startIndex; index++) {
			if (comparator.compare(array[index], min) > 0) {
				startIndex = index;
				break;
			}
		}

		// In the input array, from locations array.length - 1 to endIndex + 1,
		// find the
		// first element that is smaller than max;
		for (int index = array.length - 1; index > endIndex; index--) {
			if (comparator.compare(array[index], max) < 0) {
				endIndex = index;
				break;
			}
		}

		return new Pair<Integer, Integer>(startIndex, endIndex);
	}

	public static void main(String[] args) {
		int[] array = { 10, 12, 20, 30, 25, 40, 32, 31, 35, 50, 60 };
		Pair<Integer, Integer> solution = findMinLengthUnsortedSubArray(array,
				new Comparator<Integer>() {
					@Override
					public int compare(Integer a, Integer b) {
						return a - b;
					}
				});

		System.out.println(solution);

		array = new int[] { 1, 2, 3, 4, 5 };
		solution = findMinLengthUnsortedSubArray(array,
				new Comparator<Integer>() {
					@Override
					public int compare(Integer a, Integer b) {
						return a - b;
					}
				});

		System.out.println(solution);

		array = new int[] { 5, 4, 3, 2, 1 };
		solution = findMinLengthUnsortedSubArray(array,
				new Comparator<Integer>() {
					@Override
					public int compare(Integer a, Integer b) {
						return a - b;
					}
				});

		System.out.println(solution);

		array = new int[] { 5, 4, 2, 3, 1 };
		solution = findMinLengthUnsortedSubArray(array,
				new Comparator<Integer>() {
					@Override
					public int compare(Integer a, Integer b) {
						return a - b;
					}
				});

		System.out.println(solution);

		array = new int[] { 1, 2, 4, 3, 5 };
		solution = findMinLengthUnsortedSubArray(array,
				new Comparator<Integer>() {
					@Override
					public int compare(Integer a, Integer b) {
						return a - b;
					}
				});

		System.out.println(solution);
	}
}
