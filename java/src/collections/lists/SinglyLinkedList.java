package collections.lists;

import java.util.NoSuchElementException;

/**
 * A singly linked list implementation of the list interface.
 */
public class SinglyLinkedList<T> implements List<T> {

	private static class Node<E> {
		private E value;
		private Node<E> next;

		public Node(E value) {
			this(value, null);
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

	@Override
	public void add(T element, int offset) {
		if (offset < 0) {
			throw new IllegalArgumentException(String.format(
					"Offset cannot be negative. You passed: %d", offset));
		}

		if (offset > size) {
			throw new IllegalArgumentException(
					String.format(
							"The offset: %d is greater than the max allowed offset: %d",
							offset, size));
		}

		if (offset == 0) {
			addFirst(element);
			return;
		}

		if (offset == size) {
			addLast(element);
			return;
		}

		Node<T> newNode = new Node<T>(element);
		Node<T> iter = head;
		for (int count = 0; count < offset - 1; count++) {
			iter = iter.getNext();
		}

		Node<T> next = iter.getNext();
		iter.setNext(newNode);

		newNode.setNext(next);

		size++;
	}

	@Override
	public void addFirst(T element) {
		Node<T> node = new Node<T>(element);
		node.setNext(head);
		head = node;

		if (tail == null) {
			tail = head;
		}

		size++;
	}

	@Override
	public void addLast(T element) {
		Node<T> node = new Node<T>(element);
		if (tail != null) {
			tail.setNext(node);
		}

		tail = node;
		if (head == null) {
			head = tail;
		}

		size++;
	}

	@Override
	public T removeFirst() {
		if (head == null) {
			throw new NoSuchElementException();
		}

		T toReturn = head.getValue();
		if (head == tail) {
			head = tail = null;
		} else {
			head = head.getNext();
		}

		size--;
		return toReturn;
	}

	@Override
	public T removeLast() {
		if (head == null) {
			throw new NoSuchElementException();
		}

		T toReturn = tail.getValue();
		if (head == tail) {
			head = tail = null;
			return toReturn;
		}

		Node<T> iter = head;
		while (iter.getNext() != tail) {
			iter = iter.getNext();
		}

		iter.setNext(null);
		tail = iter;

		size--;
		return toReturn;
	}

	@Override
	public T remove(int offset) {
		if (offset < 0) {
			throw new IllegalArgumentException(String.format(
					"Offset cannot be negative. You passed: %d", offset));
		}

		if (head == null) {
			throw new NoSuchElementException();
		}

		if (offset >= size) {
			throw new IllegalArgumentException(
					String.format(
							"The offset: %d is greater than the max allowed offset: %d",
							offset, size));
		}

		if (offset == 0) {
			return removeFirst();
		}

		if (offset == size - 1) {
			return removeLast();
		}

		Node<T> prev = null, current = head;
		int count = 0;
		while (count < offset) {
			count++;
			prev = current;
			current = current.getNext();
		}

		T toReturn = current.getValue();
		prev.setNext(current.getNext());

		size--;
		return toReturn;
	}

	@Override
	public boolean search(T element) {
		if (head == null) {
			throw new NoSuchElementException();
		}

		Node<T> iter = head;
		while (iter != null) {
			if (iter.getValue().equals(element)) {
				return true;
			}

			iter = iter.getNext();
		}

		return false;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public T get(int offset) {
		if (offset < 0) {
			throw new IllegalArgumentException(String.format(
					"Offset cannot be negative. You passed: %d", offset));
		}

		if (offset >= size) {
			throw new IllegalArgumentException(
					String.format(
							"The offset: %d is greater than the max allowed offset: %d",
							offset, size));
		}

		Node<T> iter = head;
		for (int count = 0; count < offset; count++) {
			iter = iter.getNext();
		}

		return iter.getValue();
	}

	@Override
	public T getFirst() {
		if (head == null) {
			throw new NoSuchElementException();
		}

		return head.getValue();
	}

	@Override
	public T getLast() {
		if (tail == null) {
			throw new NoSuchElementException();
		}

		return tail.getValue();
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("[");

		if (head != null) {
			T value = head.getValue();
			buffer.append(value == null ? "null" : value.toString());
			Node<T> iterator = head.getNext();

			while (iterator != null) {
				buffer.append(",");
				value = iterator.getValue();
				buffer.append(value == null ? "null" : value.toString());
				iterator = iterator.getNext();
			}
		}

		buffer.append("]");
		return buffer.toString();
	}

	@SuppressWarnings("unchecked")
	public void pairWiseReverse() {
		head = pairWiseReverse(head);
	}

	private Node<T> pairWiseReverse(Node<T> head) {
		if (head == null) {
			return null;
		}

		if (head.getNext() == null) {
			return head;
		}

		Node<T> next = head.getNext();
		Node<T> pwReversed = pairWiseReverse(next.getNext());
		next.setNext(head);
		head.setNext(pwReversed);

		return next;
	}
}
