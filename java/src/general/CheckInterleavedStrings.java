package general;

import java.util.HashSet;
import java.util.Set;

import common.Pair;

/**
 * We are given 3 strings: str1, str2, and str3. Str3 is said to be a shuffle of
 * str1 and str2 if it can be formed by interleaving the characters of str1 and
 * str2 in a way that maintains the left to right ordering of the characters
 * from each string. For example, given str1=”abc” and str2=”def”, str3=”dabecf”
 * is a valid shuffle since it preserves the character ordering of the two
 * strings. So, given these 3 strings write a function that detects whether str3
 * is a valid shuffle of str1 and str2.
 * 
 * http://www.ardendertat.com/2011/10/10/programming-interview-questions-6-combine-two-strings/
 */
public class CheckInterleavedStrings {

	public static boolean isValidInterleavedString(String foo, String bar,
			String target) {
		if (foo == null || bar == null || target == null) {
			return false;
		}

		if (target.length() != foo.length() + bar.length()) {
			return false;
		}

		return isValidInterleavedString(foo, 0, bar, 0, target, 0,
				new HashSet<Pair<Integer, Integer>>());
	}

	private static boolean isValidInterleavedString(String foo, int fooIndex,
			String bar, int barIndex, String target, int targetIndex,
			Set<Pair<Integer, Integer>> known) {
		if (known.contains(new Pair<Integer, Integer>(fooIndex, barIndex))) {
			return false;
		}

		if (targetIndex == target.length()) {
			if (fooIndex != foo.length() || barIndex != bar.length()) {
				return false;
			}

			return true;
		}

		boolean valid = false;
		char targetChar = target.charAt(targetIndex);
		boolean found = fooIndex != foo.length() ? foo.charAt(fooIndex) == targetChar
				: false;
		if (found) {
			valid = isValidInterleavedString(foo, fooIndex + 1, bar, barIndex,
					target, targetIndex + 1, known);
		}

		if (!valid) {
			found = barIndex != bar.length() ? bar.charAt(barIndex) == targetChar
					: false;
			if (found) {
				valid = isValidInterleavedString(foo, fooIndex, bar,
						barIndex + 1, target, targetIndex + 1, known);
			}
		}

		if (!valid) {
			known.add(new Pair<Integer, Integer>(fooIndex, barIndex));
		}

		return valid;
	}
}
