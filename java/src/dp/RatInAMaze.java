package dp;

import java.util.Arrays;

/**
 * A maze is represented as an M x N matrix of 0s and 1s. A rat is stuck in
 * location (0,0), and needs to find its way through the maze to location (M-1,
 * N-1).
 *
 * The rat can move either right, or down.
 *
 * If maze[x][y] == 0, it means that the rat cannot visit this location. If
 * maze[x][y] == 1, the rat is allowed to pass through the location.
 *
 * Write a function to check if a path exists for the rate from (0, 0) to (M-1,
 * N-1) in the maze.
 */
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
