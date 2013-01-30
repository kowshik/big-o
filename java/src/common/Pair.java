package common;

public class Pair<Foo, Bar> {
	private Foo first;

	public Foo getFirst() {
		return first;
	}

	public void setFirst(Foo foo) {
		this.first = foo;
	}

	private Bar second;

	public Bar getSecond() {
		return second;
	}

	public void setSecond(Bar bar) {
		this.second = bar;
	}

	@Override
	public boolean equals(Object other) {
		if (other == null) {
			return false;
		}

		if (!this.getClass().equals(other.getClass())) {
			return false;
		}

		@SuppressWarnings("unchecked")
		Pair<Foo, Bar> otherPair = (Pair<Foo, Bar>) other;

		if ((first == null && otherPair.getFirst() != null)
				|| !first.equals(otherPair.getFirst())) {
			return false;
		}
		if ((second == null && otherPair.getSecond() != null)
				|| !second.equals(otherPair.getSecond())) {
			return false;
		}

		return true;
	}
}
