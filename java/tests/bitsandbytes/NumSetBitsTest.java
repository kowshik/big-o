package bitsandbytes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NumSetBitsTest {
	@Test
	public void getNumSetBits_TestAllBytes() {
		for (int number = 0; number <= 255; number++) {
			int expected = 0;
			for (char c : Integer.toBinaryString(number).toCharArray()) {
				if (c == '1') {
					expected++;
				}
			}

			assertEquals(expected,
					NumSetBits.getNumSetBits((byte) number, 0, 7));
		}
	}

	@Test
	public void getNumSetBits_TestOffsetChecking() {
		assertEquals(8, NumSetBits.getNumSetBits((byte) 255, 0, 7));
		assertEquals(6, NumSetBits.getNumSetBits((byte) 255, 1, 6));
		assertEquals(4, NumSetBits.getNumSetBits((byte) 255, 2, 5));
		assertEquals(2, NumSetBits.getNumSetBits((byte) 255, 3, 4));
		assertEquals(1, NumSetBits.getNumSetBits((byte) 255, 4, 4));
	}

	@Test
	public void getNumSetBits_ByteArray() {
		byte[] array = new byte[] { (byte) 100, (byte) 255, (byte) 255,
				(byte) 175 };
		assertEquals(25, NumSetBits.getNumSetBitsBetweenWholeBytes(array, 0, 3));
		assertEquals(16, NumSetBits.getNumSetBitsBetweenWholeBytes(array, 1, 2));
	}

	@Test
	public void getNumSetBits_ByteArrayWithOffsetAndLength() {
		byte[] array = new byte[] { (byte) 100, (byte) 255, (byte) 255,
				(byte) 175 };
		assertEquals(1, NumSetBits.getNumSetBits(array, 3, 3));
		assertEquals(20, NumSetBits.getNumSetBits(array, 3, 26));
	}
}
