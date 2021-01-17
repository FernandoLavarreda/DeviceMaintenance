/*
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
*/

import java.util.Iterator;
import java.io.IOException;
import java.util.ArrayList;

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
	
	public void addDevices(ArrayList<Device> dvs){
		devices.addAll(dvs);
	}
	
	public boolean addVehicle(String lastServiceDate, String lastUpdateDate, String description, String kilometers, String lastService){
		boolean status;
		int biggestID = 0;
		for(Device dv:devices){
			if(dv.getID()>biggestID){
				biggestID = dv.getID()+1;
			}
		}
		String ID = Integer.toString(biggestID);
		try{
			devices.add(new Vehicle(ID, lastServiceDate, lastUpdateDate, description, kilometers, lastService));
			status = true;
		}catch(Exception e){
			status = false;
		}
		return status;
	}
	
	public boolean addMachine(String lastServiceDate, String lastUpdateDate, String description, String hours, String hoursLast){
		boolean status;
		int biggestID = 0;
		for(Device dv:devices){
			if(dv.getID()>biggestID){
				biggestID = dv.getID()+1;
			}
		}
		String ID = Integer.toString(biggestID);
		try{
			devices.add(new Machine(ID, lastServiceDate, lastUpdateDate, description, hours, hoursLast));
			status = true;
		}catch(Exception e){
			status = false;
		}
		return status;
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