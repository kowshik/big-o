package collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;
import java.util.Vector;

public class DeepIterator<T> implements Iterator<T> {
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
