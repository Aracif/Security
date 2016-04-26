

import java.util.ArrayList;
import java.io.File;
import java.io.Serializable;

/**
 * 
 * Business Class will responsible to hold an ArrayList of rooms
 * and will implements Serializable
 * It will contain File class to save and delete the information of this business
 *
 */
public class Business implements Serializable {
	// An ArrayList of Room
	private ArrayList<Room> rooms;
	// String name of the Business
	private String name;
	// Instance of file
	private File logFile;
	// Instance of file that will parse the file
	private File parseableLogFile;
	// String of information of last time editing the room
	private String lastEdit;

	/**
	 * Constructor that takes one parameter
	 * @param name of the business 
	 */
	public Business(String name) {
		rooms = new ArrayList<Room>();
		this.name = name;
		lastEdit = "";
	}

	/**
	 * Constructor that takes three parameters
	 * 
	 * @param name of the business
	 * @param log contain the information of the business
	 * @param pLog file that can parse
	 */
	public Business(String name, File log, File pLog) {
		rooms = new ArrayList<Room>();
		this.name = name;
		logFile = log;
		parseableLogFile = pLog;
		lastEdit = "";
	}

	/**
	 * Constructor that takes four parameters
	 * 
	 * @param name of the business
	 * @param rooms is an ArrayList of Room will be added to this business
	 * @param log contain the information of the business
	 * @param pLog file that can parse
	 */
	public Business(String name, ArrayList<Room> rooms, File log, File pLog) {
		this.rooms = rooms;
		this.name = name;
		logFile = log;
		parseableLogFile = pLog;
		lastEdit = "";
	}

	/**
	 * 
	 * @return the ArrayList of room
	 */
	public ArrayList<Room> getRooms() {
		return rooms;
	}
	
	/**
	 * 
	 * @param edit to make changes to the editing from last edit
	 */
	public void setLastEdit(String edit){
		lastEdit = edit;
	}
	
	/**
	 * 
	 * @return the information of previous editing
	 */
	public String getLastEdit(){
		return lastEdit;
	}

	/**
	 * 
	 * @param rooms change the ArrayList of Room
	 */
	public void setRooms(ArrayList<Room> rooms) {
		this.rooms = rooms;
	}

	/**
	 * 
	 * @return the name of Business
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name change previous name of the business
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the name of Business class
	 */
	public String toString() {
		return name;
	}

	/**
	 * 
	 * @param f change the previous saved information 
	 */
	public void setLogFile(File f) {
		logFile = f;
	}

	/**
	 * 
	 * @return the saved information of Business
	 */
	public File getFile() {
		return logFile;
	}

	/**
	 * 
	 * @param f change the File that is 'parseable'
	 */
	public void setParseableLogFile(File f) {
		parseableLogFile = f;
	}

	/**
	 * 
	 * @return the saved information that is parseable
	 */
	public File getParseableLogFile() {
		return parseableLogFile;
	}

}
