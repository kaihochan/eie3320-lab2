
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
        upperpanel.add(content);
        
        //Panel for displaying the JScrollPane
        JPanel middlepanel = new JPanel();
        JTable bookrecord = new JTable();
        DefaultTableModel model = new DefaultTableModel(); 
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
        
        //Add function to the buttons
        add.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                if(isbn.getText().equals("") || title.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "ISBN and Title required.");
                }
                else {
                    boolean repeat = false;
                    for(int i=0; i<model.getRowCount(); i++) {
                        if(model.getValueAt(i,0).toString().equals(isbn.getText())) {
                            repeat = true;
                            JOptionPane.showMessageDialog(null, "ISBN already exists.");
                        }
                    }
                    if(!repeat) {
                        row[0] = isbn.getText();
                        row[1] = title.getText();
                        row[2] = "true";
                        model.addRow(row);
                        isbn.setText("");
                        title.setText("");
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
