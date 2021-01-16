

import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;

public class Machine extends Device{
	private int hours = 0;
	private int hoursLast = 0;
	private final int TIMEFORSERVICE = 250;
	private final int SAFETYRADIUS = 10;
	private final int DAYSFORSERVICE = 60;
	private final int SAFETYRADIUSDAYS = 5;
	
	public Machine(String ID, String lastServiceDate, String lastUpdateDate, String description, String hours, String hoursLast){
		super(ID, lastServiceDate, lastUpdateDate, description);
		setHours(hours);
		setHoursLast(hoursLast);
	}
	
	public int getHours(){
		return hours;
	}
	
	public int getHoursLast(){
		return hoursLast;
	}
	
	
	public void setHours(String h) throws InputMismatchException{
		int time = 0;
		try{
			time = Integer.parseInt(h);
		}catch(Exception e){
			throw new InputMismatchException("Not a number");
		}
		if(hours>time){
			throw new InputMismatchException("Number smaller than expected");
		}else{
			hours = time;
		}
	}
	
	public void setHoursLast(String hl){
		int time = 0;
		try{
			time = Integer.parseInt(hl);
		}catch(Exception e){
			throw new InputMismatchException("Not a number");
		}
		if(hoursLast>time){
			throw new InputMismatchException("Number smaller than expected");
		}else{
			hoursLast = time;
		}
	}
	
	public boolean maintenance(){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat nm = new SimpleDateFormat("dd-MM-yyyy");
		String now = nm.format(cal.getTime());
		if(hours-hoursLast>TIMEFORSERVICE-SAFETYRADIUS)
			return true;
		else if(super.daysBetween(lastServiceDate, now)>DAYSFORSERVICE-SAFETYRADIUSDAYS)
			return true;
		else
			return false;
	}
	
}