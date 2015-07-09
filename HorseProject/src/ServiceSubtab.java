/*
 * Author: Olivia Alford
 * Date:4/9/15
 * Purpose of the class: sub tab of the details tab, used to show all current services and add new ones
 */


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


public class ServiceSubtab extends Observer implements ActionListener,ListSelectionListener{
	private JTable table;//tabel to display current services
	JButton AddNewServiceButton;//if user desires to add a new service
	Mediator m;//mediator
	ArrayList<String> serviceTypes = new ArrayList<String>();//holds all service types currently available
	JButton btnEditService;//allows user to edit service

	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public ServiceSubtab() throws SQLException 
	{
		try {
			//gets single instance of the mediator
			m = Mediator.getInstance();
			//attaches observer
			m.attach(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		initialize();
	}
	public void initialize ()
	{
		setLayout(null);

		//adds title label
		JLabel lblCurrentServices = new JLabel("Current Services");
		lblCurrentServices.setBounds(162, 6, 137, 25);
		add(lblCurrentServices);

		//add new service button
		AddNewServiceButton = new JButton("Add New Service");
		AddNewServiceButton.setBounds(70, 265, 169, 29);
		add(AddNewServiceButton);
		AddNewServiceButton.addActionListener(this);

		JPanel TablePanel = new JPanel();
		TablePanel.setBounds(17, 26, 413, 231);
		add(TablePanel);
		TablePanel.setLayout(new BorderLayout(0, 0));

		//set up table

		try {
			m.connect();
			serviceTypes = m.getAllTypesOfServices();
			m.disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		//creates and adds table
		String[] columnNames = {"Name","Description","Price"};
		String [][] Rowdata = new String [serviceTypes.size()][3];

		Collections.sort(serviceTypes, String.CASE_INSENSITIVE_ORDER);


		for (int i = 0; i < serviceTypes.size();i++ )
		{
			String o = serviceTypes.get(i);
			String[] ServiceInfoList = o.split(",");
			String name = ServiceInfoList[0];
			String des = ServiceInfoList[1];
			String price = ServiceInfoList[2];
			Rowdata[i][0] = name;
			Rowdata[i][1] = des;
			Rowdata[i][2] = price;
		}


		table = new JTable(Rowdata,columnNames);

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(this);

		JScrollPane scrollPane = new JScrollPane(table);
		TablePanel.add(scrollPane);

		btnEditService = new JButton("Edit Service");
		btnEditService.addActionListener(this);
		btnEditService.setBounds(272, 265, 117, 29);
		add(btnEditService);
		table.setFillsViewportHeight(true);


	}

	//handle button presses
	//@param: any action event
	public void actionPerformed(ActionEvent e) {
		setCursor (Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		// TODO Auto-generated method stub
		repaint();
		if(e.getSource() == AddNewServiceButton)//if add new service clicked
		{
			//creates popup
			PopUpNewService newService = new PopUpNewService();
			newService.setVisible(true);
		}
		else if(e.getSource() == btnEditService)//if edit button clicked
		{
			//creates popup
			int row = table.getSelectedRow();
			if(row ==-1)
			{
				JOptionPane.showMessageDialog(null, "Please select a service first.");
			}
			else
			{
				String[] thisOwner = serviceTypes.get(row).split(",");
				popUpServiceEditor EditOwner = new popUpServiceEditor(thisOwner);
				EditOwner.setVisible(true);
			}
		}
		setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

	@Override
	/*
	 * updates panel per request of the observer
	 */
	public void updateData() {
		// TODO Auto-generated method stub
		this.removeAll();
		this.initialize();
		this.updateUI();
	}

	/*
	 * handles all chicks on the table
	 * @param: any list selection event
	 */
	@Override
	public void valueChanged(ListSelectionEvent e) 
	{
		// TODO Auto-generated method stub
		if (e.getSource() == table.getSelectionModel()) 
		{
			int row = table.getSelectedRow();
			int col = table.getSelectedColumn();
			String value = (String)table.getValueAt(row, col);

		}
	}
}
