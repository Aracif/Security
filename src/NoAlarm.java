/**
 * 
 * Drill Alarm class
 * Subclass of Alarm class
 *
 */

public class NoAlarm extends Alarm
{
	public NoAlarm (String name, int priority) 
	{
		super(name, priority);
		
	}

	/**
	 * @return the name of Drill Alarm class
	 */
	public String getName() 
	{
		return super.getName();
	}
	
	/**
	 * @return a string of message telling the name of which alarm is going off
	 */
	public String goesOff() 
	{
		String alert;
		alert = "This is the"+ getName() +"This is prioty number" + super.getPriority() +"."
			   +" This is only a drill. Please, don' be panic and follow what you have been told";

		return alert;
	}
}
