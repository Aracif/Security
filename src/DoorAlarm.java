package src;

public class DoorAlarm extends Alarm
{

	public DoorAlarm(String name, int priority) 
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
			   +" Please follow " + getName() + " drill.";

		return alert;
	}

}
