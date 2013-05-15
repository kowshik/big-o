package binarytrees;

/**
 * Check if a binary tree is a binary search tree. To keep things simple, assume
 * that the binary tree contains only integers.
 * 
 * Signature of expected method:
 * 
 *    public static boolean isBst(TreeNode<Integer> rootNode) {...}
 */
public class BstIsBst {
	public static boolean isBst(TreeNode<Integer> rootNode) {
		return isBst(rootNode, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private static boolean isBst(TreeNode<Integer> rootNode, int minValue,
			int maxValue) {
		if (rootNode == null) {
			return true;
		}

		boolean check = minValue <= rootNode.getValue()
				&& rootNode.getValue() < maxValue;
		if (check) {
			check = isBst(rootNode.getLeft(), minValue, rootNode.getValue());
			if (check) {
				check = isBst(rootNode.getRight(), rootNode.getValue() + 1,
						maxValue);
			}
		}

		return check;
	}
}
