package collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An iterator that hops specified number of times and then returns the next
 * element after the hop.
 * 
 * Examples:
 * 
 * If the original iterator returns: [1, 2, 3, 4, 5] in order, then the hopping
 * iterator will return [1, 3, 5] in order when the hop value is 1.
 * 
 * If the original iterator returns: [1, 2, 3, 4, 5] in order, then the hopping
 * iterator will return [1, 4] in order when the hop value is 2.
 * 
 * If the original iterator returns: [1, 2, 3, 4, 5] in order, then the hopping
 * iterator will return [1, 5] in order when the hop value is 3.
 */
public class HoppingIterator<T> implements Iterator<T> {

	private final Iterator<T> iterator;
	private T nextItem;
	private final int numHops;

	public HoppingIterator(Iterator<T> iterator, int numHops) {
		if (numHops < 0) {
			throw new IllegalArgumentException(String.format(
					"numHops needs to be >= 0. You passed: %d", numHops));
		}

		this.numHops = numHops;
		this.iterator = iterator;
		nextItem = null;
	}

	@Override
	public boolean hasNext() {
		if (nextItem != null) {
			return true;
		}

		for (int hop = 0; hop < numHops && iterator.hasNext(); hop++) {
			iterator.next();
		}

		if (iterator.hasNext()) {
			nextItem = iterator.next();
		}

		return nextItem != null ? true : false;
	}

	@Override
	public T next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}

		T toReturn = nextItem;
		nextItem = null;
		return toReturn;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
