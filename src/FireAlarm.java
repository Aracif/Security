
public class FireAlarm extends Alarm 
{


	public FireAlarm(String name, int priority) 
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

