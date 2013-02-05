package binarytrees;

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

	public static TreeNode<Integer> getInorderPredecessor(
			TreeNode<Integer> bstRootNode, TreeNode<Integer> node) {
		if (bstRootNode == null || node == null) {
			return null;
		}

		if (bstRootNode.equals(node)) {
			TreeNode<Integer> predecessor = bstRootNode.getLeft();
			if (predecessor != null) {
				while (predecessor.hasRight()) {
					predecessor = predecessor.getRight();
				}
			}

			return predecessor;
		}

		if (node.getValue() <= bstRootNode.getValue()) {
			return getInorderPredecessor(bstRootNode.getLeft(), node);

		}

		TreeNode<Integer> inorderPredecessor = getInorderPredecessor(
				bstRootNode.getRight(), node);
		return inorderPredecessor != null ? inorderPredecessor : bstRootNode;
	}

	public static TreeNode<Integer> getInorderSuccessor(
			TreeNode<Integer> bstRootNode, TreeNode<Integer> node) {
		if (bstRootNode == null || node == null) {
			return null;
		}

		if (bstRootNode.equals(node)) {
			TreeNode<Integer> successor = bstRootNode.getRight();
			if (successor != null) {
				while (successor.hasLeft()) {
					successor = successor.getLeft();
				}
			}

			return successor;
		}

		if (node.getValue() <= bstRootNode.getValue()) {
			TreeNode<Integer> inorderSuccessor = getInorderSuccessor(
					bstRootNode.getLeft(), node);
			return inorderSuccessor != null ? inorderSuccessor : bstRootNode;
		}

		return getInorderSuccessor(bstRootNode.getRight(), node);
	}
}
