package bitsandbytes;

/**
 * You have a series of LEDs whose state is represented as a series of bits -- 1
 * represents ON and 0 represents off. You are also given an offset and a
 * length. You need to find the number of LED bulbs present in ON state,
 * starting at the specified offset in the series of bits, until the specified
 * length from the offset. Write the method signature and code in any language.
 * (There are a number of tricky cases in low-level bits/bytes manipulation that
 * I realized only when I started implementing the solution).
 * 
 * @author kprakasam
 * 
 */
public class NumSetBits {
	public static long getNumSetBits(byte[] leds, long offset, long length) {
		// s/offset/firstBitIndex/ for readability.
		long firstBitIndex = offset;
		long lastBitIndex = firstBitIndex + length - 1;
		byte firstByteIndex = (byte) (firstBitIndex / 8);
		byte lastByteIndex = (byte) ((firstBitIndex + length - 1) / 8);

		long totalSetBits = 0L;
		if (firstByteIndex != lastByteIndex) {
			totalSetBits += getNumSetBitsBetweenWholeBytes(leds,
					firstByteIndex + 1, lastByteIndex - 1);
			totalSetBits += getNumSetBits(leds[firstByteIndex],
					(int) (firstBitIndex % 8), 7);
			totalSetBits += getNumSetBits(leds[lastByteIndex], 0,
					(int) (lastBitIndex % 8));
		} else {
			totalSetBits += getNumSetBits(leds[firstByteIndex],
					(int) (firstBitIndex % 8), (int) (lastBitIndex % 8));
		}

		return totalSetBits;
	}

	public static int getNumSetBits(byte b, int startOffset, int endOffset) {
		if ((startOffset < 0 || startOffset > 7)
				|| (endOffset < 0 || endOffset > 7) || endOffset < startOffset) {
			throw new IllegalArgumentException(String.format(
					"Either startOffset: %d or endOffset: %d is incorrect",
					startOffset, endOffset));
		}

		int totalBits = 0;
		for (int bitIndex = startOffset; bitIndex <= endOffset; bitIndex++) {
			totalBits += (byte) (b & (1 << (8 - bitIndex - 1))) != 0 ? 1 : 0;
		}

		return totalBits;
	}

	public static long getNumSetBitsBetweenWholeBytes(byte[] array,
			int firstByte, int lastByte) {
		long totalBits = 0;
		for (int index = firstByte; index <= lastByte; index++) {
			totalBits += getNumSetBits(array[index], 0, 7);
		}

		return totalBits;
	}
}
