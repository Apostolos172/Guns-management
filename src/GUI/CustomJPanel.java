package GUI;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class CustomJPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image img;

	public CustomJPanel() {
	}

	public CustomJPanel(String image) {
		setBackgroundImage(image);
	}

	public CustomJPanel(LayoutManager layout) {
		super(layout);
	}

	public CustomJPanel(LayoutManager layout, String image) {
		super(layout);
		setBackgroundImage(image);
	}

	private void setBackgroundImage(String image) {
		switch (image) {
		case "sky":
			img = Toolkit.getDefaultToolkit().getImage("media/images/orangeSky.jpg");
			break;
		default:
			img = Toolkit.getDefaultToolkit().getImage("media/images/armyBig.jpg");
			break;
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, null);
	}

}
