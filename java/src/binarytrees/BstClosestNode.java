package binarytrees;

/**
 * Given the root of a BST and a node in the tree, find the node closest to the
 * given node.
 */
public class BstClosestNode {
	public static TreeNode<Integer> findClosestNode(
			TreeNode<Integer> bstRootNode, TreeNode<Integer> node) {
		TreeNode<Integer> inorderPredecessor = getInorderPredecessor(
				bstRootNode, node);
		TreeNode<Integer> inorderSuccessor = getInorderSuccessor(bstRootNode,
				node);

		if (inorderPredecessor == null) {
			return inorderSuccessor;
		}

		if (inorderSuccessor == null) {
			return inorderPredecessor;
		}

		if ((node.getValue() - inorderPredecessor.getValue()) < (inorderSuccessor
				.getValue() - node.getValue())) {
			return inorderPredecessor;
		}

		return inorderSuccessor;
	}

	public static TreeNode<Integer> getInorderSuccessor(
			TreeNode<Integer> bstRootNode, TreeNode<Integer> node) {
		if (bstRootNode == null || node == null) {
			return null;
		}

		TreeNode<Integer> successorNode = getMinValueNode(node.getRight());
		if (successorNode != null) {
			return successorNode;
		}

		while (bstRootNode != null) {
			if (node.getValue() < bstRootNode.getValue()) {
				successorNode = bstRootNode;
				bstRootNode = bstRootNode.getLeft();
			} else {
				bstRootNode = bstRootNode.getRight();
			}
		}

		return successorNode;
	}

	public static TreeNode<Integer> getInorderPredecessor(
			TreeNode<Integer> bstRootNode, TreeNode<Integer> node) {
		if (bstRootNode == null || node == null) {
			return null;
		}

		TreeNode<Integer> predecessorNode = getMaxValueNode(node.getLeft());
		if (predecessorNode != null) {
			return predecessorNode;
		}

		while (bstRootNode != null) {
			if (node.getValue() > bstRootNode.getValue()) {
				predecessorNode = bstRootNode;
				bstRootNode = bstRootNode.getRight();
			} else {
				bstRootNode = bstRootNode.getLeft();
			}
		}

		return predecessorNode;
	}

	private static TreeNode<Integer> getMinValueNode(TreeNode<Integer> node) {
		if (node == null) {
			return null;
		}

		while (node.hasLeft()) {
			node = node.getLeft();
		}

		return node;
	}

	private static TreeNode<Integer> getMaxValueNode(TreeNode<Integer> node) {
		if (node == null) {
			return null;
		}

		while (node.hasRight()) {
			node = node.getRight();
		}

		return node;
	}
}
