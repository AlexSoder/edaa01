package phonebook;
import javax.swing.*;

import java.awt.event.*;

public class RemoveMenu extends JMenuItem implements ActionListener {
	private PhoneBook phoneBook;
	private PhoneBookGUI gui;
	
	public RemoveMenu(PhoneBook phoneBook, PhoneBookGUI gui) {
		super("Remove");
		this.phoneBook = phoneBook;
		this.gui = gui;
		addActionListener(this);
	}
	
	 public void actionPerformed(ActionEvent e) {
		 gui.setText("");
		 String name = JOptionPane.showInputDialog("Enter Name");
			if(name != null) {
				if (phoneBook.remove(name)) {
					gui.setText("Success! " + name +  " removed.");
				} else {
					gui.setText("Failure! " + name + " not found!");
				}
			}
			else {
				gui.setText("");
			}
	 }
}
