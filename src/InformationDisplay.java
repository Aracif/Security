

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
/**
 * 
 * Displaying the the information input in detail
 * with time
 *
 */
public class InformationDisplay {

	/**
	 * 
	 * @param alarms input of the alarms in the array
	 * @return the name of the alarms within the array
	 */
	public static String getAlarmNames(Alarm[] alarms) {
		String buildString = "";
		for (int i = 0; i <= alarms.length - 1 && alarms[i] != null; i++) {
			Alarm currentAlarm = alarms[i];
			buildString += "<li>" + currentAlarm.getName() + "</li>\n";
		}
		return "<ul>\n" + buildString + "</ul>";
	}

	/**
	 * 
	 * @param r input of Room type
	 * @return the room's name
	 */
	public static String roomName(Room r) {
		return r.getRoomName();
	}

	/**
	 * 
	 * @param r input of Room type
	 * @return the room's name
	 */
	public static String riskLevel(Room r) {
		return r.getRiskLevel();
	}

	/**
	 * Date of creating the information
	 * @return the local date in a String
	 */
	public static String dateOfCreation() {
		LocalDate currentDate = LocalDate.now();
		return currentDate.toString();
	}

	/**
	 * Time of creating the information
	 * @return the local time in a String
	 */
	public static String timeOfCreation() {
		LocalTime currentTime = LocalTime.now();
		return currentTime.toString();
	}

	/**
	 * Formatting the time 
	 * @return the time in right format of hour:minute:second
	 */
	public static String timeOfCreationFormatted() {
		LocalTime currentTime = LocalTime.now();
		DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("hh:mm:ss a");
		String formattedTime = currentTime.format(formatterTime);
		return formattedTime;

	}

	/**
	 * Formatting the date
	 * @return the date in the right format of month date, year
	 */
	public static String dateOfCreationFormatted() {
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
		String formattedDate = currentDate.format(formatterDate);
		return formattedDate;
	}

}
