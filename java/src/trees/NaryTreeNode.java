package trees;

import java.util.LinkedList;
import java.util.List;

public class NaryTreeNode<T> {
	private T value;
	private List<NaryTreeNode<T>> children;

	public NaryTreeNode(T value) {
		this.value = value;
		this.children = new LinkedList<NaryTreeNode<T>>();
	}

	public NaryTreeNode() {
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public List<NaryTreeNode<T>> getChildren() {
		return children;
	}
}
