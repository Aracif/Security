package src;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SystemConstructionPanelRisk extends JPanel {
	private Border blackline;
	private FlowLayout lay;
	private JComponent[] comp;

	public SystemConstructionPanelRisk() {
		blackline = BorderFactory.createLineBorder(Color.decode("#00BFFF"));
		lay = new FlowLayout();
		this.setLayout(lay);
		this.setBorder(BorderFactory.createTitledBorder(blackline, "Risk"));
		this.setPreferredSize(new Dimension(100, 125));
		comp = new JComponent[3];
		// this.setBackground(Color.decode("#C6D2C6"));
		radioButtons();
	}

	private void radioButtons() {
		JRadioButton low = new JRadioButton("Low Risk");
		JRadioButton none = new JRadioButton("No Risk");
		none.setSelected(true);
		JRadioButton high = new JRadioButton("High Risk");
		ButtonGroup bg = new ButtonGroup();
		comp[0] = none;
		comp[1] = low;
		comp[2] = high;
		bg.add(none);
		bg.add(low);
		bg.add(high);
		this.add(none);
		this.add(low);
		this.add(high);
	}

	public JComponent[] getArray() {
		return comp;
	}
}
