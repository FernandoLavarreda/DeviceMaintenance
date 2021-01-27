package MaintenanceApp;
/*
	@author Fernando Lavarreda
	@version 25/01/2021
	Application to keep track of device maintenance
*/

import java.io.File;
import javax.swing.*;
import backend.MainWindow;
import java.io.IOException;
import java.util.ArrayList;
import backend.devices.Device;
import backend.EvaluateDevices;
import backend.EvaluateDevicesGraphics;

public class DeviceMaintenanceApp{
	
	
	/*
	 *Start of the program
	 *@param args, not defined
	*/
	public static void main(String[] args){
		EvaluateDevices dvmm = new EvaluateDevices();
		EvaluateDevicesGraphics graphics = new EvaluateDevicesGraphics(dvmm);
		try{
			dvmm.addDevices(Device.loadFromFiles("resources\\vehicles", 'v'));
			dvmm.addDevices(Device.loadFromFiles("resources\\machines", 'm'));
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
		
		MainWindow mw = new MainWindow("Device Management", "resources\\vehicles", "resources\\machines", dvmm);
		mw.getContentPane().add(graphics);
		mw.pack();
		mw.setVisible(true);
		mw.setResizable(false);
		graphics.allRefresh();

		
	}
	
}