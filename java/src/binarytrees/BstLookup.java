package binarytrees;

import java.util.Comparator;

/**
 * Check if a given node exists in the binary search tree.
 * 
 * Signature of expected method:
 * 
 *    public static <T> boolean lookup(TreeNode<T> rootNode,
			TreeNode<T> searchedNode, Comparator<T> comparator) {...}
 */
public class BstLookup {
	public static <T> boolean lookup(TreeNode<T> rootNode,
			TreeNode<T> searchedNode, Comparator<T> comparator) {
		if (rootNode == null) {
			return false;
		}

		int compared = comparator.compare(rootNode.getValue(),
				searchedNode.getValue());
		if (compared < 0) {
			return lookup(rootNode.getLeft(), searchedNode, comparator);
		} else if (compared == 0) {
			return true;
		}

		return lookup(rootNode.getRight(), searchedNode, comparator);
	}
}
