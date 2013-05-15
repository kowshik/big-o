package binarytrees;

/**
 * Given a binary tree and a sum, return true if the tree has a root-to-leaf
 * path such that adding up all the values along the path equals the given sum.
 * Return false if no such path can be found.
 * 
 * Signature of expected method:
 * 
 *    public static boolean hasPathSum(TreeNode<Integer> root, int sum) {...}
 */
public class TreeHasPathSum {
	public static boolean hasPathSum(TreeNode<Integer> root, int sum) {
		if (root == null) {
			return false;
		}

		Integer value = root.getValue();
		if (value == null) {
			throw new NullPointerException("You are an insane programmer.");
		}
		return hasPathSum(root.getLeft(), sum - value)
				|| hasPathSum(root.getRight(), sum - value)
				|| (sum - value == 0 && !root.hasLeft() && !root.hasRight());
	}
}
