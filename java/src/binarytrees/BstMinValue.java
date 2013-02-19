package binarytrees;

/**
 * Find the smallest value in a binary search tree.
 */
public class BstMinValue {
	public static <T> T minValue(TreeNode<T> root) {
		if (root == null) {
			return null;
		}

		while (root.getLeft() != null) {
			root = root.getLeft();
		}

		return root.getValue();
	}
}
