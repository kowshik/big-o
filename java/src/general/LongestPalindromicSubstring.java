package general;

import common.Pair;

/**
 * Given a string S, find the longest palindromic substring in S.
 */
public class LongestPalindromicSubstring {
	public static String getLongestPalindromicSubstring(String s) {
		if (s == null || s.isEmpty()) {
			return s;
		}

		Pair<Integer, Integer> maxPalindrome = new Pair<Integer, Integer>();
		Pair<Integer, Integer> current = new Pair<Integer, Integer>();
		for (int index = 0; index < s.length(); index++) {
			current.setFirst(index - 1);
			current.setSecond(index + 1);
			getLongestPalindromicSubstring(s, current, maxPalindrome);

			current.setFirst(index);
			current.setSecond(index + 1);
			getLongestPalindromicSubstring(s, current, maxPalindrome);
		}

		return s.substring(maxPalindrome.getFirst(),
				maxPalindrome.getSecond() + 1);
	}

	private static void getLongestPalindromicSubstring(String s,
			Pair<Integer, Integer> current, Pair<Integer, Integer> maxPalindrome) {
		int startIndex = current.getFirst(), endIndex = current.getSecond();
		while (startIndex >= 0 && endIndex <= s.length()
				&& s.charAt(startIndex) == s.charAt(endIndex)) {
			startIndex--;
			endIndex++;
		}

		current.setFirst(startIndex);
		current.setSecond(endIndex);
		if (exceeded(current, maxPalindrome)) {
			maxPalindrome.setFirst(current.getFirst());
			maxPalindrome.setSecond(current.getSecond());
		}
	}

	private static boolean exceeded(Pair<Integer, Integer> current,
			Pair<Integer, Integer> maxPalindrome) {
		int startIndex = current.getFirst(), endIndex = current.getSecond();
		int maxStartIndex = maxPalindrome.getFirst(), maxEndIndex = maxPalindrome
				.getSecond();

		if (maxStartIndex == -1
				|| (endIndex - startIndex + 1) > (maxEndIndex - maxStartIndex + 1)) {
			return true;
		}

		return false;
	}
}
