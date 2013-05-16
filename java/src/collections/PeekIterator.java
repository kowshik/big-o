package collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implement a peeking iterator. This can be used to peek at values
 * in the underlying iterator before iterating over them.
 * 
 * Methods expected to be implemented:
 * 
 * public class PeekIterator<T> implements Iterator<T> {
 * 		public PeekIterator(Iterator<T> iterator) {...}
 * 		public boolean hasNext() {...}
 * 		public T next() {...}
 * }
 */
public class PeekIterator<T> implements Iterator<T> {

	private final Iterator<T> iterator;
	private T nextitem;

	public PeekIterator(Iterator<T> iterator) {
		this.iterator = iterator;
	}

	@Override
	public boolean hasNext() {
		if (nextitem != null) {
			return true;
		}

		if (iterator.hasNext()) {
			nextitem = iterator.next();
		}

		return nextitem != null;
	}

	@Override
	public T next() {
		if (!hasNext()) {
			throw (new NoSuchElementException("Iterator has no elements left."));
		}

		T toReturn = nextitem;
		nextitem = null;
		return toReturn;
	}

	public T peek() {
		if (!hasNext()) {
			throw (new NoSuchElementException("Iterator has no elements left."));
		}

		return nextitem;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
