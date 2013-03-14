package strings;

/**
 * Write a function to match a given string to a regular expression.
 * 
 * '.' Matches any single character. '*' Matches zero or more of the preceding
 * element.
 * 
 * The matching should cover the entire input string (not partial).
 * 
 * Some examples:
 * 
 * isMatch("aa","a") → false isMatch("aa","aa") → true isMatch("aaa","aa")
 * → false isMatch("aa", "a*") → true isMatch("aa", ".*") → true
 * isMatch("ab", ".*") → true isMatch("aab", "c*a*b") → true
 */
public class RegexMatching {

	public static boolean isMatch(String str, String regex) {
		if (str == null || regex == null) {
			return false;
		}

		return isMatch(str, 0, regex, 0);
	}

	private static boolean isMatch(String str, int strIndex, String regex,
			int regexIndex) {
		if (strIndex >= str.length() && regexIndex == regex.length()) {
			return true;
		}

		if (regexIndex == regex.length()) {
			return false;
		}

		char regexChar = regex.charAt(regexIndex);

		if (regexIndex < regex.length() - 1) {
			char nextRegexChar = regex.charAt(regexIndex + 1);
			if (nextRegexChar == '*') {
				// Check if we could skip this wild card and still match the
				// regex.
				if (isMatch(str, strIndex, regex, regexIndex + 2)) {
					return true;
				}

				// We could have clubbed the two for loops below into one,
				// but having a separate loop for the '.' character prevents
				// checking of the matching condition for every iteration,
				// because '.' matches any character. For 1,2,...,N
				// possible occurrences of the '.' character, we
				// recursively check if there is a regex match.
				if (regexChar == '.') {
					for (int wildCardMatcher = strIndex; wildCardMatcher < str
							.length(); wildCardMatcher++) {
						if (isMatch(str, wildCardMatcher + 1, regex,
								regexIndex + 2)) {
							return true;
						}
					}
				}

				// For 1,2,...,N possible occurrences of the current regex
				// character, we recursively check if there is a regex match.
				for (int wildCardMatcher = strIndex; wildCardMatcher < str
						.length() && str.charAt(wildCardMatcher) == regexChar; wildCardMatcher++) {
					if (isMatch(str, wildCardMatcher + 1, regex, regexIndex + 2)) {
						return true;
					}
				}

				return false;
			}
		}

		if (regexChar == '*') {
			throw new IllegalArgumentException("Invalid regex input.");
		}

		// We need a concrete match for a single character now before we
		// recursively check the rest. So if there are no more characters left,
		// we bail out.
		if (strIndex >= str.length()) {
			return false;
		}

		if (regexChar == '.' || (regexChar == str.charAt(strIndex))) {
			return isMatch(str, strIndex + 1, regex, regexIndex + 1);
		}

		return false;
	}
}
