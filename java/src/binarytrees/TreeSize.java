package binarytrees;

/**
 * Find the size of a binary tree.
 */
public class TreeSize {
	public static int size(TreeNode<?> root) {
		if (root == null) {
			return 0;
		}

		return size(root.getLeft()) + 1 + size(root.getRight());
	}
}
