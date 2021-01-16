

import java.util.InputMismatchException;

public class Vehicle extends Device{
	private int kilometers = 0;
	private int lastService = 0;
	private final int KMFORSERVICE = 5000;
	private final int SAFETYRADIUS = 150;
	
	public Vehicle(String ID, String lastServiceDate, String lastUpdateDate, String description, String kilometers, String lastService){
		super(ID, lastServiceDate, lastUpdateDate, description);
		setKilometers(kilometers);
		setLastService(lastService);
	}
	
	public int getKilometers(){
		return kilometers;
	}
	
	public int getLastService(){
		return lastService;
	}
	
	
	public void setLastService(String last) throws InputMismatchException{
		int temp_km;
		try{
			temp_km = Integer.parseInt(last);
		}catch(Exception e){
			throw new InputMismatchException("Number not an integer");
		}
		if(this.lastService>temp_km){
			throw new InputMismatchException("Number entered is smaller than expected");
		}else{
			this.lastService = temp_km;
		}
	}
	
	public void setKilometers(String km)throws InputMismatchException{
		int temp_km;
		try{
			temp_km = Integer.parseInt(km);
		}catch(Exception e){
			throw new InputMismatchException("Number not an integer");
		}
		if(this.kilometers>temp_km){
			throw new InputMismatchException("Number entered is smaller than expected");
		}else{
			this.kilometers = temp_km;
		}
	}
	
	public boolean maintenance(){
		if(kilometers-lastService>KMFORSERVICE-SAFETYRADIUS)
			return true;
		else
			return false;
	}
	
}