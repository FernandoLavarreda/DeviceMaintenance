
/*
	@author Fernando Jose Lavarreda Urizar
	@version 25/01/2021
	Class to model a Vehicle that requires maintenance
*/

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;

public class Vehicle extends Device{
	private int kilometers = 0;
	private int lastService = 0;
	private final int KMFORSERVICE = 5000;
	private final int SAFETYRADIUS = 150;
	
	/*
	 *Constructor of the class, read setters and getters for more information
	 *@param ID, unique identifier of the device.
	 *@param lastServiceDate, date of the last service of the device
	 *@param lastUpdateDate, date of the last update of the information/stats of the device
	 *@param description, general description of the device: model, name, etc anything required to identify it.
	 *@param kilometers, all kilometers traveled by the Vehicle
	 *@param lastService, amount of kilometers when the last service was given
	 *@exception InputMismatchException if the setters of the class are not obeyed
	*/
	public Vehicle(String ID, String lastServiceDate, String lastUpdateDate, String description, String kilometers, String lastService) throws InputMismatchException{
		super(ID, lastServiceDate, lastUpdateDate, description);
		setKilometers(kilometers);
		setLastService(lastService);
	}
	
	/*
	 *Persist instance's values into a file. The file name will be IDs.txt
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
			nw.write(lastService+"\n");
			nw.write(kilometers+"\n");
			nw.write(description+"\n");
			nw.close();
		}catch(Exception e){
			throw new IOException("Problems writing to File");
		}
	}
	
	/*
	 *Obtain kilometers traveled by the Vehicle
	 *@return kilometers, total kilometers traveled
	*/
	public int getKilometers(){
		return kilometers;
	}
	
	/*
	 *Obtain kilometers when the last service was made
	 *@return lastService, kilometers when the last service was made
	*/
	public int getLastService(){
		return lastService;
	}
	
	/*
	 *Establish the kilometers when the las service was made
	 *@param last, kilometers when the last service was made must be an int
	 *@exception InputMismatchException if the value given is smaller than the current value of lastService
	 *@exception InputMismatchException if the value given is bigger than the value of total kilometers traveled
	*/
	public void setLastService(String last) throws InputMismatchException{
		int temp_km;
		try{
			temp_km = Integer.parseInt(last);
		}catch(Exception e){
			throw new InputMismatchException("Kilometros deben ser un numero entero");
		}
		if(this.lastService>temp_km){
			throw new InputMismatchException("Valor de kilometros menor al esperado");
		}else if(temp_km>kilometers){
			throw new InputMismatchException("Km. de servicio mayor al total");
		}else{
			this.lastService = temp_km;
		}
	}
	
	/*
	 *Establish the total kilometers traveled by the vehicle
	 *@param km, total kilometers. Must be an int
	 *@exception InputMismatchException if the value of km given is smallet than the current value of kilometers for the car
	 *@exception InputMismatchException if the value is not an int
	*/
	public void setKilometers(String km)throws InputMismatchException{
		int temp_km;
		try{
			temp_km = Integer.parseInt(km);
		}catch(Exception e){
			throw new InputMismatchException("Kilometros deben ser un numero entero");
		}
		if(this.kilometers>temp_km){
			throw new InputMismatchException("Valor de kilometros menor al esperado");
		}else{
			this.kilometers = temp_km;
		}
	}
	
	/*
	 *Determine if the car requires maintenance. If the difference of km between lastService and Kilometers is bigger than the constant KMFORSERVICE-SAFETYRADIUS
	 *@return maintain, indicates whether a maintenance is requiered or not
	*/
	public boolean maintenance(){
		if(kilometers-lastService>KMFORSERVICE-SAFETYRADIUS){
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
		String repr = super.toString()+"Kilometros: "+getKilometers()+"\nKilometraje en Ultimo Servicio: "+getLastService()+"\n";
		return repr;
	}
	
}