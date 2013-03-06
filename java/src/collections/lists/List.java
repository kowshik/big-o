package collections.lists;

public interface List<E> {
	void add(E element, int offset);

	void addFirst(E element);

	void addLast(E element);

	E removeFirst();

	E removeLast();

	E remove(int offset);

	boolean search(E element);

	int size();

	void reverse();
}
