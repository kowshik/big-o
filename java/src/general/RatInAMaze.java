package general;

public class RatInAMaze {
	public static boolean doesPathExist(int[][] maze) {
		if (maze == null) {
			return false;
		}

		return doesPathExist(maze, 0, 0);
	}

	private static boolean doesPathExist(int[][] maze, int x, int y) {
		if (x >= maze.length || y >= maze[0].length) {
			return false;
		}

		if (maze[x][y] == 0) {
			return false;
		}

		if (x == maze.length - 1 && y == maze[0].length - 1) {
			return true;
		}

		return doesPathExist(maze, x, y + 1) || doesPathExist(maze, x + 1, y);
	}
}
