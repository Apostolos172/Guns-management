package listener;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;

public class JComboListener implements ItemListener {
	private JComboBox<String> dropdown;

	public JComboListener() {
	}

	public JComboListener(JComboBox<String> c1) {
		this.dropdown = c1;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// System.out.println(e.getSource().toString());
		// System.out.println(e.getItemSelectable().toString());
		// System.out.println(e.SELECTED);
		String x = String.valueOf(this.dropdown.getSelectedItem());
		System.out.println(x);
	}

}
