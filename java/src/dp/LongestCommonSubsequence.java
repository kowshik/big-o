package dp;

/**
 * Find the longest common subsequence between two strings.
 */
public class LongestCommonSubsequence {
	public static String lcs(String x, String y) {
		if (x == null || y == null) {
			return null;
		}

		int[][] dpTable = new int[x.length() + 1][y.length() + 1];
		for (int row = 1; row <= x.length(); row++) {
			for (int col = 1; col <= y.length(); col++) {
				if (x.charAt(row - 1) == y.charAt(col - 1)) {
					dpTable[row][col] = dpTable[row - 1][col - 1] + 1;
				} else {
					if (dpTable[row - 1][col] >= dpTable[row][col - 1]) {
						dpTable[row][col] = dpTable[row - 1][col];
					} else {
						dpTable[row][col] = dpTable[row][col - 1];
					}
				}
			}
		}

		return generateSolution(x, y, dpTable).toString();
	}

	private static String generateSolution(String x, String y, int[][] dpTable) {
		StringBuffer solution = new StringBuffer();
		generateSolution(x, y, x.length(), y.length(), dpTable, solution);

		return solution.toString();
	}

	private static void generateSolution(String x, String y, int xIndex,
			int yIndex, int[][] dpTable, StringBuffer solution) {
		if (xIndex <= 0 || yIndex <= 0) {
			return;
		}

		if (x.charAt(xIndex - 1) == y.charAt(yIndex - 1)) {
			generateSolution(x, y, xIndex - 1, yIndex - 1, dpTable, solution);
			solution.append(x.charAt(xIndex - 1));
		} else {
			if (dpTable[xIndex - 1][yIndex] >= dpTable[xIndex][yIndex - 1]) {
				generateSolution(x, y, xIndex - 1, yIndex, dpTable, solution);
			} else {
				generateSolution(x, y, xIndex, yIndex - 1, dpTable, solution);
			}
		}
	}

	public static void main(String[] args) {
		System.out.println(lcs("ABCDBB", "AEFBABB"));
		System.out.println(lcs("ABCDBB", "EAGBFAAB"));
	}
}
