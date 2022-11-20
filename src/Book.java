
/**
 * @title   EIE3320 Lab 2: Library Admin System
 * @author  CHAN Kai Ho 19057769D
 * @author  SZE Kin Sang 19062606D
 * @date    3 Nov 2022
 */

public class Book {
	private String title;					// store the title of the book
	private String ISBN;					// store the ISBN of the book
	private boolean available; 				// keep the status of whether the book is available;
											// initially should be true
	private MyQueue<String> reservedQueue; 	// store the queue of waiting list
	
	public Book() {
		this.title = "";
		this.ISBN = "";
		this.available = true;
		this.reservedQueue = new MyQueue<>();
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}
	
	public String getISBN() {
		return ISBN;
	}
	
	public void setAvailable(boolean avaliable) {
		this.available = avaliable;
	}
	
	public boolean isAvailable() {
		return available;
	}
	
	public MyQueue<String> getReservedQueue() {
		return reservedQueue;
	}
}
