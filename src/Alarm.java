package src;

import java.io.Serializable;

public class Alarm implements Serializable {
	private String name;

	public Alarm(String name) {
		this.name = name;
	}

	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

}
