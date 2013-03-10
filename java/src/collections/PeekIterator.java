package collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class PeekIterator<T> implements Iterator<T> {

	private final Iterator<T> iterator;
	private T next;

	public PeekIterator(Iterator<T> iterator) {
		this.iterator = iterator;
	}

	@Override
	public boolean hasNext() {
		if (next == null && iterator.hasNext()) {
			next = iterator.next();
		}

		return next != null;
	}

	@Override
	public T next() {
		if (!hasNext()) {
			throw (new NoSuchElementException("Iterator has no elements left."));
		}

		T toReturn = next;
		next = null;
		return toReturn;
	}

	public T peek() {
		if (!hasNext()) {
			throw (new NoSuchElementException("Iterator has no elements left."));
		}

		return next;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
