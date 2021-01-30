package backend.devices;
/*
	@author Fernando Jose Lavarreda Urizar
	@version 25/01/2021
	Class to model a Machine that requires maintenance
*/

import java.io.File;
import java.io.FileWriter;
import java.util.Calendar;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;

public class Machine extends Device{
	private int hours = 0;
	private int hoursLast = 0;
	private final int TIMEFORSERVICE = 250;
	private final int SAFETYRADIUS = 40;
	private final int DAYSFORSERVICE = 60;
	private final int SAFETYRADIUSDAYS = 5;
	
	/*
	 *Constructor of the class, read setters and getters for more information
	 *@param ID, unique identifier of the device.
	 *@param lastServiceDate, date of the last service of the device
	 *@param lastUpdateDate, date of the last update of the information/stats of the device
	 *@param description, general description of the device: model, name, etc anything required to identify it.
	 *@param hours, hours worked by the machine
	 *@param hoursLast, hours worked by the machine when the last service was made
	 *@exception InputMismatchException if the conditions for the setters are not met
	*/
	public Machine(String ID, String lastServiceDate, String lastUpdateDate, String description, String hours, String hoursLast) throws InputMismatchException{
		super(ID, lastServiceDate, lastUpdateDate, description);
		setHours(hours);
		setHoursLast(hoursLast);
	}
	
	/*
	 *Persist the contents from the instance into a file for later retrieval
	 *@param dirDump, folder path to create the file without slash at the end
	 *@exception IOException if the path does not exist or can't be written
	*/
	public void dumpFile(String dirDump) throws IOException{
		File destination = new File(dirDump+"\\"+ID+".txt");
		if (destination.isFile()){
			destination.delete();
		}
		try{
			destination.createNewFile();
			FileWriter nw = new FileWriter(destination);
			nw.write(ID+"\n");
			nw.write(lastServiceDate+"\n");
			nw.write(lastUpdateDate+"\n");
			nw.write(hoursLast+"\n");
			nw.write(hours+"\n");
			nw.write(description+"\n");
			nw.close();
		}catch(Exception e){
			throw new IOException("Problem writing to File");
		}
	}
	
	/*
	 *Obtain hours worked by the machine
	 *@return hours, total hours worked by the machine
	*/
	public int getHours(){
		return hours;
	}
	
	/*
	 *Obtain hours from last service
	 *@return hoursLast, amount of hours worked by the machine the moment it got its last service
	*/
	public int getHoursLast(){
		return hoursLast;
	}
	
	/*
	 *Establish the amount of hours worked by the machine
	 *@param h, total hours worked by the machine. Must be an int
	 *@exception InputMismatchException if the h provided are less than the current value stored in hours
	 *@exception InputMismatchException if the value given is not an int
	*/
	public void setHours(String h) throws InputMismatchException{
		int time = 0;
		try{
			time = Integer.parseInt(h);
		}catch(Exception e){
			throw new InputMismatchException("Las horas deben ser numero");
		}
		if(hours>time){
			throw new InputMismatchException("Valor de horas menor al esperado");
		}else{
			hours = time;
		}
	}
	
	/*
	 *Establish the amount of hours worked when the las service was made
	 *@param hl, amount of hours worked when the last service was provided to the machine. Must be an int
	 *@exception InputMismatchException if the hl provided are less than the current value stored in hoursLast
	 *@exception InputMismatchException if the value given is not an int
	 *@exception InputMismatchException if the value provided is smaller than the values of hours
	*/
	public void setHoursLast(String hl){
		int time = 0;
		try{
			time = Integer.parseInt(hl);
		}catch(Exception e){
			throw new InputMismatchException("Las horas deben ser numero");
		}
		if(hoursLast>time){
			throw new InputMismatchException("Valor de horas menor al esperado");
		}else if(time>hours){
			throw new InputMismatchException("Hrs. de servicio mayor al total");
		}else{
			hoursLast = time;
		}
	}
	
	/*
	 *Determine if the car requires maintenance. If the difference of km between lastService and Kilometers is bigger than the constant KMFORSERVICE-SAFETYRADIUS
	 *@return maintain, indicates whether a maintenance is requiered or not
	*/
	public boolean maintenance(){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat nm = new SimpleDateFormat("dd-MM-yyyy");
		String now = nm.format(cal.getTime());
		if(hours-hoursLast>TIMEFORSERVICE-SAFETYRADIUS){
			boolean maintain = true;
			return maintain;
		}else if(super.daysBetween(lastServiceDate, now)>DAYSFORSERVICE-SAFETYRADIUSDAYS){
			boolean maintain = true;
			return maintain;
		}else{
			boolean maintain = false;
			return maintain;
		}
	}
	
	/*
	 *String representation of the vehicle
	 *@return repr, representation of the instance's field values
	*/
	public String toString(){
		String repr = super.toString()+"\nHoras de trajo: "+getHours()+"\nHoras en ultimo servicio: "+getHoursLast()+"\n";
		return repr;
	}
	
}