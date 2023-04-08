package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import listener.BtnListener;
import main.ConnectionWithDatabase;
import main.Util;

public class Main_GUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel mainPanel, southPanel;
	private String path;
	private ConnectionWithDatabase conn;
	private Player play = new Player();


	public Main_GUI(String title) throws ClassNotFoundException, SQLException {
		super(title);
		this.path = "guns.db";
		this.conn = new ConnectionWithDatabase("jdbc:sqlite:" + path);
		makeFrame();
		this.conn.CloseConnection();
	}

	public Main_GUI() throws ClassNotFoundException, SQLException {
		super("Διαχειριστής όπλων");
		this.path = "guns.db";
		this.conn = new ConnectionWithDatabase("jdbc:sqlite:" + path);
		makeFrame();
		this.conn.CloseConnection();
	}

	private void makeFrame() {

		String filePath = "media/ThePianoGuys-AThousandYears.wav";
		play.playMusic(filePath);
		
		mainPanel = new JPanel(new BorderLayout());
		makeNorthPanel();
		makeSouthPanel();
		JPanel tempPanel = new JPanel();
		tempPanel.setBackground(Color.PINK);
		GUI.setPadding(tempPanel, "DEFAULT");
		mainPanel.add(tempPanel, BorderLayout.SOUTH);

		//this.add(mainPanel);
		this.setContentPane(mainPanel);

		pack();
		GUI.setSizeOfTheWindow(this);
		this.setSize(1200, 650);
		this.setVisible(true);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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

		JPanel entryButtonPanel = new CustomJPanel("default");
		JButton goToSaveEntry = new JButton("Νέα καταχώριση");

		BtnListener btnlistener = new BtnListener(this, this.play);
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

		String[][] dataList = null;
		try {
			dataList = conn.getInputMovements();
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		String data[][] = { { "G3A3", "101", "Βαδραχάνης", "ΤΕΛΟΣ", Util.getTime() },
//				{ "G3A3", "101", "Βαδραχάνης", "ΤΕΛΟΣ", Util.getTime() },
//				{ "G3A3", "101", "Βαδραχάνης", "ΤΕΛΟΣ", Util.getTime() },
//				{ "G3A3", "101", "Βαδραχάνης", "ΤΕΛΟΣ", Util.getTime() },
//				{ "G3A3", "101", "Βαδραχάνης", "ΤΕΛΟΣ", Util.getTime() },
//				{ "G3A3", "101", "Βαδραχάνης", "ΤΕΛΟΣ", Util.getTime() },
//				{ "G3A3", "101", "Βαδραχάνης", "ΤΕΛΟΣ", Util.getTime() },
//				{ "G3A3", "101", "Βαδραχάνης", "ΤΕΛΟΣ", Util.getTime() },
//				{ "G3A3", "101", "Βαδραχάνης", "ΤΕΛΟΣ", Util.getTime() },
//				{ "G3A3", "101", "Βαδραχάνης", "ΤΕΛΟΣ", Util.getTime() },
//				{ "G3A3", "101", "Βαδραχάνης", "ΤΕΛΟΣ", Util.getTime() },
//				{ "G3A3", "101", "Βαδραχάνης", "ΤΕΛΟΣ", Util.getTime() },
//				{ "G3A3", "101", "Βαδραχάνης", "ΤΕΛΟΣ", Util.getTime() },
//				{ "G3A3", "101", "Βαδραχάνης", "ΤΕΛΟΣ", Util.getTime() },
//				{ "G3A3", "101", "Βαδραχάνης", "ΤΕΛΟΣ", Util.getTime() },
//				{ "G3A3", "101", "Βαδραχάνης", "ΤΕΛΟΣ", Util.getTime() },
//				{ "G3A3", "101", "Βαδραχάνης", "ΤΕΛΟΣ", Util.getTime() },
//				{ "G3A3", "101", "Βαδραχάνης", "ΤΕΛΟΣ", Util.getTime() },
//				{ "G3A3", "101", "Βαδραχάνης", "ΤΕΛΟΣ", Util.getTime() },
//				{ "G3A3", "101", "Βαδραχάνης", "ΤΕΛΟΣ", Util.getTime() },
//				{ "G3A3", "101", "Βαδραχάνης", "ΤΕΛΟΣ", Util.getTime() },
//				{ "G3A3", "101", "Βαδραχάνης", "ΤΕΛΟΣ", Util.getTime() },
//				{ "G3A3", "101", "Βαδραχάνης", "ΤΕΛΟΣ", Util.getTime() },
//				{ "G3A3", "101", "Βαδραχάνης", "ΤΕΛΟΣ", Util.getTime() },
//				{ "G3A3", "101", "Βαδραχάνης", "ΤΕΛΟΣ", Util.getTime() }, };
		String column[] = { "Τύπος", "No", "Θ", "Αιτιολογία", "Ώρα" };
		JTableCustom jt = new JTableCustom(dataList, column);
		
		// JTable j = new JTable(dataList, column);
		JScrollPane sp = new JScrollPane(jt);
		sp.setOpaque(false);
		sp.getViewport().setOpaque(false);
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
		sp.setOpaque(false);
		sp.getViewport().setOpaque(false);
		outputGuns.add(sp, BorderLayout.CENTER);
		mainPanel.add(outputGuns, BorderLayout.LINE_END);
	}

}
