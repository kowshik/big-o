package general;

/**
 * Place N queens on an N x N chess board so that no two queens attack each
 * other.
 */
public class NQueens {
	// If the problem is solvable, returns a boolean[][] with true values for
	// queen locations. Returns null otherwise.
	public static boolean[][] solveNQueens(int boardLength) {
		if (boardLength <= 0) {
			throw new IllegalArgumentException(
					String.format(
							"Board length needs to be > 0. You passed: %d",
							boardLength));
		}

		boolean[][] board = new boolean[boardLength][boardLength];
		if (solveNQueens(board, 0)) {
			return board;
		} else {
			return null;
		}
	}

	private static boolean solveNQueens(boolean[][] board, int col) {
		if (col >= board.length) {
			return true;
		}

		for (int row = 0; row < col; row++) {
			if (isSafe(board, row, col)) {
				board[row][col] = true;

				if (solveNQueens(board, col + 1)) {
					return true;
				}

				board[row][col] = false; // backtrack
			}
		}

		return false;
	}

	private static boolean isSafe(boolean[][] board, int row, int col) {
		int i, j;

		// Check row on the left.
		for (i = 0; i < col; i++) {
			if (board[row][i]) {
				return false;
			}
		}

		// Check upper diagonal on the left.
		for (i = row, j = col; i >= 0 && j >= 0; i--, j--) {
			if (board[i][j]) {
				return false;
			}
		}

		// Check lower diagonal on the left.
		for (i = row, j = col; j >= 0 && i < board.length; i++, j--) {
			if (board[i][j]) {
				return false;
			}
		}

		return true;
	}
}
