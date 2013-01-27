package general;

public class Schedule {
	private Time startTime;
	private Time endTime;

	public Schedule(Time startTime, Time endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
}
