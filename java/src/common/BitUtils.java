package common;

/**
 * Common methods used for manipulating bits.
 */
public class BitUtils {
	// Serializes an integer into a byte array.
	public static byte[] serialize(int number) {
		byte[] serialized = new byte[4];

		for (int count = 0; count < 4; count++) {
			serialized[count] = (byte) (number & 0xff);
			number >>>= 8;
		}

		return serialized;
	}

	// Deserializes an integer from a byte array generated using
	// the method above.
	public static int deserialize(byte[] serialized) {
		int number = 0;
		for (int count = 0; count < 4; count++) {
			number |= ((serialized[count] & 0xff) << (count * 8));
		}

		return number;
	}
}
