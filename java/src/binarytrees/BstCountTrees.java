package binarytrees;

/**
 * Suppose you are building an N node binary search tree with the values 1..N.
 * How many structurally different binary search trees are there that store
 * those values? Write a recursive function that, given the number of distinct
 * values, computes the number of structurally unique binary search trees that
 * store those values. For example, countTrees(4) should return 14, since there
 * are 14 structurally unique binary search trees that store 1, 2, 3, and 4.
 */
public class BstCountTrees {

	public static <T> long countTrees(int numKeys) {
		if (numKeys <= 0) {
			return 0;
		}

		long sum = 0L;
		for (int root = 1; root <= numKeys; root++) {
			sum += countTrees(root - 1) * countTrees(numKeys - root);
		}

		return sum;
	}
}
