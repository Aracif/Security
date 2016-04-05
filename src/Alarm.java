package src;

import java.io.Serializable;

public abstract class Alarm implements Serializable {
	
	private String name;
	private int priority

	public Alarm(String name, int priority) {
		this.name = name;
		this.priority = priority;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public String gePriority() {
		return priority;
	}

	public abstract String goesOff();

}
