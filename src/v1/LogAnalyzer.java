package v1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.io.StringReader;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Element;
import javax.swing.text.ElementIterator;
import javax.swing.text.StyleConstants;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

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
	private File parseableLogFile;
	private BufferedReader read;
	private BufferedReader readParseable;
	

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
		parseableLogFile = currentBusiness.getParseableLogFile();
		read = new BufferedReader(new FileReader(currentLogFile));
		
		readInFile();
		this.setLocation(parentFrame.getLocation());
		parseJPane();
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

	private void readInParseableFile()throws IOException{

	}

	private void parseJPane() throws IOException{
		PrintWriter writeParseableSecurityLog = new PrintWriter(new BufferedWriter(new FileWriter(currentBusiness.getParseableLogFile(),false)));
		String logText = logDisplay.getText();
		HTMLEditorKit htmlKit = new HTMLEditorKit();
		HTMLDocument htmlDoc = (HTMLDocument)htmlKit.createDefaultDocument();
		int leafNumber = 0;

		try {
			htmlKit.read(new StringReader(logText), htmlDoc, 0);
			ElementIterator iterator = new ElementIterator(htmlDoc);	
			Element element;
			int i = 0;

			while((element=iterator.next())!=null){	
				if(element.isLeaf()){
					leafNumber ++;
					int startOffset =element.getStartOffset();
					int endOffset = element.getEndOffset();
					int length = endOffset -startOffset;
					String t = htmlDoc.getText(startOffset, length);
					if(leafNumber>1){
						writeParseableSecurityLog.write(htmlDoc.getText(startOffset, length));
						AttributeSet checkAtt = element.getAttributes();
						Object name = checkAtt.getAttribute(StyleConstants.NameAttribute);
						if(name==HTML.Tag.BR){
							writeParseableSecurityLog.write("\n");
						}
					}
					i++;
				}
			}
			writeParseableSecurityLog.close();



		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
				DefaultHighlighter.DefaultHighlightPainter highlightPainter = 
				        new DefaultHighlighter.DefaultHighlightPainter(Color.GREEN);
				    
				String currentLine;
				int line = 0;
				String searchText = searchField.getText();
				try {
					readParseable = new BufferedReader(new FileReader(parseableLogFile));
					while((currentLine=readParseable.readLine())!=null){
						if(currentLine.contains(searchText)){
							System.out.println("Search Text Length: " +  searchField.getText().length());
							System.out.println("Start Index : " + currentLine.indexOf(searchText) );
							System.out.println("Found on line " + line );
//							logDisplay.getHighlighter().addHighlight(currentLine.indexOf(searchText), searchField.getText().length(), 
//						            highlightPainter);
						}
						line++;
					}
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {

			}
		});
	}

}
