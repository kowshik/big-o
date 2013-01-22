package sorting;

import exceptions.EmptyHeapException;

public interface Heap<T> {
	int size();

	T peek() throws EmptyHeapException;

	T pop() throws EmptyHeapException;

	void add(T element);
}
