package sorting;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class QuickSortTest {

	private final Comparator<Integer> ascOrderComparator = new Comparator<Integer>() {
		@Override
		public int compare(Integer a, Integer b) {
			return a - b;
		}
	};

	private final Comparator<Integer> descOrderComparator = new Comparator<Integer>() {
		@Override
		public int compare(Integer a, Integer b) {
			return b - a;
		}
	};

	@Test
	public void ascendingOrderSort() {
		int[] array = { 5, 4, 3, 2, 1 };
		int[] sorted = MergeSort.mergeSort(array, ascOrderComparator);
		assertArrayEquals("Failure => Array returned is not sorted.", sorted,
				new int[] { 1, 2, 3, 4, 5 });
	}

	@Test
	public void descendingOrderSort() {
		int[] array = { 1, 2, 3, 4, 5 };
		int[] sorted = MergeSort.mergeSort(array, descOrderComparator);
		assertArrayEquals("Failure => Array returned is not sorted.", sorted,
				new int[] { 5, 4, 3, 2, 1 });
	}

	@Test
	public void randomOrderSort() {
		int[] array = { 5, 1, 3, 2, 4 };
		int[] sorted = MergeSort.mergeSort(array, ascOrderComparator);
		assertArrayEquals("Failure => Array returned is not sorted.", sorted,
				new int[] { 1, 2, 3, 4, 5 });
	}

	@Test
	public void onlyPivotsInArray() {
		int pivot = 3;
		Integer[] array = { pivot, pivot, pivot, pivot, pivot };
		int pivotIndex = QuickSort.partition(array, 0, array.length - 1, pivot,
				ascOrderComparator);
		assertEquals(pivotIndex, 0);
		assertArrayEquals(
				"Failure => Array returned is not partitioned according to the pivot.",
				array, new Integer[] { pivot, pivot, pivot, pivot, pivot });
	}

	@Test
	public void noPivotsInArray() {
		int pivot = 3;
		Integer[] array = { 5, 1, 2, 4, 6 };
		int pivotIndex = QuickSort.partition(array, 0, array.length - 1, pivot,
				ascOrderComparator);
		assertEquals(pivotIndex, -1);
		assertArrayEquals(
				"Failure => Array returned is not partitioned according to the pivot.",
				array, new Integer[] { 1, 2, 5, 4, 6 });
	}

	@Test
	public void smallerElementsAndPivotsInArray() {
		int pivot = 3;
		Integer[] array = { 3, 0, 2, 3, -1, -2, 0, 3 };
		int pivotIndex = QuickSort.partition(array, 0, array.length - 1, pivot,
				ascOrderComparator);
		assertEquals(pivotIndex, 5);
		assertArrayEquals(
				"Failure => Array returned is not partitioned according to the pivot.",
				array, new Integer[] { 0, 2, -1, -2, 0, 3, 3, 3 });
	}

	@Test
	public void largerElementsAndPivotsInArray() {
		int pivot = 3;
		Integer[] array = { 3, 10, 4, 3, 5, 7, 7, 3 };
		int pivotIndex = QuickSort.partition(array, 0, array.length - 1, pivot,
				ascOrderComparator);
		assertEquals(pivotIndex, 0);
		assertArrayEquals(
				"Failure => Array returned is not partitioned according to the pivot.",
				array, new Integer[] { 3, 3, 3, 10, 5, 7, 7, 4 });
	}

	public static void main(String args[]) {
		org.junit.runner.JUnitCore.main("sorting.QuickSortTest");
	}
}
