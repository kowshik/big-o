package general;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an array of integers, find if any two numbers sum to a given value.
 */
public class TwoSum {
	public static boolean twoSum(int[] array, int sum) {
		Set<Integer> set = new HashSet<Integer>();

		for (int value : array) {
			if (set.contains(sum - value)) {
				return true;
			}

			set.add(value);
		}

		return false;
	}
}
