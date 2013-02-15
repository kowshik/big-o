package concurrency;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Implement add() and remove() methods for the queue data structure.
 * 
 * The queue should be a bounded queue i.e. at any point of time, it can only
 * hold a specified number of elements.
 * 
 * The queue should be blocking i.e. if a thread is trying to read from the
 * queue, and does not find any elements left to read, then it should wait until
 * an element becomes available. Similarly, if a thread is trying to write to
 * the queue, and finds the queue to be full, then it should wait until an
 * element is removed from the queue.
 * 
 * The queue should be fair i.e. if there are 10 readers waiting to read from
 * the queue, and if a queue element becomes available for reading, then the
 * reader thread that arrived earliest in time should be allowed access to read
 * the element, and other reader threads should continue to remain blocked.
 * Similarly, if there are 10 writers waiting to write to the queue, and if
 * slots become available in the queue for writing, then the writer thread that
 * arrived earliest in time should be allowed access to write the element, and
 * the other writer threads should continue to remain blocked.
 */
public class BoundedBlockingQueue<T> {
	private final Queue<T> elementQueue;
	private final Queue<Long> readerQueue;
	private final Queue<Long> writerQueue;
	private final int capacity;

	public BoundedBlockingQueue(int capacity) {
		if (capacity < 0) {
			throw new IllegalArgumentException(String.format(
					"Queue size cannot be negative. You passed: %d", capacity));
		}

		this.capacity = capacity;
		this.elementQueue = new LinkedList<T>();
		this.readerQueue = new LinkedList<Long>();
		this.writerQueue = new LinkedList<Long>();
	}

	public synchronized void add(T element) throws InterruptedException {
		long threadId = Thread.currentThread().getId();
		writerQueue.add(threadId);

		while (writerQueue.size() == capacity || writerQueue.peek() != threadId) {
			wait();
		}

		writerQueue.remove();
		elementQueue.add(element);
		notifyAll(); // notify all waiting readers as well as writers.
	}

	public synchronized T remove() throws InterruptedException {
		long threadId = Thread.currentThread().getId();
		readerQueue.add(threadId);

		while (elementQueue.isEmpty() || readerQueue.peek() != threadId) {
			wait();
		}

		readerQueue.remove();
		T toReturn = elementQueue.remove();
		notifyAll(); // notify all waiting readers as well as writers.

		return toReturn;
	}
}
