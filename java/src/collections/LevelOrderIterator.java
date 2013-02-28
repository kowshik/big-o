package collections;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

import binarytrees.TreeNode;

/**
 * Implement a level order iterator for binary trees.
 */
public class LevelOrderIterator<T> implements Iterator<T> {

	private T nextItem;
	private final Queue<TreeNode<T>> queue;

	public LevelOrderIterator(TreeNode<T> rootNode) {
		queue = new LinkedList<TreeNode<T>>();
		queue.add(rootNode);
	}

	@Override
	public boolean hasNext() {
		if (nextItem != null) {
			return true;
		}

		if (queue.isEmpty()) {
			return false;
		}

		TreeNode<T> node = queue.remove();
		if (node.hasLeft()) {
			queue.add(node.getLeft());
		}

		if (node.hasRight()) {
			queue.add(node.getRight());
		}

		nextItem = node.getValue();
		;
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
		// TODO Auto-generated method stub

	}
}
