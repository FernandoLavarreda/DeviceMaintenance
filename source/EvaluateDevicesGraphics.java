package backend;
/*
	@author Fernando Lavarreda
	@version 25/01/2021
	GUI to interact with EvaluateDevices
*/

import backend.devices.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.InputMismatchException;

public class EvaluateDevicesGraphics extends JPanel{
	private EvaluateDevices eval;
	private JComboBox<String> acciones;
	private JTextField[] deviceInfo = new JTextField[6];
	private JLabel[] deviceExplained = new JLabel[6];
	private JButton addvehicle, addmachine, modify, addDevice, deleteDevice;
	private JTextField id = new JTextField();
	private JLabel info, feedback;
	private JPanel presentVehicles = new JPanel();
	private JPanel presentMachines = new JPanel();
	private JPanel presentDiagnostics = new JPanel();
	
	//Important alter this line for future addition to more devices, currently only a boolean system allows for only the vehicles and machines
	private boolean machines = false;
	//
	
	/*
	 *Constructor of the class
	 *@param eval, class to manage the devices
	*/
	public EvaluateDevicesGraphics(EvaluateDevices eval){
		this.eval = eval;
		Color color = new Color(114, 164, 179);
		setBackground(color);
		setLayout(new GridBagLayout());
		GridBagConstraints gcn = new GridBagConstraints();
		gcn.gridx = 0;
		gcn.gridy = 0;
		gcn.weightx = 0.5;
		gcn.weighty = 0.5;
		gcn.gridwidth = 4;
		gcn.gridheight = 2;
		gcn.fill = GridBagConstraints.BOTH;
		gcn.anchor = GridBagConstraints.LINE_START;
		
		JLabel title = new JLabel("Mantenimiento de Maquinaria. Concretar S.A.");
		title.setFont(new Font("Verdana", Font.BOLD, 24));
		add(title, gcn);
		
		
		//Logo -------------------
		gcn.gridx = 4;
		gcn.gridwidth = 4;
		ImageIcon imageIcon = new ImageIcon(new ImageIcon("resources/cover.png").getImage().getScaledInstance(400, 100, Image.SCALE_SMOOTH));
		JLabel logo = new JLabel();
		logo.setIcon(imageIcon);
		add(logo, gcn);
		//End of Logo ----------
		
		
		gcn.gridx = 0;
		gcn.gridy = 2;
		gcn.gridheight = 1;
		gcn.weighty = 0.3;
		gcn.gridwidth = 1;
		JLabel sub1 = new JLabel("Panel de Control");
		sub1.setFont(new Font("Arial", Font.BOLD, 14));
		add(sub1, gcn);
		
		gcn.gridy = 3;
		gcn.weighty = 0.5;
		gcn.gridx = 1;
		JLabel sub2 = new JLabel("Accion a Ejecutar: ");
		add(sub2, gcn);
		
		String[] acts = {"Adicionar Maquinaria", "Diagnostico", "Eliminar Maquinaria", "Actualizar Datos"};
		acciones = new JComboBox<String>(acts);
		gcn.gridx = 2;
		add(acciones, gcn);
		
		gcn.gridy = 4;
		gcn.gridx = 1;
		gcn.gridwidth = 1;
		info = new JLabel("ID del dispositivo: ");
		add(info, gcn);
		
		
		
		gcn.gridx = 3;
		JLabel spacing = new JLabel("ID del dispositivo:");
		spacing.setForeground(color);
		add(spacing, gcn);
		
		gcn.gridx = 2;
		add(id, gcn);
		/*
		 Buttones que tambien se coolocan aqui cuando se solicita agregar un objeto
		*/
		gcn.gridx = 1;
		addmachine = new JButton("Agregar Maquina");
		add(addmachine, gcn);
		addmachine.setVisible(false);
		addmachine.setEnabled(false);
		
		gcn.gridx = 2;
		addvehicle = new JButton("Agregar Vehiculo");
		add(addvehicle, gcn);
		addvehicle.setVisible(false);
		addvehicle.setEnabled(false);
		/*
		Finaliza la instanciacion de los botones
		*/
		
		
		gcn.gridy = 5;
		gcn.gridwidth = 4;
		gcn.gridx = 0;
		gcn.insets = new Insets(0, 0, 10, 0);
		JLabel separator = new JLabel("----------------------------------------------------------------------------------------------------------------------------------------------------------------");
		add(separator, gcn);
		
		gcn.insets = new Insets(0, 0, 0, 0);
		gcn.gridy = 6;
		gcn.gridwidth = 1;
		deviceExplained[0] = new JLabel("Fecha de Ultimo Servicio");
		deviceExplained[1] = new JLabel("  Ultima Actualizacion de Datos");
		deviceExplained[2] = new JLabel("Kilometraje Actual");
		deviceExplained[3] = new JLabel("  Kilometraje Ultimo Servicio");
		deviceExplained[4] = new JLabel("Descripcion");
		deviceExplained[5] = new JLabel("ID");
		
		int counter = 0;
		for(int i = 0; i<3; i++){
			gcn.gridy = 6+i;
			for(int j = 0; j<=2;j+=2){
				deviceInfo[counter] = new JTextField();
				gcn.gridx = j;
				add(deviceExplained[counter], gcn);
				gcn.gridx += 1;
				add(deviceInfo[counter], gcn);
				gcn.gridx -= 1;
				counter++;
			}
		}
		
		deviceExplained[5].setVisible(false);
		deviceInfo[5].setVisible(false);
		
		gcn.gridy = 9;
		gcn.gridx = 2;
		gcn.gridwidth = 2;
		gcn.insets = new Insets(0, 4, 0, 0);
		modify = new JButton("Modificar");
		addDevice = new JButton("Agregar");
		deleteDevice = new JButton("Eliminar");
		deleteDevice.setVisible(false);
		deleteDevice.setEnabled(false);
		modify.setVisible(false);
		modify.setEnabled(false);
		add(modify, gcn);
		add(deleteDevice, gcn);
		add(addDevice, gcn);
		
		feedback = new JLabel("Retroalimentar");
		gcn.insets = new Insets(0, 0, 0, 0);
		gcn.gridx = 0;
		add(feedback,gcn);
		
		gcn.gridx = 4;
		gcn.gridy = 2;
		gcn.gridwidth = 4;
		gcn.gridheight = 8;
		gcn.fill = GridBagConstraints.BOTH;
		gcn.insets = new Insets(0, 8, 0, 0);
		
		
		presentDiagnostics.setLayout(new BoxLayout(presentDiagnostics, BoxLayout.Y_AXIS));
		presentMachines.setLayout(new BoxLayout(presentMachines, BoxLayout.Y_AXIS));
		presentVehicles.setLayout(new BoxLayout(presentVehicles, BoxLayout.Y_AXIS));
		
		JTabbedPane tabs = new JTabbedPane();
		JScrollPane scrollMaquinas = new JScrollPane(presentMachines, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		JScrollPane scrollVehiculos = new JScrollPane(presentVehicles, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		JScrollPane scrollDiagnosticos = new JScrollPane(presentDiagnostics, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tabs.addTab("Maquinas", scrollMaquinas);
		tabs.addTab("Vehiculos", scrollVehiculos);
		tabs.addTab("Diagnosticos", scrollDiagnosticos);
		add(tabs, gcn);
		
		
		
		
		setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
		
		id.addActionListener(new ButtonListener());
		acciones.addActionListener(new ButtonListener());
		addvehicle.addActionListener(new ButtonListener());
		addmachine.addActionListener(new ButtonListener());
		modify.addActionListener(new ButtonListener());
		addDevice.addActionListener(new ButtonListener());
		deleteDevice.addActionListener(new ButtonListener());
		
		acciones.setSelectedIndex(0);
		
		
		
	}
	
	private void clearTextFields(){
		for(int i=0; i<5;i++){
			deviceInfo[i].setText("");
		}
	}
	
	//Reresh the tabs
	public void allRefresh(){
		refreshMachines(eval.viewMachines());
		refreshVehicles(eval.viewVehicles());
		refreshDiagnostics(eval.maintenanceCheck());
	}
	
	private void refreshVehicles(ArrayList<Vehicle> vhs){
		presentVehicles.removeAll();
		int i = 0;
		for(Vehicle v: vhs){
			presentVehicles.add(vehicleToLabel(v, i++));
		}
		presentVehicles.revalidate();
		presentVehicles.repaint();
	}
	
	private void refreshMachines(ArrayList<Machine> mchs){
		presentMachines.removeAll();
		int i = 0;
		for(Machine m: mchs){
			presentMachines.add(machineToLabel(m, i++));
		}
		presentMachines.revalidate();
		presentMachines.repaint();
	}
	
	private void refreshDiagnostics(ArrayList<Device> dvs){
		presentDiagnostics.removeAll();
		int i = 0;
		for(Device d: dvs){
			if(d instanceof Vehicle){
				presentDiagnostics.add(vehicleToLabel((Vehicle)d, i++));
			}else if(d instanceof Machine){
				presentDiagnostics.add(machineToLabel((Machine)d, i++));
			}else{
				
			}
		}
		presentMachines.revalidate();
		presentMachines.repaint();
	}
	
	/*
	 *@param v, vehiculo definido por la clase Vehicle
	 *@return presentation, JLabel to graphically represent a Vehicle
	 */
	private JLabel vehicleToLabel(Vehicle v, int color){
		String desc = "<html>ID: "+v.getID()+"<br>Kilometraje: "+v.getKilometers()+"Km. Ultimo servicio: "+v.getLastService()
					+ "<br>Ultima Actualizacion: "+v.getLastUpdateDate()+"<br>Fecha Ultimo Servicio: "+v.getLastServiceDate()
					+ "<br>Descripcion:<br>"+v.getDescription()+"<br>-----------------------</html>";
		JLabel presentation = new JLabel(desc);
		presentation.setOpaque(true);
		if(color%2 == 0){
			presentation.setBackground(new Color(223, 239, 240));
		}else{
			presentation.setBackground(new Color(231, 245, 223));
		}
		return presentation;
	}
	
	/*
	 *@param e, maquina definda por la clase Machine
	 *@return presentation, JLabel to graphically represent a Machine 
	 */
	private JLabel machineToLabel(Machine e, int color){
		String desc = "<html>ID: "+e.getID()+"<br>Horas de trabajo: "+e.getHours()+"\tHrs. Ultimo servicio: "+e.getHoursLast()
					+ "<br>Ultima Actualizacion: "+e.getLastUpdateDate()+"<br>Fecha Ultimo Servicio: "+e.getLastServiceDate()
					+ "<br>Descripcion:<br>"+e.getDescription()+"<br>-----------------------</html>";
		JLabel presentation = new JLabel(desc);
		presentation.setOpaque(true);
		if(color%2 == 0){
			presentation.setBackground(new Color(223, 239, 240));
		}else{
			presentation.setBackground(new Color(231, 245, 223));
		}
		return presentation;
	}
	
	
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == acciones){
				clearTextFields();
				id.setText("");
				String selection = acciones.getSelectedItem().toString();
				if(selection.equals("Eliminar Maquinaria")){
					addmachine.setVisible(false);
					addmachine.setEnabled(false);
					addvehicle.setVisible(false);
					addvehicle.setEnabled(false);
					
					modify.setEnabled(false);
					modify.setVisible(false);
					addDevice.setEnabled(false);
					addDevice.setVisible(false);
					
					for(JLabel t: deviceExplained){
						t.setVisible(false);
					}
					
					for(JTextField t: deviceInfo){
						t.setVisible(false);
						t.setEnabled(false);
					}
					
					info.setVisible(true);
					id.setVisible(true);
					id.setEnabled(true);
					deleteDevice.setEnabled(true);
					deleteDevice.setVisible(true);
					
				}else if(selection.equals("Adicionar Maquinaria")){
					modify.setEnabled(false);
					modify.setVisible(false);
					info.setVisible(false);
					id.setVisible(false);
					id.setEnabled(false);
					deleteDevice.setEnabled(false);
					deleteDevice.setVisible(false);
					
					addmachine.setVisible(true);
					addmachine.setEnabled(true);
					addvehicle.setVisible(true);
					addvehicle.setEnabled(true);
					addDevice.setEnabled(true);
					addDevice.setVisible(true);
					
					for(int i = 0; i<5; i++){
						deviceExplained[i].setVisible(true);
					}
					
					for(int i = 0; i<5; i++){
						deviceInfo[i].setVisible(true);
						deviceInfo[i].setEnabled(true);
					}
					
					
				}else if(selection.equals("Actualizar Datos")){
					addmachine.setVisible(false);
					addmachine.setEnabled(false);
					addvehicle.setVisible(false);
					addvehicle.setEnabled(false);
					addDevice.setEnabled(false);
					addDevice.setVisible(false);
					deleteDevice.setEnabled(false);
					deleteDevice.setVisible(false);
					
					modify.setEnabled(true);
					modify.setVisible(true);
					info.setVisible(true);
					id.setVisible(true);
					id.setEnabled(true);
					
					for(int i = 0; i<5; i++){
						deviceExplained[i].setVisible(true);
					}
					
					for(int i = 0; i<5; i++){
						deviceInfo[i].setVisible(true);
						deviceInfo[i].setEnabled(true);
					}
					
				}else{
					refreshDiagnostics(eval.maintenanceCheck());
				}
				
			}else if(e.getSource() == addvehicle){
				machines = false;
				deviceExplained[2].setText("Kilometraje Actual");
				deviceExplained[3].setText(" Kilometraje Ultimo Servicio");
				clearTextFields();
				
			}else if(e.getSource() == addmachine){
				machines = true;
				deviceExplained[2].setText("Horas de Servicio");
				deviceExplained[3].setText(" Horas Ultimo Servicio");
				clearTextFields();
				
			}else if(e.getSource() == modify){
				try{
					if(!deviceInfo[5].getText().equals("")){
						int deviceID = Integer.parseInt(deviceInfo[5].getText());
						eval.editDevice(eval.getDevice(deviceID), deviceInfo[0].getText(), deviceInfo[1].getText(), deviceInfo[4].getText(), deviceInfo[2].getText(), deviceInfo[3].getText());
						refreshDiagnostics(eval.maintenanceCheck());
						refreshMachines(eval.viewMachines());
						refreshVehicles(eval.viewVehicles());
						clearTextFields();
					}
				}catch(InputMismatchException except){
					feedback.setText(except.getMessage());
				}
				deviceInfo[5].setText("");
			}else if(e.getSource() == deleteDevice){
				try{
					int index = Integer.parseInt(id.getText());
					eval.removeDevice(index);
					refreshMachines(eval.viewMachines());
					refreshVehicles(eval.viewVehicles());
					refreshDiagnostics(eval.maintenanceCheck());
				}catch(Exception noNum){
					feedback.setText("El indice debe ser una valor numerico");
				}
			}else if(e.getSource() == addDevice){
				if(machines){
					try{
						eval.addMachine(deviceInfo[0].getText(), deviceInfo[1].getText(), deviceInfo[4].getText(), deviceInfo[2].getText(), deviceInfo[3].getText());
						refreshMachines(eval.viewMachines());
						refreshDiagnostics(eval.maintenanceCheck());
						clearTextFields();
					}catch(InputMismatchException addicion){
						feedback.setText(addicion.getMessage());
					}
				}else{
					try{
						eval.addVehicle(deviceInfo[0].getText(), deviceInfo[1].getText(), deviceInfo[4].getText(), deviceInfo[2].getText(), deviceInfo[3].getText());
						refreshVehicles(eval.viewVehicles());
						refreshDiagnostics(eval.maintenanceCheck());
					}catch(InputMismatchException addicion2){
						feedback.setText(addicion2.getMessage());
					}
				}
				
				
			}else if(e.getSource() == id){
				if(acciones.getSelectedItem().toString().equals("Actualizar Datos")){
					try{
						int id_ = Integer.parseInt(id.getText());
						Device obtained = eval.getDevice(id_);
						if(obtained != null){
							deviceInfo[0].setText(obtained.getLastServiceDate());
							deviceInfo[1].setText(obtained.getLastUpdateDate());
							deviceInfo[4].setText(obtained.getDescription());
							deviceInfo[5].setText(""+obtained.getID());
							if(obtained instanceof Vehicle){
								deviceExplained[2].setText("Kilometraje Actual");
								deviceExplained[3].setText("  Kilometraje Ultimo Servicio");
								Vehicle vhi = (Vehicle)obtained;
								deviceInfo[2].setText(""+vhi.getKilometers());
								deviceInfo[3].setText(""+vhi.getLastService());
							}else if(obtained instanceof Machine){
								deviceExplained[2].setText("Horas de Servicio");
								deviceExplained[3].setText(" Horas Ultimo Servicio");
								Machine mchi = (Machine)obtained;
								deviceInfo[2].setText(""+mchi.getHours());
								deviceInfo[3].setText(""+mchi.getHoursLast());
							}else{
								
							}
						}else{
							feedback.setText("ID no hallado");
						}
					}catch(Exception NoNum){
						feedback.setText("Valor no numerico");
					}
				}
			}
		}
		
	}
	
}