/*
 * Author: Olivia Alford
 * Date:4/2/15
 * Purpose of the class: displays horse tab
 */

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.GridLayout;



import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


public class InvoicePane extends Observer implements ActionListener {

	private JTextField FromDateMonth;//allows user to enter the date on whcih the invoice will be sent
	private JTextField ToDateMonth;//allows user to enter the date on whcih the invoice will be due
	//private JTextArea CommentsText;//allows user to enter other comments
	JButton ClearButton;//clears form
	JButton SubmitButton;// submits form
	JComboBox OwnerChooser;// allows user to choose owner to bill
	String[] chooserOptions;//options for jcombo box
	ArrayList<String>activeOwners;//all active owners
	Mediator m;//mediator
	ArrayList<String> owners = new ArrayList<String>();//holds all owners
	private JTextField FromDateDay;
	private JTextField FromDateYear;
	private JTextField ToDateDay;
	private JTextField ToDateYear;

	/**
	 * Create the panel.
	 */
	public InvoicePane() {
		try {
			//gets single instance of mediator
			m = Mediator.getInstance();
			//attaches observer
			m.attach(this);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.initialize();

	}

	/*
	 * initializes whole pane
	 */
	public void initialize()
	{
		activeOwners = new ArrayList<String>();
		setLayout(new GridLayout(2, 1, 0, 0));

		JPanel TitlePanel = new JPanel();
		add(TitlePanel);
		TitlePanel.setLayout(null);

		JLabel TitleLabel = new JLabel("Create invoice for owner selected");
		TitleLabel.setBounds(0, 0, 450, 16);
		TitlePanel.add(TitleLabel);

		//add a panel for all text entry fields to be held in
		JPanel TextEntryPanel = new JPanel();
		TextEntryPanel.setBounds(108, 16, 324, 111);
		TitlePanel.add(TextEntryPanel);
		TextEntryPanel.setLayout(null);

		//adds all text entry fields
		FromDateMonth = new JTextField();
		FromDateMonth.setBounds(6, 0, 39, 28);
		FromDateMonth.setText("MM");
		TextEntryPanel.add(FromDateMonth);
		FromDateMonth.setColumns(10);

		ToDateMonth = new JTextField();
		ToDateMonth.setBounds(6, 35, 39, 28);
		ToDateMonth.setText("MM");
		TextEntryPanel.add(ToDateMonth);
		ToDateMonth.setColumns(10);



		//adds combo box for owner selection
		try {
			m.connect();
			owners = m.getAllOwner();
			m.disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



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

		//adds all active owners to combo box options
		String[] chooserOptions= new String[activeOwners.size()];

		for(int i = 0; i<activeOwners.size(); i++)
		{
			String thisO=activeOwners.get(i);
			String [] thisOwner=thisO.split(",");

			chooserOptions[i] = thisOwner[0]+","+thisOwner[1];

		}

		//creates and adds combo box
		OwnerChooser = new JComboBox(chooserOptions);
		OwnerChooser.setBounds(6, 75, 312, 28);
		TextEntryPanel.add(OwnerChooser);

		JLabel label = new JLabel("/");
		label.setBounds(57, 6, 15, 16);
		TextEntryPanel.add(label);

		FromDateDay = new JTextField();
		FromDateDay.setBounds(84, 0, 43, 28);
		FromDateDay.setText("DD");
		TextEntryPanel.add(FromDateDay);
		FromDateDay.setColumns(10);

		JLabel label_1 = new JLabel("/");
		label_1.setBounds(139, 6, 15, 16);
		TextEntryPanel.add(label_1);

		FromDateYear = new JTextField();
		FromDateYear.setBounds(166, 0, 51, 28);
		FromDateYear.setText("YYYY");
		TextEntryPanel.add(FromDateYear);
		FromDateYear.setColumns(10);

		JLabel label_2 = new JLabel("/");
		label_2.setBounds(57, 41, 15, 16);
		TextEntryPanel.add(label_2);

		ToDateDay = new JTextField();
		ToDateDay.setText("DD");
		ToDateDay.setColumns(10);
		ToDateDay.setBounds(84, 35, 43, 28);
		TextEntryPanel.add(ToDateDay);

		JLabel label_3 = new JLabel("/");
		label_3.setBounds(139, 41, 15, 16);
		TextEntryPanel.add(label_3);

		ToDateYear = new JTextField();
		ToDateYear.setText("YYYY");
		ToDateYear.setColumns(10);
		ToDateYear.setBounds(166, 35, 51, 28);
		TextEntryPanel.add(ToDateYear);


		JPanel ButtonEntryPanel = new JPanel();
		ButtonEntryPanel.setBounds(0, 129, 450, 39);
		TitlePanel.add(ButtonEntryPanel);

		SubmitButton = new JButton("Submit");
		SubmitButton.addActionListener(this);
		ButtonEntryPanel.add(SubmitButton);



		//adds buttons
		ClearButton = new JButton("Clear");
		ClearButton.addActionListener(this);
		ButtonEntryPanel.add(ClearButton);

		//add a panel to hold all labels
		JPanel TextPanel = new JPanel();
		TextPanel.setBounds(0, 16, 94, 95);
		TitlePanel.add(TextPanel);
		TextPanel.setLayout(null);

		//adds all labels
		JLabel DateInvoicedLabel = new JLabel("Invoice From:");
		DateInvoicedLabel.setBounds(0, 0, 85, 15);
		TextPanel.add(DateInvoicedLabel);

		JLabel DateDueLabel = new JLabel("Invoice To:");
		DateDueLabel.setBounds(0, 47, 85, 15);
		TextPanel.add(DateDueLabel);


		JLabel OwnerLabel = new JLabel("Owner");
		OwnerLabel.setBounds(0, 74, 85, 15);
		TextPanel.add(OwnerLabel);
	}


	//handles all button pressed
	//@param: any action event
	public void actionPerformed(ActionEvent e) 
	{
		setCursor (Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		// TODO Auto-generated method stub
		repaint();
		if(e.getSource() == ClearButton)//if clear button pressed
		{
			FromDateMonth.setText("");
			ToDateMonth.setText("");
			FromDateDay.setText("");
			ToDateDay.setText("");
			FromDateYear.setText("");
			ToDateYear.setText("");
			//	CommentsText.setText("");
		}
		else if(e.getSource() == SubmitButton)//if submit button pressed
		{
			try
			{
				//gets all info from fields
				String dateInvoiced = FromDateMonth.getText()+"/"+FromDateDay.getText()+"/"+FromDateYear.getText();
				String dateDue = ToDateMonth.getText()+"/"+ToDateDay.getText()+"/"+ToDateYear.getText();
				String Owner = OwnerChooser.getSelectedItem().toString();
				String [] names = Owner.split(",");
				String lastNameSelected = names[0];
				String firstNameSelected = names[1];
				int ownerID =0;

				//gets the owner id from list of active owners
				for(int i = 0; i<activeOwners.size(); i++)
				{
					String [] thisOwner=activeOwners.get(i).split(",");
					if(lastNameSelected.equals(thisOwner[0])&&firstNameSelected.equals(thisOwner[1]))
					{
						ownerID= Integer.parseInt(thisOwner[4]);
					}
				}

				//inserts invoice into table
				try {
					m.connect();
					int ID = m.getNextInvoiceID();
					m.disconnect();
					invoice thisInvoice = new  invoice(lastNameSelected, firstNameSelected, dateInvoiced, dateDue, ID);
					String fileName = thisInvoice.getFileName();
					try {
						java.awt.Desktop.getDesktop().edit(new File(fileName));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					m.connect();
					m.insertInvoice(lastNameSelected, firstNameSelected, ownerID, Integer.parseInt(ToDateDay.getText()),Integer.parseInt(ToDateMonth.getText()), Integer.parseInt(ToDateYear.getText()),ID,fileName) ;
					m.disconnect();
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}


			}

			catch(NumberFormatException NFE)
			{
				JOptionPane.showMessageDialog(null, "Make sure you have entered a numeral value for all day, month, and year fields.");
			}
		}
		setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	@Override
	/*
	 * updates panel per request of observer
	 */
	public void updateData()
	{
		this.removeAll();
		this.initialize();
		this.updateUI();

	}
}
