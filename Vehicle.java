
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;

public class Vehicle extends Device{
	private int kilometers = 0;
	private int lastService = 0;
	private final int KMFORSERVICE = 5000;
	private final int SAFETYRADIUS = 150;
	
	public Vehicle(String ID, String lastServiceDate, String lastUpdateDate, String description, String kilometers, String lastService) throws InputMismatchException{
		super(ID, lastServiceDate, lastUpdateDate, description);
		setKilometers(kilometers);
		setLastService(lastService);
	}
	
	
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
			throw new InputMismatchException("Kilometros deben ser un numero entero");
		}
		if(this.lastService>temp_km){
			throw new InputMismatchException("Valor de kilometros menor al esperado");
		}else{
			this.lastService = temp_km;
		}
	}
	
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
	
	public boolean maintenance(){
		if(kilometers-lastService>KMFORSERVICE-SAFETYRADIUS)
			return true;
		else
			return false;
	}
	
	public String toString(){
		return super.toString()+"Kilometros: "+getKilometers()+"\nKilometraje en Ultimo Servicio: "+getLastService()+"\n";
	}
	
}