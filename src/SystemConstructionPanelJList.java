package V1;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Arrays;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SystemConstructionPanelJList extends JPanel {
	private Border blackline;
	private JFrame topFrame;
	private JPanel alarmsPanel;
	private Business currentBusiness;
	private JButton createRoomButton;
	private JButton deleteRoomButton;
	private JButton editRoomButton;
	private JButton triggerButton;
	private JButton backButton;
	private JScrollPane listPane;
	private DefaultListModel<Room> listModel;
	private JList<Room> list;
	private JTextField addRoomTextInput;
	private JTextPane consoleTextArea;
	private File currentBusinessLogFile;

	@SuppressWarnings("unchecked")
	public SystemConstructionPanelJList(JPanel alarms, Business bus, SystemConstructionPanelInput inPanel,
			SystemConstructionConsole conPanel, JFrame fra) throws IOException {

		this.setLayout(new FlowLayout());
		topFrame = fra; 
		alarmsPanel = alarms; 
		triggerButton = new JButton ("Trigger Alarm");
		alarms.add(triggerButton);		
		currentBusiness = bus; 
		blackline = BorderFactory.createLineBorder(Color.decode("#00BFFF")); 
		this.setBorder(BorderFactory.createTitledBorder(blackline, "Room List"));//
		makeJList(); 
		JViewport consolePane = (JViewport) ((JScrollPane) conPanel.getPanel().getComponent(0)).getViewport(); 
		consoleTextArea = (JTextPane) consolePane.getView();						
		addRoomTextInput = (JTextField) inPanel.getComponent(0); 
		listPane = (JScrollPane) this.getComponent(0); 
		list = (JList<Room>) listPane.getViewport().getComponent(0); 	
		listModel = (DefaultListModel<Room>) list.getModel(); 	
		JPanel buttonPanel = (JPanel) this.getComponent(1); 
		createRoomButton = (JButton) buttonPanel.getComponent(0); 		
		deleteRoomButton = (JButton) buttonPanel.getComponent(1); 
		backButton = (JButton) buttonPanel.getComponent(3); 														
		editRoomButton = (JButton) buttonPanel.getComponent(2); 
		currentBusinessLogFile = currentBusiness.getFile();																
		addListeners(); 
		loadRoomList();
		
	}

	private void makeJList() {
		DefaultListModel<Room> model = new DefaultListModel<Room>();
		JList<Room> list = new JList<Room>(model);
		JScrollPane listScroll = new JScrollPane(list);
		listScroll.setPreferredSize(new Dimension(290, 160));

		listScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.add(listScroll);
		JButton create = new JButton("Add Room");
		JButton delete = new JButton("Delete Room");
		JButton back = new JButton("Back");
		JButton edit = new JButton("Edit Room");
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		buttonPanel.add(create);
		buttonPanel.add(delete);
		buttonPanel.add(edit);
		buttonPanel.add(back);
		this.add(buttonPanel);
	}
	
	// A method to write and save the log of the trigger button
	// Not a full version but it works the way we want
	public String wordSearch(String s, PrintWriter bw) throws IOException {
		BufferedReader r = new BufferedReader(new FileReader(currentBusinessLogFile));
		String currentLine;
		String text = "";
		String newString = "";
		while((currentLine = r.readLine())!=null){
			if(currentLine.contains(s)){
				newString = currentLine.replace(s, s + " TRIGGERED" );
				System.out.println(newString);
				text=newString;
				return text;
			}

		}
		System.out.println(text);
		return currentLine;	
	}


	private void addListeners() throws IOException {
		// DELETE BUTTON
		deleteRoomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedInd = list.getSelectedIndex();
				Room currentRoom = listModel.getElementAt(selectedInd);
				listModel.removeElementAt(selectedInd);
				currentBusiness.getRooms().remove(currentRoom);
			}
		});
			
		// UPDATE CONSOLE THROUGH JLIST SELECTIONS
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				Room currentRoom = list.getSelectedValue();
				if (currentRoom != null) {
					String statusText = "";
					statusText += "<html><p><b>ROOM : </b>" + currentRoom.getRoomName() + "</p>";
					statusText += "<p><b>RISK LEVEL : </b>" + currentRoom.getRiskLevel() + "</p>";
					statusText += "<p><b>ALARMS : </b></p>";
					statusText += InformationDisplay.getAlarmNames(currentRoom.getAlarms()) + "\n";
					consoleTextArea.setText(
							"<b>CURRENT BUSINESS : </b>" + currentBusiness.getName() + "\n" + statusText + "</html>");
				}
			}
		});
		
		
		// BACK BUTTON
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				topFrame.dispose();
			}
		});
		
	
		// EDIT ROOM BUTTON
		editRoomButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Room currentRoom = list.getSelectedValue();
				Alarm[] alarms = createSelectedAlarmsArray();
				String riskLevel = createRiskStringFromPanel();
				currentRoom.setAlarms(alarms);
				currentRoom.setRiskLevel(riskLevel);
				try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(inputFile, true))))
				{
					String statusText = "";

					statusText += "<html><p><b>ROOM : </b>" + currentRoom.getRoomName() + "</p>";
					statusText += "<p><b>RISK LEVEL : </b>" + currentRoom.getRiskLevel() + "</p>";
					statusText += "<p><b>ALARMS : </b></p>";
					statusText += InformationDisplay.getAlarmNames(alarms) + "\n";
					
					writer.write("\n<br><br><b>*ROOM EDIT*</b>\n");
					writer.write(statusText);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			
		});
		
		//TRIGGER BUTTON
		//That included writing log to the file
		triggerButton.addActionListener(new ActionListener()
		{	
			
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(currentBusinessLogFile,true)));
			
			public void actionPerformed(ActionEvent e)
			{
				Alarm[] a = createSelectedAlarmsArray();
				int j = 0;
				for (Alarm k = a[j]; k!=null; k = a[++j])
				{
					try{
					switch(k.getName())
					{
						case "Chemical Alarm":	
							String s = wordSearch("Chemical Alarm", writer);							
							writer.write(s);
							writer.close();							
							popUp(k.goesOff());
							break;
						case "Fire Alarm":
							String t = wordSearch("Fire Alarm", writer);							
							writer.write(t);
							writer.close();	
							popUp(k.goesOff());
							break;
						case "Window Alarm":
							String d = wordSearch("Window Alarm", writer);							
							writer.write(d);
							writer.close();	
							popUp(k.goesOff());
							break;
						case"Door Alarm":
							String h = wordSearch("Door Alarm", writer);							
							writer.write(h);
							writer.close();	
							popUp(k.goesOff());
							break;
						case "Flood Alarm":
							String i = wordSearch("Flood Alarm", writer);							
							writer.write(i);
							writer.close();	
							popUp(k.goesOff());
							break;
						case "Zombie Apocalypse":
							String v = wordSearch("Zombie Alarm", writer);							
							writer.write(v);
							writer.close();	
							popUp(k.goesOff());
							break;
					}
					}
					catch(IOException y){
						y.printStackTrace();
					}
				}
			}

		});
	
	
		// ADD ROOM BUTTON
		createRoomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Alarm[] alarms = createSelectedAlarmsArray(); 
				String riskLevel = createRiskStringFromPanel();
				Room newRoom = new Room(addRoomTextInput.getText(), alarms, riskLevel); 
				currentBusiness.getRooms().add(newRoom); 													
				listModel.addElement(newRoom); 
				String statusText = "";

				statusText += "<html><p><b>ROOM : </b>" + newRoom.getRoomName() + "</p>";
				statusText += "<p><b>RISK LEVEL : </b>" + newRoom.getRiskLevel() + "</p>";
				statusText += "<p><b>ALARMS : </b></p>";
				statusText += InformationDisplay.getAlarmNames(alarms) + "\n";
				statusText += "<p><b>Creation Status </b></p>: <span style=\"color:Green\">Success</span> ";

				consoleTextArea.setText("<b>Current Business : </b>" + currentBusiness.getName() + "\n" + statusText);

				// Write info to the log file about this rooms creation
				try {
					String writeString = "\n<br><br><b>*ROOM CREATION*</b>\n";
					writeString += "<br><b>Room Name : </b>" + newRoom.getRoomName();
					writeString += "<br><b>Risk Level : </b>" + newRoom.getRiskLevel();
					writeString += "<br><b>Alarms : </b>" + InformationDisplay.getAlarmNames(alarms);
					writeString += "<b>Created on : </b>" + "<span style=\"color:rgb(0, 137, 178);font:14px;\">"
							+ InformationDisplay.dateOfCreationFormatted() + " at "
							+ InformationDisplay.timeOfCreationFormatted() + "</span><br><br>";
					File currentFile = currentBusiness.getFile();
					PrintWriter write = new PrintWriter(new BufferedWriter(new FileWriter(currentFile, true)));
					write.write("\n\n");
					write.write(writeString);
					write.close();
					addRoomTextInput.setText("");
				} catch (FileNotFoundException c) {
					System.out.println(c);
				} catch (IOException io) {
					System.out.println(io);
				}
			}
		});
	}

	// iterate through risk panel and return string of the currently checked
	// risk level
	private String createRiskStringFromPanel() {
		String riskLevel = "";
		JPanel panelArray2 = (JPanel) alarmsPanel.getComponent(1);
		for (Component c : panelArray2.getComponents()) {
			if (c instanceof JRadioButton) {
				if (((JRadioButton) c).isSelected()) {
					riskLevel = ((JRadioButton) c).getText();
				}
			}
		}
		return riskLevel;
	}

	// Create the alarms array for the current room to be built
	private Alarm[] createSelectedAlarmsArray() {
		Alarm[] alarms = new Alarm[7];
		JPanel panelArray = (JPanel) alarmsPanel.getComponent(0);
		int i = 0;
		for (Component c : panelArray.getComponents()) {
			if (c instanceof JCheckBox) {
				if (((JCheckBox) c).isSelected()) {
					String alarmText = ((JCheckBox) c).getText();
					alarms[i++]  = checkAlarm(alarmText);					
				}
			}
		}
		return alarms;
	}
	
	// Method [to be used in createSelectedAlarmsArray()] checking the name of the the alarms
	// If the box/boxes [ that is/are checked ] is matching the case/cases
	// It will create that alarm
	private Alarm checkAlarm(String i)
	{
		Alarm al = null;
		switch(i)
		{
			case "No Alarm":
				al = new NoAlarm ("No Alarm", 0);
				break;
			case "Chemical":
				al = new ChemicalAlarm ("Chemical Alarm", 2);
				break;
			case "Fire":
				al = new FireAlarm ("Fire Alarm", 4);
				break;
			case "Window":
				al = new WindowAlarm ("Window Alarm", 1);
				break;
			case"Door":
				al = new DoorAlarm ("Door Alarm", 1);
				break;
			case "Flood":
				al = new FloodAlarm ("Flood Alarm", 3);
				break;
			case "Zombie Apocalypse":
				al = new ZombieAlarm ("Zombie Apocalypse", 9999);
				break;
		}
		
		return al;
	}
	
	// Method [will be used in the trigger button] that passes a String
	// Which will create a message dialog [ invoke the goesOff() method ] 
	public void popUp(String i)
	{
		JOptionPane.showMessageDialog(topFrame, i);
	}
	
	
	// Load list of rooms from current business upon opening
	private void loadRoomList() {
		for (Room r : currentBusiness.getRooms()) {
			listModel.addElement(r);
		}
	}
}
