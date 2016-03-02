package v1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LogAnalyzer extends JFrame{
	private JPanel westPanel;
	private JPanel northPanel;
	private JPanel southPanel;
	private JPanel centerPanel;
	private JPanel buttonPanel;
	private JPanel searchPanel;
	private JLabel labelName;
	private JLabel changingLabel;
	private JLabel searchLabel;
	private JTextField searchField;
	private JButton back;
	private JButton edit;
	private JButton save;
	private JTextPane logDisplay;
	private JScrollPane scrollPane;
	private JFrame currentFrame;
	private Business currentBusiness;
	private File currentLogFile;
	private BufferedReader read;
	
	public LogAnalyzer(JFrame parentFrame, Business cB) throws IOException{
		this.getContentPane().setLayout(new BorderLayout());
		currentFrame = this;
		makeWestPanel();
		makeNorthPanel();
		makeSouthPanel();
		makeCenterPanel();
		currentFrame.setPreferredSize(new Dimension(800, 600));
		currentBusiness = cB;
		currentLogFile = currentBusiness.getFile();
		read = new BufferedReader(new FileReader(currentLogFile));	
		readInFile();
		this.setLocation(parentFrame.getLocation());
		if(logDisplay.getText()!=""){
			changingLabel.setText(currentBusiness.getName() + " log file has been successfully loaded.");
		}
		
		this.pack();
		this.setVisible(true);
	}
	
	private void makeWestPanel(){
		back = new JButton("Back");
		edit = new JButton("Edit");
		save = new JButton("Save");
		save.setEnabled(false);
		searchLabel = new JLabel("Search");
		searchField = new JTextField();
		Box buttonBox = Box.createHorizontalBox();
		Box searchBox = Box.createHorizontalBox();
		buttonBox.add(back);
		buttonBox.add(edit);
		buttonBox.add(save);
		searchBox.add(searchLabel);
		searchBox.add(searchField);
		
		searchPanel = new JPanel();
		searchPanel.setLayout(new FlowLayout());
		buttonPanel = new JPanel();
		searchPanel.add(searchBox);
		buttonPanel.add(buttonBox);
		westPanel = new JPanel();
		westPanel.setLayout(new BoxLayout(westPanel,BoxLayout.Y_AXIS));
		westPanel.setPreferredSize(new Dimension(250, currentFrame.getHeight()));
		westPanel.setBorder(BorderFactory.createLineBorder(new Color(0,45,255), 2, true));
		westPanel.add(Box.createRigidArea(new Dimension(0,25)));
		westPanel.add(searchBox);
		westPanel.add(Box.createRigidArea(new Dimension(0,425)));
		westPanel.add(buttonPanel);
		currentFrame.add(westPanel, BorderLayout.WEST);
		addListeners();
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
		logDisplay.setEditable(false);
		logDisplay.setContentType("text/html");
		logDisplay.setPreferredSize(new Dimension(500,500));
		scrollPane = new JScrollPane(logDisplay);		
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		centerPanel.add(scrollPane);
		currentFrame.add(centerPanel,BorderLayout.CENTER);
	}
	
	private void readInFile() throws IOException{	
		String currentLine;
		String text = "";
		while((currentLine=read.readLine())!=null){
			text += currentLine+"\n";
		}
		logDisplay.setText(text);
	}
	
	private void addListeners(){
		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				currentFrame.dispose();
			}
		});
		
		edit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				logDisplay.setEditable(true);
				save.setEnabled(true);
			}
		});
		
		searchField.getDocument().addDocumentListener(new DocumentListener(){
			public void changedUpdate(DocumentEvent e){
				
		
				}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				String logText = logDisplay.getText();
				File logf = new File(logText);
				String searchText = searchField.getText();
				System.out.println(logText);
				try{
				BufferedReader search = new BufferedReader(new FileReader(currentLogFile));
				String currentLine;
				int line = 0;
					while((currentLine=search.readLine())!=null){			
						if(currentLine.contains(searchText)){
							//System.out.println("Found on line " + line);
							//System.out.println("Text Index : " + currentLine.indexOf(searchText));
						}
						System.out.println("line " + line);
						line++;
					}
				} catch (FileNotFoundException s) {
					// TODO Auto-generated catch block
					s.printStackTrace();
				}
				catch(IOException f){
					
				}
			
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				
			}
		});
	}

}
