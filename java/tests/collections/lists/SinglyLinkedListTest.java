package collections.lists;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import collections.lists.SinglyLinkedList;

public class SinglyLinkedListTest {

	private SinglyLinkedList<Integer> tested;

	@Before
	public void setup() {
		tested = new SinglyLinkedList<Integer>();
	}

	@Test
	public void reverseEmptyList() {
		SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>();

		list.reverse();
		assertEquals(0, list.size());
	}
}
