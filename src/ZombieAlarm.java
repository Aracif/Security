package V1;

public class ZombieAlarm extends Alarm 
{

	public ZombieAlarm(String name, int priority) 
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
