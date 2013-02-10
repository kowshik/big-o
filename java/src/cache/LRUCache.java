package cache;

import java.util.HashMap;
import java.util.Map;

/**
 * A bounded cache that evicts keys based on least recently used strategy.
 * 
 * @author kprakasam
 * 
 * @param <K>
 *            Type of cache key
 * @param <V>
 *            Type of cache value
 */
public class LRUCache<K, V> implements Cache<K, V> {

	private static class CacheNode<P, Q> {
		private CacheNode<P, Q> previous;
		private CacheNode<P, Q> next;
		private P key;
		private Q value;

		public CacheNode<P, Q> getPrevious() {
			return previous;
		}

		public void setPrevious(CacheNode<P, Q> previous) {
			this.previous = previous;
		}

		public CacheNode<P, Q> getNext() {
			return next;
		}

		public void setNext(CacheNode<P, Q> next) {
			this.next = next;
		}

		public P getKey() {
			return key;
		}

		public void setKey(P key) {
			this.key = key;
		}

		public Q getValue() {
			return value;
		}

		public void setValue(Q value) {
			this.value = value;
		}
	}

	private int capacity;
	private final Map<K, CacheNode<K, V>> cache = new HashMap<K, CacheNode<K, V>>();
	private CacheNode<K, V> head, tail;
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
			CacheNode<K, V> evicted = evict();
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
	public V get(K key) {
		if (!cache.containsKey(key)) {
			return null;
		}

		CacheNode<K, V> node = cache.get(key);
		moveNodeToLast(node);

		return node.getValue();
	}

	private void moveNodeToLast(CacheNode<K, V> node) {
		if (tail == node) {
			return;
		}

		CacheNode<K, V> previous = node.getPrevious();
		CacheNode<K, V> next = node.getNext();
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
	public void put(K key, V value) {
		if (cache.containsKey(key)) {
			CacheNode<K, V> existing = cache.get(key);
			existing.setValue(value);
			moveNodeToLast(existing);
			return;
		}

		CacheNode<K, V> newNode;
		if (cache.size() == capacity) {
			newNode = evict();
			cache.remove(newNode.getKey());
		} else {
			newNode = new CacheNode<K, V>();
		}

		newNode.setKey(key);
		newNode.setValue(value);
		addNewNode(newNode);

		cache.put(key, newNode);
	}

	private CacheNode<K, V> evict() {
		if (head == null) {
			throw new AssertionError("Cannot evict a node from an empty cache.");
		}

		CacheNode<K, V> evicted = head;
		head = evicted.getNext();
		head.setPrevious(null);
		evicted.setNext(null);

		return evicted;
	}

	private void addNewNode(CacheNode<K, V> node) {
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
