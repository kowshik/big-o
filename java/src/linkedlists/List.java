package linkedlists;

import java.util.Comparator;

/**
 * A simple list interface.
 */
public interface List<E> {
	void add(E element, int offset);

	void addFirst(E element);

	void addLast(E element);

	E removeFirst();

	E removeLast();

	E remove(int offset);

	// Remove all occurences of the element.
	void remove(E element, Comparator<E> comparator);

	boolean search(E element);

	E get(int offset);

	E getFirst();

	E getLast();

	int size();

	void reverse();
}
