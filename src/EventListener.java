
/**
 * @title   EIE3320 Lab 2: Library Admin System
 * @author  CHAN Kai Ho 19057769D
 * @author  SZE Kin Sang 19062606D
 * @date    5 Nov 2022
 */

import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;

public class EventListener {
    final static Object row[] = new Object[3];
    final static Object savedindex[] = new Object[1];

    //new Book array
    final static Book loadData[] = {new Book(), new Book(), new Book()};

    public EventListener() {
        
    };

    public static ActionListener add_button(JTable bookrecord, DefaultTableModel model, JTextField isbn, JTextField title, MyLinkedList<Book> bookList) {
        ActionListener add_listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
                //Remove filter
                TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
                sorter.setRowFilter(null);
                bookrecord.setRowSorter(sorter);
                //Check if isbn or title is empty
                if(isbn.getText().equals("") || title.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Error: ISBN and Title required.");
                }
                else {
                    boolean repeat = false;
                    //Going through the table to check if ISBN is repeated or not
                    for(int i=0; i<bookrecord.getRowCount(); i++) {
                        if(bookrecord.getValueAt(i,0).toString().equals(isbn.getText())) {
                            repeat = true;
                            JOptionPane.showMessageDialog(null, "Error: ISBN already exists.");
                        }
                    }
                    //Add to the table
                    if(!repeat) {
                        //Add to book list
                        Book book = new Book();
                        book.setTitle(isbn.getText());
                        book.setISBN(title.getText());
                        bookList.add(book);
                        row[0] = bookList.getLast().getISBN();
                        row[1] = bookList.getLast().getTitle();
                        row[2] = String.valueOf(bookList.getLast().isAvailable());
                        ((DefaultTableModel)bookrecord.getModel()).addRow(row);
                        isbn.setText("");
                        title.setText(""); 
                    }
                }
            }
        };
        return add_listener;
    }

    public static ActionListener load_button(JTable bookrecord, DefaultTableModel model, MyLinkedList<Book> bookList) {
        ActionListener load_listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Remove filter
                TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
                sorter.setRowFilter(null);
                bookrecord.setRowSorter(sorter);
                //Set title and ISBN
                loadData[0].setTitle("HTML How to Program");
                loadData[0].setISBN("0131450913");
                loadData[1].setTitle("C++ How to Program");
                loadData[1].setISBN("0131857576");
                loadData[2].setTitle("Java How to Program");
                loadData[2].setISBN("0132222205");
                for (Book book: loadData) {
                    if (!bookList.contains(book)) {
                        bookList.add(book);
                        row[0] = bookList.getLast().getISBN();
                        row[1] = bookList.getLast().getTitle();
                        row[2] = String.valueOf(bookList.getLast().isAvailable());
                        ((DefaultTableModel)bookrecord.getModel()).addRow(row);
                    }
                    else
                        JOptionPane.showMessageDialog(
                            null, 
                            String.format("Error: \"%s\" already exists.", book.getTitle()),
                            "Error",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        };
        return load_listener;
    }

    public static MouseAdapter bookrecord_click(JTable bookrecord, JTextField isbn, JTextField title, JButton save) {
        MouseAdapter click_adapter = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if(!save.isEnabled()) {
                    //disable set text when save button is enabled
                    int index = bookrecord.getSelectedRow();
                    isbn.setText(bookrecord.getValueAt(index, 0).toString());
                    title.setText(bookrecord.getValueAt(index, 1).toString());
                }
            }
        };
        return click_adapter;
    }

    public static ActionListener edit_button(JTable bookrecord, DefaultTableModel model, JTextField isbn, JTextField title, 
    JButton add, JButton edit, JButton save, JButton delete, JButton search, JButton more, JButton load, JButton displayall,
    JButton displayall_isbn, JButton displayall_title, JButton exit) {
        ActionListener edit_listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Remove filter
                TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
                sorter.setRowFilter(null);
                bookrecord.setRowSorter(sorter);
                //Check if isbn field is empty or not
                if(!(isbn.getText().equals(""))) {
                    int index = 0;
                    boolean match = false;
                    //Check the table to search the title based on the ISBN
                    for(int i=0; i<bookrecord.getRowCount(); i++) {
                        if(bookrecord.getValueAt(i,0).toString().equals(isbn.getText())) {
                            match = true;
                            index = i;
                        }
                    }
                    if(match) {
                        title.setText(bookrecord.getValueAt(index, 1).toString());
                        //local variable used
                        savedindex[0] = index;
                        //disable row selection
                        bookrecord.setRowSelectionAllowed(false);
                        //enable save button
                        save.setEnabled(true); add.setEnabled(false); edit.setEnabled(false); delete.setEnabled(false);
                        search.setEnabled(false); more.setEnabled(false); load.setEnabled(false); displayall.setEnabled(false);
                        displayall_isbn.setEnabled(false); displayall_title.setEnabled(false); exit.setEnabled(false);
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Error: ISBN doesn't exist.");
                    }
                }
            }
        };
        return edit_listener;
    }

    public static ActionListener save_button(JTable bookrecord, DefaultTableModel model, JTextField isbn, JTextField title, 
    MyLinkedList<Book> bookList, JButton add, JButton edit, JButton save, JButton delete, JButton search, JButton more, 
    JButton load, JButton displayall, JButton displayall_isbn, JButton displayall_title, JButton exit) {
        ActionListener save_listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //check if the isbn or title is empty
                if(isbn.getText().equals("") || title.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Error: ISBN and Title required.");
                }
                else {
                    boolean repeat = false;
                    //Going through the table to check if ISBN is repeated or not except the current selected row
                    for(int i=0; i<bookrecord.getRowCount(); i++) {
                        if(Integer.valueOf(savedindex[0].toString()) == i) {
                            continue;
                        }
                        else if(bookrecord.getValueAt(i,0).toString().equals(isbn.getText())) {
                            repeat = true;
                            JOptionPane.showMessageDialog(null, "Error: ISBN already exists.");
                        }
                    }
                    if(!repeat) {
                        int index = Integer.valueOf(savedindex[0].toString());
                        //Edit BookList
                        //Have Bug have fun
                        Book book = new Book();
                        book.setTitle(title.getText());
                        book.setISBN(isbn.getText());
                        System.out.println("Index: " + index);
                        bookList.remove(index);
                        for(Book storedbook: bookList) {
                            System.out.println(storedbook.getISBN());
                        }
                        System.out.println();
                        bookList.add(index, book);
                        for(Book storedbook: bookList) {
                            System.out.println(storedbook.getISBN());
                        }
                        //Edit table based on the bookList
                        row[0] = bookList.get(index).getISBN();
                        row[1] = bookList.get(index).getTitle();
                        row[2] = String.valueOf(bookList.get(index).isAvailable());
                        bookrecord.setValueAt(row[0], index, 0);
                        bookrecord.setValueAt(row[1], index, 1);
                        bookrecord.setValueAt(row[2], index, 2);
                        isbn.setText("");
                        title.setText("");

                        //enable and clear row selection
                        bookrecord.setRowSelectionAllowed(true);
                        bookrecord.clearSelection();
                        //disable save button
                        save.setEnabled(false); add.setEnabled(true); edit.setEnabled(true); delete.setEnabled(true);
                        search.setEnabled(true); more.setEnabled(true); load.setEnabled(true); displayall.setEnabled(true);
                        displayall_isbn.setEnabled(true); displayall_title.setEnabled(true); exit.setEnabled(true);
                    }
                }  
            }
        };
        return save_listener;
    }

    public static ActionListener delete_button(JTable bookrecord, DefaultTableModel model, JTextField isbn, JTextField title, MyLinkedList<Book> bookList) {
        ActionListener delete_listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Remove filter
                TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
                sorter.setRowFilter(null);
                bookrecord.setRowSorter(sorter);
                //Check if isbn field is empty or not
                if(!(isbn.getText().equals(""))) {
                    int index = 0;
                    boolean match = false;
                    //Check the table to search the title based on the ISBN
                    for(int i=0; i<bookrecord.getRowCount(); i++) {
                        if(bookrecord.getValueAt(i,0).toString().equals(isbn.getText())) {
                            match = true;
                            index = i;
                        }
                    }
                    if(match) {
                        //Delete from linkedlist
                        //Have Bug have fun
                        bookList.remove((int)index);
                        for(Book storedbook: bookList) {
                            System.out.println(storedbook.getISBN());
                        }
                        ((DefaultTableModel)bookrecord.getModel()).removeRow(index);
                        isbn.setText("");
                        title.setText("");
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Error: ISBN doesn't exist.");
                    }
                }
            }
        };
        return delete_listener;
    }

    public static ActionListener search_button(JTable bookrecord, DefaultTableModel model, JTextField isbn, JTextField title) {
        ActionListener search_listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Check if the text fields are empty
                if(!(isbn.getText().equals("") && title.getText().equals(""))) {
                    RowFilter<Object, Object> filter = new RowFilter<Object, Object>() {
                        public boolean include(Entry entry) {
                            String book_isbn = (String) entry.getValue(0);
                            String book_title = (String) entry.getValue(1);
                            if((book_isbn.matches("(.*)" + isbn.getText() + "(.*)")) && !(isbn.getText().equals(""))) {
                                return true;
                            }
                            else if((book_title.matches("(.*)" + title.getText() + "(.*)")) && !(title.getText().equals(""))) {
                                return true;
                            }
                            return false;
                        }
                    };
                    TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
                    sorter.setRowFilter(filter);
                    bookrecord.setRowSorter(sorter);
                }
            }
        };
        return search_listener;
    }

    public static ActionListener more_button(JTable bookrecord, MyLinkedList<Book> bookList) {
        ActionListener more_listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (bookrecord.getSelectedRow() == -1) {
                    JOptionPane.showMessageDialog(null, "Book is not selected.", "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                BookDetailPrompt bookFrame = new BookDetailPrompt(bookList.get(bookrecord.getSelectedRow()));
                bookFrame.setVisible(true);
            }
        };
        return more_listener;
    }
}
