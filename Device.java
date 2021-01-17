/*
 *Fernando Jose Lavarreda Urizar
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
	public abstract boolean maintenance();
	public abstract void dumpFile(String dirDump) throws IOException;
	
	public Device(String ID, String lastServiceDate, String lastUpdateDate, String description) throws InputMismatchException{
		setID(ID);
		setLasServiceDate(lastServiceDate);
		setLastUpdateDate(lastUpdateDate);
		setDescription(description);
	}
	
	
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
	
	public long daysBetween(String date1, String date2){
		DateTimeFormatter why = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDate before = LocalDate.parse(date1, why);
		LocalDate after = LocalDate.parse(date2, why);
		
		long daysbtw = ChronoUnit.DAYS.between(before, after);
		return daysbtw;
	}
	
	public int getID(){
		return ID;
	}
	
	public String getLastServiceDate(){
		return lastServiceDate;
	}
	
	public String getLastUpdateDate(){
		return lastUpdateDate;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setID(String Id) throws InputMismatchException{
		int temp;
		try{
			temp = Integer.parseInt(Id);
		}catch(Exception e){
			throw new InputMismatchException("Not a number");
		}
		this.ID = temp;
	}
	
	public void setLasServiceDate(String lastServiceDate){
		this.lastServiceDate = lastServiceDate;
	}
	
	public void setLastUpdateDate(String lastUpdateDate){
		this.lastUpdateDate = lastUpdateDate;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
}