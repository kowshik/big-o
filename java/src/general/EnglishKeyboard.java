package general;

/**
 * You are given  an imaginary onscreen keyboard containing the English
 * alphabets, 'a' through 'z' (lowercase), and an imaginary with the letters
 * laid out in a specified number of columns.
 * 
 * For example, if number of cols = 6, then the onscreen keyboard will be:
 * 
 * a b c d e f
 * g h i j k l
 * m n o p q r
 * s t u v w x
 * y z
 * 
 * You are using a remote control - (up - 'u', down 'd', left 'l', right 'r' and
 * enter '!') to navigate the keyboard, and you start at position (0, 0).
 * 
 * Write a function that given a word will produce the sequence
 * of key presses required to type out the word on the onscreen keyboard.
 * The function should return the sequence string.
 * 
 * For example:
 * 
 * getSequence(6, "google") should return "d!drr!!ull!rrrrr!ul!"
 * getSequence(1, "google") should return "dddddd!dddddddd!!uuuuuuuu!ddddd!uuuuuuu!"
 */
public class EnglishKeyboard {
	public static String getSequence(int cols, String word) {
		if (cols <= 0) {
			throw new IllegalArgumentException(String.format(
					"cols should be > 0. You passed: %d", cols));
		}

		if (word == null) {
			throw new NullPointerException("word cannot be null");
		}

		StringBuffer buffer = new StringBuffer();
		int prevRow = 0;
		int prevCol = 0;
		for (int index = 0; index < word.length(); index++) {
			int row = findRow(word.charAt(index), cols);
			int col = findCol(word.charAt(index), cols);

			move(prevRow, row, buffer, 'd');
			move(row, prevRow, buffer, 'u');
			move(prevCol, col, buffer, 'r');
			move(col, prevCol, buffer, 'l');

			buffer.append("!");

			prevRow = row;
			prevCol = col;
		}

		return buffer.toString();
	}

	private static void move(int start, int end, StringBuffer buffer,
			char appendChar) {
		while (start < end) {
			buffer.append(appendChar);
			start++;
		}
	}

	private static int findRow(char ch, int cols) {
		return (ch - 'a') / cols;
	}

	private static int findCol(char ch, int cols) {
		return (ch - 'a') % cols;
	}

	public static void main(String[] args) {
		System.out.println(getSequence(6, "google"));
		System.out.println(getSequence(1, "google"));
		System.out.println(getSequence(2, "google"));
		System.out.println(getSequence(10, "google"));
	}
}
