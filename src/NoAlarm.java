package src;

public class NoAlarm extends Alarm
{
	public NoAlarm (String name, int priority) 
	{
		super(name, priority);
		
	}

	public String getName() 
	{
		return super.getName();
	}
	
	public String goesOff() 
	{
		String alert;
		alert = "This is the"+ getName() +"This is prioty number" + super.getPriority() +"."
			   +" This is only a drill. Please, don' be panic and follow what you have been told";

		return alert;
	}
}
