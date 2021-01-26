
import java.io.File;
import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class MainWindow extends JFrame{
	private String routeVehicles;
	private String routeMachines;
	private EvaluateDevices dvmm;
	
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