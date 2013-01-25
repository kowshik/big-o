package sorting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.RandomAccess;

import exceptions.EmptyHeapException;

public class SimpleHeapImpl<T> implements Heap<T> {

	private final List<T> heap;
	private final Comparator<T> comparator;

	public SimpleHeapImpl(Comparator<T> comparator) {
		this.comparator = comparator;
		this.heap = new ArrayList<T>();
	}

	public SimpleHeapImpl(List<T> list, Comparator<T> comparator) {
		if (!(list instanceof RandomAccess)) {
			throw new IllegalArgumentException(
					"The list passed has to support random access.");
		}

		this.comparator = comparator;
		this.heap = list;
		heapify();
	}

	public SimpleHeapImpl(T[] array, Comparator<T> comparator) {
		this(new ArrayList<T>(Arrays.asList(array)), comparator);
	}

	@Override
	public int size() {
		return heap.size();
	}

	@Override
	public T peek() throws EmptyHeapException {
		if (heap.isEmpty()) {
			throw new EmptyHeapException();
		}

		return heap.get(0);
	}

	@Override
	public T pop() throws EmptyHeapException {
		if (heap.isEmpty()) {
			throw new EmptyHeapException();
		}

		common.ListUtils.swap(heap, 0, heap.size() - 1);
		T toReturn = heap.remove(heap.size() - 1);
		heapify(0);

		return toReturn;
	}

	@Override
	public void add(T element) {
		heap.add(element);
		heapify();
	}

	private void heapify() {
		for (int rootIndex = parentIndex(heap.size() - 1); rootIndex >= 0; rootIndex--) {
			heapify(rootIndex);
		}
	}

	private void heapify(int rootIndex) {
		heapify(heap.size(), rootIndex);
	}

	private void heapify(int length, int rootIndex) {
		int indexToBeSwapped = rootIndex;

		if (leftChildIndex(rootIndex) < length) {
			if (comparator.compare(heap.get(leftChildIndex(rootIndex)),
					heap.get(indexToBeSwapped)) > 0) {
				indexToBeSwapped = leftChildIndex(rootIndex);
			}
		}

		if (rightChildIndex(rootIndex) < length) {
			if (comparator.compare(heap.get(rightChildIndex(rootIndex)),
					heap.get(indexToBeSwapped)) > 0) {
				indexToBeSwapped = rightChildIndex(rootIndex);
			}
		}

		if (indexToBeSwapped != rootIndex) {
			common.ListUtils.swap(heap, rootIndex, indexToBeSwapped);
			heapify(length, indexToBeSwapped);
		}
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

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

}
