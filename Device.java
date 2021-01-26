
/*
	@author Fernando Jose Lavarreda Urizar
	@version 25/01/2021
	Class to model a Device that requires maintenance for example a car, or a machine
*/

import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.util.Calendar;
import java.util.ArrayList;
import java.time.LocalDate;
import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.InputMismatchException;
import java.time.format.DateTimeFormatter;


public abstract class Device{
	protected int ID;
	protected String lastServiceDate;
	protected String lastUpdateDate;
	protected String description;
	//Method that indicates if the device requires maintenance
	public abstract boolean maintenance();
	//Method to deposit contents of device into a File for later upload
	public abstract void dumpFile(String dirDump) throws IOException;
	
	
	/*
	 *Constructor of the class. Read on individual setters and getters for more information
	 *@param ID, unique identifier of the device.
	 *@param lastServiceDate, date of the last service of the device
	 *@param lastUpdateDate, date of the last update of the information/stats of the device
	 *@param description, general description of the device: model, name, etc anything required to identify it.
	 *@exception InputMismatchException if one of the entries is against setters
	*/
	public Device(String ID, String lastServiceDate, String lastUpdateDate, String description) throws InputMismatchException{
		setID(ID);
		setLastServiceDate(lastServiceDate);
		setLastUpdateDate(lastUpdateDate);
		setDescription(description);
	}
	
	/*
	 *Load multiple devices from a directory. Folder should only contain Files of one Device subclass and nothing else
	 *@param dirLoad, path of the folder from which contents will be loaded
	 *@param type, Current supported chars: 'v' and 'm' for a Vehicle and a Machine.
	 *@return devices, list of the Device type loaded from the folder
	 *@exception IOException if path is not valid
	 *@exception IOException if a File can't be read
	*/
	public static ArrayList<Device> loadFromFiles(String dirLoad, char type) throws IOException{
		ArrayList<Device> devices = new ArrayList<Device>();
		File files = new File(dirLoad);
		File[] devs;
		if(files.isDirectory()){
			devs = files.listFiles();
		}else{
			throw new IOException("Not a directory");
		}
		switch(type){
			case 'v':
				for(File dev:devs){
					devices.add(loadVehicleFile(dev.toString()));
				}
				break;
			case 'm':
				for(File dev:devs){
					devices.add(loadMachineFile(dev.toString()));
				}
				break;
			default:
				break;
			
		}
		return devices;
	}
	
	
	/*
	 *Loads a Vehicle from a File
	 *@param fileLoad, path to file to be loaded. must contain the following structure:
	  ID fo the vehicle
	  Date of the last Service
	  Date of the last Update of Information
	  Kilometers since last service
	  Kilometers it has up to date
	  Description of the device 
	  IMPORTANT TO END with newline character
	 *@return vehicle, Vehicle object reulsting from the data in fileLoad
	 *@exception IOException if fileLoad is not a File
	 *@exception IOException if the format does not allow to load a Vehicle
	*/
	public static Vehicle loadVehicleFile(String fileLoad) throws IOException{
		Vehicle vehicle = null;
		File load = new File(fileLoad);
		if(load.isFile()){
			try{
				Scanner reader = new Scanner(load);
				String id = reader.nextLine();
				String lastServiceDate = reader.nextLine();
				String lastUpdateDate = reader.nextLine();
				String lastService = reader.nextLine();
				String kilometers = reader.nextLine();
				String description = "";
				while(reader.hasNextLine()){
					description += reader.nextLine(); 
				}
				vehicle = new Vehicle(id, lastServiceDate, lastUpdateDate, description, kilometers, lastService);
				return vehicle;
			}catch(Exception e){
				throw new IOException("Problems reading file");
			}
		}else{
			throw new IOException("File Not Found");
		}
	}
	
