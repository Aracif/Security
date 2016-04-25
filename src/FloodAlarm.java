

public class FloodAlarm extends Alarm 
{

	public FloodAlarm(String name, int priority) 
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
		alert = "This is the "+ getName() +" Alarm. This is prioty number" + super.getPriority() +"."
			   +" Please follow"+ getName() +"drill";
		return alert;
	}	
}
