
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
	
	/*
	 * Constructor of the Book
	 * title and ISBN is null string.
	 * available is true.
	 * reservedQueue is empty queue.
	 */
	public Book() {
		this.title = "";
		this.ISBN = "";
		this.available = true;
		this.reservedQueue = new MyQueue<>();
	}
	
	/*
	 * Set the title of Book
	 * @param title title of the book
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/*
	 * Get the title of Book
	 */
	public String getTitle() {
		return title;
	}
	
	/*
	 * Set the ISBN of Book
	 * @param ISBN ISBN of the book
	 */
	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}
	
	/*
	 * Get the ISBN of Book
	 */
	public String getISBN() {
		return ISBN;
	}
	
	/*
	 * Set the availability of Book
	 * @param avaliable availability of the book
	 */
	public void setAvailable(boolean available) {
		this.available = available;
	}
	
	/*
	 * Get the availability of Book
	 */
	public boolean isAvailable() {
		return available;
	}
	
	/*
	 * Get the waiting queue of Book
	 */
	public MyQueue<String> getReservedQueue() {
		return reservedQueue;
	}
}
