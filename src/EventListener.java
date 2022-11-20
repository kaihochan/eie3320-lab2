
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

    public EventListener() {

    };

    public static ActionListener add_button(JTable bookrecord, DefaultTableModel model, JTextField isbn, JTextField title) {
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
                        row[0] = isbn.getText();
                        row[1] = title.getText();
                        row[2] = "true";
                        ((DefaultTableModel)bookrecord.getModel()).addRow(row);
                        isbn.setText("");
                        title.setText("");
                    }
                }
            }
        };
        return add_listener;
    }

    public static ActionListener load_button(JTable bookrecord, DefaultTableModel model, JTextField isbn, JTextField title) {
        ActionListener load_listener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //Remove filter
                TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
                sorter.setRowFilter(null);
                bookrecord.setRowSorter(sorter);
                Boolean exist = false;
                //Same as add function
                for(int i=0; i<bookrecord.getRowCount(); i++) {
                    if(bookrecord.getValueAt(i,0).toString().equals("0131450913")) {
                        exist = true;
                        JOptionPane.showMessageDialog(null, "Error: Test data already exists.");
                    }
                }
                if(!exist) {
                    row[0] = "0131450913";
                    row[1] = "HTML How to Program";
                    row[2] = "true";
                    ((DefaultTableModel)bookrecord.getModel()).addRow(row);
                }
                
                exist = false;
                for(int i=0; i<bookrecord.getRowCount(); i++) {
                    if(bookrecord.getValueAt(i,0).toString().equals("0131857576")) {
                        exist = true;
                        JOptionPane.showMessageDialog(null, "Error: Test data already exists.");
                    }
                }
                if(!exist) {
                    row[0] = "0131857576";
                    row[1] = "C++ How to Program";
                    row[2] = "true";
                    ((DefaultTableModel)bookrecord.getModel()).addRow(row);
                }
                
                exist = false;
                for(int i=0; i<bookrecord.getRowCount(); i++) {
                    if(bookrecord.getValueAt(i,0).toString().equals("0132222205")) {
                        exist = true;
                        JOptionPane.showMessageDialog(null, "Error: Test data already exists.");
                    }
                }
                if(!exist) {
                    row[0] = "0132222205";
                    row[1] = "Java How to Program";
                    row[2] = "true";
                    ((DefaultTableModel)bookrecord.getModel()).addRow(row);
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
    JButton add, JButton edit, JButton save, JButton delete, JButton search, JButton more, JButton load, JButton displayall,
    JButton displayall_isbn, JButton displayall_title, JButton exit) {
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
                        row[0] = isbn.getText();
                        row[1] = title.getText();
                        row[2] = "true";
                        bookrecord.setValueAt(row[0], Integer.valueOf(savedindex[0].toString()), 0);
                        bookrecord.setValueAt(row[1], Integer.valueOf(savedindex[0].toString()), 1);
                        bookrecord.setValueAt(row[2], Integer.valueOf(savedindex[0].toString()), 2);
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

    public static ActionListener delete_button(JTable bookrecord, DefaultTableModel model, JTextField isbn, JTextField title) {
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
}
