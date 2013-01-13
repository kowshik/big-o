package sorting;

import java.util.Comparator;
import java.lang.Math;
import common.ArrayUtils;

public class HeapSort {
    public static <T> T[] heapSort(T[] array, Comparator<T> comparator) {
	array = heapify(array, comparator);

	for (int length = array.length; length > 1; length--) {
	    common.ArrayUtils.swap(array, 0, length - 1);
	    array = heapify(array, length - 1, 0, comparator);
	}

	return array;
    }

    private static <T> T[] heapify(T[] array, Comparator<T> comparator) {
	for(int rootIndex = parentIndex(array.length - 1); rootIndex >= 0; rootIndex--) {
	    array = heapify(array, rootIndex, comparator);
	}

	return array;
    }

    private static <T> T[] heapify(T[] array, int rootIndex, Comparator<T> comparator) {
	return heapify(array, array.length, rootIndex, comparator);
    }

    private static <T> T[] heapify(T[] array, int length, int rootIndex, Comparator<T> comparator) {
	int indexToBeSwapped = rootIndex;

	if (leftChildIndex(rootIndex) < length) {
	    if (comparator.compare(array[leftChildIndex(rootIndex)], array[indexToBeSwapped]) > 0) {
		indexToBeSwapped = leftChildIndex(rootIndex);
	    }
	}

	if (rightChildIndex(rootIndex) < length) {
	    if (comparator.compare(array[rightChildIndex(rootIndex)], array[indexToBeSwapped]) > 0) {
		indexToBeSwapped = rightChildIndex(rootIndex);
	    }
	}

	if (indexToBeSwapped != rootIndex) {
	    common.ArrayUtils.swap(array, rootIndex, indexToBeSwapped);
	    array = heapify(array, length, indexToBeSwapped, comparator);
	}

	return array;
    }

    private static int leftChildIndex(int index) {
	return 2 * index + 1;
    }

    private static int rightChildIndex(int index) {
	return 2 * index + 2;
    }

    private static int parentIndex(int index) {
	return new Double(Math.floor((index - 1) / 2)).intValue();
    }

    public static void main(String[] args) {
	Integer[] array = {3, 1, 4, 8, 2, 7, 5, 6};
	Comparator<Integer> maxHeapComparator = new Comparator<Integer>() {
	    @Override
	    public int compare(Integer a, Integer b) {
		return a - b;
	    }
	};

	array = heapSort(array, maxHeapComparator);
    }
}
