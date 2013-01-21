package sorting;

import java.util.Comparator;

public class MergeSort {
	public static int[] mergeSort(int[] array, Comparator<Integer> comparator) {
		if (array.length == 0) {
			return new int[] {};
		}

		int[] sorted = mergeSort(array, 0, array.length - 1, comparator);
		for (int index = 0; index < array.length; index++) {
			array[index] = sorted[index];
		}

		return sorted;
	}

	private static int[] mergeSort(int[] array, int start, int end,
			Comparator<Integer> comparator) {
		if (start == end) {
			return new int[] { array[start] };
		}

		int[] left = mergeSort(array, start, (start + end) / 2, comparator);
		int[] right = mergeSort(array, (start + end) / 2 + 1, end, comparator);
		int[] sorted = merge(left, right, comparator);

		return sorted;
	}

	public static int[] merge(int[] foo, int[] bar,
			Comparator<Integer> comparator) {
		int[] merged = new int[foo.length + bar.length];

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
