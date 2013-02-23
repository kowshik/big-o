package collections;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A class that can iterate over a list of iterators of the same type.<br>
 * For example, you can do the following : <br>
 * 
 * <pre>
 * {@code
 * List<Integer> l1 = new Vector<Integer>();
 *    l1.add(1); l1.add(2); l1.add(3);
 * List<Integer> l2 = new Vector<Integer>();
 *    l2.add(4); l2.add(5); l2.add(6);
 * List<Integer> l3 = new Vector<Integer>();
 *    l3.add(7); l3.add(8); l3.add(9);
 * 
 * List<Iterator<Integer>> iterators = new Vector<Iterator<Integer>>();
 *    iterators.add(l1.iterator());
 *    iterators.add(l2.iterator());
 *    iterators.add(l3.iterator());
 * 
 * MultiIterator<Integer> mi = new MultiIterator<Integer>(iterators);
 * 
 * // Prints 1 to 9 in order
 * while(mi.hasNext()) { 
 *   System.out.println(mi.next());
 * }
 * @code}
 * </pre>
 * 
 * @author Kowshik Prakasam
 * @param <T>
 */
public class MultiIterator<T> implements Iterator<T> {
	// Top level iterator for the list
	private final Iterator<Iterator<T>> motherIterator;
	// Handle to the current iterator being used
	private Iterator<T> childIterator;

	public MultiIterator(List<Iterator<T>> iterList) {
		motherIterator = iterList.iterator();
	}

	@Override
	public boolean hasNext() {

		// Attempts to set the current iterator to a valid iterator
		// using setNextChildIterator().
		// If this operation succeeds, then this method queries the
		// current iterator to checks if the current iterator using
		// its hasNext() method.
		// Else, this method returns false.

		if (setNextChildIterator()) {
			return childIterator.hasNext();
		}
		return false;
	}

	private boolean setNextChildIterator() {
		// childIterator can be null when hasNext() is called
		// on the object for the first time ever.
		if (childIterator == null || !childIterator.hasNext()) {
			do {
				// checks the top-level iterator for a new child iterator
				childIterator = getNextChildIterator();
				if (childIterator == null) {
					// return false if the top-level iterator
					// doesn't have child iterators left to be used.
					return false;
				}
				// loop until we find a valid usable iterator
			} while (!childIterator.hasNext());
		}
		// valid child iterator found! so, return true.
		return true;
	}

	// iterates the top-level iterator to pick-up a valid child iterator
	// returns the first found valid child iterators.
	// if there are no valid child iterators left, then returns null
	private Iterator<T> getNextChildIterator() {
		Iterator<T> localChildIterator = null;
		while (localChildIterator == null && motherIterator.hasNext()) {
			localChildIterator = motherIterator.next();
		}
		return localChildIterator;
	}

	@Override
	public T next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}

		return childIterator.next();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
