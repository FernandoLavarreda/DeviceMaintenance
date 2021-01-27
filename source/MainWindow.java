package backend;
/*
	@author Fernando Lavarreda
	@version 25/01/2021
	JFrame to control information to be saved
*/

import java.io.File;
import javax.swing.JFrame;
import backend.EvaluateDevices;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;


public class MainWindow extends JFrame{
	private String routeVehicles;
	private String routeMachines;
	private EvaluateDevices dvmm;
	
	/*
	 *Constructor of the class
	 *@param windowTitle, title to appear on the frame
	 *@param routeVehicles, path to save the vehicles
	 *@param routeMachines, path to save the machines
	 *@param dvmm, class to operate on the intelligence of the program
	*/
	public MainWindow(String windowTitle, String routeVehicles, String routeMachines, EvaluateDevices dvmm){
		super(windowTitle);
		this.routeVehicles = routeVehicles;
		this.routeMachines = routeMachines;
		this.dvmm = dvmm;
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e){
				saveChanges();
				super.windowClosing(e);
			}
		});
	}
	
	//Methodd to be called when closing JFrame save info
	private void saveChanges(){
		File dir1 = new File(routeVehicles);
		File dir2 = new File(routeMachines);
		File[] all1 = dir1.listFiles();
		File[] all2 = dir2.listFiles();
		
		for(File d: all1){
			d.delete();
		}
		
		for(File d: all2){
			d.delete();
		}
		
		try{
			dvmm.saveVehicles(routeVehicles);
			dvmm.saveMachines(routeMachines);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
	}
	
}