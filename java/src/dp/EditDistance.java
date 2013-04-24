package dp;

/**
 * Given two text strings A of length n and B of length m, you want to transform
 * A into B with a minimum number of operations of the following types: delete a
 * character from A, insert a character into A, or change some character in A
 * into a new character. The minimal number of such operations required to
 * transform A into B is called the edit distance between A and B.
 *
 * Write a function to compute the edit distance between two given strings.
 */
public class EditDistance {
	private static final int COST_OF_DELETION = 1;
	private static final int COST_OF_INSERTION = 1;
	private static final int COST_OF_REPLACEMENT = 1;

	private static int min(int a, int b, int c) {
		return Math.min(Math.min(a, b), c);
	}

	public static int findEditDistance(String foo, String bar) {
		if (foo == null) {
			throw new NullPointerException("foo can't be null.");
		}

		if (bar == null) {
			throw new NullPointerException("bar can't be null.");
		}

		int[][] dpTable = new int[foo.length() + 1][bar.length() + 1];
		dpTable[0][0] = 0;

		// fill first row with cost of transforming an empty string
		// to bar[0..0], bar[0..1], bar[0..2], ... , bar[0..bar.length - 1].
		for (int colIndex = 1; colIndex < dpTable[0].length; colIndex++) {
			dpTable[0][colIndex] = dpTable[0][colIndex - 1] + 1;
		}

		// fill first col with cost of transforming an empty string
		// to foo[0..0], foo[0..1], foo[0..2], ... , foo[0..bar.length - 1].
		for (int rowIndex = 1; rowIndex < dpTable.length; rowIndex++) {
			dpTable[rowIndex][0] = dpTable[rowIndex - 1][0] + 1;
		}

		for (int fooIndex = 1; fooIndex <= foo.length(); fooIndex++) {
			for (int barIndex = 1; barIndex <= bar.length(); barIndex++) {
				int deleteAndTransform = dpTable[fooIndex - 1][barIndex]
						+ COST_OF_DELETION;
				int insertAndTransform = dpTable[fooIndex][barIndex - 1]
						+ COST_OF_INSERTION;
				int replaceAndTransform = dpTable[fooIndex - 1][barIndex - 1];
				if (foo.charAt(fooIndex - 1) != bar.charAt(barIndex - 1)) {
					replaceAndTransform += COST_OF_REPLACEMENT;
				}

				dpTable[fooIndex][barIndex] = min(deleteAndTransform,
						insertAndTransform, replaceAndTransform);
			}
		}

		return dpTable[foo.length()][bar.length()];
	}

	public static void main(String[] args) {
		System.out.println(findEditDistance("c", "abmd"));
	}
}
