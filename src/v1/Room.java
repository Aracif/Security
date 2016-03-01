package v1;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Room implements Serializable {
	private Alarm[] alarms;
	private String roomName;
	private String riskLevel;
	public Room(String name){
		alarms = new Alarm[7];
		roomName = name;
		riskLevel = "None";
		
		
	}
	
	public Room(String name, Alarm[] alarms, String risk){
		this.roomName = name;
		this.alarms = alarms;
		this.riskLevel = risk;
	}
	protected Alarm[] getAlarms() {
		return alarms;
	}
	protected void setAlarms(Alarm[] alarms) {
		this.alarms = alarms;
	}
	protected String getRoomName() {
		return roomName;
	}
	protected void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	
	public String toString(){
		return roomName + "(" + riskLevel + ")";
	}
	
	public String getRiskLevel(){
		return riskLevel;
	}

}
