package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import listener.AdditionBtnListener;
import listener.BtnListener;
import listener.JComboListener;
import main.ConnectionWithDatabase;

public class EntryGUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel;
	private String path;
	private ConnectionWithDatabase conn;

	public EntryGUI() throws ClassNotFoundException, SQLException {
		super("Νέα καταχώριση");
		this.path = "C:\\databases\\guns.db";
		this.path = "guns.db";
		this.conn = new ConnectionWithDatabase("jdbc:sqlite:" + path);

		makeEntryPanel();
		
		this.conn.CloseConnection();

		this.setSize(600, 700);
		this.setVisible(true);
	}

	private void makeEntryPanel() throws ClassNotFoundException, SQLException {
		mainPanel = new JPanel(new BorderLayout());

		makeCenterPanel();

		JButton btnReturn = new JButton("Επιστροφή");
		BtnListener listener = new BtnListener(this, "return");
		btnReturn.addActionListener(listener);
		mainPanel.add(btnReturn, BorderLayout.SOUTH);

		GUI.setPadding(mainPanel, "DEFAULT");
		this.add(mainPanel);
	}

	private void makeCenterPanel() throws ClassNotFoundException, SQLException {
		JPanel centralPanel = new JPanel();
		GridLayout layout = new GridLayout(5, 1, 0, 25);
		centralPanel.setLayout(layout);
		centralPanel.setBackground(Color.orange);

		JPanel tempPanel = null;

		// ΟΠΛΙΤΗΣ
		tempPanel = new JPanel();
		GUI.setPadding(tempPanel, "BIG");

		// array of string containing soldiers
		// String s1[] = { "Παπαδημητρίου", "Παπαδόπουλος", "Βασιλείου", "Αντωνίου"
		// };
		String[] s1 = conn.getSoldiersSurnames().toArray(new String[0]);

		// create dropdown
		JComboBox<String> c1 = new JComboBox<>(s1);

		// add ItemListener
		c1.addItemListener(new JComboListener(c1));

		// create label
		JLabel l = new JLabel("Επίλεξε οπλίτη προς ή από υπηρεσία");
		// set color of text
		l.setForeground(Color.WHITE);

		tempPanel.add(l);
		tempPanel.add(c1);

		centralPanel.add(tempPanel);

		// ΕΙΣΟΔΟΣ - ΕΞΟΔΟΣ
		tempPanel = new JPanel();
		GUI.setPadding(tempPanel, "BIG");

		// array of string containing type of the movement
		String s2[] = { "Είσοδος", "Έξοδος" };

		// create dropdown
		JComboBox<String> c2 = new JComboBox<>(s2);

		// add ItemListener
		c2.addItemListener(new JComboListener(c2));

		// create label
		JLabel l0 = new JLabel("Επίλεξε είσοδος ή έξοδος όπλου στον ή από, αντίστοιχα, οπλοβαστό");
		// set color of text
		l0.setForeground(Color.WHITE);

		tempPanel.add(l0);
		tempPanel.add(c2);

		centralPanel.add(tempPanel);

		// ΑΙΤΙΟΛΟΓΙΑ
		tempPanel = new JPanel();
		GUI.setPadding(tempPanel, "BIG");

		// array of string containing rationales
		String s4[] = { "ΚΠ", "ΠΕΡ", "ΤΑΕ", "ΑΛΛΟ" };

		// create dropdown
		JComboBox<String> c4 = new JComboBox<>(s4);

		// add ItemListener
		c4.addItemListener(new JComboListener(c4));

		// create label
		JLabel l000 = new JLabel("Επίλεξε αιτιολογία εισόδου ή εξόδου του όπλου");
		// set color of text
		l000.setForeground(Color.WHITE);

		tempPanel.add(l000);
		tempPanel.add(c4);

		centralPanel.add(tempPanel);

		// ΘΑΛΑΜΟΦΥΛΑΚΑΣ
		tempPanel = new JPanel();
		GUI.setPadding(tempPanel, "BIG");

		// array of string containing soldiers
		// String s3[] = { "Παπαδημητρίου", "Παπαδόπουλος", "Βασιλείου", "Αντωνίου"
		// };
		String s3[] = conn.getSoldiersSurnames().toArray(new String[0]);

		// create dropdown
		JComboBox<String> c3 = new JComboBox<>(s3);

		// add ItemListener
		c3.addItemListener(new JComboListener(c3));

		// create label
		JLabel l00 = new JLabel("Επίλεξε οπλίτη που τυγχάνει θαλαμοφύλακας λόχου στρατηγείου");
		// set color of text
		l00.setForeground(Color.WHITE);

		tempPanel.add(l00);
		tempPanel.add(c3);

		centralPanel.add(tempPanel);

		// button to add the entry
		tempPanel = new JPanel();
		GUI.setPadding(tempPanel, "BIG");

		JButton btn = new JButton("Ενημέρωσε το βιβλίο εισόδου - εξόδου με την νέα εγγραφή");

		AdditionBtnListener additionBtnList = new AdditionBtnListener(c1, c2, c3, c4);
		btn.addActionListener(additionBtnList);
		tempPanel.add(btn);
		centralPanel.add(tempPanel);

		mainPanel.add(centralPanel, BorderLayout.CENTER);
	}

}
