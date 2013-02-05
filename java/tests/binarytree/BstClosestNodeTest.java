package binarytree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import binarytrees.BstClosestNode;
import binarytrees.TreeNode;

public class BstClosestNodeTest {

	@Test
	public void emptyTree() {
		assertNull(BstClosestNode.findClosestNode(null, null));
	}

	@Test
	public void treeWithOnlyRoot() {
		TreeNode<Integer> root = new TreeNode<Integer>(10);
		assertEquals(null, BstClosestNode.findClosestNode(root, root));
	}

	@Test
	public void treeWithNoInorderPredecessorAndSubtreeInorderSuccessor() {
		TreeNode<Integer> subTreeNode3 = new TreeNode<Integer>(72);
		TreeNode<Integer> subTreeNode2 = new TreeNode<Integer>(73);
		subTreeNode2.setLeft(subTreeNode3);
		TreeNode<Integer> subTreeNode1 = new TreeNode<Integer>(74);
		subTreeNode1.setLeft(subTreeNode2);

		TreeNode<Integer> rootLeftRight = new TreeNode<Integer>(75);
		rootLeftRight.setLeft(subTreeNode1);

		TreeNode<Integer> rootLeft = new TreeNode<Integer>(100);
		rootLeft.setRight(rootLeftRight);

		TreeNode<Integer> rootRightLeft = new TreeNode<Integer>(300);
		TreeNode<Integer> rootRightRight = new TreeNode<Integer>(450);

		TreeNode<Integer> rootRight = new TreeNode<Integer>(400);
		rootRight.setLeft(rootRightLeft);
		rootRight.setRight(rootRightRight);

		TreeNode<Integer> root = new TreeNode<Integer>(250);
		root.setLeft(rootLeft);
		root.setRight(rootRight);

		TreeNode<Integer> answer = binarytrees.BstClosestNode.findClosestNode(
				root, rootLeft);
		System.out.println(answer.getValue());
		assertEquals(subTreeNode3, answer);
	}

	@Test
	public void treeWithNoInorderSuccessorAndSubtreeInorderPredecessor() {
		TreeNode<Integer> subTreeNode3 = new TreeNode<Integer>(54);
		TreeNode<Integer> subTreeNode2 = new TreeNode<Integer>(53);
		subTreeNode2.setRight(subTreeNode3);
		TreeNode<Integer> subTreeNode1 = new TreeNode<Integer>(52);
		subTreeNode1.setRight(subTreeNode2);

		TreeNode<Integer> rootLeftLeft = new TreeNode<Integer>(50);
		rootLeftLeft.setRight(subTreeNode1);

		TreeNode<Integer> rootLeft = new TreeNode<Integer>(100);
		rootLeft.setLeft(rootLeftLeft);

		TreeNode<Integer> rootRightLeft = new TreeNode<Integer>(300);
		TreeNode<Integer> rootRightRight = new TreeNode<Integer>(450);

		TreeNode<Integer> rootRight = new TreeNode<Integer>(400);
		rootRight.setLeft(rootRightLeft);
		rootRight.setRight(rootRightRight);

		TreeNode<Integer> root = new TreeNode<Integer>(250);
		root.setLeft(rootLeft);
		root.setRight(rootRight);

		TreeNode<Integer> answer = binarytrees.BstClosestNode.findClosestNode(
				root, rootLeft);

		assertEquals(subTreeNode3, answer);
	}
}
