package concurrency;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import common.Pair;

public class SimpleSchedulingExecutor implements SchedulingExecutor {
	private static final long IDLING_TIME = 10000L;

	private final PriorityQueue<Pair<Runnable, Long>> executionHeap;
	private final Map<Runnable, Long> runnables;
	private final Thread dispatcherThread;
	private final int threadPoolSize;
	private final Lock lock;
	private final Dispatcher dispatcher;
	private boolean shutdown;

	private class Dispatcher implements Runnable {

		private final ExecutorService executor;
		private Long sleepTime;
		private volatile boolean shutdown;

		Dispatcher() {
			this.executor = Executors.newFixedThreadPool(threadPoolSize);
			this.sleepTime = IDLING_TIME;
			this.shutdown = false;
		}

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
					if (shutdown) {
						executor.shutdownNow();
						return;
					}
				}

				dispatch();
			}
		}

		private void dispatch() {
			try {
				lock.lock();
				if (executionHeap.isEmpty()) {
					sleepTime = IDLING_TIME;
				} else {
					sleepTime = null;
					while (sleepTime == null && !executionHeap.isEmpty()) {
						Pair<Runnable, Long> topOfHeap = executionHeap.peek();
						Runnable task = topOfHeap.getFirst();
						Long taskStartTime = topOfHeap.getSecond();
						long currentTime = System.currentTimeMillis();
						if (taskStartTime - currentTime <= 0) {
							executor.execute(task);
							executionHeap.remove();
							runnables.remove(task);
						} else {
							sleepTime = taskStartTime - currentTime;
						}
					}
				}
			} catch (RuntimeException e) {
				System.out.printf(
						"Caught RuntimeException in dispatcher loop: %s\n", e);
			} finally {
				lock.unlock();
			}

			sleepTime = (sleepTime == null) ? IDLING_TIME : sleepTime;
		}

		void shutdown() {
			shutdown = true;
		}
	}

	public SimpleSchedulingExecutor(int threadPoolSize) {
		runnables = new HashMap<Runnable, Long>();
		Comparator<Pair<Runnable, Long>> comparator = new Comparator<Pair<Runnable, Long>>() {
			@Override
			public int compare(Pair<Runnable, Long> foo,
					Pair<Runnable, Long> bar) {
				if (foo.getSecond() > bar.getSecond()) {
					return 1;
				} else if (foo.getSecond() < bar.getSecond()) {
					return -1;
				}

				return 0;
			}
		};
		executionHeap = new PriorityQueue<Pair<Runnable, Long>>(10, comparator);

		this.threadPoolSize = threadPoolSize;
		this.lock = new ReentrantLock();

		dispatcher = new Dispatcher();
		dispatcherThread = new Thread(dispatcher);
		dispatcherThread.start();

		shutdown = false;
	}

	@Override
	public void schedule(Runnable task, long epochTime) {
		checkState();

		long currentTime = System.currentTimeMillis();
		if (epochTime < currentTime) {
			new IllegalArgumentException(
					String.format(
							"Can't execute a task with time: %d which starts before current system time: %d.\n",
							epochTime, currentTime));
		}

		try {
			lock.lock();
			if (runnables.containsKey(task)) {
				throw new IllegalArgumentException(String.format(
						"Task: %s has already been scheduled to run at: %d.",
						task, runnables.get(task)));
			}
			runnables.put(task, epochTime);
			executionHeap.add(new Pair<Runnable, Long>(task, epochTime));
		} finally {
			lock.unlock();
		}

		dispatcherThread.interrupt();
	}

	@Override
	public void delete(Runnable task) {
		checkState();

		try {
			lock.lock();
			if (!runnables.containsKey(task)) {
				throw new IllegalArgumentException(
						String.format(
								"Task: %s was never scheduled earlier, or is running now.",
								task));
			}
			Long startTime = runnables.remove(task);
			executionHeap.remove(new Pair<Runnable, Long>(task, startTime));
		} finally {
			lock.unlock();
		}
	}

	@Override
	public void shutdown() {
		checkState();

		try {
			lock.lock();
			shutdown = true;
			dispatcher.shutdown();
			dispatcherThread.interrupt();
		} finally {
			lock.unlock();
		}
	}

	private void checkState() {
		if (shutdown) {
			throw new IllegalStateException(
					"Shutdown has already been initiated.");
		}
	}

	public static void main(String[] args) throws InterruptedException {
		final int firstTaskId = 1;
		final int secondTaskId = 2;
		final int thirdTaskId = 3;
		final int fourthTaskId = 4;
		final int fifthTaskId = 5;

		SimpleSchedulingExecutor sse = new SimpleSchedulingExecutor(3);
		Runnable firstTask = new Runnable() {
			@Override
			public void run() {
				System.out.printf("Performing task: %d at: %d\n", firstTaskId,
						System.currentTimeMillis());
			}
		};

		Runnable secondTask = new Runnable() {
			@Override
			public void run() {
				System.out.printf("Performing task: %d at %d\n", secondTaskId,
						System.currentTimeMillis());
			}
		};

		Runnable thirdTask = new Runnable() {
			@Override
			public void run() {
				System.out.printf("Performing task: %d at: %d\n", thirdTaskId,
						System.currentTimeMillis());
			}
		};

		Runnable fourthTask = new Runnable() {
			@Override
			public void run() {
				System.out.printf("Performing task: %d at: %d\n", fourthTaskId,
						System.currentTimeMillis());
			}
		};

		Runnable fifthTask = new Runnable() {
			@Override
			public void run() {
				System.out.printf("Performing task: %d at: %d\n", fifthTaskId,
						System.currentTimeMillis());
			}
		};

		sse.schedule(firstTask, System.currentTimeMillis() + 2000L);
		sse.schedule(secondTask, System.currentTimeMillis() + 2000L);
		sse.schedule(thirdTask, System.currentTimeMillis() + 3000L);
		sse.schedule(fourthTask, System.currentTimeMillis() + 5000L);
		sse.schedule(fifthTask, System.currentTimeMillis() + 8000L);
		sse.delete(thirdTask);

		Thread.sleep(5000L);

		// third and fifth task should never run.
		sse.shutdown();
	}
}
