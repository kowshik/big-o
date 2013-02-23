package general;

/**
 * Methods to serialize/deserialize an array of Strings.
 * 
 * i.e. Assume: String[] strings = {"abcd", "efgh"};
 * 
 * Then, the following always returns true as the serialize and deserialize
 * methods are consistent:
 * 
 * java.util.Arrays.equals(deserializeStrings(serializeStrings(strings)),
 * strings);
 */
public class StringSerialization {
	/**
	 * Serializes an array of strings into bytes.
	 * 
	 * @param strings
	 *            array of strings to be deserialized
	 * @return array of bytes representing the string
	 */
	public static byte[] serializeStrings(String[] strings) {
		if (strings == null) {
			throw new NullPointerException("strings can't be null.");
		}

		int length = (strings.length + 1) * 4;
		for (String s : strings) {
			if (s != null) {
				length += s.length() * 2;
			}
		}

		byte[] serialized = new byte[length];
		serializeInt(serialized, 0, strings.length);
		int nextByte = 4;
		for (int offset = 0; offset < strings.length; offset++) {
			if (strings[offset] == null) {
				serializeInt(serialized, nextByte, -1);
				nextByte += 4;
			} else {
				serializeInt(serialized, nextByte, strings[offset].length());
				nextByte += 4;
				serializeString(serialized, nextByte, strings[offset]);
				nextByte += strings[offset].length() * 2;
			}
		}

		return serialized;
	}

	/**
	 * Deserializes an array of strings from bytes. NOTE: The array of strings
	 * should have been serialized using: {@link #serialize(String[])}.
	 * 
	 * @param bytes
	 *            array of bytes to be deserialized
	 * @return deserialized array of strings
	 */
	public static String[] deserializeStrings(byte[] serialized) {
		int length = deserializeInt(serialized, 0);
		String[] strings = new String[length];
		int nextByte = 4;
		for (int count = 0; count < length; count++) {
			int strLength = deserializeInt(serialized, nextByte);
			nextByte += 4;
			if (strLength == -1) {
				strings[count] = null;
			} else {
				strings[count] = deserializeString(serialized, nextByte,
						strLength);
				nextByte += strLength * 2;
			}
		}

		return strings;
	}

	public static void serializeInt(byte[] serialized, int offset, int number) {
		if (offset > serialized.length) {
			throw new AssertionError(String.format(
					"offset: %d can't be greater than length of array: %d",
					offset, serialized.length));
		}

		for (int count = 0; count < 4; count++) {
			serialized[offset + count] = (byte) (number & 0xff);
			number >>>= 8;
		}
	}

	public static int deserializeInt(byte[] serialized, int offset) {
		if (offset > serialized.length) {
			throw new AssertionError(String.format(
					"offset: %d can't be greater than length of array: %d",
					offset, serialized.length));
		}

		int number = 0;
		for (int count = 0; count < 4; count++) {
			number |= ((serialized[offset + count] & 0xff) << (count * 8));
		}

		return number;
	}

	public static void serializeString(byte[] serialized, int offset, String str) {
		for (int index = 0; index < str.length(); index++) {
			serializeChar(serialized, offset, str.charAt(index));
			offset += 2;
		}
	}

	public static String deserializeString(byte[] serialized, int offset,
			int length) {
		char[] str = new char[length];
		for (int index = 0; index < length; index++) {
			str[index] = deserializeChar(serialized, offset);
			offset += 2;
		}

		return new String(str);
	}

	public static void serializeChar(byte[] serialized, int offset, char ch) {
		serialized[offset] = (byte) (ch & 0xff);
		ch >>>= 8;
		serialized[offset + 1] = (byte) (ch & 0xff);
	}

	public static char deserializeChar(byte[] serialized, int offset) {
		char ch = 0;
		ch |= (serialized[offset] & 0xff);
		ch |= (serialized[offset + 1] & 0xff) << 8;
		return ch;
	}
}
