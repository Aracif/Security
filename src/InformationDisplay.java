package src;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class InformationDisplay {

	public static String getAlarmNames(Alarm[] alarms) {
		String buildString = "";
		for (int i = 0; i <= alarms.length - 1 && alarms[i] != null; i++) {
			Alarm currentAlarm = alarms[i];
			buildString += "<li>" + currentAlarm.getName() + "</li>";
		}
		return "<ul>" + buildString + "</ul>";
	}

	public static String roomName(Room r) {
		return r.getRoomName();
	}

	public static String riskLevel(Room r) {
		return r.getRiskLevel();
	}

	public static String dateOfCreation() {
		LocalDate currentDate = LocalDate.now();
		return currentDate.toString();
	}

	public static String timeOfCreation() {
		LocalTime currentTime = LocalTime.now();
		return currentTime.toString();
	}

	public static String timeOfCreationFormatted() {
		LocalTime currentTime = LocalTime.now();
		DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("hh:mm:ss a");
		String formattedTime = currentTime.format(formatterTime);
		return formattedTime;

	}

	public static String dateOfCreationFormatted() {
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
		String formattedDate = currentDate.format(formatterDate);
		return formattedDate;
	}

}
