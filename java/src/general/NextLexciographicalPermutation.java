package general;

import common.ArrayUtils;

/**
 * Given a string 'S', generate the next lexicographical permutation of the same
 * size as string 'S'. If no such permutation exists, return null. Assume that
 * each permutation (including the input) has unique characters, and the
 * charater set is lowercase english alphabets ('a' to 'z').
 */
public class NextLexciographicalPermutation {

	public static String nextLexciographicalPermutation(String s) {
		if (s == null) {
			return null;
		}

		char[] array = s.toCharArray();

		int swapIndex = s.length() - 2;
		while (swapIndex >= 0
				&& getRank(array[swapIndex]) > getRank(array[swapIndex + 1])) {
			swapIndex--;
		}

		if (swapIndex < 0) {
			return null; // next permutation does not exist.
		}

		int nextCharIndex = array.length - 1;
		while (array[nextCharIndex] < getRank(array[swapIndex])) {
			nextCharIndex--;
		}

		ArrayUtils.swap(array, swapIndex, nextCharIndex);
		reverse(array, swapIndex + 1, array.length - 1);
		return new String(array);
	}

	private static void reverse(char[] array, int start, int end) {
		for (int left = start, right = end; start > end; start++, end--) {
			ArrayUtils.swap(array, left, right);
		}
	}

	private static int getRank(char c) {
		return c - 'a';
	}
}
