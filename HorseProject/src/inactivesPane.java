/*
 * Author: Olivia Alford
 * Date:4/20/15
 * Purpose of the class: this pane shows all of the inactive horses and owners so that they can then be reactivated 
 */


import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;


public class inactivesPane extends Observer implements ActionListener, ListSelectionListener {
	Mediator m;//mediator
	private JTable InactiveOwnerTable;//shows inactive owners
	private JTable InactiveHorseTable;//shows inactive horses
	JButton OwnerEdit;//allows one to reactivate owner
	JButton HorseEdit;//allows one to reactivate horse
	ArrayList<String> horses = new ArrayList<String>();//holds all horses
	ArrayList<String>inactiveHorses ;//hodls all inactive horses
	ArrayList<String> owners = new ArrayList<String>();//holds all owners
	ArrayList<String>inactiveOwners;//holds all inactive owners

	/**
	 * Create the panel.
	 */
	public inactivesPane() 
	{

		try {
			//gets sinlge instance of mediator
			m = Mediator.getInstance();
			//attaches observer
			m.attach(this);
			setLayout(null);


		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.initialize();

	}

	/*
	 * initilaizes whole panel
	 */
	public void initialize()
	{
		inactiveHorses = new ArrayList<String>();
		inactiveOwners = new ArrayList<String>(); 

		//gets all horses and owners form mediator
		m.connect();
		try {
			horses = m.getAllHorse();
			owners= m.getAllOwner();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m.disconnect();
		
		//sorts owners and horses
		Collections.sort(horses, String.CASE_INSENSITIVE_ORDER);
		Collections.sort(owners, String.CASE_INSENSITIVE_ORDER);

		//fill inactive horses and owners
		for (int i = 0; i < horses.size();i++ )
		{

			String h = horses.get(i);
			String[] horseInfoList = h.split(",");
			if(horseInfoList[6].equals("1"))
			{
				inactiveHorses.add(h);
			}


		}
		for (int i = 0; i < owners.size();i++ )
		{

			String o = owners.get(i);
			String[] ownerInfoList = o.split(",");
			if(ownerInfoList[5].equals("1"))
			{
				inactiveOwners.add(o);
			}


		}


		//set up horse table
		String[] columnNames = {"Horse Name"};
		String [][] Rowdata = new String [inactiveHorses.size()][1];
		for (int i = 0; i < inactiveHorses.size();i++ )
		{
			String o = inactiveHorses.get(i);
			String[] HorseInfoList = o.split(",");
			String lname = HorseInfoList[0];
			Rowdata[i][0] = lname;
		}

		InactiveHorseTable = new JTable(Rowdata,columnNames);

		//set up table listener
		InactiveHorseTable.getSelectionModel().addListSelectionListener(this);
		InactiveHorseTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(InactiveHorseTable);
		scrollPane.setBounds(284, 24, 146, 195);
		add(scrollPane);
		InactiveHorseTable.setFillsViewportHeight(true);




		//set up owners table
		String [][] Rowdata2 = new String [inactiveOwners.size()][1];
		String[] columnNames2 = {"Last Name"};
		for (int i = 0; i < inactiveOwners.size();i++ )
		{


			String o = inactiveOwners.get(i);
			String[] ownerInfoList = o.split(",");
			String lname = ownerInfoList[0];
			Rowdata2[i][0] = lname;
		}

		InactiveOwnerTable = new JTable(Rowdata2,columnNames2);

		//set up table listener
		InactiveOwnerTable.getSelectionModel().addListSelectionListener(this);
		InactiveOwnerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane2 = new JScrollPane(InactiveOwnerTable);
		scrollPane2.setBounds(25, 24, 153, 195);
		add(scrollPane2);
		InactiveOwnerTable.setFillsViewportHeight(true);




//add buttons
		OwnerEdit = new JButton("Edit Owner Info");
		OwnerEdit.setBounds(25, 243, 153, 29);
		OwnerEdit.addActionListener(this);
		add(OwnerEdit);

		HorseEdit = new JButton("Edit Horse Info");
		HorseEdit.setBounds(284, 243, 146, 29);
		HorseEdit.addActionListener(this);
		add(HorseEdit);	


	}

	@Override
	/*
	 * updates panel per request of the observer
	 */
	public void updateData()
	{
		this.removeAll();
		this.initialize();
		this.updateUI();

	}

	@Override
	/*
	 * handles all button presses
	 */
	public void actionPerformed(ActionEvent e) {
		setCursor (Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		repaint();
		if(e.getSource() == OwnerEdit)//if owner edit presses
		{
			//update that owner
			int row = InactiveOwnerTable.getSelectedRow();
			String[] thisOwner = inactiveOwners.get(row).split(",");
			PopUpOwnerEditor EditOwner = new PopUpOwnerEditor(thisOwner);
			EditOwner.setVisible(true);
		}
		else if (e.getSource() == HorseEdit)//if horse edit pressed
		{
			//update that horse
			int row = InactiveHorseTable.getSelectedRow();
			String[] thisHorse = inactiveHorses.get(row).split(",");
			PopUpHorseEditor EditHorse = new PopUpHorseEditor(thisHorse);
			EditHorse.setVisible(true);
		}
		setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

/*
 * used for table listener
 */
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub

	}
}
