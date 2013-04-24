package general;

import common.ArrayUtils;

/**
 * Young's tableau is an m x n matrix such that the entries of each row are in
 * sorted order from left to right and the entries of each column are in sorted
 * order from top to bottom. Some of the entries of a Young tableau may be null,
 * which we treat as nonexistent elements. Thus a Young's tableau can be used to
 * hold r <= m x n numbers.
 *
 * 1. Implement a function to search the Young's tableau for a specific number.
 * 2. Implement a function to extract the minimum value from the Young' tableau.
 */
public class YoungsTableau {
	public static boolean search(int[][] table, int searched) {
		if (table == null) {
			throw new NullPointerException("Table cannot be null.");
		}

		int row = table.length - 1, col = 0;
		boolean found = false;

		while (!found && row >= 0 && row < table.length && col >= 0
				&& col < table[0].length) {
			if (searched < table[row][col]) {
				row--;
			} else if (searched > table[row][col]) {
				col++;
			} else {
				found = true;
			}
		}

		return found;
	}

	public static int extractMin(Integer[][] table) {
		if (table == null) {
			throw new NullPointerException("Table cannot be null.");
		}

		if (table.length == 0) {
			throw new IllegalArgumentException(
					"Can't extract from an empty table");
		}

		if (table[0][0] == null) {
			throw new IllegalArgumentException("No min value to extract.");
		}

		Integer toReturn = table[0][0];
		table[0][0] = null;
		youngify(table);

		return toReturn;
	}

	private static void youngify(Integer[][] table) {
		int row = 0;
		int col = 0;

		while (row < table.length && col < table[0].length) {
			int smallestRow = row;
			int smallestCol = col;

			if (col + 1 < table[0].length && table[row][col + 1] != null) {
				if (table[smallestRow][smallestCol] == null
						|| table[row][col + 1] < table[smallestRow][smallestCol]) {
					smallestRow = row;
					smallestCol = col + 1;
				}
			}

			if (row + 1 < table.length && table[row + 1][col] != null) {
				if (table[smallestRow][smallestCol] != null
						&& table[row + 1][col] < table[smallestRow][smallestCol]) {
					smallestRow = row + 1;
					smallestCol = col;
				}
			}

			if (smallestRow == row && smallestCol == col) {
				break;
			}

			ArrayUtils.swap(table, row, col, smallestRow, smallestCol);
			row = smallestRow;
			col = smallestCol;
		}
	}
}
