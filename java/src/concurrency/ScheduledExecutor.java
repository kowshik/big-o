package concurrency;

public interface ScheduledExecutor {
	void schedule(Runnable task, long epochTime);

	void delete(Runnable task);

	void shutdown();
}
