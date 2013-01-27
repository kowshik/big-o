package general;

public class Time {
	public static final Time EPOCH = new Time(0);
	private int value;

	public int getValue() {
		return value;
	}

	public void setValue(int secondsElapsedSinceEpoch) {
		this.value = secondsElapsedSinceEpoch;
	}

	public Time(int secondsElapsedSinceEpoch) {
		setValue(secondsElapsedSinceEpoch);
	}
}
