package strings;

/**
 * Reverse the sentence, and reverse each word in it.
 */
public class SentenceReverse {

	private static void reverse(char[] array, int startIndex, int endIndex) {
		if (array == null) {
			return;
		}

		if (startIndex > endIndex) {
			throw new IllegalArgumentException(String.format(
					"invalid startIndex: %d and endIndex: %d", startIndex,
					endIndex));
		}

		if (startIndex < 0) {
			throw new IllegalArgumentException(String.format(
					"invalid startIndex: %d", startIndex));
		}

		if (endIndex >= array.length) {
			throw new IllegalArgumentException(String.format(
					"invalid endIndex: %d", endIndex));
		}

		for (int left = startIndex, right = endIndex; left < right; left++, right--) {
			char temp = array[left];
			array[left] = array[right];
			array[right] = temp;
		}
	}

	private static void reverse(char[] array) {
		if (array == null || array.length == 0) {
			return;
		}

		reverse(array, 0, array.length - 1);
	}

	public static String reverseSentence(String sentence) {
		if (sentence == null) {
			return null;
		}

		char[] reversed = sentence.toCharArray();
		reverse(reversed);

		int startIndex, index = 0;
		while (index < reversed.length) {
			startIndex = index;
			while (index < reversed.length && reversed[index] != ' ') {
				index++;
			}

			if (startIndex < index) {
				reverse(reversed, startIndex, index - 1);
			}

			while (index < reversed.length && reversed[index] == ' ') {
				index++;
			}
		}

		return new String(reversed);
	}
}