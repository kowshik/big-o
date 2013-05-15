package binarytrees;

import java.util.Comparator;

/**
 * Reconstruct a binary tree from its inorder and preorder traversals.
 * 
 * Signature of expected method:
 * 
 *    public static <T> TreeNode<T> reconstruct(T[] inorder, T[] preorder,
 *			                                    Comparator<T> comparator) {...}
 */

public class TreeReconstruct {

	public static <T> TreeNode<T> reconstruct(T[] inorder, T[] preorder,
			Comparator<T> comparator) {
		return reconstruct(inorder, 0, inorder.length - 1, preorder, 0,
				preorder.length - 1, comparator);
	}

	private static <T> TreeNode<T> reconstruct(T[] inorder, int inorderStart,
			int inorderEnd, T[] preorder, int preorderStart, int preorderEnd,
			Comparator<T> comparator) {
		if (inorderStart > inorderEnd || preorderStart > preorderEnd) {
			return null;
		}

		int rootNodeIndex = findNode(inorder, inorderStart, inorderEnd,
				preorder[preorderStart], comparator);
		if (rootNodeIndex == -1) {
			throw new RuntimeException("Bad input.");
		}

		TreeNode<T> rootNode = new TreeNode<T>(inorder[rootNodeIndex]);

		int leftSubTreeSize = rootNodeIndex - inorderStart;
		TreeNode<T> leftNode = reconstruct(inorder, inorderStart,
				rootNodeIndex - 1, preorder, preorderStart + 1, preorderStart
						+ leftSubTreeSize, comparator);

		TreeNode<T> rightNode = reconstruct(inorder, rootNodeIndex + 1,
				inorderEnd, preorder, preorderStart + leftSubTreeSize + 1,
				preorderEnd, comparator);

		rootNode.setLeft(leftNode);
		rootNode.setRight(rightNode);
		return rootNode;
	}

	private static <T> int findNode(T[] array, int startIndex, int endIndex,
			T value, Comparator<T> comparator) {
		for (int index = startIndex; index <= endIndex; index++) {
			if (comparator.compare(array[index], value) == 0) {
				return index;
			}
		}

		return -1;
	}
}
