


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * Console for the GUI Constuctor
 *
 */
public class SystemConstructionConsole extends JPanel {
	private FlowLayout lay;
	private JComponent[] comp;

	/**
	 * Constructor for the console GUI
	 */
	public SystemConstructionConsole() {
		this.setLayout(new FlowLayout());
		Border b = BorderFactory.createLineBorder(Color.decode("#00BFFF"), 1, true);
		this.setBorder(BorderFactory.createTitledBorder(b, "Status"));
		this.setPreferredSize(new Dimension(100, 125));
		comp = new JComponent[3];
		createConsole();
	}

	/**
	 * Creating the console GUI
	 */
	private void createConsole() {
		JTextPane status = new JTextPane();
		status.setContentType("text/html");
		status.setEditable(false);
		status.setPreferredSize(new Dimension(300, 240));
		JScrollPane p = new JScrollPane(status);
		comp[0] = status;
		comp[1] = p;
		this.add(p);
	}

	protected JComponent[] getArray() {
		return comp;
	}

	protected JPanel getPanel() {
		return this;
	}
}
