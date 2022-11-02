
/**
 * Write a description of class Frame here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import javax.swing.JFrame;

public class InputPrompt
{
    public static void main(String[] args) {
    	/*
        PromptContent frame = new PromptContent();
        frame.setTitle("Library Admin System");
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        */
        Book book = new Book();
        book.setISBN("6969696969");
        book.setTitle("The 69 Ways");
        book.setAvailable(false);
    	BookDetailPrompt frame2 = new BookDetailPrompt(book);
    	frame2.setVisible(true);
    }
}
