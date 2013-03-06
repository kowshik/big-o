package collections.lists;


public class SinglyLinkedList<T> implements CustomizedList<T> {

	private class Node<E> {
		private E value;
		private Node<E> next;

		public Node(E value) {
			this(null, null);
		}

		public Node(E value, Node<E> next) {
			this.value = value;
			this.next = next;
		}

		public Node<E> getNext() {
			return next;
		}

		public void setNext(Node<E> next) {
			this.next = next;
		}

		public E getValue() {
			return value;
		}

		public void setValue(E value) {
			this.value = value;
		}

	}

	private Node<T> head;
	private Node<T> tail;
	private int size;

	@Override
	public void reverse() {
		if (head == tail) {
			return;
		}

		tail = head;
		Node<T> next = head.getNext();
		head.setNext(null);

		while (next != null) {
			Node<T> tmp = next.getNext();
			next.setNext(head);
			head = next;
			next = tmp;
		}
	}
}
