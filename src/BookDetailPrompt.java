
/**
 * @title   EIE3320 Lab 2: Library Admin System
 * @author  CHAN Kai Ho 19057769D
 * @author  SZE Kin Sang 19062606D
 * @date    3 Nov 2022
 */

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
	// text area for book's detail and system message
	private JTextArea bookDetail;
	private JTextArea systemMessage = new JTextArea();
	
	// panel for storing the buttons
	private JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
	
	// menu buttons
	private JButton borrowButton = new JButton("Borrow");
	private JButton returnButton = new JButton("Return");
	private JButton reserveButton = new JButton("Reserve");
	private JButton waitingQueueButton = new JButton("Waiting Queue");
	
	public BookDetailPrompt(Book book) {
		// set to border layout, title is book name, centered when showed, disposed when closed
		setLayout(new BorderLayout());
		setTitle(book.getTitle());
		setSize(475, 375);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// book detail are shown on top of the frame, set to not editable for users
		bookDetail = new JTextArea(String.format("ISBN: %s%nTitle: %s%nAvailable: %s%n", book.getISBN(), book.getTitle(), book.isAvailable()));
		bookDetail.setEditable(false);
		
		// system message set to not editable for users
		systemMessage.setEditable(false);
		
		// action listener for the borrow button
		// disable borrow button and enable other buttons
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
		
		// action listener for the return button
		// automatically borrow to the first person in the queue if exist
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
		
		// action listener for the reserve button
		// show pop up window to ask for the name and then add to the queue
		reserveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String name = JOptionPane.showInputDialog("What's your name?");
				 book.getReservedQueue().enqueue(name);
				 systemMessage.setText(String.format("The book is reserved by %s.", name));
			}
		});
		
		// action listener for the waiting queue button
		// show all pending users in the system message
		waitingQueueButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String output = "The waiting queue:\n";
				for (Iterator<String> it = book.getReservedQueue().getList().iterator(); it.hasNext();)
					output += String.format("%s%n", it.next());
				systemMessage.setText(output);
			}
		});
		
		// add button components to the button panel
		buttonPanel.add(borrowButton);
		buttonPanel.add(returnButton);
		buttonPanel.add(reserveButton);
		buttonPanel.add(waitingQueueButton);
		
		// add relevant components to the frame
		add(bookDetail, BorderLayout.NORTH);
		add(buttonPanel, BorderLayout.CENTER);
		add(systemMessage, BorderLayout.SOUTH);
		
		// disable return, reserve and waiting queue button if book is available
		// disable borrow if the book is not available
		if (book.isAvailable()) {
			returnButton.setEnabled(false);
			reserveButton.setEnabled(false);
			waitingQueueButton.setEnabled(false);
		}
		else
			borrowButton.setEnabled(false);
	}
}
