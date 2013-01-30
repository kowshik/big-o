package binarytree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import binarytrees.FindMode;
import binarytrees.TreeNode;

public class FindModeTest {
	@Test
	public void emptyBst() {
		TreeNode<Integer> root = null;
		Integer mode = FindMode.findMode(root);
		assertNull(mode);
	}

	@Test
	public void bstWithOneElement() {
		TreeNode<Integer> root = new TreeNode<Integer>(1);
		Integer mode = FindMode.findMode(root);
		assertEquals(1, mode.intValue());
	}

	@Test
	public void bstWithUniqueElements() {
		TreeNode<Integer> rootLeft = new TreeNode<Integer>(1);
		TreeNode<Integer> rootRight = new TreeNode<Integer>(3);

		TreeNode<Integer> root = new TreeNode<Integer>(2);
		root.setLeft(rootLeft);
		root.setRight(rootRight);

		Integer mode = FindMode.findMode(root);
		assertEquals(1, mode.intValue());
	}

	@Test
	public void bstWithDuplicateElements() {
		TreeNode<Integer> rootLL = new TreeNode<Integer>(2);
		TreeNode<Integer> rootLR = new TreeNode<Integer>(3);

		TreeNode<Integer> rootL = new TreeNode<Integer>(2);
		rootL.setLeft(rootLL);
		rootL.setRight(rootLR);

		TreeNode<Integer> rootRLLL = new TreeNode<Integer>(4);
		TreeNode<Integer> rootRLL = new TreeNode<Integer>(4);
		rootRLL.setLeft(rootRLLL);

		TreeNode<Integer> rootRL = new TreeNode<Integer>(4);
		rootRL.setLeft(rootRLL);

		TreeNode<Integer> rootRR = new TreeNode<Integer>(7);

		TreeNode<Integer> rootR = new TreeNode<Integer>(4);
		rootR.setLeft(rootRL);
		rootR.setRight(rootRR);

		TreeNode<Integer> root = new TreeNode<Integer>(4);

		Integer mode = FindMode.findMode(root);
		assertEquals(4, mode.intValue());
	}
}
