package binarytrees;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TreeNonRecursiveTraversals {
	public static void traversePreorder(TreeNode<?> rootNode) {
		if (rootNode == null) {
			return;
		}

		Stack<TreeNode<?>> stack = new Stack<TreeNode<?>>();
		stack.push(rootNode);
		while (!stack.empty()) {
			TreeNode<?> current = stack.peek();
			System.out.println(current);
			if (current.hasLeft()) {
				stack.push(current.getLeft());
			} else {
				// Pop until we have a node with a right sub-tree.
				while (!stack.empty()) {
					current = stack.pop();
					if (current.hasRight()) {
						stack.push(current.getRight());
						break;
					}
				}
			}
		}
	}

	public static void traverseInorder(TreeNode<?> rootNode) {
		if (rootNode == null) {
			return;
		}

		Stack<TreeNode<?>> stack = new Stack<TreeNode<?>>();
		stack.push(rootNode);
		while (!stack.empty()) {
			TreeNode<?> current = stack.peek();
			if (current.hasLeft()) {
				stack.push(current.getLeft());
			} else {
				// Pop and visit until we have a node with a right sub-tree.
				while (!stack.empty()) {
					current = stack.pop();
					System.out.println(current);
					if (current.hasRight()) {
						stack.push(current.getRight());
						break;
					}
				}
			}
		}
	}

	public static void traversePostorder(TreeNode<?> rootNode) {
		if (rootNode == null) {
			return;
		}

		Stack<TreeNode<?>> childStack = new Stack<TreeNode<?>>();
		Stack<TreeNode<?>> rootStack = new Stack<TreeNode<?>>();

		childStack.push(rootNode);
		while (!childStack.empty()) {
			TreeNode<?> current = childStack.peek();
			if (!rootStack.empty() && current == rootStack.peek()) {
				// This means we have already traversed the left and
				// right sub-trees of current.
				System.out.println(current);
				childStack.pop();
				rootStack.pop();
			} else {
				rootStack.push(current);
				if (current.hasRight()) {
					childStack.push(current.getRight());
				}
				if (current.hasLeft()) {
					childStack.push(current.getLeft());
				}
			}
		}
	}

	// level (starting from 0) is the level that should be traversed.
	public static void traverseLevelOrder(TreeNode<?> rootNode, int level) {
		if (level < 0) {
			throw new IllegalArgumentException(String.format(
					"level should be >= 0. You passed: %d.", level));
		}

		if (rootNode == null) {
			return;
		}

		Queue<TreeNode<?>> q = new LinkedList<TreeNode<?>>();
		q.add(rootNode);
		q.add(null);

		int levelCount = 0;
		while (!q.isEmpty()) {
			TreeNode<?> node = q.remove();
			if (node == null) {
				if (levelCount == level) {
					break;
				}

				if (!q.isEmpty()) {
					q.add(null);
					levelCount++;
				}
			} else {
				if (levelCount == level) {
					System.out.println(node);
				}

				if (node.hasLeft()) {
					q.add(node.getLeft());
				}

				if (node.hasRight()) {
					q.add(node.getRight());
				}
			}
		}

		if (levelCount != level) {
			throw new IllegalArgumentException(String.format(
					"You passed an invalid level: %d.", level));
		}
	}

	// traverse all levels in the binary tree.
	public static void traverseLevelOrder(TreeNode<?> rootNode) {
		if (rootNode == null) {
			return;
		}

		Queue<TreeNode<?>> q = new LinkedList<TreeNode<?>>();
		q.add(rootNode);
		q.add(null);

		while (!q.isEmpty()) {
			TreeNode<?> node = q.remove();

			System.out.println(node);

			if (node.hasLeft()) {
				q.add(node.getLeft());
			}

			if (node.hasRight()) {
				q.add(node.getRight());
			}
		}
	}
}
