
/**
 * @title   EIE3320 Lab 2: Library Admin System
 * @author  CHAN Kai Ho 19057769D
 * @author  SZE Kin Sang 19062606D
 * @date    28 Oct 2022
 */

public class MyQueue<E> {
  private MyLinkedList<E> list = new MyLinkedList<E>();

  public void enqueue(E e) {
    list.addLast(e);
  }

  public E dequeue() {
    return list.removeFirst();
  }

  public int getSize() {
    return list.size();
  }

  public MyLinkedList<E> getList() {
	return list;
  }
  
  @Override
  public String toString() {
    return "Queue: " + list.toString();
  }
}