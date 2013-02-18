package sorting;

import java.lang.reflect.Array;
import java.util.Comparator;

public class MergeSort {

	public static <T> T[] mergeSort(Class<T> type, T[] array,
			Comparator<T> comparator) {
		if (array.length == 0) {
			@SuppressWarnings("unchecked")
			T[] toReturn = (T[]) Array.newInstance(type, 0);
			return toReturn;
		}

		T[] sorted = mergeSort(type, array, 0, array.length - 1, comparator);
		for (int index = 0; index < array.length; index++) {
			array[index] = sorted[index];
		}

		return sorted;
	}

	private static <T> T[] mergeSort(Class<T> type, T[] array, int start,
			int end, Comparator<T> comparator) {
		if (start == end) {
			@SuppressWarnings("unchecked")
			T[] toReturn = (T[]) Array.newInstance(type, 1);
			toReturn[0] = array[start];

			return toReturn;
		}

		T[] left = mergeSort(type, array, start, (start + end) / 2, comparator);
		T[] right = mergeSort(type, array, (start + end) / 2 + 1, end,
				comparator);

		return merge(type, left, right, comparator);
	}

	public static <T> T[] merge(Class<T> type, T[] foo, T[] bar,
			Comparator<T> comparator) {
		@SuppressWarnings("unchecked")
		T[] merged = (T[]) Array.newInstance(type, foo.length + bar.length);

		int fooIndex = 0, barIndex = 0, mergedIndex = 0;
		while (fooIndex < foo.length && barIndex < bar.length) {
			if (comparator.compare(foo[fooIndex], bar[barIndex]) < 0) {
				merged[mergedIndex] = foo[fooIndex];
				fooIndex++;
			} else {
				merged[mergedIndex] = bar[barIndex];
				barIndex++;
			}

			mergedIndex++;
		}

		while (fooIndex < foo.length) {
			merged[mergedIndex++] = foo[fooIndex++];
		}

		while (barIndex < bar.length) {
			merged[mergedIndex++] = bar[barIndex++];
		}

		return merged;
	}
}
