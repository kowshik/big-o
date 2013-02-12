package general;

/*
 * We are given a nxn square matrix with 0s and 1s.
 * 0 means blocked cell and 1 means open cell where a
 * robot can walk. If your robot is at (0,0) initially,
 * what are the number of ways to reach (n-1,n-1)?
 * Your robot can go only right or down.
 */
public class RobotMove {
	public static long countMoves(int[][] board) {
		if (board == null || board[board.length - 1][board[0].length - 1] == 1) {
			return 0;
		}

		int numRows = board.length;
		int numCols = board[0].length;
		long[][] dp = new long[numRows][numCols];
		dp[numRows - 1][numCols - 1] = 1; // base case.

		for (int col = numCols - 2; col >= 0; col--) {
			if (board[numRows - 1][col] == 0) {
				dp[numRows - 1][col] = dp[numRows - 1][col + 1];
			} else {
				dp[numRows - 1][col] = 0;
			}
		}

		for (int row = numRows - 2; row >= 0; row--) {
			if (board[row][numCols - 1] == 0) {
				dp[row][numCols - 1] = dp[row + 1][numCols - 1];
			} else {
				dp[row][numCols - 1] = 0;
			}
		}

		for (int row = numRows - 2; row >= 0; row--) {
			for (int col = numCols - 2; col >= 0; col--) {
				if (board[row][col] == 1) {
					dp[row][col] = 0;
				} else {
					dp[row][col] = dp[row][col + 1] + dp[row + 1][col];
				}

			}
		}

		return dp[0][0];
	}
}
