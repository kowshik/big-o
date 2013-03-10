package dp;

import java.util.Arrays;

public class RatInAMaze {
	public static boolean doesPathExist(int[][] maze) {
		if (maze == null) {
			return false;
		}

		int[][] solved = new int[maze.length][maze[0].length];
		for (int[] array : solved) {
			Arrays.fill(array, 2);
		}

		return doesPathExist(maze, solved, 0, 0);
	}

	private static boolean doesPathExist(int[][] maze, int[][] solved, int x,
			int y) {
		if (x >= maze.length || y >= maze[0].length) {
			return false;
		}

		if (solved[x][y] != 2) {
			return solved[x][y] == 1;
		}

		if (maze[x][y] == 0) {
			solved[x][y] = 0;
			return false;
		}

		if (x == maze.length - 1 && y == maze[0].length - 1) {
			solved[x][y] = 1;
			return true;
		}

		solved[x][y] = (doesPathExist(maze, solved, x, y + 1) || doesPathExist(
				maze, solved, x + 1, y)) ? 1 : 0;
		return solved[x][y] == 1;
	}
}
