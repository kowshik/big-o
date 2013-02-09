package general;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithNoRepetition {

	public static int lengthOfLongestSubstring(String s) {
		if (s == null || s.isEmpty()) {
			return 0;
		}
		Set<Character> seen = new HashSet<Character>();

		int windowStart = 0;
		int windowEnd = 0;
		int maxWindowStart = -1;
		int maxWindowEnd = -1;
		while (windowEnd < s.length()) {
			if (seen.contains(s.charAt(windowEnd))) {
				for (; windowStart <= windowEnd - 1
						&& s.charAt(windowStart) != s.charAt(windowEnd); windowStart++) {
					seen.remove(s.charAt(windowStart));
				}

				windowStart++;
			} else {
				seen.add(s.charAt(windowEnd));
			}

			if (exceeded(windowStart, windowEnd, maxWindowStart, maxWindowEnd)) {
				maxWindowStart = windowStart;
				maxWindowEnd = windowEnd;
			}

			windowEnd++;
		}

		return maxWindowEnd - maxWindowStart + 1;
	}

	private static boolean exceeded(int windowStart, int windowEnd,
			int maxWindowStart, int maxWindowEnd) {
		if (maxWindowStart == -1 || maxWindowEnd == -1) {
			return true;
		}

		return (windowEnd - windowStart) > (maxWindowEnd - maxWindowStart);
	}
}