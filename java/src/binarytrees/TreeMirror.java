package binarytrees;

/**
 * Given a binary tree, convert it into its mirror.
 * 
 * Signature of expected method:
 *    
 *    public static <T> void mirror(TreeNode<T> root) {
 */
public class TreeMirror {
	public static <T> void mirror(TreeNode<T> root) {
		if (root == null) {
			return;
		}

		mirror(root.getLeft());
		mirror(root.getRight());

		TreeNode<T> temp = root.getLeft();
		root.setLeft(root.getRight());
		root.setRight(temp);
	}
}
