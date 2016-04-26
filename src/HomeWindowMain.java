

import javax.swing.*;
import java.awt.*;

/**
 * 
 * The Main Window for Security System
 *
 */
public class HomeWindowMain extends JFrame {

	/**
	 * Constructor of this class
	 */
	public HomeWindowMain() {
		// Close the window when press exit
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Setting the grid layout of for main window
		GridLayout grid = new GridLayout(2, 1);
		// Set the layout using grid
		this.setLayout(grid);
		// Set the opacity of the main window
		this.setOpacity(1.0f);
		// Set the title of main window
		this.setTitle("ACME SECURITY Inc.");
		// Set the back ground of main window as white
		this.setBackground(Color.white);
		// Set the main window to be sized and fit to the preference size along with its subcomponent
		this.pack();
		// Setting the size of main window
		this.setSize(800, 600);
		// Adding title of main window
		this.add(new HomeWindowMainTitle());
		this.add(new HomeWindowMainPanel(this));
		this.setLocationRelativeTo(null);
		// Set it to visible
		this.setVisible(true);
	}

}
