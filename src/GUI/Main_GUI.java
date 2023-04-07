package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import listener.BtnListener;
import main.Util;

public class Main_GUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel, southPanel;

	public Main_GUI(String title) {
		super(title);
		makeFrame();
	}

	public Main_GUI() {
		super("Διαχειριστής όπλων");
		makeFrame();
	}

	private void makeFrame() {

		mainPanel = new JPanel(new BorderLayout());
		makeNorthPanel();
		makeSouthPanel();
		JPanel tempPanel = new JPanel();
		tempPanel.setBackground(Color.PINK);
		GUI.setPadding(tempPanel, "DEFAULT");
		mainPanel.add(tempPanel, BorderLayout.SOUTH);

		this.add(mainPanel);

		GUI.setSizeOfTheWindow(this);
		this.setSize(1200, 650);
		this.setVisible(true);
	}

	private void makeSouthPanel() {
		southPanel = new JPanel();
		JLabel title = new JLabel("Διαχείριση εισόδου και εξόδου του οπλισμού");
		southPanel.add(title);
		GUI.setPadding(southPanel, "default");
		southPanel.setBackground(Color.CYAN);
		southPanel.setBackground(Color.white);
		mainPanel.add(southPanel, BorderLayout.NORTH);

	}

	private void makeNorthPanel() {
		makeInputGunsTable();
		makeOutputGunsTable();

		JPanel entryButtonPanel = new JPanel();
		JButton goToSaveEntry = new JButton("Νέα καταχώριση");

		BtnListener btnlistener = new BtnListener(this);
		goToSaveEntry.addActionListener(btnlistener);

		entryButtonPanel.add(goToSaveEntry);
		// mainPanel.add(entryButtonPanel, BorderLayout.SOUTH);
		mainPanel.add(entryButtonPanel, BorderLayout.CENTER);
		GUI.setPadding(entryButtonPanel, "HUGE");

	}

	private void makeInputGunsTable() {
		JPanel inputGuns = new JPanel(new BorderLayout());
		GUI.setPadding(inputGuns, "");

		JLabel l = new JLabel("Είσοδος όπλων στον οπλοβαστό");
		GUI.setPaddingAtJLabel(l);
		inputGuns.add(l, BorderLayout.NORTH);

		String data[][] = { { "G3A3", "101", "Βαδραχάνης", "ΤΕΛΟΣ", Util.getTime() },
				{ "G3A3", "101", "Βαδραχάνης", "ΤΕΛΟΣ", Util.getTime() },
				{ "G3A3", "101", "Βαδραχάνης", "ΤΕΛΟΣ", Util.getTime() },
				{ "G3A3", "101", "Βαδραχάνης", "ΤΕΛΟΣ", Util.getTime() },
				{ "G3A3", "101", "Βαδραχάνης", "ΤΕΛΟΣ", Util.getTime() },
				{ "G3A3", "101", "Βαδραχάνης", "ΤΕΛΟΣ", Util.getTime() },
				{ "G3A3", "101", "Βαδραχάνης", "ΤΕΛΟΣ", Util.getTime() },
				{ "G3A3", "101", "Βαδραχάνης", "ΤΕΛΟΣ", Util.getTime() },
				{ "G3A3", "101", "Βαδραχάνης", "ΤΕΛΟΣ", Util.getTime() },
				{ "G3A3", "101", "Βαδραχάνης", "ΤΕΛΟΣ", Util.getTime() },
				{ "G3A3", "101", "Βαδραχάνης", "ΤΕΛΟΣ", Util.getTime() },
				{ "G3A3", "101", "Βαδραχάνης", "ΤΕΛΟΣ", Util.getTime() },
				{ "G3A3", "101", "Βαδραχάνης", "ΤΕΛΟΣ", Util.getTime() },
				{ "G3A3", "101", "Βαδραχάνης", "ΤΕΛΟΣ", Util.getTime() },
				{ "G3A3", "101", "Βαδραχάνης", "ΤΕΛΟΣ", Util.getTime() },
				{ "G3A3", "101", "Βαδραχάνης", "ΤΕΛΟΣ", Util.getTime() },
				{ "G3A3", "101", "Βαδραχάνης", "ΤΕΛΟΣ", Util.getTime() },
				{ "G3A3", "101", "Βαδραχάνης", "ΤΕΛΟΣ", Util.getTime() },
				{ "G3A3", "101", "Βαδραχάνης", "ΤΕΛΟΣ", Util.getTime() },
				{ "G3A3", "101", "Βαδραχάνης", "ΤΕΛΟΣ", Util.getTime() },
				{ "G3A3", "101", "Βαδραχάνης", "ΤΕΛΟΣ", Util.getTime() },
				{ "G3A3", "101", "Βαδραχάνης", "ΤΕΛΟΣ", Util.getTime() },
				{ "G3A3", "101", "Βαδραχάνης", "ΤΕΛΟΣ", Util.getTime() },
				{ "G3A3", "101", "Βαδραχάνης", "ΤΕΛΟΣ", Util.getTime() },
				{ "G3A3", "101", "Βαδραχάνης", "ΤΕΛΟΣ", Util.getTime() }, };
		String column[] = { "Τύπος", "No", "Θ", "Αιτιολογία", "Ώρα" };
		JTableCustom jt = new JTableCustom(data, column);
		JScrollPane sp = new JScrollPane(jt);
		inputGuns.add(sp, BorderLayout.CENTER);
		mainPanel.add(inputGuns, BorderLayout.LINE_START);
	}

	private void makeOutputGunsTable() {
		JPanel outputGuns = new JPanel(new BorderLayout());
		GUI.setPadding(outputGuns, "");

		JLabel l = new JLabel("Έξοδος όπλων από τον οπλοβαστό");
		GUI.setPaddingAtJLabel(l);
		outputGuns.add(l, BorderLayout.NORTH);

		String data[][] = { { "G3A3", "101", "Παπαδημητρίου", "ΚΠ", Util.getTime() },
				{ "G3A3", "101", "Παπαδημητρίου", "ΚΠ", Util.getTime() },
				{ "G3A3", "101", "Παπαδημητρίου", "ΚΠ", Util.getTime() },
				{ "G3A3", "101", "Παπαδημητρίου", "ΚΠ", Util.getTime() },
				{ "G3A3", "101", "Παπαδημητρίου", "ΚΠ", Util.getTime() },
				{ "G3A3", "101", "Παπαδημητρίου", "ΚΠ", Util.getTime() },
				{ "G3A3", "101", "Παπαδημητρίου", "ΚΠ", Util.getTime() },
				{ "G3A3", "101", "Παπαδημητρίου", "ΚΠ", Util.getTime() },
				{ "G3A3", "101", "Παπαδημητρίου", "ΚΠ", Util.getTime() },
				{ "G3A3", "101", "Παπαδημητρίου", "ΚΠ", Util.getTime() },
				{ "G3A3", "101", "Παπαδημητρίου", "ΚΠ", Util.getTime() },
				{ "G3A3", "101", "Παπαδημητρίου", "ΚΠ", Util.getTime() },
				{ "G3A3", "101", "Παπαδημητρίου", "ΚΠ", Util.getTime() },
				{ "G3A3", "101", "Παπαδημητρίου", "ΚΠ", Util.getTime() },
				{ "G3A3", "101", "Παπαδημητρίου", "ΚΠ", Util.getTime() },
				{ "G3A3", "101", "Παπαδημητρίου", "ΚΠ", Util.getTime() },
				{ "G3A3", "101", "Παπαδημητρίου", "ΚΠ", Util.getTime() },
				{ "G3A3", "101", "Παπαδημητρίου", "ΚΠ", Util.getTime() },
				{ "G3A3", "101", "Παπαδημητρίου", "ΚΠ", Util.getTime() },
				{ "G3A3", "101", "Παπαδημητρίου", "ΚΠ", Util.getTime() },
				{ "G3A3", "101", "Παπαδημητρίου", "ΚΠ", Util.getTime() },
				{ "G3A3", "101", "Παπαδημητρίου", "ΚΠ", Util.getTime() },
				{ "G3A3", "101", "Παπαδημητρίου", "ΚΠ", Util.getTime() },
				{ "G3A3", "101", "Παπαδημητρίου", "ΚΠ", Util.getTime() },
				{ "G3A3", "101", "Παπαδημητρίου", "ΚΠ", Util.getTime() }, };
		String column[] = { "Τύπος", "No", "Οπλίτης", "Αιτιολογία", "Ώρα" };
		JTableCustom jt = new JTableCustom(data, column);
		JScrollPane sp = new JScrollPane(jt);
		outputGuns.add(sp, BorderLayout.CENTER);
		mainPanel.add(outputGuns, BorderLayout.LINE_END);
	}

}
