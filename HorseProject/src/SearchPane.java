/*
 * Author: Olivia Alford
 * Date:4/2/15
 * Purpose of the class: holds search tab display allows for searching of invoices
 */

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Cursor;
import java.awt.GridLayout;

import javax.swing.JSplitPane;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


public class SearchPane extends Observer implements ActionListener, ListSelectionListener{
	private JTextField FNameText;//holds text of first name 
	private JTextField LNameText;//holds text of last name 
	private JTextField FromDateMonth;//holds text of date where search begins
	private JTable table;//displays invoices found
	JButton ClearButton;//clears form
	JButton SearchButton;//submits form
	JComboBox InvoiceChooser;//allows user to select what sort of invoices to search
	Mediator m;//mediator
	ArrayList<String> found;//holds all invoices found when search is implemented
	JComboBox searchType ;//holds type of search to be done
	private JTextField FromDateDay;
	private JTextField FromDateYear;
	private JTextField ToDateMonth;
	private JTextField ToDateDay;
	private JTextField ToDateYear;

	/**
	 * Create the panel.
	 */
	public SearchPane() {
		try {
			//gets single instance of mediator
			m = Mediator.getInstance();
			//attaches observer
			m.attach(this);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		found = new ArrayList <String>();
		this.initialize();
	}

	/*
	 * initializes whole panel
	 */
	public void initialize()
	{

		setLayout(new GridLayout(2, 1, 0, 0));

		JPanel TitlePanel = new JPanel();
		add(TitlePanel);
		TitlePanel.setLayout(new BorderLayout(0, 0));

		JLabel TitleLabel = new JLabel("Search: Search for invoice with given criteria");
		TitlePanel.add(TitleLabel, BorderLayout.NORTH);

		//add panel to hold all labels
		JPanel TextPanel = new JPanel();
		TitlePanel.add(TextPanel, BorderLayout.WEST);
		TextPanel.setLayout(new GridLayout(6, 1, 0, 0));

		JLabel FNameLabel = new JLabel("Owner First Name");
		TextPanel.add(FNameLabel);

		JLabel LNameLabel = new JLabel("Owner Last Name");
		TextPanel.add(LNameLabel);

		JLabel fromDateLabel = new JLabel("From:");
		TextPanel.add(fromDateLabel);

		JLabel toDateLabel = new JLabel("To:");
		TextPanel.add(toDateLabel);


		//add panel to hold all text entry fields
		JPanel TextEntryPanel = new JPanel();
		TitlePanel.add(TextEntryPanel, BorderLayout.CENTER);
		TextEntryPanel.setLayout(null);

		FNameText = new JTextField();
		FNameText.setBounds(0, 0, 431, 27);
		FNameText.setText("First Name");
		TextEntryPanel.add(FNameText);
		FNameText.setColumns(10);

		LNameText = new JTextField();
		LNameText.setBounds(0, 27, 431, 27);
		LNameText.setText("Last Name");
		TextEntryPanel.add(LNameText);
		LNameText.setColumns(10);

		FromDateMonth = new JTextField();
		FromDateMonth.setBounds(0, 54, 44, 27);
		FromDateMonth.setText("MM");
		TextEntryPanel.add(FromDateMonth);
		FromDateMonth.setColumns(10);

		searchType = new JComboBox();
		searchType.setBounds(0, 108, 431, 27);
		searchType.addItem("Search by Start Date and End date");
		searchType.addItem("Search by Owner Name");
		TextEntryPanel.add(searchType);

		JLabel label = new JLabel("/");
		label.setBounds(46, 54, 17, 27);
		TextEntryPanel.add(label);

		FromDateDay = new JTextField();
		FromDateDay.setText("DD");
		FromDateDay.setColumns(10);
		FromDateDay.setBounds(65, 54, 44, 27);
		TextEntryPanel.add(FromDateDay);

		JLabel label_1 = new JLabel("/");
		label_1.setBounds(121, 55, 17, 27);
		TextEntryPanel.add(label_1);

		FromDateYear = new JTextField();
		FromDateYear.setText("YYYY");
		FromDateYear.setColumns(10);
		FromDateYear.setBounds(146, 54, 59, 27);
		TextEntryPanel.add(FromDateYear);

		ToDateMonth = new JTextField();
		ToDateMonth.setText("MM");
		ToDateMonth.setColumns(10);
		ToDateMonth.setBounds(0, 83, 44, 27);
		TextEntryPanel.add(ToDateMonth);

		JLabel label_2 = new JLabel("/");
		label_2.setBounds(46, 83, 17, 27);
		TextEntryPanel.add(label_2);

		JLabel label_3 = new JLabel("/");
		label_3.setBounds(117, 84, 17, 27);
		TextEntryPanel.add(label_3);

		ToDateDay = new JTextField();
		ToDateDay.setText("DD");
		ToDateDay.setColumns(10);
		ToDateDay.setBounds(65, 83, 44, 27);
		TextEntryPanel.add(ToDateDay);

		ToDateYear = new JTextField();
		ToDateYear.setText("YYYY");
		ToDateYear.setColumns(10);
		ToDateYear.setBounds(146, 83, 59, 27);
		TextEntryPanel.add(ToDateYear);


		JPanel TabelPanel = new JPanel();
		add(TabelPanel);

		//set up tabel for displaying invoices found in search



		String [][] Rowdata = new String [found.size()][4];
		if(found.size()==0)		//if invoices not found
		{
			Rowdata = new String [found.size()+1][4];
			Rowdata[0][0] ="No Such Invoices Found";
		}
		else
		{
			Rowdata = new String [found.size()][4];
			for (int i = 0; i < found.size();i++ )
			{
				// file  first last  month year bill id
				String o = found.get(i);
				String[] invoiceInfoList = o.split(",");
				String ownerId  = invoiceInfoList[2];


				ArrayList<String> ownerNameSearch = new ArrayList <String> ();
				try {
					m.connect();
					ownerNameSearch = m.searchOwnerALL("", "","", -1, Integer.parseInt(ownerId), -1,-1, 1, 1, -1,-1, -1, -1, -1);
					m.disconnect();
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				String [] ownerInfo =  ownerNameSearch.get(0).split(",");

				String OwnerName = ownerInfo[0] + " " + ownerInfo[1];
				String date = invoiceInfoList[4]+"/"+invoiceInfoList[3]+"/"+invoiceInfoList[5];
				String number = invoiceInfoList[0];
				String fileName = invoiceInfoList[1];
				Rowdata[i][0] = number;
				Rowdata[i][1] = OwnerName;
				Rowdata[i][2] = date;
				Rowdata[i][3] = fileName;

			}



			String[] columnNames = {"Invoice Number","Owner","Invoice Date", "File Name"};
			TabelPanel.setLayout(null);
			table = new JTable(Rowdata,columnNames);

			//set up table listener
			table.getSelectionModel().addListSelectionListener(this);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setBounds(0, 5, 537, 139);
			TabelPanel.add(scrollPane);
			table.setFillsViewportHeight(true);

		}



		JPanel ButtonEntryPanel = new JPanel();
		TitlePanel.add(ButtonEntryPanel, BorderLayout.SOUTH);

		//add search button
		SearchButton = new JButton("Search");
		SearchButton.addActionListener(this);
		ButtonEntryPanel.add(SearchButton);

		//add clear button
		ClearButton = new JButton("Clear");
		ClearButton.addActionListener(this);
		ButtonEntryPanel.add(ClearButton);

	}


	//handles all button presses
	//@param: any action event
	public void actionPerformed(ActionEvent e) 
	{
		setCursor (Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		try
		{
			// TODO Auto-generated method stub
			repaint();
			if(e.getSource() == SearchButton)
			{
				if(searchType.getSelectedItem().equals("Search by Start Date and End date"))
				{
					String [] fromDate =  FromDateMonth.getText().split("/");

					int sday = Integer.parseInt(FromDateDay.getText());
					int smonth = Integer.parseInt(FromDateMonth.getText());
					int syear = Integer.parseInt(FromDateYear.getText());

					int eday = Integer.parseInt(ToDateDay.getText());
					int emonth = Integer.parseInt(ToDateMonth.getText());
					int eyear = Integer.parseInt(ToDateYear.getText());

					m.connect();
					try {
						found = m.SearchInvoice( sday, smonth, syear,eday,  emonth,  eyear,0);
						////System.out.println("Search by dates"+found.size());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					m.disconnect();
				}

				else//search by owner name
				{
					String fName =  FNameText.getText();
					String lName =  LNameText.getText();
					int ownerID=0;
					m.connect();
					ArrayList<String> owners = new ArrayList<String>();
					try {
						owners = m.getAllOwner();
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					m.disconnect();
					for(int i =0;i<owners.size(); i++)
					{
						String [] thisOwner = owners.get(i).split(",");
						if(thisOwner[0].equals(lName) &&thisOwner[1].equals(fName))
						{
							ownerID = Integer.parseInt(thisOwner[4]);
						}
					}
					try {
						m.connect();
						// file  first last  month year bill id
						found = m.searchInvoicesALL(-1, "",ownerID, -1, -1, -1, 1,1,1, 1, 1, 1);

						////System.out.println("Search by Owner name"+found.size());
						m.disconnect();
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					for (int i = 0; i<found.size();i++)
					{
						////System.out.println(found.get(i));
					}
				}
				this.updateData();
				if(found.size()==0)
				{
					JOptionPane.showMessageDialog(null, "There were no invoices found under these specifications.");
				}
			}
			else if(e.getSource() == ClearButton)
			{
				this.updateData();

			}

			
		}
		catch(NumberFormatException NFE)
		{
			JOptionPane.showMessageDialog(null, "Make sure you have entered a numeral value for all day, month, and year fields.");
		}
		setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

	/*
	 * updates data per request of the observer
	 */
	@Override
	public void updateData() {
		// TODO Auto-generated method stub
		this.removeAll();
		this.initialize();
		this.updateUI();

	}

	/*
	 * handles all list selections
	 */
	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		////System.out.println(table.getSelectedRow());
		String [] thisInvoice=found.get(table.getSelectedRow()).split(",");
		String fileName = thisInvoice[1];
		try {
			java.awt.Desktop.getDesktop().edit(new File(fileName));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}


}
