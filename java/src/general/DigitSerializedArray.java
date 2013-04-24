package general;

/**
 * Imagine the sequence: 0123456789101112131415... etc. This is a sequence
 * starting from '0', but with no separation between the digits of subsequent
 * numbers.
 *
 * Here, the digits at indices 10 and 11 are '1' and '0' respectively, and they
 * together represent the number '10'. Similarly, the digits at indices 190,
 * 191, 192 are '1', '0' and '0' respectively, and they together represent the
 * number '100'.
 *
 * Write a function that given only such an index into this sequence, returns
 * the digit at the given index.
 *
 * Other examples:
 *
 * index = 100 => result = 5
 * index = 30 => result = 2
 * index = 31 => result = 0
 * index = 1000 => result = 3
 */
public class DigitSerializedArray {
	public static int getDigit(int index) {
		if (index < 0) {
			throw new IllegalArgumentException(String.format(
					"index should be >= 0. You passed: %d", index));
		}

		int slabStartValue = 0;
		int slabLevelCap = 10;
		int numDigitsAtThisLevel = 1;
		int slabOfNumbers = (slabLevelCap - slabStartValue)
				* numDigitsAtThisLevel;

		while (index - slabOfNumbers >= 0) {
			index -= slabOfNumbers;
			slabStartValue = slabLevelCap;
			slabLevelCap *= 10;
			numDigitsAtThisLevel++;
			slabOfNumbers = (slabLevelCap - slabStartValue)
					* numDigitsAtThisLevel;
		}

		int corneredNumber = index / numDigitsAtThisLevel + slabStartValue;
		int digitFindingIndex = index % numDigitsAtThisLevel + 1;
		while (digitFindingIndex < numDigitsAtThisLevel) {
			digitFindingIndex++;
			corneredNumber /= 10;
		}

		return corneredNumber % 10;
	}

	public static void main(String[] args) {
		String sequence = generateSequence(100000);
		for (int index = 0; index < sequence.length(); index++) {
			int expected = sequence.charAt(index) - '0';
			int got = getDigit(index);
			System.out.printf("Expected: %d, Got: %d\n", expected, got);
			if (expected != got) {
				throw new AssertionError();
			}
		}
	}

	private static String generateSequence(int limit) {
		StringBuffer buffer = new StringBuffer();
		for (int number = 0; number <= limit; number++) {
			buffer.append(number);
		}

		return buffer.toString();
	}
}
