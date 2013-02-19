package binarytrees;

import java.util.Comparator;

/**
 * Insert a value into a binary search tree.
 */
public class BstInsert {
	public static <T> void insert(TreeNode<T> root, T value,
			Comparator<T> comparator) {
		if (root == null || value == null || comparator == null) {
			return;
		}

		doInsert(root, value, comparator);
	}

	private static <T> TreeNode<T> doInsert(TreeNode<T> root, T value,
			Comparator<T> comparator) {
		if (root == null) {
			return new TreeNode<T>(value);
		}

		if (comparator.compare(root.getValue(), value) <= 0) {
			root.setLeft(doInsert(root.getLeft(), value, comparator));
		}

		root.setRight(doInsert(root.getRight(), value, comparator));
		return root;
	}
}
