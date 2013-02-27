package binarytrees;

import common.Pair;

/**
 * Given a BST, find the mode i.e. the value that occurs maximum number of
 * times.
 */
public class BstFindMode {
	public static <T> T findMode(TreeNode<T> root) {
		Pair<T, Integer> mode = new Pair<T, Integer>();
		mode.setSecond(0);

		findMode(root, mode);

		return mode.getFirst();
	}

	private static <T> void findMode(TreeNode<T> root, Pair<T, Integer> mode) {
		Pair<T, Integer> current = new Pair<T, Integer>();
		current.setSecond(0);

		findMode(root, mode, current);
		if (current.getFirst() != null
				&& (mode.getFirst() == null || current.getSecond() > mode
						.getSecond())) {
			mode.setFirst(current.getFirst());
			mode.setSecond(current.getSecond());
		}
	}

	private static <T> void findMode(TreeNode<T> root, Pair<T, Integer> mode,
			Pair<T, Integer> current) {
		if (root == null) {
			return;
		}

		findMode(root.getLeft(), mode, current);

		if (current.getFirst() == null) {
			current.setFirst(root.getValue());
			current.setSecond(1);
		} else {
			if (current.getFirst().equals(root.getValue())) {
				current.setSecond(current.getSecond() + 1);
			} else {
				if (mode.getFirst() == null
						|| current.getSecond() > mode.getSecond()) {
					mode.setFirst(current.getFirst());
					mode.setSecond(current.getSecond());
				}

				current.setFirst(root.getValue());
				current.setSecond(1);
			}
		}

		findMode(root.getRight(), mode, current);
	}
}
