package sorting;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import exceptions.EmptyHeapException;

public class SimpleHeapImplTest {

	private final List<Integer> emptyList = new ArrayList<Integer>();
	private final List<Integer> listWithElements = new ArrayList<Integer>();

	private final Comparator<Integer> minHeapComparator = new Comparator<Integer>() {
		@Override
		public int compare(Integer foo, Integer bar) {
			return bar - foo;
		}
	};

	@Before
	public void setup() {
		emptyList.clear();

		listWithElements.clear();
		listWithElements.add(1);
		listWithElements.add(3);
		listWithElements.add(4);
		listWithElements.add(2);
		listWithElements.add(0);
		listWithElements.add(5);
	}

	@Test
	public void sizeAfterInitialization() {
		assertEquals(0, new SimpleHeapImpl<Integer>(minHeapComparator).size());
		assertEquals(6, new SimpleHeapImpl<Integer>(listWithElements,
				minHeapComparator).size());
	}

	@Test
	public void sizeAfterAdd() {
		Heap<Integer> heap = new SimpleHeapImpl<Integer>(listWithElements,
				minHeapComparator);
		heap.add(6);
		assertEquals(7, heap.size());
	}

	@Test
	public void sizeAfterPop() throws EmptyHeapException {
		Heap<Integer> heap = new SimpleHeapImpl<Integer>(listWithElements,
				minHeapComparator);
		heap.pop();
		assertEquals(5, heap.size());
	}

	@Test
	public void popThrowsExceptionWhenHeapIsEmpty() {
		Heap<Integer> heap = new SimpleHeapImpl<Integer>(minHeapComparator);
		boolean thrown = false;

		try {
			heap.pop();
		} catch (EmptyHeapException e) {
			thrown = true;
		}

		assertTrue(thrown);
	}

	@Test
	public void popReturnsMinValue() throws EmptyHeapException {
		Heap<Integer> heap = new SimpleHeapImpl<Integer>(listWithElements,
				minHeapComparator);

		assertEquals(0, heap.pop().intValue());
		assertFalse(heap.isEmpty());
		assertEquals(1, heap.pop().intValue());
		assertFalse(heap.isEmpty());
		assertEquals(2, heap.pop().intValue());
		assertFalse(heap.isEmpty());
		assertEquals(3, heap.pop().intValue());
		assertFalse(heap.isEmpty());
		assertEquals(4, heap.pop().intValue());
		assertFalse(heap.isEmpty());
		assertEquals(5, heap.pop().intValue());
		assertTrue(heap.isEmpty());
	}

	@Test
	public void peekThrowsExceptionWhenHeapIsEmpty() {
		Heap<Integer> heap = new SimpleHeapImpl<Integer>(minHeapComparator);
		boolean thrown = false;

		try {
			heap.peek();
		} catch (EmptyHeapException e) {
			thrown = true;
		}

		assertTrue(thrown);
	}

	@Test
	public void peekReturnsMinValue() throws EmptyHeapException {
		Heap<Integer> heap = new SimpleHeapImpl<Integer>(listWithElements,
				minHeapComparator);

		assertEquals(0, heap.peek().intValue());
		heap.pop();
		assertFalse(heap.isEmpty());

		assertEquals(1, heap.peek().intValue());
		heap.pop();
		assertFalse(heap.isEmpty());

		assertEquals(2, heap.peek().intValue());
		heap.pop();
		assertFalse(heap.isEmpty());

		assertEquals(3, heap.peek().intValue());
		heap.pop();
		assertFalse(heap.isEmpty());

		assertEquals(4, heap.peek().intValue());
		heap.pop();
		assertFalse(heap.isEmpty());

		assertEquals(5, heap.peek().intValue());
		heap.pop();
		assertTrue(heap.isEmpty());
	}

	@Test
	public void addMinValuePreservesHeapProperty() throws EmptyHeapException {
		Heap<Integer> heap = new SimpleHeapImpl<Integer>(listWithElements,
				minHeapComparator);
		heap.add(-1);
		assertEquals(-1, heap.peek().intValue());
	}

	@Test
	public void addMaxValuePreservesHeapProperty() throws EmptyHeapException {
		Heap<Integer> heap = new SimpleHeapImpl<Integer>(listWithElements,
				minHeapComparator);
		heap.add(6);
		assertEquals(0, heap.peek().intValue());
	}

	@Test
	public void isEmptyReturnsTrueWithEmptyHeap() {
		Heap<Integer> heap = new SimpleHeapImpl<Integer>(minHeapComparator);
		assertTrue(heap.isEmpty());
	}

	@Test
	public void isEmptyReturnsFalseWithNonEmptyHeap() {
		Heap<Integer> heap = new SimpleHeapImpl<Integer>(listWithElements,
				minHeapComparator);
		assertFalse(heap.isEmpty());
	}
}
