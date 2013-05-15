package binarytrees;

/**
 * Find the size of a binary tree.
 * 
 * Signature of expected method:
 * 
 *    public static int size(TreeNode<?> root) {...}
 */
public class TreeSize {
	public static int size(TreeNode<?> root) {
		if (root == null) {
			return 0;
		}

		return size(root.getLeft()) + 1 + size(root.getRight());
	}
}
