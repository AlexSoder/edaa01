package phonebook;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class FindNameMenu extends JMenuItem implements ActionListener {

	private PhoneBook pb;
	private PhoneBookGUI view;
	
	public FindNameMenu(PhoneBook pb, PhoneBookGUI view) {
		super("Find name(s)");
		this.pb = pb;
		this.view = view;
		addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		view.setText("");
		String number = JOptionPane.showInputDialog("Enter Number");
		if(number != null) {
			List<String> names = pb.findNames(number);
			if(names == null) {
				view.setText(number + " not found!");
			} else {
				String s = number + ":" + "\t"  + names.get(0);
				for(int i = 1; i < names.size(); i++) {
					s = s + "\n" + "\t" + names.get(i);
				}
				view.setText(s);
			}
		} else {
			view.setText("");
		}
	}

}
