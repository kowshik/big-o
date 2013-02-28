package collections;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Vector;

/**
 * Implement a max iterator i.e. it always returns the next item in the list
 * bigger than the last item returned.
 */
public class MaxIterator<T> implements Iterator<T> {

	private final Iterator<T> iterator;
	private T nextItem;
	private T lastItem;
	private final Comparator<T> comparator;

	public MaxIterator(Iterator<T> iterator, Comparator<T> comparator) {
		this.iterator = iterator;
		this.comparator = comparator;
	}

	@Override
	public boolean hasNext() {
		if (nextItem != null) {
			return true;
		}

		while (nextItem == null && iterator.hasNext()) {
			T item = iterator.next();
			if (lastItem == null || comparator.compare(item, lastItem) > 0) {
				nextItem = item;
			}
		}

		return nextItem != null;
	}

	@Override
	public T next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}

		lastItem = nextItem;
		nextItem = null;
		return lastItem;
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

	public static void main(String[] args) {
		Vector<Integer> vec = new Vector<Integer>();
		vec.add(3);
		vec.add(5);
		vec.add(9);

		MaxIterator<Integer> mi = new MaxIterator<Integer>(vec.iterator(),
				new Comparator<Integer>() {
					@Override
					public int compare(Integer a, Integer b) {
						return a - b;
					}
				});

		System.out.println(mi.next());
		System.out.println(mi.next());
		System.out.println(mi.next());
	}
}
