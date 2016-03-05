package src;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.PrintWriter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.io.FileNotFoundException;
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
	private JButton backButton;
	private JScrollPane listPane;
	private DefaultListModel<Room> listModel;
	private JList<Room> list;
	private JTextField addRoomTextInput;
	private JTextPane consoleTextArea;

	@SuppressWarnings("unchecked")
	public SystemConstructionPanelJList(JPanel alarms, Business bus, SystemConstructionPanelInput inPanel,
			SystemConstructionConsole conPanel, JFrame fra) {

		this.setLayout(new FlowLayout());

		topFrame = fra; // reference to the frame for system creation so it can
						// be closed
		alarmsPanel = alarms; // reference to alarm panel
		currentBusiness = bus; // current business object

		blackline = BorderFactory.createLineBorder(Color.decode("#00BFFF")); // next
																				// 2
																				// lines
																				// create
																				// border
																				// and
																				// apply
																				// border
		this.setBorder(BorderFactory.createTitledBorder(blackline, "Room List"));//

		makeJList(); // Construct the JList panel

		JViewport consolePane = (JViewport) ((JScrollPane) conPanel.getPanel().getComponent(0)).getViewport(); // Reference
																												// to
																												// the
																												// console
																												// viewport
		consoleTextArea = (JTextPane) consolePane.getView(); // Reference to
																// console area
																// viewport
		addRoomTextInput = (JTextField) inPanel.getComponent(0); // Reference to
																	// JTextfield
																	// for room
																	// name
		listPane = (JScrollPane) this.getComponent(0); // Reference to
														// JScrolPane containing
														// list
		list = (JList<Room>) listPane.getViewport().getComponent(0); // Reference
																		// to
																		// JList
																		// inside
																		// JScrollPane
		listModel = (DefaultListModel<Room>) list.getModel(); // Reference to
																// the JList
																// model
		JPanel buttonPanel = (JPanel) this.getComponent(1); // Reference to
															// JPanel containing
															// the JButtons
		createRoomButton = (JButton) buttonPanel.getComponent(0); // Reference
																	// to the
																	// create
																	// JButton
		deleteRoomButton = (JButton) buttonPanel.getComponent(1); // Reference
																	// to the
																	// delete
																	// JButton
		backButton = (JButton) buttonPanel.getComponent(3); // Reference to the
															// back JButton
		editRoomButton = (JButton) buttonPanel.getComponent(2); // Reference to
																// the edit
																// JButton

		addListeners(); // Add listeners
		loadRoomList(); // Load the room list into JList model
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

	private void addListeners() {
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
		// ADD ROOM BUTTON
		createRoomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Alarm[] alarms = createSelectedAlarmsArray(); // Get an Alarm[]
																// array
																// containing
																// the selected
																// alarms
				String riskLevel = createRiskStringFromPanel(); // Get risk
																// level
				Room newRoom = new Room(addRoomTextInput.getText(), alarms, riskLevel); // Create
																						// room
																						// using
																						// alarms,riskLevel
																						// and
																						// room
																						// in.
																						// field
				currentBusiness.getRooms().add(newRoom); // Add new room to
															// business
				listModel.addElement(newRoom); // Add new room to the list model

				// Display creation status into console
				String statusText = "";

				statusText += "<html><p><b>ROOM : </b>" + newRoom.getRoomName() + "</p>";
				statusText += "<p><b>RISK LEVEL : </b>" + newRoom.getRiskLevel() + "</p>";
				statusText += "<p><b>ALARMS : </b></p>";
				statusText += InformationDisplay.getAlarmNames(alarms) + "\n";
				statusText += "<b>Creation Status </b>: <span style=\"color:Green\">Success</span> ";

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
		int i = -1;
		for (Component c : panelArray.getComponents()) {
			if (c instanceof JCheckBox) {
				if (((JCheckBox) c).isSelected()) {
					String alarmText = ((JCheckBox) c).getText();
					alarms[++i] = new Alarm(((JCheckBox) c).getText());
				}
			}
		}
		return alarms;
	}

	// Load list of rooms from current business upon opening
	private void loadRoomList() {
		for (Room r : currentBusiness.getRooms()) {
			listModel.addElement(r);
		}
	}

}