package arrays;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

public class BoundedQueue<T> implements Queue<T> {

	private int size;
	private int head;
	private int tail;
	private final Object[] elements;

	public BoundedQueue(int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException("Size should be positive.");
		}

		elements = new Object[capacity];
		this.size = 0;
		this.tail = 0;
		this.head = -1;
	}

	@Override
	public boolean add(T element) {
		if (size == elements.length) {
			throw new IllegalStateException("Size exceeded.");
		}

		elements[tail] = element;
		tail++;
		tail %= elements.length;
		size++;

		return true;
	}

	@Override
	public T remove() {
		if (size == 0) {
			throw new NoSuchElementException("No elements left to be removed.");
		}

		@SuppressWarnings("unchecked")
		T removed = (T) elements[head];
		elements[head] = null;
		head++;
		head %= elements.length;
		size--;

		return removed;
	}

	// We don't implement the methods below.

	@Override
	public boolean addAll(Collection<? extends T> arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean contains(Object arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isEmpty() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterator<T> iterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean remove(Object arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int size() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object[] toArray() {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T> T[] toArray(T[] arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public T element() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean offer(T arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public T poll() {
		throw new UnsupportedOperationException();
	}

	@Override
	public T peek() {
		throw new UnsupportedOperationException();
	}
}
