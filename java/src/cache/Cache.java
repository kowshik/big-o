package cache;

/**
 * A simple cache interface.
 * 
 * @param <CacheKeyType>
 *            Type of cache key
 * @param <CacheValueType>
 *            Type of cache value
 */
public interface Cache<CacheKeyType, CacheValueType> {
	CacheValueType get(CacheKeyType key);

	void put(CacheKeyType key, CacheValueType value);

	int getSize();
}
