package concurrency;

public interface SchedulingExecutor {
	void schedule(Runnable task, long epochTime);

	void delete(Runnable task);

	void shutdown();
}
