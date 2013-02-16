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

	public Pair(Foo first, Bar second) {
		setFirst(first);
		setSecond(second);
	}

	public Pair() {
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((first == null) ? 0 : first.hashCode());
		result = prime * result + ((second == null) ? 0 : second.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		@SuppressWarnings("rawtypes")
		Pair other = (Pair) obj;
		if (first == null) {
			if (other.first != null)
				return false;
		} else if (!first.equals(other.first))
			return false;
		if (second == null) {
			if (other.second != null)
				return false;
		} else if (!second.equals(other.second))
			return false;
		return true;
	}
}
