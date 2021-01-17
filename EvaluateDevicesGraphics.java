
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class EvaluateDevicesGraphics extends JPanel{
	private EvaluateDevices eval;
	private JComboBox<String> acciones;
	private JTextField[] deviceInfo = new JTextField[6];
	private JLabel[] deviceExplained = new JLabel[6];
	private JButton addvehicle, addmachine, modify, addDevice;
	private JTextField id = new JTextField();
	private JLabel info;
	private JPanel presentVehicles = new JPanel();
	private JPanel presentMachines = new JPanel();
	
	
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
		
		String[] acts = {"Adicionar Maquinaria", "Eliminar Maquinaria", "Actualizar Datos"};
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
		add(addvehicle);
		addvehicle.setVisible(false);
		addvehicle.setEnabled(false);
		/*
		Finaliza la instanciacion de los bootnes
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
		deviceExplained[1] = new JLabel("  Ultima Actualizacion");
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
		add(modify, gcn);
		
		
		
		gcn.gridx = 4;
		gcn.gridy = 2;
		gcn.gridwidth = 4;
		gcn.gridheight = 8;
		gcn.fill = GridBagConstraints.BOTH;
		gcn.insets = new Insets(0, 8, 0, 0);
		JTabbedPane tabs = new JTabbedPane();
		JScrollPane scrollMaquinas = new JScrollPane(presentMachines, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		JScrollPane scrollVehiculos = new JScrollPane(presentVehicles, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		tabs.addTab("Maquinas", scrollMaquinas);
		tabs.addTab("Vehiculos", scrollVehiculos);
		add(tabs, gcn);
		
		setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
		
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			
		}
		
	}
	
}