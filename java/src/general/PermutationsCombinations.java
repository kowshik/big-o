package general;

import common.ArrayUtils;

/**
 * Print all permutations/combinations of a given array.
 */
public class PermutationsCombinations {
	public static void printPermutations(char[] array) {
		if (array == null) {
			throw new NullPointerException("Array passed cannot be null.");
		}

		printPermutations(array, 0);
	}

	private static void printPermutations(char[] array, int index) {
		if (index == array.length) {
			System.out.println(ArrayUtils.arrayToString(ArrayUtils
					.autoBox(array)));
		}

		for (int i = index; i < array.length; i++) {
			ArrayUtils.swap(array, index, i);
			printPermutations(array, index + 1);
			ArrayUtils.swap(array, index, i);
		}
	}

	public static void printCombinations(char[] array, int count) {
		if (array == null) {
			throw new NullPointerException("Array passed cannot be null.");
		}

		if (array.length == 0) {
			throw new IllegalArgumentException("Array is empty.");
		}

		if (count <= 0 || count > array.length) {
			throw new IllegalArgumentException(
					String.format(
							"count should be in the range: %d to %d (inclusive). You passed: %d",
							1, array.length, count));
		}
		printCombinations(array, 0, count, new char[count], 0);
	}

	private static void printCombinations(char[] array, int arrayIndex,
			int count, char[] combination, int combinationIndex) {
		if (count == 0) {
			System.out.println(ArrayUtils.arrayToString(ArrayUtils
					.autoBox(combination)));
			return;
		}

		if (arrayIndex + count > array.length) {
			return;
		}

		for (int index = arrayIndex; index < array.length; index++) {
			combination[combinationIndex] = array[index];
			printCombinations(array, index + 1, count - 1, combination,
					combinationIndex + 1);
		}
	}
}
