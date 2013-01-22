package collections.lists;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

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

	public static void main(String[] args) {
		new SinglyLinkedList<Integer>();
	}

	private Node<T> head;
	private Node<T> tail;
	private int size;

	@Override
	public void add(int arg0, T arg1) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean add(T value) {
		Node<T> newNode = new Node<T>(value);
		size += 1;

		if (head == null) {
			head = tail = newNode;
			return true;
		}

		tail.setNext(newNode);
		tail = newNode;

		return true;
	}

	@Override
	public boolean addAll(Collection<? extends T> arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(int arg0, Collection<? extends T> arg1) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean contains(Object arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsAll(Collection<?> arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public T get(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(Object arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isEmpty() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterator<T> iterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int lastIndexOf(Object arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<T> listIterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ListIterator<T> listIterator(int arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public T remove(int arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean remove(Object arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> arg0) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> arg0) {
		throw new UnsupportedOperationException();
	}

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
	public T set(int arg0, T arg1) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public List<T> subList(int arg0, int arg1) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object[] toArray() {
		throw new UnsupportedOperationException();
	}

	@Override
	public <X> X[] toArray(X[] arg0) {
		throw new UnsupportedOperationException();
	}
}
