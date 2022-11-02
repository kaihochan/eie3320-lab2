
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
        PromptContent frame = new PromptContent();
        frame.setTitle("Library Admin System");
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
