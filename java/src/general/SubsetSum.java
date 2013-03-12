package general;

import java.util.ArrayList;
import java.util.List;

import common.ArrayUtils;

/**
 * Find a subset of elements that are selected from a given set whose sum adds
 * up to a given number K. Assume that the set contains non-negative, unique
 * values.
 */
public class SubsetSum {
	public static List<Integer> findSubsetSum(int[] numbers, int sum) {
		ArrayList<Integer> subset = new ArrayList<Integer>();
		for (int i = 0; i < numbers.length; i++) {
			if (findSubsetSum(numbers, i, subset, sum)) {
				return subset;
			}
		}

		return null;
	}

	private static boolean findSubsetSum(int[] numbers, int index,
			ArrayList<Integer> subset, int sum) {
		if (index >= numbers.length) {
			return false;
		}

		if (sum - numbers[index] == 0) {
			subset.add(numbers[index]);
			return true;
		}

		if (sum - numbers[index] < 0) {
			return false;
		}

		sum -= numbers[index];
		for (int i = index + 1; i < numbers.length; i++) {
			if (findSubsetSum(numbers, i, subset, sum)) {
				subset.add(numbers[index]);
				return true;
			}
		}

		return false;
	}

	public static void main(String[] args) {
		int values[] = { 10, 7, 5, 18, 12, 20, 15 };
		System.out.println(ArrayUtils.arrayToString(values));
		System.out.println(findSubsetSum(values, 47));
	}
}
