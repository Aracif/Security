package src;

import java.util.ArrayList;
import java.io.File;
import java.io.Serializable;

public class Business implements Serializable {
	private ArrayList<Room> rooms;
	private String name;
	private File logFile;
	private File parseableLogFile;
	private String lastEdit;

	public Business(String name) {
		rooms = new ArrayList<Room>();
		this.name = name;
		lastEdit = "";
	}

	public Business(String name, File log, File pLog) {
		rooms = new ArrayList<Room>();
		this.name = name;
		logFile = log;
		parseableLogFile = pLog;
		lastEdit = "";
	}

	public Business(String name, ArrayList<Room> rooms, File log, File pLog) {
		this.rooms = rooms;
		this.name = name;
		logFile = log;
		parseableLogFile = pLog;
		lastEdit = "";
	}

	public ArrayList<Room> getRooms() {
		return rooms;
	}
	
	public void setLastEdit(String edit){
		lastEdit = edit;
	}
	
	public String getLastEdit(){
		return lastEdit;
	}

	public void setRooms(ArrayList<Room> rooms) {
		this.rooms = rooms;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}

	public void setLogFile(File f) {
		logFile = f;
	}

	public File getFile() {
		return logFile;
	}

	public void setParseableLogFile(File f) {
		parseableLogFile = f;
	}

	public File getParseableLogFile() {
		return parseableLogFile;
	}

}
