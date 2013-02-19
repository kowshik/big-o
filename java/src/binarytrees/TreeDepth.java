package binarytrees;

/**
 * Given a binary tree, find its depth.
 */
public class TreeDepth {
	public static int maxDepth(TreeNode<?> root) {
		if (root == null) {
			return 0;
		}

		return Math.max(maxDepth(root.getLeft()), maxDepth(root.getRight())) + 1;
	}
}
