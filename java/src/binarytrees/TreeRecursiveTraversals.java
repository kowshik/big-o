package binarytrees;

/**
 * Given the root of a binary tree recursively print the (in/pre/post)-order
 * traversals to standard out.
 * 
 * Signature of expected methods:
 * 
 *    public static <T> void inorderTraversal(TreeNode<T> root) {...}
 *    public static <T> void preorderTraversal(TreeNode<T> root) {...}
 *    public static <T> void postorderTraversal(TreeNode<T> root) {...}
 */
public class TreeRecursiveTraversals {

	public static <T> void inorderTraversal(TreeNode<T> root) {
		if (root == null) {
			return;
		}

		inorderTraversal(root.getLeft());
		System.out.printf("%s ", root.getValue());
		inorderTraversal(root.getRight());
	}

	public static <T> void preorderTraversal(TreeNode<T> root) {
		if (root == null) {
			return;
		}

		System.out.printf("%s ", root.getValue());
		preorderTraversal(root.getLeft());
		preorderTraversal(root.getRight());
	}

	public static <T> void postorderTraversal(TreeNode<T> root) {
		if (root == null) {
			return;
		}

		postorderTraversal(root.getLeft());
		postorderTraversal(root.getRight());
		System.out.printf("%s ", root.getValue());
	}
}
