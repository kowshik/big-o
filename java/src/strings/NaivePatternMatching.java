package strings;

/**
 * Given a text and a pattern, print all starting indices in the text from where
 * the pattern matches the text.
 * 
 * Case 1: Pattern can have repeating characters.
 * 
 * Case 2: Pattern cannot have repeating characters.
 */
public class NaivePatternMatching {
	public void printMatchStartIndices(String text, String pattern) {
		if (text == null || pattern == null) {
			throw new NullPointerException("Don't pass null(s).");
		}

		for (int textIndex = 0; textIndex < text.length(); textIndex++) {
			int patternIndex = 0;
			for (patternIndex = 0; patternIndex < pattern.length(); patternIndex++) {
				if (pattern.charAt(patternIndex) != text.charAt(textIndex
						+ patternIndex)) {
					break;
				}
			}

			if (patternIndex == pattern.length()) {
				System.out.println(String.format("Match!: %d", textIndex));
			}
		}
	}

	public static void printMatchStartIndicesForPatternWithNoRepeatingCharacters(
			String text, String pattern) {
		if (text == null || pattern == null) {
			throw new NullPointerException("Don't pass null(s).");
		}

		for (int textIndex = 0; textIndex < text.length();) {
			int patternIndex = 0;
			for (patternIndex = 0; patternIndex < pattern.length(); patternIndex++) {
				if (pattern.charAt(patternIndex) != text.charAt(textIndex
						+ patternIndex)) {
					break;
				}
			}

			if (patternIndex == pattern.length()) {
				System.out.println(String.format("Match!: %d", textIndex));
			}

			textIndex += (patternIndex == 0) ? 1 : patternIndex;
		}
	}
}
