package general;

/**
 * You are given a maze (2D boolean matrix) where true = free way, false =
 * obstacle. Write a function to find out how many ways exist to reach [N][M]
 * from [0][0].
 */
public class Maze {
	public static long findNumWays(boolean[][] maze) {
		if (maze == null) {
			return 0;
		}

		boolean[][] visited = new boolean[maze.length][maze[0].length];
		return findNumWays(maze, 0, 0, visited);
	}

	private static long findNumWays(boolean[][] maze, int row, int col,
			boolean[][] visited) {
		if (!isValid(maze, row, col) || visited[row][col] || !maze[row][col]) {
			return 0L;
		}

		if (endOfMaze(maze, row, col)) {
			return 1L;
		}

		visited[row][col] = true;

		long numWays = 0L;
		numWays += findNumWays(maze, row - 1, col, visited);
		numWays += findNumWays(maze, row + 1, col, visited);
		numWays += findNumWays(maze, row, col + 1, visited);
		numWays += findNumWays(maze, row, col - 1, visited);

		visited[row][col] = false;
		return numWays;
	}

	private static boolean endOfMaze(boolean[][] maze, int row, int col) {
		return maze != null && row == maze.length - 1
				&& col == maze[0].length - 1;
	}

	private static boolean isValid(boolean[][] maze, int row, int col) {
		return maze != null && row >= 0 && row < maze.length && col >= 0
				&& col < maze[0].length;
	}
}
