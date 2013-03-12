package common;

import java.util.Comparator;

public class ArrayUtils {
	public static Integer[] autoBox(int[] array) {
		if (array == null) {
			return null;
		}

		Integer[] converted = new Integer[array.length];
		for (int index = 0; index < array.length; index++) {
			converted[index] = array[index];
		}

		return converted;
	}

	public static Character[] autoBox(char[] array) {
		if (array == null) {
			return null;
		}

		Character[] converted = new Character[array.length];
		for (int index = 0; index < array.length; index++) {
			converted[index] = array[index];
		}

		return converted;
	}

	public static <T> String arrayToString(T[] array) {
		if (array == null) {
			return null;
		}

		if (array.length == 0) {
			return "[]";
		}

		StringBuffer buffer = new StringBuffer();
		int index = 0;
		buffer.append("[");
		buffer.append(array[index] == null ? "null" : array[index].toString());
		index++;
		for (; index < array.length; index++) {
			buffer.append(", ");
			buffer.append(array[index] == null ? "null" : array[index]
					.toString());
		}
		buffer.append("]");

		return buffer.toString();
	}

	public static <T> String arrayToString(int[] array) {
		return arrayToString(autoBox(array));
	}

	public static void swap(char[] array, int fooIndex, int barIndex) {
		char temp = array[fooIndex];
		array[fooIndex] = array[barIndex];
		array[barIndex] = temp;
	}

	public static void swap(int[] array, int fooIndex, int barIndex) {
		int temp = array[fooIndex];
		array[fooIndex] = array[barIndex];
		array[barIndex] = temp;
	}

	public static void swap(long[] array, int fooIndex, int barIndex) {
		long temp = array[fooIndex];
		array[fooIndex] = array[barIndex];
		array[barIndex] = temp;
	}

	public static <T> void swap(T[] array, int fooIndex, int barIndex) {
		T temp = array[fooIndex];
		array[fooIndex] = array[barIndex];
		array[barIndex] = temp;
	}

	public static int findIndex(int[] array, int value) {
		for (int index = 0; index < array.length; index++) {
			if (array[index] == value) {
				return index;
			}
		}

		return -1;
	}

	public static <T> int findIndex(T[] array, T value, Comparator<T> comparator) {
		for (int index = 0; index < array.length; index++) {
			if (comparator.compare(array[index], value) == 0) {
				return index;
			}
		}

		return -1;
	}

	public static void swap(Integer[][] table, int a, int b, int c, int d) {
		Integer temp = table[a][b];
		table[a][b] = table[c][d];
		table[c][d] = temp;
	}
}
