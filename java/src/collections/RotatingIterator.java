package collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implement a rotating iterator. The iterator accepts an array list of
 * iterators, and iterates over them column-wise.
 *
 * i.e If the following iterators are passed:
 *
 * iterator_1 => [1] iterator_2 => [2, 3] iterator_3 => [4, 5]
 *
 * Then the rotating iterator returns the following elements in sequential
 * order:
 *
 * 1, 2, 4, 3, 5.
 * 
 * Methods expected to be implemented:
 * 
 * public class RotatingIterator<T> implements Iterator<T> {
 * 		public RotatingIterator(ArrayList<Iterator<T>> iterators) {...}
 * 		public boolean hasNext() {...}
 * 		public T next() {...}
 * }
 */
public class RotatingIterator<T> implements Iterator<T> {

	private final ArrayList<Iterator<T>> iterators;
	private int rotatingIndex;
	private T nextItem;

	public RotatingIterator(ArrayList<Iterator<T>> iterators) {
		this.iterators = iterators;
		this.rotatingIndex = 0;
	}

	@Override
	public boolean hasNext() {
		if (nextItem != null) {
			return true;
		}

		int count = 0;
		boolean itemFound = false;
		while (!itemFound && count < iterators.size()) {
			Iterator<T> iterator = iterators.get(rotatingIndex);
			if (iterator != null && iterator.hasNext()) {
				nextItem = iterator.next();
				// we need this because nextItem can possibly be null.
				itemFound = true;
			}

			rotatingIndex++;
			rotatingIndex %= iterators.size();
			count++;
		}

		return count != iterators.size();
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
