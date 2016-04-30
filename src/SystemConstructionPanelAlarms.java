

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * Creating the panel for the Alarms
 *
 */
public class SystemConstructionPanelAlarms extends JPanel {
	private Border blackline;
	private FlowLayout lay;
	private JComponent[] comp;

	/**
	 * Constructor the panel of Alarms
	 */
	public SystemConstructionPanelAlarms() {
		comp = new JComponent[7];
		blackline = BorderFactory.createLineBorder(Color.decode("#00BFFF"));
		lay = new FlowLayout();
		this.setLayout(lay);
		this.setBorder(BorderFactory.createTitledBorder(blackline, "Alarms"));
		this.setPreferredSize(new Dimension(175, 150));
		radioButtons();
	}

	/**
	 * Creating the JButton for each individual alarm 
	 * Put them into an component array
	 */
	private void radioButtons() {

		JCheckBox none = new JCheckBox("No Alarm");
		JCheckBox fire = new JCheckBox("Fire");
		JCheckBox win = new JCheckBox("Window");
		JCheckBox door = new JCheckBox("Door");
		JCheckBox chem = new JCheckBox("Chemical");
		JCheckBox water = new JCheckBox("Flood");
		JCheckBox zom = new JCheckBox("Zombie Apocalypse");
		comp[0] = none;
		comp[1] = fire;
		comp[2] = win;
		comp[3] = door;
		comp[4] = chem;
		comp[5] = water;
		comp[6] = zom;
		none.setSelected(true);
		this.add(none);
		this.add(chem);
		this.add(fire);
		this.add(win);
		this.add(door);
		this.add(water);
		this.add(zom);
	}

	public JComponent[] getComponentArray() {
		return comp;
	}

}
