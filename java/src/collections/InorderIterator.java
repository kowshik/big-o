package collections;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

import binarytrees.TreeNode;

/**
 * An iterator that iterates through a binary tree in in-order.
 */
public class InorderIterator<T> implements Iterator<T> {
	private T nextItem;
	private final Stack<TreeNode<T>> stack;
	private boolean unwind;

	public InorderIterator(TreeNode<T> rootNode) {
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

		TreeNode<T> node;
		if (!unwind) {
			while ((node = stack.peek()).hasLeft()) {
				stack.push(node.getLeft());
			}
		}

		node = stack.pop();
		nextItem = node.getValue();

		if (node.hasRight()) {
			unwind = false;
			stack.push(node.getRight());
		} else {
			unwind = true;
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

	// Some quick testing below.
	public static void main(String[] args) {
		TreeNode<Integer> ll = new TreeNode<Integer>(1);
		TreeNode<Integer> l = new TreeNode<Integer>(2);

		TreeNode<Integer> r = new TreeNode<Integer>(5);
		TreeNode<Integer> rl = new TreeNode<Integer>(4);
		TreeNode<Integer> rr = new TreeNode<Integer>(6);

		l.setLeft(ll);

		r.setLeft(rl);
		r.setRight(rr);

		TreeNode<Integer> rootNode = new TreeNode<Integer>(3);
		rootNode.setLeft(l);
		rootNode.setRight(r);

		InorderIterator<Integer> ii = new InorderIterator<Integer>(rootNode);

		System.out.println(ii.next());
		System.out.println(ii.hasNext());
		System.out.println(ii.next());
		System.out.println(ii.hasNext());
		System.out.println(ii.next());
		System.out.println(ii.hasNext());
		System.out.println(ii.next());
		System.out.println(ii.hasNext());
		System.out.println(ii.next());
		System.out.println(ii.hasNext());
		System.out.println(ii.next());
		System.out.println(ii.hasNext());
	}
}
