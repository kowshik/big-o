package general;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an array of integers, find if the difference of any two numbers equals
 * the given value.
 */
public class TwoDifference {
	public static boolean twoDifference(int[] array, int difference) {
		Set<Integer> set = new HashSet<Integer>();
		for (int value : array) {
			if (set.contains(value + difference)
					|| set.contains(value - difference)) {
				return true;
			}

			set.add(value);
		}

		return false;
	}
}
