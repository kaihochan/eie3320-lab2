
/**
 * @title   EIE3320 Lab 2: Library Admin System
 * @author  CHAN Kai Ho 19057769D
 * @author  SZE Kin Sang 19062606D
 * @date    15 Nov 2022
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableRowSorter;
import java.util.List;

public class MainPrompt extends JFrame {
	private MyLinkedList<Book> bookList = new MyLinkedList<>();
    private Book testData[] = {new Book(), new Book(), new Book()};
	
    private final String studentsInfo = "Stduent Name and ID: Chan Kai Ho  (19057769D)\nStduent Name and ID: Sze Kin Sang (19062606D)\n";
    private JTextArea content = new JTextArea(studentsInfo + String.valueOf(new Date()) + "\n\n");
    
    private BookTableModel bookModel = new BookTableModel();
    private JTable bookTable = new JTable(bookModel);
    private JScrollPane bookScrollPane = new JScrollPane(bookTable);

    private JLabel ISBNLabel = new JLabel("ISBN: ");
    private JTextField ISBNField = new JTextField(10);
    private JLabel titleLabel = new JLabel("Title: ");
    private JTextField titleField = new JTextField(10);

    private JButton addButton = new JButton("Add");
    private JButton editButton = new JButton("Edit");
    private JButton saveButton = new JButton("Save");
    private JButton deleteButton = new JButton("Delete");
    private JButton searchButton = new JButton("Search");
    private JButton moreButton = new JButton("More>>");

    private JButton loadTestDataButton = new JButton("Load Test Data");
    private JButton displayAllButton = new JButton("Display All");
    private JButton sortByISBNButton = new JButton("Display All by ISBN");
    private JButton sortByTitleButton = new JButton("Display All by Title");
    private JButton exitButton = new JButton("Exit");
    
    private TableRowSorter<BookTableModel> sorter = new TableRowSorter<BookTableModel>(bookModel);
    
    private boolean reversedISBN = false;
    private boolean reversedTitle = false;
    private int selectedIndex = -1;
    
    public MainPrompt() {
    	setLayout(new BorderLayout());
		setTitle("Library Admin System");
		setSize(700, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// JTable configuration
		bookTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		bookTable.getTableHeader().setReorderingAllowed(false);
		
		
		// JTable mouse listener
		bookTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if(!saveButton.isEnabled()) {
                    //disable set text when save button is enabled
                    ISBNField.setText(bookList.get(bookTable.convertRowIndexToModel(bookTable.getSelectedRow())).getISBN());
                    titleField.setText(bookList.get(bookTable.convertRowIndexToModel(bookTable.getSelectedRow())).getTitle());
                }
            }
        });
		
		// Add, JButton action listener
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateContent();
				if (ISBNField.getText().isEmpty() || titleField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Error: ISBN and Title required.");
                    return;
                }
				for (Iterator<Book> it = bookList.iterator(); it.hasNext();) {
					if (it.next().getISBN().equals(ISBNField.getText())) {
						JOptionPane.showMessageDialog(null, "Error: ISBN already exists.");
						return;
					}
				}
				Book book = new Book();
                book.setTitle(titleField.getText());
                book.setISBN(ISBNField.getText());
				bookModel.add(book);
				ISBNField.setText("");
				titleField.setText(""); 
			}
		});
		
		// Edit, JButton action listener
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateContent();
				if ((bookTable.getSelectedRow() == -1) && (ISBNField.getText().isEmpty())) {
                    JOptionPane.showMessageDialog(null, "Book is not selected.", "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }
				if (ISBNField.getText().isEmpty()) {
					selectedIndex = bookTable.convertRowIndexToModel(bookTable.getSelectedRow());
					toggleButtonForEdit(true);
					return;
				}
				for (Iterator<Book> it = bookList.iterator(); it.hasNext();) {
					Book book = it.next();
					if (book.getISBN().equals(ISBNField.getText())) {
						selectedIndex = bookList.indexOf(book);
						titleField.setText(bookList.get(selectedIndex).getTitle());
						toggleButtonForEdit(true);
						return;
					}
				}
				JOptionPane.showMessageDialog(null, "Error: ISBN doesn't exist.");
			}
		});
		
		// Save, JButton action listener
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateContent();
				if (ISBNField.getText().isEmpty() || titleField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Error: ISBN and Title required.");
                    return;
                }
				for (Iterator<Book> it = bookList.iterator(); it.hasNext();) {
					Book book = it.next();
					if ((bookList.indexOf(book) != selectedIndex) && book.getISBN().equals(ISBNField.getText())) {
						JOptionPane.showMessageDialog(null, "Error: ISBN already exists.");
						return;
					}
				}
				Book book = bookList.get(selectedIndex);
				selectedIndex = -1;
				book.setISBN(ISBNField.getText());
				book.setTitle(titleField.getText());
				ISBNField.setText("");
				titleField.setText("");
				toggleButtonForEdit(false);
			}
		});
		
		// Delete, JButton action listener
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateContent();
				if ((bookTable.getSelectedRow() == -1) && ISBNField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Book is not selected.", "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }
				if (ISBNField.getText().isEmpty()) {
					bookModel.remove(bookTable.getSelectedRow());
					return;
				}
				else {
					int index = 0;
					for (Iterator<Book> it = bookList.iterator(); it.hasNext(); index++) {
						Book book = it.next();
						if (book.getISBN().equals(ISBNField.getText())) {
							bookModel.remove(index);
							return;
						}
					}
				}
				JOptionPane.showMessageDialog(null, "Book is not found for this ISBN.", "Error", JOptionPane.WARNING_MESSAGE);
			}
		});
		
		// Search, JButton action listener (NOT MOVED YET)
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateContent();
				if (ISBNField.getText().isEmpty() && titleField.getText().isEmpty()) {
                    return;
                }
                RowFilter<Object, Object> filter = new RowFilter<Object, Object>() {
                    public boolean include(Entry entry) {
                        String book_isbn = (String) entry.getValue(0);
                        String book_title = (String) entry.getValue(1);
                        if((book_isbn.matches("(.*)" + ISBNField.getText() + "(.*)")) && !(ISBNField.getText().equals(""))) {
                            return true;
                        }
                        else if((book_title.matches("(.*)" + titleField.getText() + "(.*)")) && !(titleField.getText().equals(""))) {
                            return true;
                        }
                        return false;
                    }
                };
                sorter.setRowFilter(filter);
                bookTable.setRowSorter(sorter);
			}
		});
		
		// More>>, JButton action listener
		moreButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateContent();
				if ((bookTable.getSelectedRow() == -1) && (ISBNField.getText().isEmpty())) {
                    JOptionPane.showMessageDialog(null, "Book is not selected.", "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }
				BookDetailPrompt frame;
				if (ISBNField.getText().isEmpty()) {
					frame = new BookDetailPrompt(bookList.get(bookTable.convertRowIndexToModel(bookTable.getSelectedRow())));
					frame.setVisible(true);
					return;
				}
				else {
					int index = 0;
					for (Iterator<Book> it = bookList.iterator(); it.hasNext(); index++) {
						Book book = it.next();
						if (book.getISBN().equals(ISBNField.getText())) {
							frame = new BookDetailPrompt(bookList.get(index));
							frame.setVisible(true);
							return;
						}
					}
				}
				JOptionPane.showMessageDialog(null, "Book is not found for this ISBN.", "Error", JOptionPane.WARNING_MESSAGE);
			}
		});
		
		testData[0].setTitle("HTML How to Program");
		testData[0].setISBN("0131450913");
		testData[1].setTitle("C++ How to Program");
		testData[1].setISBN("0131857576");
		testData[2].setTitle("Java How to Program");
		testData[2].setISBN("0132222205");
		
		// Load Test Data, JButton action listener
		loadTestDataButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateContent();
				for (Book book: testData) {
					if (!bookList.contains(book)) {
						bookModel.add(book);
					}
					else
						JOptionPane.showMessageDialog(null, "Error: \"" + book.getTitle() + "\" already exists.", "Error", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		// Display All, JButton action listener (NOT STARTED)
		displayAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateContent();
                sorter.setSortKeys(null);
                sorter.setRowFilter(null);
                bookTable.setRowSorter(sorter);
			}
		});
		
		// Display All by ISBN, JButton action listener
        sortByISBNButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateContent();
                if(!reversedISBN) {
                    List<RowSorter.SortKey> sortKeys = new ArrayList<>(1);
                    sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
                    sorter.setSortKeys(sortKeys);
                    bookTable.setRowSorter(sorter);
                    reversedISBN = true;
                }
                else {
                    List<RowSorter.SortKey> sortKeys = new ArrayList<>(1);
                    sortKeys.add(new RowSorter.SortKey(0, SortOrder.DESCENDING));
                    sorter.setSortKeys(sortKeys);
                    bookTable.setRowSorter(sorter);
                    reversedISBN = false;
                }
            }
        });
		
		// Display All by Title, JButton action listener
        sortByTitleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateContent();
                if(!reversedTitle) {
                    List<RowSorter.SortKey> sortKeys = new ArrayList<>(2);
                    sortKeys.add(new RowSorter.SortKey(1, SortOrder.ASCENDING));
                    sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
                    sorter.setSortKeys(sortKeys);
                    bookTable.setRowSorter(sorter);
                    reversedTitle = true;
                }
                else {
                    List<RowSorter.SortKey> sortKeys = new ArrayList<>(2);
                    sortKeys.add(new RowSorter.SortKey(1, SortOrder.DESCENDING));
                    sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
                    sorter.setSortKeys(sortKeys);
                    bookTable.setRowSorter(sorter);
                    reversedTitle = false;
                }
            }
        });
		
		// Exit, JButton action listener
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		// Add fields and labels to input set panel
		JPanel inputSet = new JPanel(new FlowLayout(FlowLayout.CENTER));
		inputSet.add(ISBNLabel);
		inputSet.add(ISBNField);
		inputSet.add(titleLabel);
		inputSet.add(titleField);
		
		// Add buttons to button set 1 panel
		// Disable save button by default
		JPanel buttonSet1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		saveButton.setEnabled(false);
		buttonSet1.add(addButton);
		buttonSet1.add(editButton);
		buttonSet1.add(saveButton);
		buttonSet1.add(deleteButton);
		buttonSet1.add(searchButton);
		buttonSet1.add(moreButton);
		
		// Add buttons to button set 2 panel
		JPanel buttonSet2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		buttonSet2.add(loadTestDataButton);
		buttonSet2.add(displayAllButton);
		buttonSet2.add(sortByISBNButton);
		buttonSet2.add(sortByTitleButton);
		buttonSet2.add(exitButton);
		
		// Add all button sets to bottom panel
		// Three rows with different content
		JPanel bottomPanel = new JPanel(new GridLayout(3, 1));
		bottomPanel.add(inputSet);
		bottomPanel.add(buttonSet1);
		bottomPanel.add(buttonSet2);
		
		// Add all content in the frame
		add(content, BorderLayout.NORTH);
		add(bookScrollPane, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);
    }
    
    // Update the top content text area for each button click
    private void updateContent() {
        content.setText(studentsInfo + String.valueOf(new Date()) + "\n\n");
    }
    
    // Toggle on/off other buttons when Save button is off/on respectively 
    private void toggleButtonForEdit(boolean b) {
    	addButton.setEnabled(!b);
		editButton.setEnabled(!b);
		saveButton.setEnabled(b);
		deleteButton.setEnabled(!b);
		searchButton.setEnabled(!b);
		moreButton.setEnabled(!b);
		loadTestDataButton.setEnabled(!b);
		displayAllButton.setEnabled(!b);
		sortByISBNButton.setEnabled(!b);
		sortByTitleButton.setEnabled(!b);
		exitButton.setEnabled(!b);
		bookTable.setRowSelectionAllowed(!b);
    }
    
    // Inner class for the bookTable's model
    public class BookTableModel extends AbstractTableModel {
    	private String[] columnNames = {"ISBN", "Title", "Available"};

		@Override
		public int getRowCount() {
			return bookList.size();
		}

		@Override
		public int getColumnCount() {
			return columnNames.length;
		}

		@Override
		public Object getValueAt(int row, int col) {
			switch(col) {
				case 0:	return bookList.get(row).getISBN();
				case 1:	return bookList.get(row).getTitle();
				case 2: return String.valueOf(bookList.get(row).isAvailable());
			}
			return null;
		}
    	
		public String getColumnName(int col) {
			return columnNames[col];
		}
		
		public Class getColumnClass(int col) {
			return String.class;
		}
		
		public boolean isCellEditable(int row, int col) {
			return false;
		}
		
		public void add(Book book) {
			bookList.add(book);
			fireTableRowsInserted(bookList.size(), bookList.size());
		}
		
		public void remove(int index) {
			bookList.remove(bookTable.convertRowIndexToModel(index));
			fireTableRowsDeleted(index, index);
		}
    }
    
}
