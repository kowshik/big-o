package sorting;

import java.util.Comparator;

public class InsertionSort {
	public static <T> T[] insertionSort(T[] array, Comparator<T> comparator) {
		for (int outer = 1; outer < array.length; outer++) {
			T item = array[outer];
			int inner;
			for (inner = outer - 1; inner >= 0
					&& comparator.compare(array[inner], item) > 0; inner--) {
				array[inner + 1] = array[inner];
			}

			array[inner + 1] = item;
		}

		return array;
	}
}
