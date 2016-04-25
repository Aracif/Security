


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SystemConstructionConsole extends JPanel {
	private FlowLayout lay;
	private JComponent[] comp;

	public SystemConstructionConsole() {
		this.setLayout(new FlowLayout());
		Border b = BorderFactory.createLineBorder(Color.decode("#00BFFF"), 1, true);
		this.setBorder(BorderFactory.createTitledBorder(b, "Status"));
		this.setPreferredSize(new Dimension(100, 125));
		comp = new JComponent[3];
		createConsole();
	}

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
