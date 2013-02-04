package graphs;

public class GraphNode<T> {
	private T value;

	public GraphNode(T value) {
		this.value = value;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (o == null || (!getClass().equals(o.getClass()))) {
			return false;
		}

		@SuppressWarnings("unchecked")
		GraphNode<T> graphNode = (GraphNode<T>) o;
		if (graphNode.value == null && value == null) {
			return true;
		}

		return graphNode.getValue().equals(value);
	}
}
