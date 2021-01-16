/*
 *Fernando Jose Lavarreda Urizar
*/

import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.util.Calendar;
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
	
	public Device(String ID, String lastServiceDate, String lastUpdateDate, String description){
		setID(ID);
		setLasServiceDate(lastServiceDate);
		setLastUpdateDate(lastUpdateDate);
		setDescription(description);
	}
	
	public static void DumpVehicleFile(String dirDump, Vehicle vh) throws IOException{
		File destination = new File(dirDump+vh.getID());
		if (destination.isFile()){
			destination.delete();
		}
		try{
			destination.createNewFile();
			FileWriter nw = new FileWriter(destination);
			nw.write(vh.getID()+"\n");
			nw.write(vh.getLastServiceDate()+"\n");
			nw.write(vh.getLastUpdateDate()+"\n");
			nw.write(vh.getLastService()+"\n");
			nw.write(vh.getKilometers()+"\n");
			nw.write(vh.getDescription()+"\n");
			nw.close();
		}catch(Exception e){
			throw new IOException("Problems writing to File");
		}
	}
	
	public static void DumpMachineFile(String dirDump, Machine mch) throws IOException{
		File destination = new File(dirDump+mch.getID());
		if (destination.isFile()){
			destination.delete();
		}
		try{
			destination.createNewFile();
			FileWriter nw = new FileWriter(destination);
			nw.write(mch.getID()+"\n");
			nw.write(mch.getLastServiceDate()+"\n");
			nw.write(mch.getLastUpdateDate()+"\n");
			nw.write(mch.getHoursLast()+"\n");
			nw.write(mch.getHours()+"\n");
			nw.write(mch.getDescription()+"\n");
			nw.close();
		}catch(Exception e){
			throw new IOException("Problem writing to File");
		}
	}
	
	public static Vehicle LoadVehicleFile(String fileLoad) throws IOException{
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
	
	public static Machine LoadMachineFile(String fileLoad) throws IOException{
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