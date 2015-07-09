/*
 * Author: Olivia Alford
 * Date:4/9/15
 * Purpose of the class: sub tab of the display tab, displays and allows edits and additions of owners in data base
 */


import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;





public class OwnerSubtab extends Observer implements ActionListener, ListSelectionListener {
	private JTable table;//displays current owners
	ListSelectionModel listSelectionModel;//create uneditable table
	JButton NewOwnerButton;//allows for addition of new owner to db
	JButton EditButton;//allows for a current owner to be edited
	Mediator m;//mediator
	JLabel fNameLabel;//labels first name
	JLabel LNameLabel;//holds owners last name
	JLabel PhoneLabel ;//labels phone number
	JLabel StreetAdressLabel;//labels street address
	JLabel BalanceLabel;//labels out standing balalnce on account
	JLabel HorsesLabel;//labels horses
	JLabel ActiveLabel;//labels active or not
	JLabel ZipLabel;//labels zip
	ArrayList<String> owners = new ArrayList<String>();//holds all owners
	ArrayList<String>activeOwners ;//holds all acive owners

	/**
	 * Create the panel.
	 */
	public OwnerSubtab() throws Exception
	{
		try {
			//get single instance of mediator
			m = Mediator.getInstance();
			//attach observer
			m.attach(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initialize();
	}

/*
 * initialize the whole panel
 */
	public void initialize ()
	{

		activeOwners = new ArrayList<String>();
		setLayout(new BorderLayout(0, 0));

		JPanel TitlePanel = new JPanel();
		add(TitlePanel, BorderLayout.NORTH);
		TitlePanel.setLayout(new BorderLayout(0, 0));

		JLabel TitleLabel = new JLabel("Owner by Last Name");
		TitlePanel.add(TitleLabel, BorderLayout.NORTH);

		JPanel MiddlePanel = new JPanel();


		add(MiddlePanel, BorderLayout.CENTER);


		//get all owners
		m.connect();
		try {
			owners = m.getAllOwner();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m.disconnect();

		Collections.sort(owners, String.CASE_INSENSITIVE_ORDER);

		//fill active owners
		for (int i = 0; i < owners.size();i++ )
		{

			String o = owners.get(i);
			String[] ownerInfoList = o.split(",");
			if(ownerInfoList[5].equals("0"))
			{
				activeOwners.add(o);
			}


		}
		//set up table and add it
		String [][] Rowdata = new String [activeOwners.size()][1];
		String[] columnNames = {"Last Name"};

		for (int i = 0; i < activeOwners.size();i++ )
		{
			String o = activeOwners.get(i);
			String[] ownerInfoList = o.split(",");
			String lname = ownerInfoList[0];
			Rowdata[i][0] = lname;
		}


		table = new JTable(Rowdata,columnNames);

		

		//sets up listener fo the table

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(this);
		MiddlePanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(6, 0, 130, 278);
		MiddlePanel.add(scrollPane);
		table.setFillsViewportHeight(true);
		MiddlePanel.add(scrollPane);





		//adds panel to hold all text
		JPanel InputPanel = new JPanel();
		InputPanel.setBounds(134, 0, 353, 389);
		MiddlePanel.add(InputPanel);
		InputPanel.setLayout(null);

		JPanel TextPanel = new JPanel();
		TextPanel.setBounds(6, 0, 341, 231);
		InputPanel.add(TextPanel);
		TextPanel.setLayout(new GridLayout(9, 1, 0, 0));

		fNameLabel = new JLabel("First Name: ");
		TextPanel.add(fNameLabel);

		LNameLabel = new JLabel("Last Name: " );
		TextPanel.add(LNameLabel);

		PhoneLabel = new JLabel("Phone: ");
		TextPanel.add(PhoneLabel);

		StreetAdressLabel = new JLabel("Address: ");
		TextPanel.add(StreetAdressLabel);


//add buttons and button panel
		JPanel ButtonPanel = new JPanel();
		ButtonPanel.setBounds(-60, 281, 407, 102);
		InputPanel.add(ButtonPanel);

		BalanceLabel = new JLabel("Balance Due:");
		TextPanel.add(BalanceLabel);

		ActiveLabel = new JLabel("Active: ");
		TextPanel.add(ActiveLabel);

		//adds buttons
		EditButton = new JButton("Edit Owner Info");
		EditButton.addActionListener(this);
		ButtonPanel.add(EditButton);

		NewOwnerButton = new JButton("Add New Owner");
		NewOwnerButton.addActionListener(this);
		ButtonPanel.add(NewOwnerButton);

	}

	//handles all button presses
	//@param: any action event
	@Override
	public void actionPerformed(ActionEvent e) {
		setCursor (Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		// TODO Auto-generated method stub
		repaint();
		if(e.getSource() == NewOwnerButton)//if new owner button clicked
		{
			//gathers all information through popup
			PopUpOwnerAdder newOwner;
			try {
				newOwner = new PopUpOwnerAdder();
				newOwner.setVisible(true);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

		else if(e.getSource() == EditButton)//if edit owner button selected
		{
			//does all work through popup 
			int row = table.getSelectedRow();
			if(row ==-1)
			{
				JOptionPane.showMessageDialog(null, "Please select an owner first.");
			}
			else{
			
			String[] thisOwner = activeOwners.get(row).split(",");
			PopUpOwnerEditor EditOwner = new PopUpOwnerEditor(thisOwner);
			EditOwner.setVisible(true);
			}

		}
	
		setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

	/*
	 * handles all interaction with tables
	 * @param: any list selection event
	 */
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == table.getSelectionModel()) 
		{
	

			int row = table.getSelectedRow();
			int col = table.getSelectedColumn();
			String value = (String)table.getValueAt(row, col);
			////System.out.println("The value Is" + value+ ". and "+activeOwners.size());
			String[] thisOwner = activeOwners.get(row).split(",");
			fNameLabel.setText("First Name: "+ thisOwner[1]);
			LNameLabel.setText("Last Name: "+ thisOwner[0]);
			StreetAdressLabel.setText("Address: "+thisOwner[2]);
			PhoneLabel.setText("Phone: "+thisOwner[3]);
			String a;
			////System.out.println("Active gotten out" +thisOwner[5]);
			if(thisOwner[5].equals("0"))
			{
				a = "Active";
			}
			else
			{
				a = "Not Active";
			}

			ActiveLabel.setText("Active: " + a);
			BalanceLabel.setText("Balance Due: "+thisOwner[6]);
		} 


	}


	/*
	 * updates panel per request of the observer
	 */
	@Override
	public void updateData()
	{
		this.removeAll();
		this.initialize();
		this.updateUI();

	}
}
