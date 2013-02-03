package sorting;

import java.util.Comparator;
import java.util.Random;

public class QuickSort {
	public static <T> T[] quickSort(T[] array, Comparator<T> comparator) {
		return quickSort(array, 0, array.length - 1, comparator);
	}

	private static <T> T[] quickSort(T[] array, int startIndex, int endIndex,
			Comparator<T> comparator) {
		if (startIndex >= endIndex) {
			return array;
		}

		int pivotIndex = partition(array, startIndex, endIndex, comparator, -1);
		array = quickSort(array, startIndex, pivotIndex - 1, comparator);
		array = quickSort(array, pivotIndex + 1, endIndex, comparator);
		return array;
	}

	private static int getRandomPivotIndex(int startIndex, int endIndex) {
		int length = endIndex - startIndex + 1;
		return new Random().nextInt(length) + startIndex;
	}

	public static <T> int partition(T[] array, int startIndex, int endIndex,
			Comparator<T> comparator, int pivotIndex) {
		if (pivotIndex == -1) {
			pivotIndex = getRandomPivotIndex(startIndex, endIndex);
		}

		common.ArrayUtils.swap(array, pivotIndex, endIndex);
		int swapIndex = startIndex - 1;
		for (int index = startIndex; index <= endIndex - 1; index++) {
			if (comparator.compare(array[index], array[endIndex]) <= 0) {
				swapIndex++;
				common.ArrayUtils.swap(array, swapIndex, index);
			}
		}

		common.ArrayUtils.swap(array, swapIndex + 1, endIndex);
		return swapIndex + 1;
	}
}
