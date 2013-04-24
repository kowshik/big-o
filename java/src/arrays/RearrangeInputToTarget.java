package arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given two integer arrays: input and target. These arrays contain the
 * same unique integers, and always the value zero in each of them. Also, the
 * target array is some permutation of the input array and both arrays are of
 * the same size.
 *
 * You are supposed to write a method to convert the input array to the target
 * array. The restriction is that you can modify the input array by repeating
 * only the following operation any number of times: Take any value in the input
 * array and swap its location with that of the value zero in the same array.
 */
public class RearrangeInputToTarget {

	public static void rearrange(int[] input, int[] target) {
		assert (input.length == target.length);

		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		buildIndex(input, map);
		int zeroIndex = common.ArrayUtils.findIndex(input, 0);
		int numMoves = 0;

		while (numMoves < input.length) {
			// zero has reached its final location. So we displace it
			// temporarily so that we can continue putting other elements in
			// their final locations.
			if (target[zeroIndex] == 0) {
				int outOfOrderIndex = findFirstOutOfOrderIndex(input, target);
				if (outOfOrderIndex == -1) {
					// All elements including '0' are in their final locations.
					return;
				}

				map.put(input[outOfOrderIndex], zeroIndex);
				// We need to update the index because we are going to modify
				// the original location of the value in input[outOfOrderIndex].
				common.ArrayUtils.swap(input, zeroIndex, outOfOrderIndex);
				zeroIndex = outOfOrderIndex;

			} else {
				// Swap zero with what should actually be in zero's location
				// in the target array.
				int nextZeroIndex = map.get(target[zeroIndex]);
				common.ArrayUtils.swap(input, zeroIndex, nextZeroIndex);
				zeroIndex = nextZeroIndex;
				numMoves++;
			}
		}

	}

	private static void buildIndex(int[] input, Map<Integer, Integer> index) {
		for (int location = 0; location < input.length; location++) {
			index.put(input[location], location);
		}
	}

	private static int findFirstOutOfOrderIndex(int[] input, int[] target) {
		assert (input.length == target.length);

		for (int index = 0; index < input.length; index++) {
			if (input[index] != target[index]) {
				return index;
			}
		}

		return -1;
	}
}
