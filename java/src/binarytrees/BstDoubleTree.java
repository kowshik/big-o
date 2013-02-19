package binarytrees;

/**
 * For each node in a binary search tree, create a new duplicate node, and
 * insert the duplicate as the left child of the original node. The resulting
 * tree should still be a binary search tree.
 */
public class BstDoubleTree {
	public static <T> void doubleTree(TreeNode<T> root) {
		if (root == null) {
			return;
		}

		doubleTree(root.getLeft());
		doubleTree(root.getRight());

		TreeNode<T> duplicate = new TreeNode<T>(root.getValue());
		duplicate.setLeft(root.getLeft());
		root.setLeft(duplicate);

		return;
	}
}
