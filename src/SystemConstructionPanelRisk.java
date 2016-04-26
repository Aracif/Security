

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * System Construction Panel Risk class will held responsible of the risk panel
 * It will contain Radio Button for the each type of risk
 *
 */
public class SystemConstructionPanelRisk extends JPanel {
	// Border to create the black line border around the panel
	private Border blackline;
	// FlowLayOut how the lay out of the panel will be
	private FlowLayout lay;
	// An Array of JComponent will hold the Radio Button of Risk
	private JComponent[] comp;

	/**
	 * Constructor Risk Panel
	 */
	public SystemConstructionPanelRisk() {
		// Setting the color for the border
		blackline = BorderFactory.createLineBorder(Color.decode("#00BFFF"));
		// Initialize the FlowLayout
		lay = new FlowLayout();
		// set the layout
		this.setLayout(lay);
		// Using the black border to set the layout of Risk Panel
		this.setBorder(BorderFactory.createTitledBorder(blackline, "Risk"));
		// Changing the size of the panel
		this.setPreferredSize(new Dimension(100, 125));
		// Initialize the array with 3 spaces
		comp = new JComponent[3];
		// this.setBackground(Color.decode("#C6D2C6"));
		radioButtons();
	}
	
	/**
	 * Creating the JRadioButton for Risk 
	 * Add them to the Array
	 */
	private void radioButtons() {
		// Declare and Initialize low risk
		JRadioButton low = new JRadioButton("Low Risk");
		// Declare and Initialize no risk
		JRadioButton none = new JRadioButton("No Risk");
		// Setting No Risk as selected for startout 
		none.setSelected(true);
		// Declare and Initialize high risk
		JRadioButton high = new JRadioButton("High Risk");
		// Initiazlie grouping button class
		ButtonGroup bg = new ButtonGroup();
		// Add all three risks to the array
		comp[0] = none;
		comp[1] = low;
		comp[2] = high;
		// Group them together
		bg.add(none);
		bg.add(low);
		bg.add(high);
		this.add(none);
		this.add(low);
		this.add(high);
	}

	// Return the Array of JComponent that contains JRadiotButton
	public JComponent[] getArray() {
		return comp;
	}
}
