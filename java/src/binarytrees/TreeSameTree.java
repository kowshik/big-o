package binarytrees;

import java.util.Comparator;

/**
 * Determine if two binary trees are structurally identical.
 * 
 * Signature of expected method:
 * 
 *    public static <T> boolean isSameTree(TreeNode<T> rootFoo,
			                               TreeNode<T> rootBar,
			                               Comparator<T> comparator) {...}
 */
public class TreeSameTree {
	public static <T> boolean isSameTree(TreeNode<T> rootFoo,
			TreeNode<T> rootBar, Comparator<T> comparator) {
		if (rootFoo == null && rootBar == null) {
			return true;
		}

		if (rootFoo != null && rootBar != null) {
			return isSameTree(rootFoo.getLeft(), rootBar.getLeft(), comparator)
					&& isSameTree(rootFoo.getRight(), rootBar.getRight(),
							comparator)
					&& comparator.compare(rootFoo.getValue(),
							rootBar.getValue()) == 0;
		}

		return false;
	}
}
