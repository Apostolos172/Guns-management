package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;

import GUI.Main_GUI;
import GUI.Player;

public class BtnListener implements ActionListener {
	private JFrame frame;
	private String whatToCreate;
	private Player play;

	public BtnListener() {
	}

	public BtnListener(JFrame frame) {
		this.frame = frame;
		this.whatToCreate = "mainPanel";
	}

	public BtnListener(JFrame frame, String whatToCreate) {
		this.frame = frame;
		this.whatToCreate = whatToCreate;
	}

	public BtnListener(JFrame frame, Player play) {
		this.frame = frame;
		this.whatToCreate = "mainPanel";
		this.play = play;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.frame.dispose();
		switch (this.whatToCreate) {
		case "mainPanel":
			this.play.stop();
			try {
				new GUI.EntryGUI();
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
			break;
		case "return":
			try {
				new Main_GUI();
			} catch (ClassNotFoundException | SQLException e2) {
				e2.printStackTrace();
			}
			break;
		default:
			try {
				new GUI.EntryGUI();
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
			break;
		}

	}

}
