package sorting;

import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.PriorityQueue;

import common.Pair;

/**
 * Merge K sorted arrays into a single array of size N using a heap. Complexity
 * is O(log K) for heapification, and O(K) to build the heap. Since we have N
 * total elements, we need to heapify N times after building the heap initially.
 * Therefore, the total time complexity is O(K) + O(N log K) = O(N log K).
 */
public class KwayMerge {
	public static <T> T[] merge(Class<T> type, T[][] input,
			final Comparator<T> comparator) {
		if (input == null) {
			throw new NullPointerException("input cannot be null.");
		}

		Comparator<Pair<Integer, T>> pairComparator = new Comparator<Pair<Integer, T>>() {
			@Override
			public int compare(Pair<Integer, T> pairA, Pair<Integer, T> pairB) {
				return comparator.compare(pairA.getSecond(), pairB.getSecond());
			}
		};

		T[] merged = newArray(type, totalLength(input));
		if (merged.length == 0) {
			return merged;
		}

		PriorityQueue<Pair<Integer, T>> heap = new PriorityQueue<Pair<Integer, T>>(
				input.length, pairComparator);

		int[] indices = new int[input.length];
		for (int index = 0; index < input.length; index++) {
			T firstElement = input[index][0];
			heap.add(new Pair<Integer, T>(index, firstElement));
			indices[index] = 0;
		}

		for (int mergedIndex = 0; mergedIndex < merged.length; mergedIndex++) {
			Pair<Integer, T> popped = heap.remove();

			T element = popped.getSecond();
			int whichArray = popped.getFirst();

			indices[whichArray]++;
			int nextIndex = indices[whichArray];
			if (nextIndex < input[whichArray].length) {
				popped.setSecond(input[whichArray][nextIndex]);
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

}
