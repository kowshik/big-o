package general;

/**
 * Given a string s, find its lexicographical ranking.
 */
public class LexicographicalRanking {
	public static int findLexicographicalRank(String s) {
		if (s == null) {
			throw new NullPointerException("Can't find rank for null string.");
		}

		if (s.length() == 0) {
			throw new IllegalArgumentException(
					"Can't find rank for empty string.");
		}

		int rank = 0;
		int exponent = s.length() - 1;
		for (int index = 0; index < s.length(); index++) {
			rank += new Double(Math.pow(26, exponent)).intValue()
					* (getRank(s.charAt(index)));
			exponent--;
		}

		return rank;
	}

	private static int getRank(char c) {
		if (c < 'a' || c > 'z') {
			throw new IllegalArgumentException(String.format(
					"Character is not in range: %c to %c", 'a', 'z'));
		}

		return c - 'a' + 1;
	}
}
