

import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * Room for the business for this project
 *
 */
public class Room implements Serializable {
	private Alarm[] alarms;
	private String roomName;
	private String riskLevel;
	
	/**
	 * 
	 * Constructor for the Room 
	 */
	public Room(String name) {
		alarms = new Alarm[7];
		roomName = name;
		riskLevel = "None";

	}

	/**
	 * 
	 * Second constructor that has an array of alarm and risk
	 */
	public Room(String name, Alarm[] alarms, String risk) {
		this.roomName = name;
		this.alarms = alarms;
		this.riskLevel = risk;
	}

	/**
	 * 
	 * @return the array of the alarm
	 */
	protected Alarm[] getAlarms() {
		return alarms;
	}

	/**
	 * 
	 * @param change the array of the alarms
	 */
	protected void setAlarms(Alarm[] alarms) {
		this.alarms = alarms;
	}

	/**
	 * 
	 * @return String of room's name
	 */
	protected String getRoomName() {
		return roomName;
	}

	/**
	 * 
	 * @param roomName change the room name
	 */
	protected void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	
	/**
	 * 
	 * @param riskLevel change the risk level of the room
	 */
	protected void setRiskLevel(String riskLevel)
	{
		this.riskLevel = riskLevel;
	}

	/**
	 * @return String contains room name and risk level
	 */
	public String toString() {
		return roomName + "(" + riskLevel + ")";
	}

	/**
	 * 
	 * @return risk level of the room
	 */
	public String getRiskLevel() {
		return riskLevel;
	}

}
