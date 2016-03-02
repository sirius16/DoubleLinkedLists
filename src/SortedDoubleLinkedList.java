package src;

import java.util.Comparator;

/**
 * 
 */

/**
 * A sorted double linked list
 * 
 * @author Uzoma Uwanamodo
 *
 */
public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> implements Comparator<T> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see BasicDoubleLinkedList#addToEnd(java.lang.Object)
	 */
	@Override
	public BasicDoubleLinkedList<T> addToEnd(T t) {
		throw new UnsupportedOperationException("Invalid operation for a sorted list");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see BasicDoubleLinkedList#addToFront(java.lang.Object)
	 */
	@Override
	public BasicDoubleLinkedList<T> addToFront(T t) {
		throw new UnsupportedOperationException("Invalid operation for a sorted list");
	}

	int size;
	private Comparator<T> comparator;

	@Override
	public int compare(T o1, T o2) {
		return comparator.compare(o1, o2);
	}

	/**
	 * Constructor to create new Sorted Double Linked List
	 * @param comparator
	 */
	public SortedDoubleLinkedList(Comparator<T> comparator) {
		size = 0;
		this.comparator = comparator;

	}

	/**
	 * Add a new element into the list
	 * 
	 * @param t
	 *            The element being added to the list
	 * @return This sorted list
	 */
	public SortedDoubleLinkedList<T> add(T t) {
		Node node = new Node(null, null, t);
		if (increaseSize() - 1 == 0) {
			setHead(node);
			setTail(node);
			return this;
		} else {
			Node node2 = getHead();
			if (compare(t, node2.getT()) < 0) {
				decreaseSize();
				super.addToFront(t);
				return this;
			}
			while (node2 != null) {

				T t2 = node2.getT();
				if (compare(t, t2) > 0) {
					node2 = node2.getNext();
					if (node2 != null) {
					}
					continue;
				} else {
					node.setNext(node2);
					node2.getPrev().setNext(node);
					node.setPrev(node2.getPrev());
					node2.setPrev(node);
					return this;
				}
			}
			decreaseSize();
			super.addToEnd(t);
		}
		// } else{

		// } else
		// node2 = node2.getNext();
		// }
		// decreaseSize();
		// addToFront(t);
		// }
		return this;
	}

	@Override
	public BasicDoubleLinkedList<T>.LinkedIterator<T> iterator() {
		return super.iterator();
	}

}
