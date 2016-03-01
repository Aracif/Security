package OLDFILES;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.*;
import v1.Business;
import v1.SystemConstruction;


//COMPONENT ORDER
// 0=Text Field
// 1=Add button
// 2=JList

public class HomeWindowPanel extends JPanel {
	private JComponent[] comp;
	private DefaultListModel<Business> model;
	
	
	
	public HomeWindowPanel(){
		model = new DefaultListModel<Business>();
		comp = new JComponent[8];
		this.setBorder(LineBorder.createBlackLineBorder());
		GridBagLayout lay = new GridBagLayout();
		this.setLayout(lay);
		this.setPreferredSize(new Dimension(400,400));
		this.setBackground(Color.white);
		addJList(lay);
		addButton(lay);
		minusButton(lay);
		selectButton(lay);
		loadButton(lay);
		textInput(lay);
		
		
		
	}
	
	private void addJList(GridBagLayout grid){
		
		
		GridBagConstraints c = new GridBagConstraints();
		JList<Business> j = new JList<>(model);
		JScrollPane listScroll = new JScrollPane(j);	
		int vericalPolicy = JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED;
		listScroll.setVerticalScrollBarPolicy(vericalPolicy);
		

		j.setSelectedIndex(0);
		j.setBorder(LineBorder.createGrayLineBorder());
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridheight = 10;
		c.gridwidth = 10;
		c.gridx = 1;
		c.gridy = 2;
		c.insets = new Insets(5,4,4,4);
		grid.setConstraints(j, c);
		this.add(listScroll, c);
		comp[2] = j;

	}
	
	private void addButton(GridBagLayout grid){
		GridBagConstraints c = new GridBagConstraints();
		JButton button = new JButton("                +                ");
		
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JTextField text = (JTextField)comp[0];
				Business busi = new Business(text.getText());
				model.addElement(busi);	
			}
		});
		
		c.fill = GridBagConstraints.HORIZONTAL;	
		c.gridheight = 15;
		c.gridwidth = 10;
		c.gridx = 1;
		c.gridy = 11;
		//c.insets = new Insets(0,0,60,0);
		c.weightx = 0.1;
		grid.setConstraints(button, c);
		this.add(button, c);
		comp[1] = button;
	}
	
	
	private void minusButton(GridBagLayout grid){
		GridBagConstraints c = new GridBagConstraints();
		JButton button = new JButton("              -                ");
		
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("Deleted a business");
						
			}
		});
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridheight = 10;
		c.gridwidth = 10;
		c.gridx = 5;
		c.gridy = 10;
		c.insets = new Insets(0,0,50,0);
		//c.weighty = 0.1;
		c.weightx = 0.1;
		grid.setConstraints(button, c);
		this.add(button, c);		
	}
	
	private void textInput(GridBagLayout grid){
		GridBagConstraints c = new GridBagConstraints();
		JTextField text = new JTextField("Add a business");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridheight = 10;
		c.gridwidth = 10;
		c.gridx = 0;
		c.gridy = 10;
		c.weightx = 0.1;
		//c.weighty =0.1;
		c.insets = new Insets(270,4,4,4);
		grid.setConstraints(text, c);
		this.add(text, c);
		comp[0] = text;
	}
	
	private void selectButton(GridBagLayout grid){
		GridBagConstraints c = new GridBagConstraints();
		JButton button = new JButton("  Select  ");
		
		
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				JList list = (JList)comp[2];
				System.out.println(list);
				Object currentSelection = list.getSelectedValue();
				if(currentSelection==null){
					System.out.println("Nothing was selected");
					
				}
				else{
					System.out.println(currentSelection);
					SystemConstruction win = new SystemConstruction((Business)currentSelection);
					
					
				}
				}
		});
		
		c.fill = GridBagConstraints.HORIZONTAL;		
		c.gridheight = 10;
		c.gridwidth = 10;
		c.weightx = 0.1;
		c.gridx = 3;
		c.gridy = 6;
		grid.setConstraints(button, c);
		this.add(button, c);	
		
	}
	
	private void loadButton(GridBagLayout grid){
		GridBagConstraints c = new GridBagConstraints();
		JButton button = new JButton("  Load  ");
		
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("Load a file");
			}
		});
		
		c.fill = GridBagConstraints.HORIZONTAL;		
		c.gridheight = 10;
		c.gridwidth = 10;
		//c.weighty = .01;
		c.insets = new Insets(200,0,60,0);
		c.weightx = 0.1;
		c.gridx = 5;
		c.gridy =10;
		grid.setConstraints(button, c);
		this.add(button, c);	
		
	}
	
	
	

}
