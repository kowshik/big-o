package general;

/**
 * Given a matrix of integers and coordinates of a rectangular region within the
 * matrix, find the sum of numbers falling inside the rectangle. The program
 * will be called multiple times with different rectangular regions from the
 * same matrix.
 */
public class MatrixRegionSum {
	private final int[][] matrix;
	private final long[][] sumMatrix;

	public MatrixRegionSum(int[][] matrix) {
		if (matrix == null) {
			throw new NullPointerException("null matrix is not allowed.");
		}

		this.matrix = matrix;
		this.sumMatrix = new long[matrix.length][matrix[0].length];
		preComputeSums();
	}

	private void preComputeSums() {
		for (int col = 0; col < matrix[0].length; col++) {
			sumMatrix[0][col] += matrix[0][col];
		}

		for (int row = 1; row < matrix.length; row++) {
			for (int col = 0; col < matrix[0].length; col++) {
				sumMatrix[row][col] = sumMatrix[row - 1][col]
						+ matrix[row][col];
			}
		}

		for (int row = matrix.length - 1; row >= 0; row--) {
			for (int col = 1; col < matrix[0].length; col++) {
				sumMatrix[row][col] += sumMatrix[row][col - 1];
			}
		}
	}

	// (lx, ly) is the top left co-ordinate of the rectangle.
	// (rx, ry) is the bottom right co-ordinate of the rectangle.
	public long findSum(int lx, int ly, int rx, int ry) {
		if (!valid(lx, ly) || !valid(rx, ry)) {
			throw new IllegalArgumentException(
					String.format(
							"The co-ordinates: (%d, %d), (%d, %d) are not valid co-ordinates.",
							lx, ly, rx, ry));
		}

		long sum = sumMatrix[rx][ry];
		sum -= (ly == 0 ? 0 : sumMatrix[rx][ly - 1]);
		sum -= (lx == 0 ? 0 : sumMatrix[lx - 1][ry]);
		sum += (lx == 0 || ly == 0 ? 0 : sumMatrix[lx - 1][ly - 1]);

		return sum;
	}

	public boolean valid(int x, int y) {
		return x >= 0 && x < matrix.length && y >= 0 && y < matrix[0].length;
	}
}
