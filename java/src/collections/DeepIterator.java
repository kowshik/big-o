package collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * This class provides a flattening iterator over a Collection<?>. i.e. In its
 * constructor, the class takes in a Collection<?> object which may nest either
 * a Collection<?> object or an object of type T. Also, it is expected that the
 * nested Collection<?> objects will not reference their container Collection<?>
 * objects as this will lead to a loop. But they can nest other Collection<?>
 * objects. The class then iterates through elements in the given Collection<?>
 * object, returning the values in the leaves of the nested structure one-by-one
 * upon each call to next().
 * 
 * 
 * Example: This deep iterator can be used to iterate over a vector that either
 * contains a vector of integers, or just plain integers, or both. Lets assume
 * that the Collection<?> passed has the following structure:
 * 
 * [int_a, vector_1, int_d, vector_2, int_h] / \ / \ [int_b, int_c] [int_e,
 * vector_3] / / [int_f, int_g]
 * 
 * The iterator returns the leaves in order viz.: int_a, int_b, int_c, int_d,
 * int_e, int_f, int_g, int_h.
 */

public class DeepIterator<T> implements Iterator<T> {
	// A reference to the item which will be returned during
	// the next call to next().
	private T nextItem;
	private final Stack<Iterator<?>> stack = new Stack<Iterator<?>>();

	public DeepIterator(Collection<?> collection) {
		if (collection == null) {
			throw new NullPointerException(
					"Can't iterate over a null collection.");
		}

		stack.push(collection.iterator());
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean hasNext() {
		if (nextItem != null) {
			return true;
		}

		while (!stack.isEmpty()) {
			Iterator<?> iter = stack.peek();
			if (iter.hasNext()) {
				Object item = iter.next();
				if (item instanceof Collection<?>) {
					stack.push(((Collection<?>) item).iterator());
				} else {
					nextItem = (T) item;
					return true;
				}
			} else {
				stack.pop();
			}
		}

		return false;
	}

	@Override
	public T next() {
		if (hasNext()) {
			T toReturn = nextItem;
			nextItem = null;
			return toReturn;
		}

		throw new NoSuchElementException();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
