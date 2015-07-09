/*
 * Author: Olivia Alford
 * Date:4/2/15
 * Purpose of the class: displays home tab
 */


import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;


public class HomePane extends Observer {
	private JTable table_1;// table to show all owners with outstanding balances
	Mediator m;
	ArrayList<String> owners = new ArrayList<String>();
	ArrayList<String>activeOwners;
	/**
	 * Create the panel.
	 */
	public HomePane() 
	{
		//gets the sinlge instance of the mediator
		try {
			m = Mediator.getInstance();
			//attaches observer
			m.attach(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initialize();
	}

	/*
	 * Initializes the pane
	 * 
	 */
	public void initialize ()
	{
		ArrayList<String>activeOwners = new ArrayList<String>();
		setLayout(new BorderLayout(0, 0));

		JLabel GreetingLabel = new JLabel("Hello, Lynne!");
		add(GreetingLabel, BorderLayout.NORTH);

		JPanel homePanle = new JPanel();
		add(homePanle, BorderLayout.CENTER);
		homePanle.setLayout(new BorderLayout(0, 0));

		JLabel lblBillsWithOutstanding = new JLabel("Bills with Outstanding Balance");
		homePanle.add(lblBillsWithOutstanding, BorderLayout.NORTH);


		//set up table
		//get all owners
		m.connect();
		try {
			owners = m.getAllOwner();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m.disconnect();

//sort owners
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
		//put owners in table
		String [][] Rowdata = new String [activeOwners.size()][2];

		for (int i = 0; i < activeOwners.size();i++ )
		{

			String o = activeOwners.get(i);
			String[] ownerInfoList = o.split(",");
			String lname = ownerInfoList[1] + " " +ownerInfoList[0];
			String due = ownerInfoList[6];
			Rowdata[i][0] = lname;
			Rowdata[i][1] = due;

		}


		String[] columnNames = {"Owner","Outstanding Balance"};

//set up table and add it
		table_1 = new JTable(Rowdata,columnNames);
		JScrollPane scrollPane = new JScrollPane(table_1);
		table_1.setFillsViewportHeight(true);
		homePanle.add(scrollPane);
	}
	
	@Override
	/*
	 * updates data per request of observer
	 */
	public void updateData() {
		// TODO Auto-generated method stub
		this.removeAll();
		this.initialize();
		this.updateUI();
	}

}
