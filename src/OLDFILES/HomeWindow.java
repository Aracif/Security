package OLDFILES;

import javax.swing.*;
import java.awt.*;

public class HomeWindow extends JFrame{

	
	public HomeWindow(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout grid = new GridBagLayout();
		this.setLayout(grid);
		this.setOpacity(1.0f);
		this.setTitle("Security Systems Inc.");	
		this.setBackground(Color.white);
		addTitle(grid);
		addHome(grid);
		this.pack();
		this.setLocation(700, 500);
		this.setSize(800, 600);
		this.setVisible(true);		
	}
	

	private void addTitle(GridBagLayout grid){
		GridBagConstraints c = new GridBagConstraints();
		JLabel mainTitle = new JLabel("<html>Welcome to Security Systems Inc.<br>" + 
				 "Keeping you safe for 25 years</html>");
		c.gridheight = 10;
		c.gridwidth = 10;
		
		c.weighty = 0;
		c.weightx = 0;
		c.ipady = 4;
		grid.setConstraints(mainTitle, c);
		this.add(mainTitle, c);
	}
	
	private void addHome(GridBagLayout grid){
		GridBagConstraints c = new GridBagConstraints();
		HomeWindowPanel homePanel = new HomeWindowPanel();
		c.gridheight = 10;
		c.gridwidth = 10;
		c.gridy = 10;
		//c.weighty = 1;
		c.weightx = 0.1;
		//c.ipady = 14;
		//c.ipadx = 14;
		grid.setConstraints(homePanel, c);
		this.add(homePanel, c);
		
	}
	

}
