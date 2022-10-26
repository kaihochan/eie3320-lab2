
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
		this.reservedQueue = new MyQueue();
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}
	
	public String getISBN() {
		return this.ISBN;
	}
	
	public void setAvailable(boolean avaliable) {
		this.available = avaliable;
	}
	
	public boolean isAvailable() {
		return this.available;
	}
	
	public void setReservedQueue(MyQueue<String> reservedQueue) {
		this.reservedQueue = reservedQueue;
	}
	
	public MyQueue<String> getReservedQueue() {
		return this.reservedQueue;
	}
}