import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class BookDetailPrompt extends JFrame {
	JTextArea bookDetail;
	JTextArea systemMessage = new JTextArea();
	JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	JButton borrowButton = new JButton("Borrow");
	JButton returnButton = new JButton("Return");
	JButton reserveButton = new JButton("Reserve");
	JButton waitingQueueButton = new JButton("Waiting Queue");
	
	public BookDetailPrompt(Book book) {		
		setLayout(new BorderLayout());
		setTitle(book.getTitle());
		setSize(475, 375);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		bookDetail = new JTextArea(String.format("ISBN: %s%nTitle: %s%nAvailable: %s%n", book.getISBN(), book.getTitle(), book.isAvailable()));
		bookDetail.setEditable(false);
		
		systemMessage.setEditable(false);
		
		borrowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				returnButton.setEnabled(true);
				reserveButton.setEnabled(true);
				waitingQueueButton.setEnabled(true);
				borrowButton.setEnabled(false);
				book.setAvailable(false);
				systemMessage.setText("The book is borrowed.");
			}
		});
		
		returnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				systemMessage.setText("The book is returned.\n");
				if (book.getReservedQueue().getSize() == 0) {
					returnButton.setEnabled(false);
					reserveButton.setEnabled(false);
					waitingQueueButton.setEnabled(false);
					borrowButton.setEnabled(true);
					book.setAvailable(true);
				}
				else {
					systemMessage.append(String.format("The book is now borrowed by %s.", book.getReservedQueue().dequeue()));
				}
			}
		});
		
		reserveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String name = JOptionPane.showInputDialog("What's your name?");
				 book.getReservedQueue().enqueue(name);
				 systemMessage.setText(String.format("The book is reserved by %s.", name));
			}
		});
		
		waitingQueueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String output = "The waiting queue:\n";
				for (Iterator<String> it = book.getReservedQueue().getList().iterator(); it.hasNext();)
					output += String.format("%s%n", it.next());
				systemMessage.setText(output);
			}
		});
		
		//buttonPanel.setSize(WIDTH, HEIGHT);
		buttonPanel.add(borrowButton);
		buttonPanel.add(returnButton);
		buttonPanel.add(reserveButton);
		buttonPanel.add(waitingQueueButton);
		
		add(bookDetail, BorderLayout.NORTH);
		add(buttonPanel, BorderLayout.CENTER);
		add(systemMessage, BorderLayout.SOUTH);
		
		if (book.isAvailable()) {
			returnButton.setEnabled(false);
			reserveButton.setEnabled(false);
			waitingQueueButton.setEnabled(false);
		}
		else
			borrowButton.setEnabled(false);
	}
}
