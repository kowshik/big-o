package common;

import java.util.Comparator;

/**
 * Common methods required for manipulating arrays.
 */
public class ArrayUtils {
	// Converts an integer array into Integer array.
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

	// Converts a character array into Character array.
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

	// Swaps elements of a character array.
	public static void swap(char[] array, int fooIndex, int barIndex) {
		char temp = array[fooIndex];
		array[fooIndex] = array[barIndex];
		array[barIndex] = temp;
	}

	// Swaps elements of an integer array.
	public static void swap(int[] array, int fooIndex, int barIndex) {
		int temp = array[fooIndex];
		array[fooIndex] = array[barIndex];
		array[barIndex] = temp;
	}

	// Swaps elements of a long array.
	public static void swap(long[] array, int fooIndex, int barIndex) {
		long temp = array[fooIndex];
		array[fooIndex] = array[barIndex];
		array[barIndex] = temp;
	}

	// Swaps elements of an array.
	public static <T> void swap(T[] array, int fooIndex, int barIndex) {
		T temp = array[fooIndex];
		array[fooIndex] = array[barIndex];
		array[barIndex] = temp;
	}

	// Searches the given integer array for the occurrence of the value.
	// If the value exists in the array, returns first index of occurrence of
	// the value. Otherwise, returns -1.
	public static int findIndex(int[] array, int value) {
		for (int index = 0; index < array.length; index++) {
			if (array[index] == value) {
				return index;
			}
		}

		return -1;
	}

	// Searches the given array for the occurrence of the value using the
	// comparator passed. If the value exists in the array, returns first index
	// of occurrence of the value. Otherwise, returns -1.
	public static <T> int findIndex(T[] array, T value, Comparator<T> comparator) {
		for (int index = 0; index < array.length; index++) {
			if (comparator.compare(array[index], value) == 0) {
				return index;
			}
		}

		return -1;
	}

	// Swaps the value in table[a][b] with the value in table[c][d].
	public static void swap(Integer[][] table, int a, int b, int c, int d) {
		Integer temp = table[a][b];
		table[a][b] = table[c][d];
		table[c][d] = temp;
	}
}
