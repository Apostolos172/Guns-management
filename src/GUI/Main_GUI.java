package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import listener.BtnListener;
import main.ConnectionWithDatabase;

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
		title.setForeground(Color.BLACK);
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
		entryButtonPanel.setOpaque(false);
		JButton goToSaveEntry = new JButton("Νέα καταχώριση");
		
		JPanel tempP = new CustomJPanel(new GridLayout(4,1,0,63), "default");
		
		JPanel tempP1 = new JPanel();
		tempP1.setOpaque(false);
		JLabel guns = new JLabel("Δύναμη οπλισμού");
		guns.setForeground(Color.BLACK);
		JTextField gunsfield = new JTextField();
		try {
			gunsfield.setText(conn.getGunsNumber());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		gunsfield.setEditable(false);
		tempP1.add(guns);
		tempP1.add(gunsfield);
		//GUI.setPadding(tempP1, "");
		tempP.add(tempP1);
		
		JPanel tempP2 = new JPanel();
		tempP2.setOpaque(false);
		JLabel gunsMissing = new JLabel("Απόντα");
		gunsMissing.setForeground(Color.BLACK);
		JTextField gunsMissingfield = new JTextField();
		try {
			gunsMissingfield.setText(conn.getGunsMissingNumber());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		gunsMissingfield.setEditable(false);
		tempP2.add(gunsMissing);
		tempP2.add(gunsMissingfield);
		//GUI.setPadding(tempP2, "");
		tempP.add(tempP2);
		
		JPanel tempP3 = new JPanel();
		tempP3.setOpaque(false);
		JLabel gunsHere = new JLabel("Εντός του οπλοβαστού");
		gunsHere.setForeground(Color.BLACK);
		JTextField gunsHerefield = new JTextField();
		int count = Integer.parseInt(gunsfield.getText());
		int mis = Integer.parseInt(gunsMissingfield.getText());
		Integer res = count - mis;
		gunsHerefield.setText(res.toString());
		gunsHerefield.setEditable(false);
		tempP3.add(gunsHere);
		tempP3.add(gunsHerefield);
		//GUI.setPadding(tempP3, "");
		tempP.add(tempP3);
		
		//entryButtonPanel.add(tempP);

		BtnListener btnlistener = new BtnListener(this, this.play);
		goToSaveEntry.addActionListener(btnlistener);

		entryButtonPanel.add(goToSaveEntry);
		tempP.add(entryButtonPanel);
		// mainPanel.add(entryButtonPanel, BorderLayout.SOUTH);
		//mainPanel.add(entryButtonPanel, BorderLayout.CENTER);
		mainPanel.add(tempP, BorderLayout.CENTER);
		//GUI.setPadding(entryButtonPanel, "HUGE");
		GUI.setPadding(tempP, "HUGE");

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

		String data[][] = null;
		try {
			data = conn.getOutputMovements();
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		String data[][] = { { "G3A3", "101", "Παπαδημητρίου", "ΚΠ", Util.getTime() },
//				{ "G3A3", "101", "Παπαδημητρίου", "ΚΠ", Util.getTime() },
//				{ "G3A3", "101", "Παπαδημητρίου", "ΚΠ", Util.getTime() },
//				{ "G3A3", "101", "Παπαδημητρίου", "ΚΠ", Util.getTime() },
//				{ "G3A3", "101", "Παπαδημητρίου", "ΚΠ", Util.getTime() },
//				{ "G3A3", "101", "Παπαδημητρίου", "ΚΠ", Util.getTime() },
//				{ "G3A3", "101", "Παπαδημητρίου", "ΚΠ", Util.getTime() },
//				{ "G3A3", "101", "Παπαδημητρίου", "ΚΠ", Util.getTime() },
//				{ "G3A3", "101", "Παπαδημητρίου", "ΚΠ", Util.getTime() },
//				{ "G3A3", "101", "Παπαδημητρίου", "ΚΠ", Util.getTime() },
//				{ "G3A3", "101", "Παπαδημητρίου", "ΚΠ", Util.getTime() },
//				{ "G3A3", "101", "Παπαδημητρίου", "ΚΠ", Util.getTime() },
//				{ "G3A3", "101", "Παπαδημητρίου", "ΚΠ", Util.getTime() },
//				{ "G3A3", "101", "Παπαδημητρίου", "ΚΠ", Util.getTime() },
//				{ "G3A3", "101", "Παπαδημητρίου", "ΚΠ", Util.getTime() },
//				{ "G3A3", "101", "Παπαδημητρίου", "ΚΠ", Util.getTime() },
//				{ "G3A3", "101", "Παπαδημητρίου", "ΚΠ", Util.getTime() },
//				{ "G3A3", "101", "Παπαδημητρίου", "ΚΠ", Util.getTime() },
//				{ "G3A3", "101", "Παπαδημητρίου", "ΚΠ", Util.getTime() },
//				{ "G3A3", "101", "Παπαδημητρίου", "ΚΠ", Util.getTime() },
//				{ "G3A3", "101", "Παπαδημητρίου", "ΚΠ", Util.getTime() },
//				{ "G3A3", "101", "Παπαδημητρίου", "ΚΠ", Util.getTime() },
//				{ "G3A3", "101", "Παπαδημητρίου", "ΚΠ", Util.getTime() },
//				{ "G3A3", "101", "Παπαδημητρίου", "ΚΠ", Util.getTime() },
//				{ "G3A3", "101", "Παπαδημητρίου", "ΚΠ", Util.getTime() }, };
		String column[] = { "Τύπος", "No", "Οπλίτης", "Αιτιολογία", "Ώρα" };
		JTableCustom jt = new JTableCustom(data, column);
		JScrollPane sp = new JScrollPane(jt);
		sp.setOpaque(false);
		sp.getViewport().setOpaque(false);
		outputGuns.add(sp, BorderLayout.CENTER);
		mainPanel.add(outputGuns, BorderLayout.LINE_END);
	}

}
