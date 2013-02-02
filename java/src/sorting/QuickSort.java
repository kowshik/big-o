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

		int length = endIndex - startIndex + 1;
		int randomPivotIndex = new Random().nextInt(length) + startIndex;
		T randomPivot = array[randomPivotIndex];
		int actualPivotIndex = partition(array, startIndex, endIndex,
				randomPivot, comparator);
		array = quickSort(array, startIndex, actualPivotIndex - 1, comparator);
		array = quickSort(array, actualPivotIndex + 1, endIndex, comparator);
		return array;
	}

	public static <T> int partition(T[] array, int startIndex, int endIndex,
			T pivot, Comparator<T> comparator) {
		int nextIndex = startIndex;
		int nextPivotIndex = nextIndex - 1;

		while (nextIndex <= endIndex) {
                    if (comparator.compare(array[nextIndex], pivot) <= 0) {
                        nextPivotIndex++;
                        common.ArrayUtils.swap(array, nextPivotIndex, nextIndex);
                    }
                    nextIndex++;
		}

		return nextPivotIndex;
	}
}

