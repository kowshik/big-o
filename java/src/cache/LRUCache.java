package cache;

import java.util.HashMap;
import java.util.Map;

/**
 * A bounded cache that evicts keys based on least recently used strategy.
 * 
 * @param <CacheKeyType>
 *            Type of cache key
 * @param <CacheValueType>
 *            Type of cache value
 */
public class LRUCache<CacheKeyType, CacheValueType> implements
		Cache<CacheKeyType, CacheValueType> {

	private static class CacheNode<CacheNodeKeyType, CacheNodeValueType> {
		private CacheNode<CacheNodeKeyType, CacheNodeValueType> previous;
		private CacheNode<CacheNodeKeyType, CacheNodeValueType> next;
		private CacheNodeKeyType key;
		private CacheNodeValueType value;

		public CacheNode<CacheNodeKeyType, CacheNodeValueType> getPrevious() {
			return previous;
		}

		public void setPrevious(
				CacheNode<CacheNodeKeyType, CacheNodeValueType> previous) {
			this.previous = previous;
		}

		public CacheNode<CacheNodeKeyType, CacheNodeValueType> getNext() {
			return next;
		}

		public void setNext(CacheNode<CacheNodeKeyType, CacheNodeValueType> next) {
			this.next = next;
		}

		public CacheNodeKeyType getKey() {
			return key;
		}

		public void setKey(CacheNodeKeyType key) {
			this.key = key;
		}

		public CacheNodeValueType getValue() {
			return value;
		}

		public void setValue(CacheNodeValueType value) {
			this.value = value;
		}
	}

	private int capacity;
	private final Map<CacheKeyType, CacheNode<CacheKeyType, CacheValueType>> cache = new HashMap<CacheKeyType, CacheNode<CacheKeyType, CacheValueType>>();
	private CacheNode<CacheKeyType, CacheValueType> head, tail;
	public static final int DEFAULT_CAPACITY = 100;

	public LRUCache() {
		setCapacity(DEFAULT_CAPACITY);
	}

	public LRUCache(int capacity) {
		setCapacity(capacity);
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		checkCapacity(capacity);

		for (int count = cache.size(); count > capacity; count--) {
			CacheNode<CacheKeyType, CacheValueType> evicted = evict();
			cache.remove(evicted.getKey());
		}

		this.capacity = capacity;
	}

	@Override
	public int getSize() {
		return cache.size();
	}

	private void checkCapacity(int capacity) {
		if (capacity <= 0) {
			throw new IllegalArgumentException(String.format(
					"Capacity should be positive. You passed: %d", capacity));
		}
	}

	@Override
	public CacheValueType get(CacheKeyType key) {
		if (!cache.containsKey(key)) {
			return null;
		}

		CacheNode<CacheKeyType, CacheValueType> node = cache.get(key);
		moveNodeToLast(node);

		return node.getValue();
	}

	private void moveNodeToLast(CacheNode<CacheKeyType, CacheValueType> node) {
		if (tail == node) {
			return;
		}

		CacheNode<CacheKeyType, CacheValueType> previous = node.getPrevious();
		CacheNode<CacheKeyType, CacheValueType> next = node.getNext();
		if (previous != null) {
			previous.setNext(next);
		}

		if (next != null) {
			next.setPrevious(previous);
		}

		if (head == node) {
			head = next;
		}

		tail.setNext(node);
		node.setPrevious(tail);
		node.setNext(null);
		tail = node;
	}

	@Override
	public void put(CacheKeyType key, CacheValueType value) {
		if (cache.containsKey(key)) {
			CacheNode<CacheKeyType, CacheValueType> existing = cache.get(key);
			existing.setValue(value);
			moveNodeToLast(existing);
			return;
		}

		CacheNode<CacheKeyType, CacheValueType> newNode;
		if (cache.size() == capacity) {
			newNode = evict();
			cache.remove(newNode.getKey());
		} else {
			newNode = new CacheNode<CacheKeyType, CacheValueType>();
		}

		newNode.setKey(key);
		newNode.setValue(value);
		addNewNode(newNode);

		cache.put(key, newNode);
	}

	private CacheNode<CacheKeyType, CacheValueType> evict() {
		if (head == null) {
			throw new AssertionError("Cannot evict a node from an empty cache.");
		}

		CacheNode<CacheKeyType, CacheValueType> evicted = head;
		head = evicted.getNext();
		head.setPrevious(null);
		evicted.setNext(null);

		return evicted;
	}

	private void addNewNode(CacheNode<CacheKeyType, CacheValueType> node) {
		if (cache.isEmpty()) {
			head = tail = node;
			return;
		}

		tail.setNext(node);
		node.setPrevious(tail);
		node.setNext(null);

		tail = node;
	}
}
