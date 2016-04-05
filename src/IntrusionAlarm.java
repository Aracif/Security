package V1;

public class IntrusionAlarm extends Alarm 
{

	public IntrusionAlarm(String name, int priority) 
	{
		super(name, priority);
	}
	
	
	public String goesOff()
	{
		String alert;
		alert = "This is the "+ getName() +" Alarm. This is prioty number" + super.getPriority() +"."
			   +" Please follow"+ getName() +"drill."
			   + " Remain where you are and lock the door."
			   + " Do not unlock the door until the announcement of the security team.";
		return alert;
	}	
}