	/*
	 *Loads a Mchine from a File
	 *@param fileLoad, path to file to be loaded. must contain the following structure:
	  ID fo the machine
	  Date of the last Service
	  Date of the last Update of Information
	  Hours when last service was done
	  Hours of work up to date
	  Description of the device 
	  IMPORTANT TO END with newline character
	 *@return machine, Machine object resulting from the data in the File
	 *@exception IOException if fileLoad is not a File
	 *@exception IOException if the format does not allow to load a Machine
	*/
	public static Machine loadMachineFile(String fileLoad) throws IOException{
		Machine machine = null;
		File load = new File(fileLoad);
		if(load.isFile()){
			try{
				Scanner reader = new Scanner(load);
				String id = reader.nextLine();
				String lastServiceDate = reader.nextLine();
				String lastUpdateDate = reader.nextLine();
				String hoursLast = reader.nextLine();
				String hours = reader.nextLine();
				String description = "";
				while(reader.hasNextLine()){
					description += reader.nextLine(); 
				}
				machine = new Machine(id, lastServiceDate, lastUpdateDate, description, hours, hoursLast);
				return machine;
			}catch(Exception e){
				throw new IOException("Problems reading file");
			}
		}else{
			throw new IOException("File Not Found");
		}
	}
	
	/*
	 *Calculate time between dates
	 *@param date1, date of the following format dd-MM-yyyy, it must be before date2
	 *@param date2, date of the following format dd-MM-yyyy
	 *@return daysbtw, number of days between the two dates
	*/
	public long daysBetween(String date1, String date2){
		DateTimeFormatter why = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate before = LocalDate.parse(date1, why);
		LocalDate after = LocalDate.parse(date2, why);
		
		long daysbtw = ChronoUnit.DAYS.between(before, after);
		return daysbtw;
	}
	
	/*
	 *Obtain the ID of the device
	 *@return ID, unique identifier of the device a number
	 */
	public int getID(){
		return ID;
	}
	
	/*
	 *Obtain las date of service of the device
	 *@return lastServiceDate, format of the String dd-MM-yyyy
	*/
	public String getLastServiceDate(){
		return lastServiceDate;
	}
	
	/*
	 *Obtain the last time the information of the device was updated
	 *@return lastUpdateDate, format of the String dd-MM-yyyy
	*/
	public String getLastUpdateDate(){
		return lastUpdateDate;
	}
	
	/*
	 *Obtain description of the device
	 *@return description, important to notice that the end of the description comes with newline character
	*/
	public String getDescription(){
		return description;
	}
	
	/*
	 *Establish the ID of the device
	 *@param Id, An int, very important that the ID is not shared among any of the devices grouped if used with class EvaluateDevices
	 *@exeption InputMismatchException if the ID set is not a number (int)
	*/
	public void setID(String Id) throws InputMismatchException{
		int temp;
		try{
			temp = Integer.parseInt(Id);
		}catch(Exception e){
			throw new InputMismatchException("Not a number");
		}
		this.ID = temp;
	}
	
	/*
	 *Establish the date of the last service
	 *@param lastServiceDate, date of last Service of format dd-MM-yyyy
	 *@exception InputMismatchException if the date provided is not one or if the format is incorrect
	*/
	public void setLastServiceDate(String lastServiceDate) throws InputMismatchException{
		if(lastServiceDate.length()!=10){
			throw new InputMismatchException("Formato de fecha: dd-MM-yyyy");
		}
		try{
			DateTimeFormatter why = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDate lc1 = LocalDate.parse(lastServiceDate, why);
		}catch(Exception e){
			throw new InputMismatchException("Formato de fecha: dd-MM-yyyy");
		}
		this.lastServiceDate = lastServiceDate;
	}
	
	/*
	 *Establish the last time the information of the device was updated
	 *@param lastUpdateDate, date of the last update of the information of format dd-MM-yyyy
	 *@exception InputMismatchException if the date provided is not one or if the format is incorrect
	*/
	public void setLastUpdateDate(String lastUpdateDate) throws InputMismatchException{
		if(lastServiceDate.length()!=10){
			throw new InputMismatchException("Formato de fecha: dd-MM-yyyy");
		}
		try{
			DateTimeFormatter why = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			LocalDate lc1 = LocalDate.parse(lastServiceDate, why);
		}catch(Exception e){
			throw new InputMismatchException("Formato de fecha: dd-MM-yyyy");
		}
		this.lastUpdateDate = lastUpdateDate;
	}
	
	/*
	 *Establish a description of the device
	 *@param description, information to describe Device
	*/
	public void setDescription(String description){
		this.description = description;
	}
	
	/*
	 *String representation of the Device
	 *@return repr, instance's values
	*/
	public String toString(){
		String repr = "ID: "+getID()+"\nDate of last Service: "+getLastServiceDate()+"\nLast Time data was updated: "+getLastUpdateDate()+"\nDescription: "+getDescription();
		return repr;
	}
	
}