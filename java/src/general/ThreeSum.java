package general;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an array of integers, find if any three numbers sum to a given value.
 */
public class ThreeSum {

	public static boolean threeSum(int[] array, int sum) {
		for (int index = 0; index < array.length; index++) {
			if (twoSum(array, sum - array[index], index)) {
				return true;
			}
		}

		return false;
	}

	private static boolean twoSum(int[] array, int sum, int excludeIndex) {
		Set<Integer> set = new HashSet<Integer>();

		for (int index = 0; index < array.length; index++) {
			if (index != excludeIndex) {
				if (set.contains(sum - array[index])) {
					return true;
				}

				set.add(array[index]);
			}
		}

		return false;
	}
}
