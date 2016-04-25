

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.*;

public class HomeWindowMainTitle extends JPanel {

	public HomeWindowMainTitle() {
		this.setLayout(new FlowLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 4, true));
		makeTitle();
	}

	private void makeTitle() {
		JLabel title = new JLabel(
				"<html>Welcome to ACME Security Systems Inc <br> Keeping you safe since 2016.</html>");
		title.setFont(new Font("Monospaced", Font.PLAIN, 25));
		title.setForeground(Color.BLACK);
		this.add(title);
	}

}
