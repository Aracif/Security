package src;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SystemConstructionPanelInput extends JPanel {
	private Border blackline;
	private FlowLayout lay;
	private JComponent[] comp;

	public SystemConstructionPanelInput() {
		blackline = BorderFactory.createLineBorder(Color.decode("#00BFFF"));
		lay = new FlowLayout();
		comp = new JComponent[1];
		this.setLayout(lay);
		this.setBorder(BorderFactory.createTitledBorder(blackline, "Room Name"));
		this.setPreferredSize(new Dimension(200, 125));
		addInputFields();
	}

	private void addInputFields() {
		JTextField roomName = new JTextField("", 15);
		comp[0] = roomName;
		this.add(roomName);
	}

	public JComponent[] getArray() {
		return comp;
	}

	protected JPanel getPanel() {
		return this;
	}

}