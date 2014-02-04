package binarytrees;

/**
 * Suppose you are building an N node binary search tree with the values 1..N.
 * How many structurally different binary search trees are there that store
 * those values? Write a recursive function that, given the number of distinct
 * values, computes the number of structurally unique binary search trees that
 * store those values.
 * 
 * Signature of expected method:
 * 
 *    public static <T> long countTrees(int numKeys);
 * 
 * For example, countTrees(4) should return 14, since there
 * are 14 structurally unique binary search trees that store 1, 2, 3, and 4.
 */
public class BstCountTrees
{
	
	private static <T> long countTreesInternal(int numKeys, long[] savedSums)
	{
		
		long sum = 0L;
		for (int root = 1; root <= numKeys; root++)
		{
			if (savedSums[root - 1] == 0)
				savedSums[root - 1] = countTreesInternal(root - 1,savedSums);
			if(savedSums[numKeys - root] == 0)
				savedSums[numKeys - root] = countTreesInternal(numKeys - root,savedSums);
			sum += savedSums[root - 1] * savedSums[numKeys - root];
		}

		return sum;
	}

	public static <T> long countTrees(int numKeys){
		long[] savedSums = new long[numKeys + 1];
		savedSums[0] = 1;
		return countTreesInternal(numKeys, savedSums);
	}
	public static void main(String[] args)
	{
		long  numTrees = BstCountTrees.countTrees(10);
		System.out.println(numTrees);
		
	}

}
