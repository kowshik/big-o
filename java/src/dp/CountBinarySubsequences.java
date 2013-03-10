package dp;

import java.util.Arrays;

public class CountBinarySubsequences {

	/**
	 * Returns the number of times the binary string "needle" occurs as a
	 * subsequence in the binary string "haystack". The solution below uses
	 * dynamic programming!
	 * 
	 * Recursive definition of subsequence counting:
	 * 
	 * We have the two strings: Needle[1 ... N] and Haystack [1 ... M]. We need
	 * to find # of occurences of needle in haystack. Lets call Needle as X and
	 * Haystack as H.
	 * 
	 * SubsequenceCount(X[P ... N], H[Q ... M]) =
	 * 
	 * if X[P] != H[Q]: SubsequenceCount(X[P ... N], H[Q+1 ... M]) otherwise:
	 * SubsequenceCount(X[P+1 ... N], H[Q+1 ... M]) + SubsequenceCount(X[P ...
	 * N], H[Q+1 ... M])
	 * 
	 * @param haystack
	 *            string containing 0s and 1s to be searched for subsequences
	 * @param needle
	 *            subsequence containing 0s and 1s to be searched
	 * @return number of subsequences in haystack that are the same as needle
	 */
	public long countSubSequences(String haystack, String needle) {
		if (haystack == null && needle == null) {
			throw new NullPointerException("Nulls disallowed.");
		}

		String needleReverse = new StringBuffer(needle).reverse().toString();
		String haystackReverse = new StringBuffer(haystack).reverse()
				.toString();
		long[][] dpTable = new long[needle.length() + 1][haystack.length() + 1];

		Arrays.fill(dpTable[0], 1L);
		for (int row = 1; row < dpTable.length; row++) {
			Arrays.fill(dpTable[row], 0L);
		}

		for (int row = 1; row < dpTable.length; row++) {
			for (int col = 1; col < dpTable[0].length; col++) {
				if (needleReverse.charAt(row - 1) == haystackReverse
						.charAt(col - 1)) {
					dpTable[row][col] = dpTable[row - 1][col - 1]
							+ dpTable[row][col - 1];
				} else {
					dpTable[row][col] = dpTable[row][col - 1];
				}
			}
		}
		System.out.println("Memoization Table: \n");
		printDpTable(dpTable, needle, haystack);
		long answer = dpTable[dpTable.length - 1][dpTable[0].length - 1];

		return answer;
	}

	private void printDpTable(long[][] dpTable, String needle, String haystack) {
		needle = new StringBuffer(needle).reverse().insert(0, "X").toString();
		haystack = new StringBuffer(haystack).reverse().insert(0, "X")
				.toString();
		StringBuffer separator = new StringBuffer();
		System.out.print("   #   ");
		separator.append("###########");
		for (int index = 0; index < haystack.length(); index++) {
			System.out.print(String.format("%c\t", haystack.charAt(index)));
			separator.append("########");
		}
		System.out.print("\n");
		System.out.println(separator);
		for (int row = 0; row < dpTable.length; row++) {
			System.out.print(String.format("%c  #   \t", needle.charAt(row)));
			for (int col = 0; col < dpTable[0].length; col++) {
				System.out.print(dpTable[row][col] + "\t");
			}
			System.out.print("\n");
		}
	}

	public static void main(String[] args) {
		CountBinarySubsequences obj = new CountBinarySubsequences();

		System.out.println("\n================\n");
		System.out.println("\n\nAnswer: "
				+ obj.countSubSequences("11001", "101"));
		System.out.println("\n================\n");
		System.out.println("\n\nAnswer: "
				+ obj.countSubSequences("1001101011", "1011"));
		System.out.println("\n================\n");
	}
}