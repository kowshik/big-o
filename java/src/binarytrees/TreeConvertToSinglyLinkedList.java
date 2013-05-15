package binarytrees;

import common.Pair;

/**
 * Given a binary tree, convert it into a singly linked list. The "next" pointer
 * in each node of the list is defined using the "right" pointer in the binary
 * tree. The order of nodes in the linked list is the same order that occurs in
 * the inorder representation of the binary tree. Also the solution must set all
 * "left" pointers in each node to null.
 * 
 * Signature of expected method:
 * 
 *    public static TreeNode<Integer> convertToSinglyLinkedList(
			TreeNode<Integer> root) {...}
 */
public class TreeConvertToSinglyLinkedList {
	public static TreeNode<Integer> convertToSinglyLinkedList(
			TreeNode<Integer> root) {
		return doConnectInorder(root).getFirst();
	}

	private static Pair<TreeNode<Integer>, TreeNode<Integer>> doConnectInorder(
			TreeNode<Integer> root) {
		if (root == null) {
			return null;
		}

		Pair<TreeNode<Integer>, TreeNode<Integer>> toReturn = new Pair<TreeNode<Integer>, TreeNode<Integer>>();
		Pair<TreeNode<Integer>, TreeNode<Integer>> leftPair = doConnectInorder(root
				.getLeft());
		if (leftPair != null) {
			leftPair.getSecond().setRight(root);
			toReturn.setFirst(leftPair.getFirst());
		} else {
			toReturn.setFirst(root);
		}

		Pair<TreeNode<Integer>, TreeNode<Integer>> rightPair = doConnectInorder(root
				.getRight());
		if (rightPair != null) {
			root.setRight(rightPair.getFirst());
			toReturn.setSecond(rightPair.getSecond());
		} else {
			toReturn.setSecond(root);
		}

		root.setLeft(null);

		return toReturn;
	}

	public static void main(String[] args) {
		TreeNode<Integer> rootLeftLeft = new TreeNode<Integer>(3);
		TreeNode<Integer> rootLeftRight = new TreeNode<Integer>(5);

		TreeNode<Integer> rootLeft = new TreeNode<Integer>(4);
		rootLeft.setLeft(rootLeftLeft);
		rootLeft.setRight(rootLeftRight);

		TreeNode<Integer> rootRightLeft = new TreeNode<Integer>(7);
		TreeNode<Integer> rootRightRight = new TreeNode<Integer>(9);

		TreeNode<Integer> rootRight = new TreeNode<Integer>(8);
		rootRight.setLeft(rootRightLeft);
		rootRight.setRight(rootRightRight);

		TreeNode<Integer> root = new TreeNode<Integer>(6);
		root.setLeft(rootLeft);
		root.setRight(rootRight);

		root = convertToSinglyLinkedList(root);

		while (root != null) {
			System.out.println(root);
			System.out.println(root.getLeft());
			root = root.getRight();
		}
	}
}
