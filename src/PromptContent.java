/**
 * Write a description of class UI here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

public class PromptContent extends JFrame {
    public PromptContent() {
        //Panel for displaying the student information
        JPanel upperpanel = new JPanel(new GridLayout(1,1));
        JTextArea content = new JTextArea(
        "Stduent Name and ID: Chan Kai Ho  (19057769D)\n" +
        "Stduent Name and ID: Sze Kin Sang (19062606D)\n" +
        String.valueOf(new Date())+ "\n\n");
        content.setEditable(false);
        upperpanel.add(content);
        
        //Panel for displaying the JScrollPane
        JPanel middlepanel = new JPanel();
        JTable bookrecord = new JTable();
        DefaultTableModel model = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
               //all cells should return false to protect the cells from direct editing
               return false;
            }
        }; 
        Object column[] = { "ISBN", "Title", "Available"};
        final Object row[] = new Object[3];
        model.setColumnIdentifiers(column);
        bookrecord.setModel(model);

        JScrollPane scrollpane = new JScrollPane(bookrecord);
        scrollpane.setPreferredSize(new Dimension(680, 270));
        middlepanel.add(scrollpane);
        
        //Panel for displaying the buttons
        JPanel bottompanel = new JPanel(new GridLayout(3,1));
        //Three rows with different content
        //Input Field
        JPanel inputfield = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JTextField isbn = new JTextField(10);
        JTextField title = new JTextField(10);
        inputfield.add(new JLabel("ISBN: "));
        inputfield.add(isbn);
        inputfield.add(new JLabel("Title: "));
        inputfield.add(title);
        bottompanel.add(inputfield);
        //Button Set 1
        JPanel buttonset1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton add = new JButton("Add");
        JButton edit = new JButton("Edit");
        JButton save = new JButton("Save");
        save.setEnabled(false);
        final Object savedindex[] = new Object[1];
        JButton delete = new JButton("Delete");
        JButton search = new JButton("Search");
        JButton more = new JButton("More>>");
        buttonset1.add(add);
        buttonset1.add(edit);
        buttonset1.add(save);
        buttonset1.add(delete);
        buttonset1.add(search);
        buttonset1.add(more);
        bottompanel.add(buttonset1);
        //Button Set 2
        JPanel buttonset2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton load = new JButton("Load Test Data");
        JButton displayall = new JButton("Display All");
        JButton displayall_isbn = new JButton("Display All by ISBN");
        JButton displayall_title = new JButton("Display All by Title");
        JButton exit = new JButton("Exit");
        buttonset2.add(load);
        buttonset2.add(displayall);
        buttonset2.add(displayall_isbn);
        buttonset2.add(displayall_title);
        buttonset2.add(exit);
        bottompanel.add(buttonset2);
        
        //Add functions to the buttons
        add.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
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
        });
        
        load.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
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
        });
        
        bookrecord.addMouseListener(new MouseAdapter() {
            //Click event on the table to set text of ISBN and title
            public void mouseClicked(MouseEvent e) {
                if(!save.isEnabled()) {
                    //disable set text when save button is enabled
                    int index = bookrecord.getSelectedRow();
                    isbn.setText(bookrecord.getValueAt(index, 0).toString());
                    title.setText(bookrecord.getValueAt(index, 1).toString());
                }
            }
        });
        
        edit.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
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
        });
        
        save.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
                //check if the isbn or title is empty
                if(isbn.getText().equals("") || title.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "ISBN and Title required.");
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
                            JOptionPane.showMessageDialog(null, "ISBN already exists.");
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
        });
        
        delete.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) {
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
        });
        
        //Panel for output all the content
        JPanel outputpanel = new JPanel(new BorderLayout(0,0));
        outputpanel.add(upperpanel,BorderLayout.NORTH);
        outputpanel.add(middlepanel,BorderLayout.CENTER);
        outputpanel.add(bottompanel,BorderLayout.SOUTH);
        add(outputpanel);
    }
}
