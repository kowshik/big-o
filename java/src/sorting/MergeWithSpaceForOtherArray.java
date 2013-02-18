package sorting;

import java.util.Comparator;

/**
 * Merge two sorted arrays A of length M and B of length N into the same array
 * A. Assume that A has enough space in the end to accomodate all elements of B.
 * i.e. A's size is M + N.
 */
public class MergeWithSpaceForOtherArray {
	public static <T> void merge(T[] bigArray, int bigArraySize,
			T[] smallArray, Comparator<T> comparator) {
		if (bigArray == null || smallArray == null) {
			throw new NullPointerException("You are a stupid programmer.");
		}

		int bigArrayIndex = bigArraySize - 1;
		int smallArrayIndex = smallArray.length - 1;
		int sortedIndex = bigArray.length - 1;
		while (smallArrayIndex >= 0 && bigArrayIndex >= 0) {
			if (comparator.compare(smallArray[smallArrayIndex],
					bigArray[bigArrayIndex]) < 0) {
				bigArray[sortedIndex--] = bigArray[bigArrayIndex--];
			} else {
				bigArray[sortedIndex--] = smallArray[smallArrayIndex--];
			}
		}

		while (smallArrayIndex >= 0) {
			bigArray[sortedIndex--] = smallArray[smallArrayIndex--];
		}
	}
}
