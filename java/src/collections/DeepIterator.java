package collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.Vector;

/**
 * This class provides a flattening iterator over a Collection<?>.
 * i.e. In its constructor, the class takes in a Collection<?> object
 * which may nest either a Collection<?> object or an object of type T.
 * Also, it is expected that the nested Collection<?> objects will not
 * reference their container Collection<?> objects as this will lead
 * to a loop. But they can nest other Collection<?> objects.

 * Example: This deep iterator can be used to iterate over a vector that
 *          either contains a vector of integers, or just plain integers,
 *          or both.
 */
public class DeepIterator<T> implements Iterator<T> {
    // A reference to the item which will be returned during
    // the next call to next().
    private T nextItem;
    private Stack<Iterator<?>> stack = new Stack<Iterator<?>>();

    public DeepIterator(Collection<?> collection) {
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
