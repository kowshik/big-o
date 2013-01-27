package collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class PeekIteratorTest {

	private final List<Integer> emptyList = new ArrayList<Integer>();
	private final List<Integer> listWithElements = new ArrayList<Integer>();

	@Before
	public void setup() {
		emptyList.clear();

		listWithElements.clear();
		listWithElements.add(1);
		listWithElements.add(2);
		listWithElements.add(3);
	}

	@Test
	public void next_ThrowsExceptionWithEmptyList() {
		PeekIterator<Integer> tested = new PeekIterator<Integer>(
				emptyList.iterator());

		boolean thrown = false;

		try {
			tested.next();
		} catch (NoSuchElementException e) {
			thrown = true;
		}

		assertTrue(String.format("Exception: %s not thrown",
				NoSuchElementException.class.getCanonicalName()), thrown);
	}

	@Test
	public void next_ThrowsExceptionWhenNoElementsAreLeft() {
		PeekIterator<Integer> tested = new PeekIterator<Integer>(
				listWithElements.iterator());

		boolean thrown = false;

		tested.next();
		tested.next();
		tested.next();

		try {
			tested.next();
		} catch (NoSuchElementException e) {
			thrown = true;
		}

		assertTrue(String.format("Exception: %s not thrown",
				NoSuchElementException.class.getCanonicalName()), thrown);
	}

	@Test
	public void next_ReturnsNextElementsInOrder() {
		PeekIterator<Integer> tested = new PeekIterator<Integer>(
				listWithElements.iterator());

		assertEquals(listWithElements.get(0), tested.next());
		assertEquals(listWithElements.get(1), tested.next());
		assertEquals(listWithElements.get(2), tested.next());
	}

	@Test
	public void peek_ThrowsExceptionWithEmptyList() {
		PeekIterator<Integer> tested = new PeekIterator<Integer>(
				emptyList.iterator());

		boolean thrown = false;

		try {
			tested.peek();
		} catch (NoSuchElementException e) {
			thrown = true;
		}

		assertTrue(String.format("Exception: %s not thrown",
				NoSuchElementException.class.getCanonicalName()), thrown);
	}

	@Test
	public void peek_ThrowsExceptionWhenNoElementsAreLeft() {
		PeekIterator<Integer> tested = new PeekIterator<Integer>(
				listWithElements.iterator());

		boolean thrown = false;

		tested.next();
		tested.next();
		tested.next();

		try {
			tested.peek();
		} catch (NoSuchElementException e) {
			thrown = true;
		}

		assertTrue(String.format("Exception: %s not thrown",
				NoSuchElementException.class.getCanonicalName()), thrown);
	}

	@Test
	public void peek_ReturnsPeekElementsInOrder() {
		PeekIterator<Integer> tested = new PeekIterator<Integer>(
				listWithElements.iterator());

		assertEquals(listWithElements.get(0), tested.peek());
		tested.next();

		assertEquals(listWithElements.get(1), tested.peek());
		tested.next();

		assertEquals(listWithElements.get(2), tested.peek());
		tested.next();
	}

	@Test
	public void hasNext_ReturnsFalseWithEmptyList() {
		PeekIterator<Integer> tested = new PeekIterator<Integer>(
				emptyList.iterator());

		assertFalse(tested.hasNext());
	}

	@Test
	public void hasNext_ReturnsFalseWhenNoElementsAreLeft() {
		PeekIterator<Integer> tested = new PeekIterator<Integer>(
				listWithElements.iterator());

		tested.next();
		tested.next();
		tested.next();

		assertFalse(tested.hasNext());
	}

	@Test
	public void hasNext_ReturnsTrueWhenElementsAreLeft() {
		assertTrue(new PeekIterator<Integer>(listWithElements.iterator())
				.hasNext());
	}
}
