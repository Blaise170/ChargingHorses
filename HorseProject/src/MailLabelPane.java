/*
 * Author: Olivia Alford
 * Date:4/2/15
 * Purpose of the class: displays mailing label tab
 */

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.BorderLayout;

import javax.swing.*;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


public class MailLabelPane extends Observer implements ActionListener, ListSelectionListener{
	private JTable table;//displays owerns already in the data base
	JButton MakeLabelButton;// submits form
	ArrayList<String>owners = new ArrayList<String>();//hodls all owners
	ArrayList<String>activeOwners;//holds all active owners
	Mediator m;//mediator


	/**
	 * Create the panel.
	 */
	public MailLabelPane() 	
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
	 * initialize the whole pane
	 */
	public void initialize ()
	{

		activeOwners = new ArrayList<String>();
		setLayout(new BorderLayout(0, 0));

		//add title panel and title label
		JPanel TitlePanel = new JPanel();
		add(TitlePanel, BorderLayout.NORTH);
		TitlePanel.setLayout(new BorderLayout(0, 0));

		JLabel TitleLabel = new JLabel("Owner by Last Name");
		TitlePanel.add(TitleLabel, BorderLayout.NORTH);

		JPanel MiddlePanel = new JPanel();
		add(MiddlePanel, BorderLayout.CENTER);
		String[] columnNames = {"Last Name"};

		//sets up table
		//get all owners form mediator
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
		String [][] Rowdata = new String [activeOwners.size()][1];


		for (int i = 0; i < activeOwners.size();i++ )
		{
			String o = activeOwners.get(i);
			String[] ownerInfoList = o.split(",");
			String lname = ownerInfoList[0];
			Rowdata[i][0] = lname;
		}


		table = new JTable(Rowdata,columnNames);



		//sets up action listener for table
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(this);


		MiddlePanel.setLayout(null);
		MiddlePanel.setLayout(null);


		//add table to scroll pane
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(86, 6, 134, 278);
		MiddlePanel.add(scrollPane);
		table.setFillsViewportHeight(true);
		MiddlePanel.add(scrollPane);

		//add button
		MakeLabelButton = new JButton("Create Labels");
		MakeLabelButton.setBounds(262, 123, 161, 29);
		MakeLabelButton.addActionListener(this);
		MiddlePanel.add(MakeLabelButton);


	}


	//handles all button presses
	//@param: any action event
	@Override
	public void actionPerformed(ActionEvent e) {
		setCursor (Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		// TODO Auto-generated method stub
		repaint();
		if(e.getSource() == MakeLabelButton)//if make label button pressed
		{
			String fileName = "Labels.txt";

			//write all names and addresses to Labels.txt files in mailing label format
			try 
			{
				FileWriter fileWriter = new FileWriter(fileName);
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

				for (int i = 0; i<activeOwners.size();i++)
				{
					String [] thisOwner = activeOwners.get(i).split(",");
					String [] address = thisOwner[2].split("~");
					bufferedWriter.write(thisOwner[1] + " "+thisOwner[0] );
					bufferedWriter.newLine();
					bufferedWriter.write(address[0]);
					bufferedWriter.newLine();
					bufferedWriter.write(address[1]+" "+address[2]+" "+address[3]);
					bufferedWriter.newLine();
					bufferedWriter.newLine();
					bufferedWriter.newLine();
					bufferedWriter.newLine();


				}
				bufferedWriter.close();
				
				java.awt.Desktop.getDesktop().edit(new File("Labels.txt"));
				JOptionPane.showMessageDialog(null, "Your mailing labels have been save to 'Labels.txt'.");
			}
			catch(IOException ex) {
				////System.out.println("Error writing to file '"+ fileName + "'");

			}

		}
		setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}



	
	
	/*
	 *works with table listener
	 *@param: any list selection event
	 */
	public void valueChanged(ListSelectionEvent e) {

		// TODO Auto-generated method stub
		if (e.getSource() == table.getSelectionModel()) 
		{
			int row = table.getSelectedRow();
			int col = table.getSelectedColumn();
			String value = (String)table.getValueAt(row, col);
			////System.out.println("The value Is" + value+ ".");

		} 


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
}
