package binarytrees;

import java.util.LinkedList;

/**
 * Given the root of a binary tree, print all different paths from root to
 * leaves to standard out.
 * 
 * Signature of expected method:
 * 
 *    public static void printPaths(TreeNode<?> root) {...}
 */
public class TreePrintPaths {
	public static void printPaths(TreeNode<?> root) {
		@SuppressWarnings("rawtypes")
		LinkedList list = new LinkedList();
		printPaths(root, list);
	}

	@SuppressWarnings("unchecked")
	private static void printPaths(TreeNode<?> root,
			@SuppressWarnings("rawtypes") LinkedList path) {
		if (root == null) {
			return;
		}

		Object value = root.getValue();
		path.addLast(value);
		printPaths(root.getLeft(), path);
		printPaths(root.getRight(), path);

		if (root.getLeft() == null && root.getRight() == null) {
			System.out.println(path);
		}

		path.removeLast();
	}
}
