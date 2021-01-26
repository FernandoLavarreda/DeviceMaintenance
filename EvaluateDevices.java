
/*
	@author Fernando Lavarreda
	@version 25/01/2021
	Class to manage maintenance required for the devices: Vehicle, Machine
*/

import java.util.Iterator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class EvaluateDevices{
	private ArrayList<Device> devices = new ArrayList<Device>();
	
	/*
	 *Constructor of the class
	*/
	public EvaluateDevices(){
		
	}
	
	/*
	 *Return a device once its id is given
	 *@param ID, expected to be the ID of one of the devices
	 *@return objective, reference to the Device
	*/
	public Device getDevice(int ID){
		Device objective = null;
		for(Device dv: devices){
			if(dv.getID()==ID){
				objective = dv;
				break;
			}
		}
		return objective;
	}
	
	/*
	 *Remove a Device from the class
	 *@param ID, numeric value that identifies a Device
	*/
	public void removeDevice(int ID){
		Device temp = null;
		Iterator<Device> iter = devices.iterator();
		while(iter.hasNext()){
			temp = iter.next();
			if(temp.getID()==ID){
				iter.remove();
				break;
			}
		}
	}
	
	/*
	 *Update the values from a Device
	 *@param edited, Device which contents are expected to be updated
	 *@param fields, values that define the machine
	*/
	public void editDevice(Device edited, String...fields)throws InputMismatchException{
		edited.setLastServiceDate(fields[0]);
		edited.setLastUpdateDate(fields[1]);
		edited.setDescription(fields[2]);
		if(edited instanceof Vehicle){
			Vehicle edits = (Vehicle)edited;
			edits.setKilometers(fields[3]);
			edits.setLastService(fields[4]);
		}else if(edited instanceof Machine){
			Machine edits = (Machine)edited;
			edits.setHours(fields[3]);
			edits.setHoursLast(fields[4]);
		}else{
			
		}
	}
	
	/*
	 *Add a series of devices to the class
	 *@param dvs, list of objects to add
	*/
	public void addDevices(ArrayList<Device> dvs){
		devices.addAll(dvs);
	}
	
	/*
	 *Creation of a Vehicle and automatic addition to the class
	 *@param lastServiceDate, date of the last service of the device
	 *@param lastUpdateDate, date of the last update of the information/stats of the device
	 *@param description, general description of the device: model, name, etc anything required to identify it.
	 *@param kilometers, all kilometers traveled by the Vehicle
	 *@param lastService, amount of kilometers when the last service was given
	 *@exception InputMismatchException if the entries fail to create a Vehicle
	*/
	public void addVehicle(String lastServiceDate, String lastUpdateDate, String description, String kilometers, String lastService) throws InputMismatchException{
		int biggestID = 0;
		for(Device dv:devices){
			if(dv.getID()>=biggestID){
				biggestID = dv.getID()+1;
			}
		}
		String ID = Integer.toString(biggestID);
		devices.add(new Vehicle(ID, lastServiceDate, lastUpdateDate, description, kilometers, lastService));
	
	}
	
	/*
	 *Creation of a Machine and aumomatic addition to the class
	 *@param lastServiceDate, date of the last service of the device
	 *@param lastUpdateDate, date of the last update of the information/stats of the device
	 *@param description, general description of the device: model, name, etc anything required to identify it.
	 *@param hours, hours worked by the machine
	 *@param hoursLast, hours worked by the machine when the last service was made
	 *@exception InputMismatchException if the entries fail to create a Machine
	*/
	public void addMachine(String lastServiceDate, String lastUpdateDate, String description, String hours, String hoursLast) throws InputMismatchException{
		int biggestID = 0;
		for(Device dv:devices){
			if(dv.getID()>=biggestID){
				biggestID = dv.getID()+1;
			}
		}
		String ID = Integer.toString(biggestID);
		devices.add(new Machine(ID, lastServiceDate, lastUpdateDate, description, hours, hoursLast));
	}
	
	/*
	 *Determine devices that requiere maintenance
	 *@return required, list of Device that returned true in their maintenance method
	*/
	public ArrayList<Device> maintenanceCheck(){
		ArrayList<Device> required = new ArrayList<Device>();
		for(Device dv: devices){
			if(dv.maintenance()){
				required.add(dv);
			}
		}
		return required;
	}
	
	/*
	 *Access to all vehicles inside the class
	 *@return vehicles, all the vehicles in the class
	*/
	public ArrayList<Vehicle> viewVehicles(){
		ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
		for(Device dv: devices){
			if(dv instanceof Vehicle){
				Vehicle vehicle = (Vehicle)dv;
				vehicles.add(vehicle);
			}
		}
		return vehicles;
	}
	
	/*
	 *Access to all the machines inside the class
	 *@return machines, all the machines in the class
	*/
	public ArrayList<Machine> viewMachines(){
		ArrayList<Machine> machines = new ArrayList<Machine>();
		for(Device dv: devices){
			if(dv instanceof Machine){
				Machine machine = (Machine)dv;
				machines.add(machine);
			}
		}
		return machines;
	}
	
	/*
	 *Save vehicles into a folder, each one will be saved following the dumpFile meainig their ID.txt
	 *@param folder, path to save vehicles without endind slash
	 *@exception IOException if the folder is not valid or can't be written to
	*/
	public void saveVehicles(String folder) throws IOException{
		ArrayList<Vehicle> vehicles = viewVehicles();
		for(Vehicle vh: vehicles){
			vh.dumpFile(folder);
		}
	}
	
	/*
	 *Save machines into a folder, each one will be saved following the dumpFile meainig their ID.txt
	 *@param folder, path to save machines without endind slash
	 *@exception IOException if the folder is not valid or can't be written to
	*/
	public void saveMachines(String folder) throws IOException{
		ArrayList<Machine> machines = viewMachines();
		for(Machine mch: machines){
			mch.dumpFile(folder);
		}
	}
	
}