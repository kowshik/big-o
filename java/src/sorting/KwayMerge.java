package sorting;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import common.Pair;

/**
 * Merge K sorted arrays into a single array of size N using a heap. Complexity
 * is O(log K) for heapification, and O(K) to build the heap. We have N total
 * elements, and therefore after building the heap initially we need to heapify
 * N times, each time popping the element from the top of the heap. Therefore,
 * the total time complexity is O(K) + O(N log K) = O(N log K).
 */
public class KwayMerge {
	public static <T> T[] merge(Class<T> type, T[][] input,
			final Comparator<T> comparator) {
		if (input == null) {
			throw new NullPointerException("input cannot be null.");
		}

		Comparator<Pair<Integer, T[]>> pairComparator = new Comparator<Pair<Integer, T[]>>() {
			@Override
			public int compare(Pair<Integer, T[]> pairA,
					Pair<Integer, T[]> pairB) {
				T foo = pairA.getSecond()[pairA.getFirst()];
				T bar = pairB.getSecond()[pairB.getFirst()];

				return comparator.compare(foo, bar);
			}
		};

		T[] merged = newArray(type, totalLength(input));
		if (merged.length == 0) {
			return merged;
		}

		PriorityQueue<Pair<Integer, T[]>> heap = new PriorityQueue<Pair<Integer, T[]>>(
				input.length, pairComparator);

		for (int index = 0; index < input.length; index++) {
			if (input[index].length > 0) {
				heap.add(new Pair<Integer, T[]>(0, input[index]));
			}
		}

		for (int mergedIndex = 0; mergedIndex < merged.length; mergedIndex++) {
			Pair<Integer, T[]> popped = heap.remove();
			T[] array = popped.getSecond();
			int index = popped.getFirst();
			T element = array[index];
			if (index + 1 < array.length) {
				popped.setFirst(index + 1);
				heap.add(popped);
			}

			merged[mergedIndex] = element;
		}

		return merged;
	}

	private static <T> T[] newArray(Class<T> type, int length) {
		@SuppressWarnings("unchecked")
		T[] toReturn = (T[]) Array.newInstance(type, length);
		return toReturn;
	}

	private static <T> int totalLength(T[][] input) {
		int totalLength = 0;
		for (T[] array : input) {
			if (array == null) {
				throw new NullPointerException(
						"nulls are not allowed in the input array.");
			}

			totalLength += array.length;
		}

		return totalLength;
	}

	public static void main(String[] args) {
		Integer[][] a = { { 1, 2, 3 }, { 4, 4 }, { 4, 7, 9 } };
		Comparator<Integer> comparator = new Comparator<Integer>() {
			@Override
			public int compare(Integer a, Integer b) {
				return a - b;
			}
		};

		System.out
				.println(Arrays.toString(merge(Integer.class, a, comparator)));
	}
}
