/*
	@author Fernando Lavarreda
	@version 22/01/2021
	Class to manage maintenance required for the devices
*/

import java.util.Iterator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class EvaluateDevices{
	private ArrayList<Device> devices = new ArrayList<Device>();
	
	public EvaluateDevices(){
		
	}
	
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
	
	public void addDevices(ArrayList<Device> dvs){
		devices.addAll(dvs);
	}
	
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
	
	public ArrayList<Device> maintenanceCheck(){
		ArrayList<Device> required = new ArrayList<Device>();
		for(Device dv: devices){
			if(dv.maintenance()){
				required.add(dv);
			}
		}
		return required;
	}
	
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
	
	public void saveVehicles(String folder) throws IOException{
		ArrayList<Vehicle> vehicles = viewVehicles();
		for(Vehicle vh: vehicles){
			vh.dumpFile(folder);
		}
	}
	
	
	public void saveMachines(String folder) throws IOException{
		ArrayList<Machine> machines = viewMachines();
		for(Machine mch: machines){
			mch.dumpFile(folder);
		}
	}
	
}