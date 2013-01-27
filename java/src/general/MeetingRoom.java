package general;

public class MeetingRoom {
	private Time earliestStartTime;

	public Time getEarliestStartTime() {
		return earliestStartTime;
	}

	public void setEarliestStartTime(Time earliestStartTime) {
		this.earliestStartTime = earliestStartTime;
	}

	public MeetingRoom(Time earliestStartTime) {
		setEarliestStartTime(earliestStartTime);
	}

	public MeetingRoom() {
		setEarliestStartTime(Time.EPOCH);
	}
}
