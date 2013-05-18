package concurrency;

/**
 * Defines a common interface for different types of read writer locks.
 */
public interface ReadWriteLock {
	void readLock();

	void readUnlock();

	void writeLock();

	void writeUnlock();
}
