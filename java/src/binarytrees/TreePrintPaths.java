package binarytrees;

import java.util.LinkedList;

/**
 * Print all different paths from root to leaves in a binary tree.
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
