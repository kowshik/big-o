package general;

import java.util.ArrayList;
import java.util.List;

/**
 * Find common elements between two sorted integer arrays.
 */
public class FindCommonElements {
	public static List<Integer> findCommonElements(int[] a, int[] b) {
		if (a == null || b == null) {
			return null;
		}

		List<Integer> common = new ArrayList<Integer>();
		for (int aIndex = 0, bIndex = 0; aIndex < a.length && bIndex < b.length;) {
			if (a[aIndex] < b[bIndex]) {
				aIndex++;
			} else if (b[bIndex] < a[aIndex]) {
				bIndex++;
			} else {
				common.add(a[aIndex]);
				aIndex++;
				bIndex++;
			}
		}

		return common;
	}
}
