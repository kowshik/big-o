package general;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * You have a sentence with several words with spaces removed and words having
 * their character order shuffled. You have a dictionary. Write a function to
 * produce the sentence back with spaces at the right locations.
 * 
 * Example:
 * 
 * If: dictionary = ['fox', 'quick', 'the', 'brown'], input =
 * "ehtkuqciborwnxof", then the expected sentence is: "the quick brown fox".
 */
public class RetrieveSentenceWithShuffledWords {

	public static String retrieveSentence(String sentence,
			Map<String, String> dictionary) {
		if (sentence == null || dictionary == null) {
			throw new NullPointerException(
					"Sentence/dictionary cannot be null.");
		}

		return findSentence(sentence, dictionary);
	}

	private static String findSentence(String sentence,
			Map<String, String> dictionary) {
		char[] dp = new char[sentence.length()];
		Arrays.fill(dp, '?');
		findSentence(sentence, dictionary, 0, dp);
		if (dp[0] != 'Y') {
			// no solution
			return "";
		}

		StringBuffer buffer = new StringBuffer();
		int startIndex = 0;
		for (int i = 1; i <= dp.length; i++) {
			if (i == dp.length || dp[i] == 'Y') {
				String dictWord = getDictionaryWord(sentence, startIndex, i,
						dictionary);
				if (dictWord != null) {
					buffer.append(dictWord);
				} else {
					throw new AssertionError(
							String.format(
									"Something has gone wrong. An expected word is not in the dictionary: %s",
									dictionary));
				}

				if (i != dp.length) {
					buffer.append(" ");
				}
				startIndex = i;
			}
		}

		return buffer.toString();
	}

	private static String getDictionaryWord(String sentence, int startIndex,
			int endIndex, Map<String, String> dictionary) {
		char[] word = sentence.substring(startIndex, endIndex).toCharArray();
		Arrays.sort(word);
		String wordStr = new String(word);

		return dictionary.get(wordStr);
	}

	private static boolean findSentence(String sentence,
			Map<String, String> dictionary, int index, char[] dp) {
		if (index >= sentence.length()) {
			return true;
		}

		if (dp[index] == 'Y') {
			return true;
		}

		if (dp[index] == 'N') {
			return false;
		}

		dp[index] = 'Y';
		StringBuffer buffer = new StringBuffer();
		for (int i = index; i < sentence.length(); i++) {
			buffer.append(sentence.charAt(i));
			char[] word = buffer.toString().toCharArray();
			Arrays.sort(word);
			if (dictionary.containsKey(new String(word))) {
				if (findSentence(sentence, dictionary, i + 1, dp)) {
					return true;
				}
			}
		}

		dp[index] = 'N';
		return false;
	}

	public static void main(String[] args) {
		String sentence = "akuqciborwnxof";
		Map<String, String> dictionary = new HashMap<String, String>();

		dictionary.put("fox", "fox");
		dictionary.put("a", "a");
		dictionary.put("bnorw", "brown");
		dictionary.put("cikqu", "quick");

		System.out.println(sentence);
		System.out.println(retrieveSentence(sentence, dictionary));
	}
}
