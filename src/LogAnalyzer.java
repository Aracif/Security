

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
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Element;
import javax.swing.text.ElementIterator;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.Highlight;
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
/**
 * 
 * GUI for the log analyzer with information
 * such as business name, room name, alarm, date, time, vice versa 
 *
 */
public class LogAnalyzer extends JFrame {
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
	private String context;
	private DefaultHighlightPainter hPainter;
	private int leafValue;
	private JTextPane logStats;
	private int occurences;
	private String originalDocText;
	private String originalRegText;
	protected static int saveCounter = 0;

	/**
	 * 
	 * Constructor the log analyzer GUI
	 */
	public LogAnalyzer(JFrame parentFrame, Business cB) throws IOException, BadLocationException {
		this.getContentPane().setLayout(new BorderLayout());
		occurences = 0;
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
		hPainter = new DefaultHighlightPainter(Color.decode("#00bfff"));
		readInFile();
		this.setLocation(parentFrame.getLocation());
		parseJPane();
		logStat();
		if (logDisplay.getText() != "") {
			changingLabel.setText(currentBusiness.getName() + " log file has been successfully loaded.");
		}
		getWordCount();
		originalDocText = logDisplay.getDocument().getText(0, logDisplay.getDocument().getLength());
		originalRegText = logDisplay.getText();
		//System.out.println(originalDocText);
		//System.out.println(originalRegText);
		this.repaint();
		this.pack();
		this.setVisible(true);
	}

