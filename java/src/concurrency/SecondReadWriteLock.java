package concurrency;

import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Solves the second reader writer problem:
 * 
 * 1. If the write lock has NOT been acquired, then any number of read locks are
 * allowed to be acquired.
 * 
 * 2. If the write lock has been acquired or if atleast one writer is waiting to
 * acquire the write lock, then read locks are NOT allowed to be acquired until
 * there are no waiting writers.
 * 
 * 3. If one or more read locks have been acquired, then a write lock is NOT
 * allowed to be acquired until all read locks have been released.
 * 
 * 4. Only threads that previously held a read or write lock are allowed to
 * release them.
 * 
 * 5. The implementation is not re-entrant.
 * 
 * Note: Writers canNOT starve in this implementation i.e. In a situation where
 * there are many more readers when compared to writers, a writer waiting for a
 * write lock will always get preference over a reader waiting for a read lock.
 */
public class SecondReadWriteLock implements ReadWriteLock {
	private final Semaphore readerCheckinMutex = new Semaphore(1);
	private final Semaphore readerCheckoutMutex = new Semaphore(1);
	private final Semaphore writerMutex = new Semaphore(1);
	private final Semaphore writerCheckinMutex = new Semaphore(1);

	private final Set<Long> readerIds = Collections
			.synchronizedSet(new HashSet<Long>());
	private volatile long numWriters;
	private volatile Long writerId = null;

	@Override
	public void readLock() {
		long threadId = Thread.currentThread().getId();

		readerCheckinMutex.acquire();
		readerCheckoutMutex.acquire();
		readerIds.add(threadId);
		if (readerIds.size() == 1) {
			writerMutex.acquire();
		}
		readerCheckoutMutex.release();
		readerCheckinMutex.release();

		System.out.printf("Acquired read lock for thread: %d\n", threadId);
	}

	@Override
	public void readUnlock() {
		long threadId;
		try {
			readerCheckoutMutex.acquire();
			threadId = Thread.currentThread().getId();
			if (!readerIds.contains(threadId)) {
				throw new IllegalStateException(
						String.format(
								"The current thread with id: %d never acquired a read lock before.",
								threadId));
			}
			readerIds.remove(threadId);
			if (readerIds.size() == 0) {
				writerMutex.release();
			}
		} finally {
			readerCheckoutMutex.release();
		}

		System.out.printf("Released read lock for thread: %d\n", threadId);
	}

	@Override
	public void writeLock() {
		writerCheckinMutex.acquire();
		if (numWriters == 0) {
			readerCheckinMutex.acquire();
		}
		numWriters++;
		writerCheckinMutex.release();
		writerMutex.acquire();
		writerId = Thread.currentThread().getId();

		System.out.printf("Acquired write lock for thread: %d\n", writerId);
	}

	@Override
	public void writeUnlock() {
		Long threadId = Thread.currentThread().getId();
		if (writerId == null || !writerId.equals(threadId)) {
			throw new IllegalStateException(
					String.format(
							"The current thread with id: %d never acquired a write lock before.",
							threadId));
		}

		writerCheckinMutex.acquire();
		numWriters--;
		if (numWriters == 0) {
			readerCheckinMutex.release();
		}
		writerId = null;
		writerMutex.release();
		writerCheckinMutex.release();

		System.out.printf("Released write lock for thread: %d\n", threadId);
	}

	// Simple tests.
	public static void main(String[] args) throws InterruptedException {
		final SecondReadWriteLock rwLock = new SecondReadWriteLock();

		Executor exec = Executors.newFixedThreadPool(10);
		Runnable reader = new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						rwLock.readLock();
						Thread.sleep(20000);
						rwLock.readUnlock();
					} catch (InterruptedException e) {
						throw new RuntimeException();
					}
				}
			}
		};

		final Random rand = new Random();
		Runnable writer = new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000 * rand.nextInt(10));
						rwLock.writeLock();
						Thread.sleep(1000 * rand.nextInt(10));
						rwLock.writeUnlock();
					} catch (InterruptedException e) {
						throw new RuntimeException();
					}
				}
			}
		};

		exec.execute(reader);
		exec.execute(reader);
		exec.execute(reader);
		exec.execute(reader);
		exec.execute(reader);

		exec.execute(writer);
		exec.execute(writer);
	}
}
