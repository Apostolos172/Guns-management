package listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import GUI.EntryGUI;
import GUI.Main_GUI;
import main.ConnectionWithDatabase;
import main.Util;

public class AdditionBtnListener implements ActionListener {
	private JComboBox<String> soldierSurnameChoices, typeChoices, rationaleChoices, quartermasterChoices;
	private String soldierSurname, type, rationale, quartermaster;
	private JFrame entryGui;

	public AdditionBtnListener(JComboBox<String> c1, JComboBox<String> c2, JComboBox<String> c3, JComboBox<String> c4) {

		this.soldierSurnameChoices = c1;
		this.typeChoices = c2;
		this.rationaleChoices = c4;
		this.quartermasterChoices = c3;

	}

	public AdditionBtnListener(JComboBox<String> c1, JComboBox<String> c2, JComboBox<String> c3, JComboBox<String> c4,
			EntryGUI entryGUI) {

		this.soldierSurnameChoices = c1;
		this.typeChoices = c2;
		this.rationaleChoices = c4;
		this.quartermasterChoices = c3;
		this.entryGui = entryGUI;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println();
		this.soldierSurname = (String) soldierSurnameChoices.getSelectedItem();
		System.out.println(soldierSurname);
		this.type = (String) typeChoices.getSelectedItem();
		System.out.println(type);
		this.rationale = (String) rationaleChoices.getSelectedItem();
		System.out.println(rationale);
		this.quartermaster = (String) quartermasterChoices.getSelectedItem();
		System.out.println(quartermaster);
		System.out.println();

		String path = "C:\\databases\\guns.db";
		path = "guns.db";

		if (this.soldierSurname.compareTo(this.quartermaster) == 0) {
			String msg = "Wtf. Ρε πουστόνεο συγκεντρώσου. \n"
					+ "Δεν γίνεται να είναι ένα άτομο σε 2 υπηρεσίες. \nΞαναδοκίμασε!";
			JOptionPane.showMessageDialog(entryGui, msg);
		} else {
			switch (type) {
			case "Έξοδος":

				try {
					ConnectionWithDatabase conn = new ConnectionWithDatabase("jdbc:sqlite:" + path);

					// we need
					String type = "output";
					String rationale = this.rationale;
					String date = Util.getTime();
					Integer gun = conn.getGunIdHavingSurname(soldierSurname);
					// System.out.println("gun " + gun);
					Integer quartermaster = conn.getSoldierIdHavingSurname(this.quartermaster);
					// System.out.println(quartermaster);

					boolean isGunOut = conn.isGunOut(gun);
					if (!isGunOut) {
						conn.insertAMovement(type, rationale, date, gun, quartermaster);
						this.entryGui.dispose();
						try {
							new Main_GUI();
						} catch (ClassNotFoundException | SQLException e1) {
							e1.printStackTrace();
						}
					} else {
						String msg = "Wtf. Ρε πουστόνεο συγκεντρώσου. \n"
								+ "Δεν γίνεται να βγει όπλο που είναι ήδη έξω. \nΞαναδοκίμασε!";
						JOptionPane.showMessageDialog(entryGui, msg);
					}

					conn.CloseConnection();
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}

				break;
			default: // Είσοδος

				try {
					ConnectionWithDatabase conn = new ConnectionWithDatabase("jdbc:sqlite:" + path);

					// we need
					String type = "input";
					String rationale = this.rationale;
					String date = Util.getTime();
					Integer gun = conn.getGunIdHavingSurname(soldierSurname);
					// System.out.println("gun " + gun);
					Integer quartermaster = conn.getSoldierIdHavingSurname(this.quartermaster);
					// System.out.println(quartermaster);

					boolean isGunOut = conn.isGunOut(gun);
					if (!isGunOut) {
						String msg = "Wtf. Ρε πουστόνεο συγκεντρώσου. \n"
								+ "Δεν γίνεται να μπει όπλο στον οπλοβαστό που είναι ήδη μέσα. \nΞαναδοκίμασε!";
						// new GUI.Confirmation_GUI(msg);
						JOptionPane.showMessageDialog(entryGui, msg);
					} else {
						conn.insertAMovement(type, rationale, date, gun, quartermaster);
						this.entryGui.dispose();
						try {
							new Main_GUI();
						} catch (ClassNotFoundException | SQLException e1) {
							e1.printStackTrace();
						}
					}

					conn.CloseConnection();
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}

				break;
			}

		}

	}

}
