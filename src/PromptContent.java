
/**
 * @title   EIE3320 Lab 2: Library Admin System
 * @author  CHAN Kai Ho 19057769D
 * @author  SZE Kin Sang 19062606D
 * @date    5 Nov 2022
 */

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.Date;

public class PromptContent extends JFrame {
    private JTextArea content = new JTextArea(
        "Stduent Name and ID: Chan Kai Ho  (19057769D)\n" +
        "Stduent Name and ID: Sze Kin Sang (19062606D)\n" +
        String.valueOf(new Date())+ "\n\n");

    private JTable bookrecord = new JTable();
    private DefaultTableModel model = new DefaultTableModel() {
        public boolean isCellEditable(int row, int column) {
           //all cells should return false to protect the cells from direct editing
           return false;
        }
    };
    private Object column[] = { "ISBN", "Title", "Available"};
    private JScrollPane scrollpane = new JScrollPane(bookrecord);

    private JTextField isbn = new JTextField(10);
    private JTextField title = new JTextField(10);

    private JButton add = new JButton("Add");
    private JButton edit = new JButton("Edit");
    private JButton save = new JButton("Save");
    private JButton delete = new JButton("Delete");
    private JButton search = new JButton("Search");
    private JButton more = new JButton("More>>");

    private JButton load = new JButton("Load Test Data");
    private JButton displayall = new JButton("Display All");
    private JButton displayall_isbn = new JButton("Display All by ISBN");
    private JButton displayall_title = new JButton("Display All by Title");
    private JButton exit = new JButton("Exit");

    public PromptContent() {
        //Panel for displaying the student information
        JPanel upperpanel = new JPanel(new GridLayout(1,1)); 
        content.setEditable(false);
        upperpanel.add(content);
        
        //Panel for displaying the JScrollPane
        JPanel middlepanel = new JPanel();
        model.setColumnIdentifiers(column);
        bookrecord.setModel(model);

        scrollpane.setPreferredSize(new Dimension(680, 270));
        middlepanel.add(scrollpane);
        
        //Panel for displaying the buttons
        JPanel bottompanel = new JPanel(new GridLayout(3,1));
        //Three rows with different content
        //Input Field panel
        JPanel inputfield = new JPanel(new FlowLayout(FlowLayout.CENTER));
        inputfield.add(new JLabel("ISBN: "));
        inputfield.add(isbn);
        inputfield.add(new JLabel("Title: "));
        inputfield.add(title);
        bottompanel.add(inputfield);
        //Button Set 1 panel
        JPanel buttonset1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        //Disable save button
        save.setEnabled(false);
        buttonset1.add(add);
        buttonset1.add(edit);
        buttonset1.add(save);
        buttonset1.add(delete);
        buttonset1.add(search);
        buttonset1.add(more);
        bottompanel.add(buttonset1);
        //Button Set 2 panel
        JPanel buttonset2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonset2.add(load);
        buttonset2.add(displayall);
        buttonset2.add(displayall_isbn);
        buttonset2.add(displayall_title);
        buttonset2.add(exit);
        bottompanel.add(buttonset2);
        
        //Add functions to the buttons
        add.addActionListener(EventListener.add_button(bookrecord, model, isbn, title));
        load.addActionListener(EventListener.load_button(bookrecord, model, isbn, title));
        bookrecord.addMouseListener(EventListener.bookrecord_click(bookrecord, isbn, title, save));
        edit.addActionListener(EventListener.edit_button(bookrecord, model,isbn, title, 
        add, edit, save, delete, search, more, load, displayall,
        displayall_isbn, displayall_title, exit));
        save.addActionListener(EventListener.save_button(bookrecord, model,isbn, title, 
        add, edit, save, delete, search, more, load, displayall,
        displayall_isbn, displayall_title, exit));
        delete.addActionListener(EventListener.delete_button(bookrecord, model, isbn, title));
        search.addActionListener(EventListener.search_button(bookrecord, model, isbn, title));
        
        //Panel for output all the content
        JPanel outputpanel = new JPanel(new BorderLayout(0,-5));
        outputpanel.add(upperpanel,BorderLayout.NORTH);
        outputpanel.add(middlepanel,BorderLayout.CENTER);
        outputpanel.add(bottompanel,BorderLayout.SOUTH);
        add(outputpanel);
    }
}