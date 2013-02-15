import java.util.LinkedList;
import java.util.Queue;

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
