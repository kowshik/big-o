package general;

import java.util.ArrayList;
import java.util.List;

import common.Pair;

public class KnightsTour {
	public static void printKnightsTour(int boardLength) {
		if (boardLength <= 0) {
			throw new IllegalArgumentException(String.format(
					"Board lengths should be > 0. You passed: %d", boardLength));
		}

		boolean[][] visited = new boolean[boardLength][boardLength];
		printKnightsTour(boardLength, 0, 0, visited);
	}

	private static void printKnightsTour(int boardLength, int x, int y,
			boolean[][] visited) {
		if (x >= boardLength || y >= boardLength) {
			return;
		}

		if (visited[x][y]) {
			return;
		}

		visited[x][y] = true;
		System.out.printf("Knight is visiting: (%d, %d)\n", x, y);

		for (Pair<Integer, Integer> p : getKnightMoves(boardLength, x, y)) {
			printKnightsTour(boardLength, p.getFirst(), p.getSecond(), visited);
		}
	}

	private static List<Pair<Integer, Integer>> getKnightMoves(int boardLength,
			int row, int col) {
		ArrayList<Pair<Integer, Integer>> validMoves = new ArrayList<Pair<Integer, Integer>>();

		int x = row - 2;
		int y = col - 1;
		if (validPosition(boardLength, x, y)) {
			validMoves.add(new Pair<Integer, Integer>(x, y));
		}

		y = col + 1;
		if (validPosition(boardLength, x, y)) {
			validMoves.add(new Pair<Integer, Integer>(x, y));
		}

		x = row + 2;
		y = col - 1;
		if (validPosition(boardLength, x, y)) {
			validMoves.add(new Pair<Integer, Integer>(x, y));
		}

		y = col + 1;
		if (validPosition(boardLength, x, y)) {
			validMoves.add(new Pair<Integer, Integer>(x, y));
		}

		//

		x = row - 1;
		y = col + 2;
		if (validPosition(boardLength, x, y)) {
			validMoves.add(new Pair<Integer, Integer>(x, y));
		}

		y = col - 2;
		if (validPosition(boardLength, x, y)) {
			validMoves.add(new Pair<Integer, Integer>(x, y));
		}

		x = row + 1;
		y = col + 2;
		if (validPosition(boardLength, x, y)) {
			validMoves.add(new Pair<Integer, Integer>(x, y));
		}

		y = col - 2;
		if (validPosition(boardLength, x, y)) {
			validMoves.add(new Pair<Integer, Integer>(x, y));
		}

		return validMoves;
	}

	private static boolean validPosition(int boardLength, int x, int y) {
		return x >= 0 && x < boardLength && y >= 0 && y < boardLength;
	}

	public static void main(String[] args) {
		// When board length = 3, the Knight's tour should print all positions
		// on the board except (1,1) which can never be visited by the Knight.
		printKnightsTour(3);
	}
}
