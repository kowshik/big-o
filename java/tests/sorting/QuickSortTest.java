package sorting;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import org.junit.Test;

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
		Integer[] array = { 5, 4, 3, 2, 1 };
		Integer[] sorted = QuickSort.quickSort(array, ascOrderComparator);
		assertArrayEquals("Failure => Array returned is not sorted.", sorted,
				new Integer[] { 1, 2, 3, 4, 5 });
	}

	@Test
	public void descendingOrderSort() {
		Integer[] array = { 1, 2, 3, 4, 5 };
		Integer[] sorted = QuickSort.quickSort(array, descOrderComparator);
		assertArrayEquals("Failure => Array returned is not sorted.", sorted,
				new Integer[] { 5, 4, 3, 2, 1 });
	}

	@Test
	public void randomOrderSort() {
		Integer[] array = { 5, 1, 3, 2, 4 };
		Integer[] sorted = QuickSort.quickSort(array, ascOrderComparator);
		assertArrayEquals("Failure => Array returned is not sorted.", sorted,
				new Integer[] { 1, 2, 3, 4, 5 });
	}

	@Test
	public void onlyPivotsInArray() {
		Integer[] array = { 3, 3, 3, 3, 3 };
		int pivotIndex = QuickSort.partition(array, 0, array.length - 1,
				ascOrderComparator, 3);
		assertEquals(4, pivotIndex);
		assertArrayEquals(
				"Failure => Array returned is not partitioned according to the pivot.",
				array, new Integer[] { 3, 3, 3, 3, 3 });
	}

	@Test
	public void smallerElementsAndPivotsInArray() {
		Integer[] array = { 3, 0, 2, 3, -1, -2, 0, 3 };
		int pivotIndex = QuickSort.partition(array, 0, array.length - 1,
				ascOrderComparator, 3);
		assertEquals(7, pivotIndex);
		assertArrayEquals(
				"Failure => Array returned is not partitioned according to the pivot.",
				array, new Integer[] { 3, 0, 2, 3, -1, -2, 0, 3 });
	}

	@Test
	public void largerElementsAndPivotsInArray() {
		Integer[] array = { 3, 10, 4, 3, 5, 7, 7, 3 };
		int pivotIndex = QuickSort.partition(array, 0, array.length - 1,
				ascOrderComparator, 3);
		assertEquals(2, pivotIndex);
		assertArrayEquals(
				"Failure => Array returned is not partitioned according to the pivot.",
				array, new Integer[] { 3, 3, 3, 10, 5, 7, 7, 4 });
	}

	public static void main(String args[]) {
		org.junit.runner.JUnitCore.main("sorting.QuickSortTest");
	}
}
