package collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

import common.Predicate;

public class FilteringIterator<T> implements Iterator<T> {

	private final Iterator<T> iterator;
	private final Predicate<T> predicate;
	private T nextItem;
	private boolean dirty;

	public FilteringIterator(Iterator<T> iterator, Predicate<T> predicate) {
		this.iterator = iterator;
		this.predicate = predicate;
		nextItem = null;
		this.dirty = false;
	}

	@Override
	public boolean hasNext() {
		if (nextItem != null) {
			return true;
		}

		while (iterator.hasNext()) {
			T item = iterator.next();
			if (predicate.apply(item)) {
				nextItem = item;
				break;
			}
		}

		return nextItem != null;
	}

	@Override
	public T next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}

		T toReturn = nextItem;
		nextItem = null;
		dirty = true;
		return toReturn;
	}

	@Override
	public void remove() {
		if (!dirty) {
			throw new IllegalStateException();
		}

		dirty = false;
		iterator.remove();
	}
}
