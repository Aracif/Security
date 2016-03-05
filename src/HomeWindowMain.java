package src;

import javax.swing.*;
import java.awt.*;

public class HomeWindowMain extends JFrame {

	public HomeWindowMain() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridLayout grid = new GridLayout(2, 1);
		this.setLayout(grid);
		this.setOpacity(1.0f);
		this.setTitle("Security Systems Inc.");
		this.setBackground(Color.white);
		this.pack();
		this.setSize(800, 600);
		this.add(new HomeWindowMainTitle());
		this.add(new HomeWindowMainPanel(this));
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}
