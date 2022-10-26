public class MyQueue<E> {
  private MyLinkedList<E> list 
    = new MyLinkedList<E>();

  public void enqueue(E e) {
    // Left as an exercise
  }

  public E dequeue() {
    // Left as an exercise
  }

  public int getSize() {
    // Left as an exercise
  }

  public MyLinkedList<E> getList()
  {
	// Left as an exercise
  }
  
  @Override
  public String toString() {
    return "Queue: " + list.toString();
  }
}