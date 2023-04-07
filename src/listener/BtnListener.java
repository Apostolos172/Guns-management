package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;

import GUI.Main_GUI;

public class BtnListener implements ActionListener {
	private JFrame frame;
	private String whatToCreate;
	
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

	@Override
	public void actionPerformed(ActionEvent e) {
		this.frame.dispose();
		switch (this.whatToCreate) {
		case "mainPanel":
			try {
				new GUI.EntryGUI();
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
			break;
		case "return":
			new Main_GUI();
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
