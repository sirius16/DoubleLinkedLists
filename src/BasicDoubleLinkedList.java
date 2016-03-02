package src;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import src.BasicDoubleLinkedList.Node;

/**
 * 
 */

/**
 * A generic double linked list
 * 
 * @author Uzoma Uwanamodo
 *
 */
@SuppressWarnings("unused")
public class BasicDoubleLinkedList<T> {
	/**
	 * @param size the size to set
	 * @return 
	 */
	public int increaseSize() {
		return ++size;
	}
	
	public int decreaseSize() {
		return --size;
	}

	/**
	 * @return the head
	 */
	public Node getHead() {
		return head;
	}

	/**
	 * @param head the head to set
	 */
	public void setHead(Node head) {
		this.head = head;
	}

	/**
	 * @return the tail
	 */
	public Node getTail() {
		return tail;
	}

	/**
	 * @param tail the tail to set
	 */
	public void setTail(Node tail) {
		this.tail = tail;
	}

	private int size;
	private Node head;
	private Node tail;

	public BasicDoubleLinkedList() {
		size = 0;

	}

	/**
	 * An Iterator to go throguh the linked list
	 * 
	 * @author Uzoma Uwanamodo
	 *
	 * @param <T>
	 */
	@SuppressWarnings("hiding")
	public class LinkedIterator<T> implements ListIterator<T> {

		private Node n;

		/**
		 * Initialize the iterator
		 * 
		 * @param node
		 */
		public LinkedIterator() {
			n = head;
		}

		@Override
		public void add(T e) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean hasNext() {
			return n != null;
		}

		@Override
		public boolean hasPrevious() {
			return n != null;
		}

		@SuppressWarnings("unchecked")
		@Override
		public T next() {
			if (n == null)
				throw new NoSuchElementException();
			Node val = new Node(null, null, n.t);
			n = n.next;
			return (T) val.t;
		}

		@Override
		public int nextIndex() {
			throw new UnsupportedOperationException();
		}

		@SuppressWarnings("unchecked")
		@Override
		public T previous() {
			if (n == null)
				throw new NoSuchElementException();
			Node val =  n;
			n = n.prev;
			return (T) val.t;
		}

		@Override
		public int previousIndex() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		@Override
		public void set(T e) {
			throw new UnsupportedOperationException();

		}

	}

	public class Node {
		/**
		 * Build a Node
		 * 
		 * @param next
		 *            The link to the next node
		 * @param prev
		 *            The link to the previous node
		 * @param t
		 *            The element being stored in the node
		 */
		public Node(Node next, Node prev, T t) {
			this.next = next;
			this.prev = prev;
			this.t = t;
		}

		private Node next;
		/**
		 * @return the next
		 */
		public Node getNext() {
			return next;
		}

		/**
		 * @param next the next to set
		 */
		public void setNext(Node next) {
			this.next = next;
		}

		/**
		 * @return the prev
		 */
		public Node getPrev() {
			return prev;
		}

		/**
		 * @param prev the prev to set
		 */
		public void setPrev(Node prev) {
			this.prev = prev;
		}

		/**
		 * @return the t
		 */
		public T getT() {
			return t;
		}

		/**
		 * @param t the t to set
		 */
		public void setT(T t) {
			this.t = t;
		}

		private Node prev;
		private T t;

		@Override
		public String toString() {
			return "Node [" + (next != null ? "next=" + next.t + ", " : "")
					+ (prev != null ? "prev=" + prev.t + ", " : "") + (t != null ? "t=" + t : "") + "]";
		}
	}

	/**
	 * Add an element to the end of the list
	 * 
	 * @param t
	 *            The element being added
	 * @return the linked list being used
	 */
	public BasicDoubleLinkedList<T> addToEnd(T t) {
		Node node = new Node(null, tail, t);
		if (tail != null) {
			tail.next = node;
		}
		tail = node;
		if (head == null) {
			head = node;
		}
		size++;
		return this;
	}

	/**
	 * Getter function for size
	 * 
	 * @return the size of the list
	 */
	public int getSize() {
		return size;
	}

	/**
	 * A getter function for the tail
	 * 
	 * @return the last element in the array
	 */
	public T getLast() {
		return tail.t;
	}

	/**
	 * A getter function for the head
	 * 
	 * @return the first element in the array
	 */
	public T getFirst() {
		return head.t;
	}

	/**
	 * Method to add an element to the front of the list
	 * 
	 * @param t
	 *            the element being added
	 * @return the element being operated on
	 */
	public BasicDoubleLinkedList<T> addToFront(T t) {
		Node node = new Node(head, null, t);
		if (head != null) {
			head.prev = node;
		}
		head = node;
		if (tail == null) {
			tail = node;
		}
		size++;
		return this;
	}

	/**
	 * Method to get and delete the last element
	 * 
	 * @return The last element in the list
	 */
	public T retrieveLastElement() {
		if (size == 0)
			throw new NoSuchElementException();
		Node node = tail;
		tail = tail.prev;
		if (--size == 0) {
			tail = null;
			head = null;
		} else
			tail.next = null;
		return node.t;
	}

	/**
	 * Create a new iterator
	 * 
	 * @return an {@link Iterator}
	 */
	public LinkedIterator<T> iterator() {
		return new LinkedIterator<T>();
	}

	/**
	 * Create an array list with all of the list elements
	 * 
	 * @return an {@link ArrayList} with all of the list elements
	 */
	public ArrayList<T> toArrayList() {
		ArrayList<T> list = new ArrayList<T>();
		LinkedIterator<T> iterator = iterator();
		while (iterator.hasNext()) {
			list.add(iterator.next());
		}
		return list;
	}

	/**
	 * A method to retrieve and delete the first element from the list
	 * 
	 * @return the first element
	 */
	public T retrieveFirstElement() {
		if (size == 0)
			throw new NoSuchElementException();
		Node node = head;
		head = head.next;
		if (--size == 0) {
			head = null;
			tail = null;
		} else
			head.prev = null;

		return node.t;
	}

	/**
	 * Method to remove an element from anywhere within the list
	 * @param t the element to be removed
	 * @param comparator the comparator used to determine whether the element is in the list
	 */
	public void remove(T t, Comparator<T> comparator) {
		if (size == 0)
			throw new NoSuchElementException();
		LinkedIterator<T> iterator = iterator();
		while (iterator.hasNext()) {
			T next = iterator.next();

			if (comparator.compare(next, t) == 0) {

				if (iterator.n != null) {
					if (iterator.n.prev != null) {
						if (head == iterator.n.prev) {
							retrieveFirstElement();
							return;
						}
						Node n = iterator.n.prev;
						if (iterator.hasPrevious()) {
							// if (iterator.n.prev.prev.equals(head));
							// head = new Node(iterator.n,
							// iterator.n.prev.prev.prev,
							// iterator.n.prev.prev.t);
							n.prev.next = n.next;
							n.next.prev = n.prev;
							size--;
						}
					}
					// } else
					// iterator.n = new Node(iterator.n.next, iterator.n.prev,
					// iterator.n.t);
					// size--;

				} else
					retrieveLastElement();
				// node.prev = null;
				// node.next = null;
				break;
			} else {
				continue;
			}

		}
	}
}
