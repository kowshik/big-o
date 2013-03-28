package concurrency;

/**
 * A counting semaphore implementation. You can use this as a mutex by
 * initializing the count to one.
 */
public class Semaphore {
	private int count;
	private int waitCount = 0;

	public Semaphore(int count) {
		if (count <= 0) {
			throw new IllegalArgumentException(
					String.format(
							"Semaphore count should be greater than zero. You passed: %d.",
							count));
		}

		this.count = count;
	}

	public synchronized void acquire() {
		waitCount++;
		while (count == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}

		waitCount--;
		count--;
	}

	public synchronized void release() {
		if (count == 0) {
			notify();
		}

		count++;
	}

	public synchronized int numWaitingThreads() {
		return waitCount;
	}

	public static void main(String[] args) {
		final Semaphore semaphore = new Semaphore(2);
		new Thread(new Runnable() {
			@Override
			public void run() {
				semaphore.acquire();
				try {
					System.out.println("Acquired semaphore.. sleeping!");
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				semaphore.release();
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				semaphore.acquire();
				try {
					System.out.println("Acquired semaphore.. sleeping!");
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				semaphore.release();
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				semaphore.acquire();
				try {
					System.out.println("Acquired semaphore.. sleeping!");
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				semaphore.release();
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				semaphore.acquire();
				try {
					System.out.println("Acquired semaphore.. sleeping!");
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				semaphore.release();
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				semaphore.acquire();
				try {
					System.out.println("Acquired semaphore.. sleeping!");
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				semaphore.release();
			}
		}).start();
	}
}