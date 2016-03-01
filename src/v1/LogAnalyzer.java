package v1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LogAnalyzer extends JFrame{
	private JPanel westPanel;
	private JPanel northPanel;
	private JPanel southPanel;
	private JPanel centerPanel;
	private JLabel labelName;
	private JLabel changingLabel;
	private JTextPane logDisplay;
	private JScrollPane scrollPane;
	private JFrame currentFrame;
	private Business currentBusiness;
	private File currentLogFile;
	
	public LogAnalyzer(JFrame parentFrame, Business cB) throws IOException{
		this.getContentPane().setLayout(new BorderLayout());
		currentFrame = this;
		makeWestPanel();
		makeNorthPanel();
		makeSouthPanel();
		makeCenterPanel();
		currentFrame.setPreferredSize(new Dimension(800, 600));
		currentBusiness = cB;
		readInFile(currentBusiness.getFile());
		this.setLocation(parentFrame.getLocation());
		if(logDisplay.getText()!=""){
			changingLabel.setText(currentBusiness.getName() + " log file has been successfully loaded.");
		}
		this.pack();
		this.setVisible(true);
	}
	
	private void makeWestPanel(){
		westPanel = new JPanel();
		westPanel.setLayout(new BoxLayout(westPanel,BoxLayout.Y_AXIS));
		westPanel.setPreferredSize(new Dimension(250, currentFrame.getHeight()));
		westPanel.setBorder(BorderFactory.createLineBorder(new Color(0,45,255), 2, true));
		currentFrame.add(westPanel, BorderLayout.WEST);
	}
	
	private void makeNorthPanel(){
		northPanel = new JPanel(new FlowLayout());
		northPanel.setBorder(BorderFactory.createLineBorder(new Color(0,45,235), 2, true));		
		currentFrame.add(northPanel, BorderLayout.NORTH);
	}
	
	private void makeSouthPanel(){
		southPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		labelName = new JLabel("Status/Info: ");
		changingLabel = new JLabel("");
		southPanel.add(labelName);
		southPanel.add(changingLabel);
		southPanel.setBorder(BorderFactory.createLineBorder(new Color(0,65,205), 2, true));
		currentFrame.add(southPanel,BorderLayout.SOUTH);
	}
	
	private void makeCenterPanel(){
		centerPanel = new JPanel(new FlowLayout());
		centerPanel.setBorder(BorderFactory.createLineBorder(new Color(0,65,205), 2, true));
		logDisplay = new JTextPane();
		logDisplay.setContentType("text/html");
		logDisplay.setPreferredSize(new Dimension(500,500));
		scrollPane = new JScrollPane(logDisplay);		
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		centerPanel.add(scrollPane);
		currentFrame.add(centerPanel,BorderLayout.CENTER);
	}
	
	private void readInFile(File logFile) throws IOException{
		BufferedReader read = new BufferedReader(new FileReader(logFile));
		String currentLine;
		String text = "";
		while((currentLine=read.readLine())!=null){
			text += currentLine+"\n";
		}
		logDisplay.setText(text);

	}

}