	/**
	 * Creating the west panel and what goes into it
	 */
	private void makeWestPanel() {
		back = new JButton("Back");
		back.addActionListener(backButtonAction);
		edit = new JButton("Edit");
		save = new JButton("Save");
		save.setEnabled(false);
		save.addActionListener(saveButton);
		searchLabel = new JLabel("Search");
		searchField = new JTextField(15);
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
		logStats = new JTextPane();
		JScrollPane statsScrollPane = new JScrollPane(logStats);
		logStats.setContentType("text/html");
		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
		westPanel.setPreferredSize(new Dimension(250, currentFrame.getHeight()));
		westPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 45, 255), 2, true));
		// westPanel.add(Box.createRigidArea(new Dimension(0,125)));
		westPanel.add(searchPanel);
		westPanel.add(Box.createRigidArea(new Dimension(0, 25)));
		westPanel.add(statsScrollPane);
		westPanel.add(Box.createRigidArea(new Dimension(0, 25)));
		westPanel.add(buttonPanel);
		currentFrame.add(westPanel, BorderLayout.WEST);
		addListeners();
	}

	/**
	 * Creating the north panel and goes into it
	 */
	private void makeNorthPanel() {
		northPanel = new JPanel(new FlowLayout());
		northPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 45, 235), 2, true));
		currentFrame.add(northPanel, BorderLayout.NORTH);
	}

	/**
	 * Creating the south panel and what goes ino it
	 */
	private void makeSouthPanel() {
		southPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		labelName = new JLabel("Status/Info: ");
		changingLabel = new JLabel("");
		southPanel.add(labelName);
		southPanel.add(changingLabel);
		southPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 65, 205), 2, true));
		currentFrame.add(southPanel, BorderLayout.SOUTH);
	}

	/**
	 * Creating the center panel and what goes into it
	 */
	private void makeCenterPanel() {
		centerPanel = new JPanel(new FlowLayout());
		centerPanel.setBorder(BorderFactory.createLineBorder(new Color(0, 65, 205), 2, true));
		logDisplay = new JTextPane();
		logDisplay.setEditable(false);
		logDisplay.setContentType("text/html");
		logDisplay.setPreferredSize(new Dimension(500, 500));
		scrollPane = new JScrollPane(logDisplay);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		centerPanel.add(scrollPane);
		currentFrame.add(centerPanel, BorderLayout.CENTER);
	}

	/**
	 *  Read the html based log display into the JTextPane
	 * 
	 */
	private void readInFile() throws IOException {
		read = new BufferedReader(new FileReader(currentLogFile));
		String currentLine;
		String text = "";
		while ((currentLine = read.readLine()) != null) {
			text += currentLine + "\n";
		}
		logDisplay.setText(text);
	}

	/**
	 * Log information after everything has recorded
	 * 
	 */
	private void logStat() throws BadLocationException {
		StringBuffer sb = new StringBuffer();
		sb.append("<b>Words : </b> " + getWordCount() + "<br>");
		sb.append("<b>Occurences : </b> " + occurences + "<br>");
		sb.append("<b>Tripped Alarms : </b> <br>");
		sb.append("<b>Number of Rooms : </b>" + currentBusiness.getRooms().size()+"<br>");
		sb.append("<b>Last Alarm Triggered : </b><br>");
		sb.append("<b>Alarms Currently Triggered : </b><br>");
		sb.append("<b>Last edit : </b>" +"<span style=\"font:11;\">" + currentBusiness.getLastEdit()+"</span><br>");
		logStats.setText(sb.toString());
	}

	/**
	 *  Set occurences
	 * 
	 */
	private void setOccur(int i) {
		occurences = i;
	}

	/**
	 * 
	 * Helping for the search bar to search the key word
	 * It will count the space
	 */
	private int getWordCount() throws BadLocationException {
		String logText = logDisplay.getText();
		int spaces = 0;
		DefaultStyledDocument doc = (DefaultStyledDocument) logDisplay.getDocument();
		String docContext = doc.getText(0, doc.getLength());
		String[] arr = docContext.split("\\s");
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].equals("")) {
				spaces++;
			}
		}
		return arr.length - spaces;
	}

	/**
	 *  The original log file is formatted using html, thus in order to get a
	 * 
	 * regularly formatted document
	 * containing plain text only and formatted with words,lines,spaces etc in
	 * their proper positions we
	 * must parse through the html to find the text by checking if it is a leaf
	 * element in other words if
	 * the element represents text i.e. doesn't have any children. Now, in
	 * order, this method works as follows
	 * 1. Create a PrintWriter so we can write the parsed text into a file.
	 * 2. Get the currently displayed text in the JTextPane
	 * 3. Since we need to make an HTML document we first create an
	 * HTMLEditorKit which provides a method for this.
	 * 4. Now we can use the HTMLEditorKit method .createDefaultDocument to
	 * create the document model for this editor
	 * 5. We are now ready to read our JTextPane text into our HtmlEditorKit
	 * which stores it in the HTMLDocument we created for it
 	 * 6. The final step before iteration is to create an element iterator which
	 *detects html elements in the Document model.
	**/
	private void parseJPane() throws IOException {
		PrintWriter writeParseableSecurityLog = new PrintWriter(
				new BufferedWriter(new FileWriter(currentBusiness.getParseableLogFile(), false)));
		String logText = logDisplay.getText();
		HTMLEditorKit htmlKit = new HTMLEditorKit();
		HTMLDocument htmlDoc = (HTMLDocument) htmlKit.createDefaultDocument();
		int leafNumber = 0;
		try {
			htmlKit.read(new StringReader(logText), htmlDoc, 0);
			ElementIterator iterator = new ElementIterator(htmlDoc);
			Element element;
			int i = 0;
			while ((element = iterator.next()) != null) {
				if (element.isLeaf()) {
					leafNumber++;
					int startOffset = element.getStartOffset();
					int endOffset = element.getEndOffset();
					int length = endOffset - startOffset;
					String t = htmlDoc.getText(startOffset, length);
					if (leafNumber > 1) {
						writeParseableSecurityLog.write(htmlDoc.getText(startOffset, length));
						AttributeSet checkAtt = element.getAttributes();
						Object name = checkAtt.getAttribute(StyleConstants.NameAttribute);
						if (name == HTML.Tag.BR) {
							writeParseableSecurityLog.write("\n");
						}
					}
					i++;
				}
			}
			leafValue = leafNumber;
			writeParseableSecurityLog.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Creating the action listener for the back button
	 */
	ActionListener backButtonAction = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			currentFrame.dispose();
		}
	};

	/**
	 *  Create the action to be performed when the save button is clicked
	 */
	ActionListener saveButton = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				String currentDocText = logDisplay.getText();
				if (currentDocText.equals(originalRegText)) {
					JOptionPane.showMessageDialog(currentFrame, "No changes have been made.");
				} else {
					int choice = JOptionPane.showConfirmDialog(currentFrame,
							"Are you sure you want to save these changes?");
					
					if(choice==0){
						PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(currentBusiness.getFile(),false)));
						writer.write(currentDocText);
						//currentBusiness.setLogFile(updateTextFile);
						//pw.write(currentDocText);
						writer.close();
						currentBusiness.setLastEdit(InformationDisplay.dateOfCreationFormatted() + " @ " + 
						InformationDisplay.timeOfCreationFormatted());
						logStat();
						saveCounter++;
						
						
					}
				}
			} catch (  FileNotFoundException | BadLocationException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	};

	/**
	 * Add all of the action listenres button
	 */
	private void addListeners() {

		edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logDisplay.setEditable(true);
				save.setEnabled(true);
			}
		});

		searchField.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {

			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				DefaultHighlighter highlighter = (DefaultHighlighter) logDisplay.getHighlighter();
				highlighter.removeAllHighlights();
				DefaultStyledDocument doc = (DefaultStyledDocument) logDisplay.getDocument();
				try {
					context = doc.getText(0, doc.getLength());
				} catch (BadLocationException ex) {
					System.out.println(ex);
				}
				int index = 0;
				occurences = 0;
				int wordLength = searchField.getText().length();
				while ((index = context.indexOf(searchField.getText(), index)) != -1) {
					int endIndex = index + wordLength;
					try {
						if (searchField.getText().equals(context.substring(index, endIndex))) {
							highlighter.addHighlight(index, endIndex, hPainter);
							occurences++;
						}
					} catch (BadLocationException e) {
						// Nothing to do
					}
					index = endIndex;
				}
				try {
					logStat();
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				if (searchField.getText().length() >= 1) {
					occurences = 0;
					logDisplay.getHighlighter().removeAllHighlights();
					DefaultHighlighter highlighter = (DefaultHighlighter) logDisplay.getHighlighter();
					DefaultHighlightPainter hPainter = new DefaultHighlightPainter(new Color(0xFFAA00));
					DefaultStyledDocument doc = (DefaultStyledDocument) logDisplay.getDocument();
					try {
						context = doc.getText(0, doc.getLength());
					} catch (BadLocationException ex) {
						System.out.println(ex);
					}
					int index = 0;
					int wordLength = searchField.getText().length();
					while ((index = context.indexOf(searchField.getText(), index)) != -1) {
						int endIndex = index + wordLength;
						try {
							highlighter.addHighlight(index, endIndex, hPainter);
							occurences++;
						} catch (BadLocationException e) {
							// Nothing to do
						}
						index = endIndex;
					}
					try {
						logStat();
					} catch (BadLocationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					logDisplay.getHighlighter().removeAllHighlights();
					occurences = 0;
					try {
						logStat();
					} catch (BadLocationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		});
	}

}
