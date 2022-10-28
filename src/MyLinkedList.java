public class MyLinkedList<E> implements MyList<E> {
	protected Node<E> head, tail;
	protected int size = 0; // Number of elements in the list

	/** Create an empty list */
	public MyLinkedList() {
	}

	/** Create a list from an array of objects */
	public MyLinkedList(E[] objects) {
		for (E object: objects) 
			add(object);
	}

	/** Return the head element in the list */
	public E getFirst() {
		return size == 0 ? null : head.element;
	}

	/** Return the last element in the list */
	public E getLast() {
		return size == 0 ? null : tail.element;
	}

	/** Add an element to the beginning of the list */
	public void addFirst(E e) {
		Node<E> newHead = new Node<>(e);
		newHead.next = head;
		head = newHead;
		size++;
		if (tail == null)
			tail = head;
	}

	/** Add an element to the end of the list */
	public void addLast(E e) {
		Node<E> newTail = new Node<>(e);
		if (tail == null)
			head = tail = newTail;
		else {
			tail.next = newTail;
			tail = newTail;
		}
		size++;
	}

	@Override /** Add a new element at the specified index 
	 * in this list. The index of the head element is 0 */
	public void add(int index, E e) {
		if (index < 0)
			return;
		if (index == 0)
			addFirst(e);
		else if (index >= size)
			addLast(e);
		else {
			Node<E> current = head;
			for (int i = 0; i < index; i++)
				current = current.next;
			Node<E> next = current.next;
			current.next = new Node<>(e);
			current.next.next = next;
			size++;
		}
	}

	/** Remove the head node and
	 *  return the object that is contained in the removed node. */
	public E removeFirst() {
		E element = head.element;
		head = head.next;
		size--;
		return element;
	}

	/** Remove the last node and
	 * return the object that is contained in the removed node. */
	public E removeLast() {
		// Left as an exercise
		return null;
	}

	@Override /** Remove the element at the specified position in this 
	 *  list. Return the element that was removed from the list. */
	public E remove(int index) {   
		// Left as an exercise
		return null;
	}

	@Override /** Override toString() to return elements in the list */
	public String toString() {
		StringBuilder result = new StringBuilder("[");

		Node<E> current = head;
		for (int i = 0; i < size; i++) {
			result.append(current.element);
			current = current.next;
			if (current != null) {
				result.append(", "); // Separate two elements with a comma
			}
			else {
				result.append("]"); // Insert the closing ] in the string
			}
		}

		return result.toString();
	}

	@Override /** Clear the list */
	public void clear() {
		head = tail = null;
		size = 0;
	}

	@Override /** Return true if this list contains the element e */
	public boolean contains(Object e) {
		for (java.util.Iterator<E> it = iterator(); it.hasNext();) {
			E element = it.next();
			if (e.equals(element))
				return true;
		}
		return false;
	}

	@Override /** Return the element at the specified index */
	public E get(int index) {
		int count = 0;
		for (java.util.Iterator<E> it = iterator(); it.hasNext(); count++) {
			E element = it.next();
			if (count == index)
				return element;
		}
		return null;
	}

	@Override /** Return the index of the first matching element in 
	 *  this list. Return -1 if no match. */
	public int indexOf(Object e) {
		int index = 0;
		for (java.util.Iterator<E> it = iterator(); it.hasNext(); index++) {
			E element = it.next();
			if (e.equals(element))
				return index;
		}
		return -1;
	}

	@Override /** Return the index of the last matching element in 
	 *  this list. Return -1 if no match. */
	public int lastIndexOf(E e) {
		int index = -1;
		int count = 0;
		for (java.util.Iterator<E> it = iterator(); it.hasNext(); count++) {
			E element = it.next();
			if (e.equals(element))
				index = count;
		}
		return index;
	}

	@Override /** Replace the element at the specified position 
	 *  in this list with the specified element. */
	public E set(int index, E e) {
		Node<E> current = head;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		E element = current.element;
		current.element = e;
		return element;
	}

	@Override /** Override iterator() defined in Iterable */
	public java.util.Iterator<E> iterator() {
		return new LinkedListIterator();
	}

	private class LinkedListIterator 
	implements java.util.Iterator<E> {
		private Node<E> current = head; // Current node 
		private int index=-1; // initial index before head

		@Override
		public boolean hasNext() {
			return (current != null);
		}

		@Override
		public E next() {
			E e = current.element;
			index++;	
			current = current.next;
			return e;
		}

		@Override
		// remove the last element returned by the iterator
		public void remove() {
			MyLinkedList.this.remove(index);	
		}
	}

	protected static class Node<E> {
		E element;
		Node<E> next;
		
		public Node(E element) {
			this.element = element;
		}
	}

	@Override /** Return the number of elements in this list */
	public int size() {
		return size;
	}
}