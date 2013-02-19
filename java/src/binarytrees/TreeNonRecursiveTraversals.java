package binarytrees;

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
}
