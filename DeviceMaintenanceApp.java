
import javax.swing.*;

public class DeviceMaintenanceApp{
	
	public static void main(String[] args){
		EvaluateDevices dvmm = new EvaluateDevices();
		EvaluateDevicesGraphics graphics = new EvaluateDevicesGraphics(dvmm);
		
		JFrame mw = new JFrame("Device Management");
		mw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mw.getContentPane().add(graphics);
		mw.pack();
		mw.setVisible(true);
	}
	
}