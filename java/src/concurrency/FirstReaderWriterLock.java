package concurrency;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Solves the first reader writer problem:
 *
 * 1. If no write lock has been acquired, then any number of read locks are
 * allowed to be acquired.
 *
 * 2. If a write lock has been acquired, then read locks are not allowed to be
 * acquired until the write lock has been released.
 *
 * 3. If one or more read locks have been acquired, then a write lock is not
 * allowed to be acquired until all read locks have been released.
 *
 * 4. Only threads that previously held a read or write lock are allowed to
 * release them.
 *
 * 5. The implementation is not re-entrant.
 *
 * Note: Writers can potentially starve if there are many more readers when
 * compared to writers, but we don't address this in the solution.
 */
public class FirstReaderWriterLock {
	private final Semaphore readMutex = new Semaphore(1);
	private final Semaphore writeMutex = new Semaphore(1);
	private final Set<Long> readerIds = Collections
			.synchronizedSet(new HashSet<Long>());
	private volatile Long writerId;

	public void readLock() {
		try {
			readMutex.acquire();
			readerIds.add(Thread.currentThread().getId());
			if (readerIds.size() == 1) {
				writeMutex.acquire();
			}
		} finally {
			readMutex.release();
		}
	}

	public void readUnlock() {
		try {
			readMutex.acquire();
			long threadId = Thread.currentThread().getId();
			if (!readerIds.contains(threadId)) {
				throw new IllegalStateException(
						String.format(
								"The current thread with id: %d never acquired a read lock before.",
								threadId));
			}
			readerIds.remove(threadId);
			if (readerIds.size() == 0) {
				writeMutex.release();
			}
		} finally {
			readMutex.release();
		}

	}

	public void writeLock() {
		writeMutex.acquire();
		writerId = Thread.currentThread().getId();
	}

	public void writeUnlock() {
		Long threadId = Thread.currentThread().getId();
		if (writerId == null || !writerId.equals(threadId)) {
			throw new IllegalStateException(
					String.format(
							"The current thread with id: %d never acquired a write lock before.",
							threadId));
		}

		writeMutex.release();
	}
}
