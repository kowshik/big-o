package sorting;

import common.ArrayUtils;

/**
 * Sort an array containing only 0s and/or 1s and/or 2s.
 */
public class Sort012 {

	public static void sort012(int[] array) {
		if (array == null) {
			throw new NullPointerException("You passed a null array.");
		}

		if (array.length == 1) {
			return;
		}

		int twoIndex = array.length;
		while (twoIndex >= 1 && array[twoIndex - 1] == 2) {
			twoIndex--;
		}

		int zeroIndex = -1;
		for (int index = 0; index < twoIndex; index++) {
			if (array[index] == 2) {
				twoIndex--;
				ArrayUtils.swap(array, index, twoIndex);
			}

			if (array[index] == 0) {
				zeroIndex++;
				ArrayUtils.swap(array, index, zeroIndex);
			}
		}

		return;
	}
}
