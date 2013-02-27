package collections;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

import binarytrees.TreeNode;

/**
 * An iterator that iterates through a binary tree in pre-order.
 */
public class PreorderIterator<T> implements Iterator<T> {
	private T nextItem;
	private final Stack<TreeNode<T>> stack;

	public PreorderIterator(TreeNode<T> rootNode) {
		this.nextItem = null;
		this.stack = new Stack<TreeNode<T>>();
		stack.push(rootNode);
	}

	@Override
	public boolean hasNext() {
		if (nextItem != null) {
			return true;
		}

		if (stack.empty()) {
			return false;
		}

		TreeNode<T> node = stack.peek();
		nextItem = node.getValue();
		if (node.hasLeft()) {
			stack.push(node.getLeft());
		} else {
			while (!stack.empty()) {
				TreeNode<T> current = stack.pop();
				if (current.hasRight()) {
					stack.push(current.getRight());
					break;
				}
			}
		}

		return true;
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
