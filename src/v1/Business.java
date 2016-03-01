package v1;
import java.util.ArrayList;
import java.io.File;
import java.io.Serializable;

public class Business implements Serializable {
	private ArrayList<Room> rooms;
	private String name;
	private File logFile;
	
	public Business(String name){
		rooms = new ArrayList<Room>();
		this.name = name;
	}
	
	public Business(String name,File log){
		rooms = new ArrayList<Room>();
		this.name = name;
		logFile = log;
	}
	
	public Business(String name, ArrayList<Room> rooms, File log){
		this.rooms = rooms;
		this.name = name;
		logFile = log;
	}

	public ArrayList<Room> getRooms() {
		return rooms;
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
	
	public String toString(){
		return name;
	}
	
	public void setLogFile(File f){
		logFile = f;
	}
	
	public File getFile(){
		return logFile;
	}
	
	
	

}
