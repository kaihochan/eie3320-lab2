public class MyLinkedList<E> implements MyList<E> {
	protected Node<E> head, tail;
	protected int size = 0; // Number of elements in the list

	/** Create an empty list */
	public MyLinkedList() {
	}

	/** Create a list from an array of objects */
	public MyLinkedList(E[] objects) {
		// Left as an exercise
	}

	/** Return the head element in the list */
	public E getFirst() {
		// Left as an exercise
	}

	/** Return the last element in the list */
	public E getLast() {
		// Left as an exercise
	}

	/** Add an element to the beginning of the list */
	public void addFirst(E e) {
		// Left as an exercise
	}

	/** Add an element to the end of the list */
	public void addLast(E e) {
		// Left as an exercise
	}

	@Override /** Add a new element at the specified index 
	 * in this list. The index of the head element is 0 */
	public void add(int index, E e) {
		// Left as an exercise
	}

	/** Remove the head node and
	 *  return the object that is contained in the removed node. */
	public E removeFirst() {
		// Left as an exercise
	}

	/** Remove the last node and
	 * return the object that is contained in the removed node. */
	public E removeLast() {
		// Left as an exercise
	}

	@Override /** Remove the element at the specified position in this 
	 *  list. Return the element that was removed from the list. */
	public E remove(int index) {   
		// Left as an exercise
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
		// Left as an exercise
	}

	@Override /** Return true if this list contains the element e */
	public boolean contains(Object e) {
		// Left as an exercise 

	}

	@Override /** Return the element at the specified index */
	public E get(int index) {
		// Left as an exercise 

	}

	@Override /** Return the index of the first matching element in 
	 *  this list. Return -1 if no match. */
	public int indexOf(Object e) {
		// Left as an exercise

	}

	@Override /** Return the index of the last matching element in 
	 *  this list. Return -1 if no match. */
	public int lastIndexOf(E e) {
		// Left as an exercise

	}

	@Override /** Replace the element at the specified position 
	 *  in this list with the specified element. */
	public E set(int index, E e) {
		// Left as an exercise

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
		// Left as an exercise
	}

	@Override /** Return the number of elements in this list */
	public int size() {
		// Left as an exercise
	}
}