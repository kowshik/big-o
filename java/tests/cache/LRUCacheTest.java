package cache;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

public class LRUCacheTest {

	private LRUCache<Integer, Integer> tested;

	@Before
	public void setup() {
		tested = new LRUCache<Integer, Integer>();
	}

	@Test
	public void get_emptyCache() {
		assertNull(tested.get(1));
	}

	@Test
	public void get_doesNotEvictWhenCapacityIsNotExceeded() {
		tested.setCapacity(5);

		tested.put(1, 1);
		tested.put(2, 2);
		tested.put(3, 3);
		tested.put(4, 4);
		tested.put(5, 5);

		assertEquals(1, tested.get(1).intValue());
		assertEquals(2, tested.get(2).intValue());
		assertEquals(3, tested.get(3).intValue());
		assertEquals(4, tested.get(4).intValue());
		assertEquals(5, tested.get(5).intValue());
	}

	@Test
	public void get_evictsLruWhenCapacityIsExceeded() {
		tested.setCapacity(5);

		tested.put(1, 1);
		tested.put(2, 2);
		tested.put(3, 3);
		tested.put(4, 4);
		tested.put(5, 5);
		tested.put(6, 6);

		assertNull(tested.get(1));

		assertEquals(6, tested.get(6).intValue());
		assertEquals(5, tested.get(5).intValue());
		assertEquals(4, tested.get(4).intValue());
		assertEquals(3, tested.get(3).intValue());
		assertEquals(2, tested.get(2).intValue());

		tested.put(7, 7);

		assertNull(tested.get(6));

		assertEquals(2, tested.get(2).intValue());
		assertEquals(3, tested.get(3).intValue());
		assertEquals(4, tested.get(4).intValue());
		assertEquals(5, tested.get(5).intValue());
		assertEquals(7, tested.get(7).intValue());
	}

	@Test
	public void get_doesNotEvictWhenKeyIsReplaced() {
		tested.setCapacity(5);

		tested.put(1, 1);
		tested.put(2, 2);
		tested.put(3, 3);
		tested.put(4, 4);
		tested.put(5, 5);

		tested.put(1, 1);

		assertEquals(1, tested.get(1).intValue());
		assertEquals(2, tested.get(2).intValue());
		assertEquals(3, tested.get(3).intValue());
		assertEquals(4, tested.get(4).intValue());
		assertEquals(5, tested.get(5).intValue());
	}
}