package phonebook;
import javax.swing.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class AddMenu extends JMenuItem implements ActionListener {
	private PhoneBook pb;
	private PhoneBookGUI gui;
	
	public AddMenu(PhoneBook phoneBook, PhoneBookGUI gui) {
		super("Add");
		this.pb = phoneBook;
		this.gui = gui;
		addActionListener(this);
	}
	
	 public void actionPerformed(ActionEvent e) {
		 gui.setText("");
		 String name = JOptionPane.showInputDialog("Enter Name");
		if(name != null) {
			String nbr = JOptionPane.showInputDialog("Enter Number");
			pb.put(name, nbr);
			gui.setText("Success! " + name + " added.");
		} else {
			gui.setText("");
		}
	 }
}
