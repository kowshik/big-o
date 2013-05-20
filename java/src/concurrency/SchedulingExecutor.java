package concurrency;

/**
 * Defines an executor that schedules threads to run at specified time.
 */
public interface SchedulingExecutor {
	/**
	 * Schedule the given Runnable to start execution at the specified
	 * epochTime.
	 */
	void schedule(Runnable task, long epochTime);

	/**
	 * Delete a previously scheduled Runnable.
	 */
	void delete(Runnable task);

	/**
	 * Shutdown the executor, and disallow all future schedule/delete calls.
	 */
	void shutdown();
}
