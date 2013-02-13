package general;

public class RegexMatching {

	public static boolean matchRegex(String str, String regex) {
		if (str == null || regex == null) {
			return false;
		}

		return matchRegex(str, 0, regex, 0);
	}

	private static boolean matchRegex(String str, int strIndex, String regex,
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
				if (matchRegex(str, strIndex, regex, regexIndex + 2)) {
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
						if (matchRegex(str, wildCardMatcher + 1, regex,
								regexIndex + 2)) {
							return true;
						}
					}
				}

				// For 1,2,...,N possible occurrences of the current regex
				// character, we recursively check if there is a regex match.
				for (int wildCardMatcher = strIndex; wildCardMatcher < str
						.length() && str.charAt(wildCardMatcher) == regexChar; wildCardMatcher++) {
					if (matchRegex(str, wildCardMatcher + 1, regex,
							regexIndex + 2)) {
						return true;
					}
				}

				return false;
			}
		}

		if (regexChar == '*') {
			throw new RuntimeException("Invalid regex input.");
		}

		// We need a concrete match for a single character now before we
		// recursively check the rest. So if there are no more characters left,
		// we bail out.
		if (strIndex >= str.length()) {
			return false;
		}

		if (regexChar == '.') {
			return matchRegex(str, strIndex + 1, regex, regexIndex + 1);
		}

		if (regexChar == str.charAt(strIndex)) {
			return matchRegex(str, strIndex + 1, regex, regexIndex + 1);
		}

		return false;
	}
}
