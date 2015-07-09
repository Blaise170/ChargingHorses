/*
 * Author: Olivia Alford
 * Date:4/9/15
 * Purpose of the class: subtab of the display tab, shows horse info and allows edits and adds
 */

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.table.DefaultTableModel;





public class HorseSubtab extends Observer implements ActionListener, ListSelectionListener{
	private JTable table;//table to show current horses
	JLabel LNameLabel;//labels last name 
	ListSelectionModel listSelectionModel;//creates uneditable table
	JButton NewHorseButton;//allows user to add new horse
	JButton EditButton;//allows user to edit horse info
	JButton AddServiceButton;//allows user to add a service for a horse
	Mediator m;//mediator
	JLabel fNameLabel;//labels first name
	JLabel SexLabel;//labesl sex
	JLabel PaceLabel;//labels pace
	JLabel DamLabel;//labes dam
	JLabel SireLabel;// labels sire
	JLabel YOBLabel;//label year of birth
	JLabel ActiveLabel;//labels active 
	ArrayList<String> horses = new ArrayList<String>();//holds all horses
	ArrayList<String> owners = new ArrayList<String>();//holds all owners
	JPanel TextPanel = new JPanel();//holds all text objects
	JButton btnSeeAndEdit;///allows shares to be edited
	ArrayList<String>activeHorses ;//holds all active horses
	JButton btnAddADaily;//allows user to add a daily service
	JButton RDButton;//allows user to add race details
	JButton RRCButton;//allows user to add race related charges
	/**
	 * Create the panel.
	 */
	public HorseSubtab() {
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
	 * initializes whole panel
	 */
	public void initialize()
	{
		activeHorses= new ArrayList<String>();
		setLayout(null);

		//addes panels
		JPanel TitlePanel = new JPanel();
		TitlePanel.setBounds(0, 0, 500, 16);
		add(TitlePanel);
		TitlePanel.setLayout(new BorderLayout(0, 0));

		JLabel TitleLabel = new JLabel("Horse by Name");
		TitlePanel.add(TitleLabel, BorderLayout.NORTH);

		JPanel MiddlePanel = new JPanel();
		MiddlePanel.setBounds(0, 16, 554, 464);
		add(MiddlePanel);

		//set up table
		String[] columnNames = {"Horse Name"};

		//get all horses
		m.connect();
		try {
			horses = m.getAllHorse();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		m.disconnect();

		//sort horses
		Collections.sort(horses, String.CASE_INSENSITIVE_ORDER);

		//fill active horses
		for (int i = 0; i < horses.size();i++ )
		{

			String h = horses.get(i);
			String[] horseInfoList = h.split(",");
			if(horseInfoList[6].equals("0"))
			{
				activeHorses.add(h);
			}


		}
		String [][] Rowdata = new String [activeHorses.size()][1];
		for (int i = 0; i < activeHorses.size();i++ )
		{
			////////System.out.println("Seting up table");
			String o = activeHorses.get(i);
			String[] HorseInfoList = o.split(",");
			String lname = HorseInfoList[0];
			Rowdata[i][0] = lname;
		}


		table = new JTable(Rowdata,columnNames);


		//set up table listener
		table.getSelectionModel().addListSelectionListener(this);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		MiddlePanel.setLayout(null);

		//add table to scroll pane
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(6, 0, 130, 278);
		MiddlePanel.add(scrollPane);
		table.setFillsViewportHeight(true);





		//add panel to hold all text(including textpanel)
		JPanel InputPanel = new JPanel();
		InputPanel.setBounds(134, 0, 370, 458);
		MiddlePanel.add(InputPanel);
		InputPanel.setLayout(null);

		JPanel TextPanel = new JPanel();
		TextPanel.setBounds(12, 0, 298, 191);
		InputPanel.add(TextPanel);
		TextPanel.setLayout(null);

		//add all labels
		fNameLabel = new JLabel("Name: ");
		fNameLabel.setBounds(0, 0, 298, 25);
		TextPanel.add(fNameLabel);

		SexLabel = new JLabel("Sex: ");
		SexLabel.setBounds(0, 25, 298, 25);
		TextPanel.add(SexLabel);

		PaceLabel = new JLabel("Pace: ");
		PaceLabel.setBounds(0, 50, 298, 25);
		TextPanel.add(PaceLabel);

		DamLabel = new JLabel("Dam: ");
		DamLabel.setBounds(0, 75, 298, 25);
		TextPanel.add(DamLabel);

		SireLabel = new JLabel("Sire: ");
		SireLabel.setBounds(0, 100, 298, 25);
		TextPanel.add(SireLabel);

		YOBLabel = new JLabel("Year Of Birth: ");
		YOBLabel.setBounds(0, 125, 298, 25);
		TextPanel.add(YOBLabel);


		ActiveLabel = new JLabel("Active: ");
		ActiveLabel.setBounds(0, 150, 298, 25);
		TextPanel.add(ActiveLabel);




		//add buttons

		JPanel ButtonPanel = new JPanel();
		ButtonPanel.setBounds(6, 199, 358, 262);
		InputPanel.add(ButtonPanel);

		EditButton = new JButton("Edit Horse Info");
		EditButton.addActionListener(this);
		ButtonPanel.add(EditButton);

		NewHorseButton = new JButton("Add New Horse");
		NewHorseButton.addActionListener(this);
		ButtonPanel.add(NewHorseButton);

		AddServiceButton = new JButton("Add New Service to this Horse");
		AddServiceButton.addActionListener(this);
		ButtonPanel.add(AddServiceButton);

		btnSeeAndEdit = new JButton("See and edit this Horse's ownership");
		btnSeeAndEdit.addActionListener(this);

		btnAddADaily = new JButton("Add a Daily Service to this Horse");
		btnAddADaily.addActionListener(this);
		ButtonPanel.add(btnAddADaily);
		ButtonPanel.add(btnSeeAndEdit);

		RRCButton = new JButton("Add Race Related Charges for this Horse");
		RRCButton.addActionListener(this);
		ButtonPanel.add(RRCButton);

		RDButton = new JButton("Add Race Details for this Horse");
		RDButton.addActionListener(this);
		ButtonPanel.add(RDButton);







	}


	//addresses all button pushes
	//@param: and action event
	@Override
	public void actionPerformed(ActionEvent e) {
		setCursor (Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		// TODO Auto-generated method stub
		repaint();
		if(e.getSource() == NewHorseButton)//if new horse button pressed
		{
			PopUpHorseAdder newHorse;
			try {
				newHorse = new PopUpHorseAdder();
				newHorse.setVisible(true);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		else if(e.getSource() == EditButton)//if edit horse button pressed
		{
			int row = table.getSelectedRow();
			if(row ==-1)
			{
				JOptionPane.showMessageDialog(null, "Please select a horse first.");
			}
			else{
				String[] thisHorse = activeHorses.get(row).split(",");
				PopUpHorseEditor EditHorse = new PopUpHorseEditor(thisHorse);
				EditHorse.setVisible(true);
			}

		}
		else if(e.getSource() == AddServiceButton)//if new service button pressed
		{
			int row = table.getSelectedRow();
			if(row ==-1)
			{
				JOptionPane.showMessageDialog(null, "Please select a horse first.");
			}
			else{
				//////System.out.println("See");
				String[] thisHorse = activeHorses.get(row).split(",");
				PopUpServiceAdder AddService = new PopUpServiceAdder(thisHorse);
				AddService.setVisible(true);
			}

		}
		else if(e.getSource() == btnSeeAndEdit)//if edit shares button pressed
		{
			int row = table.getSelectedRow();
			if(row ==-1)
			{
				JOptionPane.showMessageDialog(null, "Please select a horse first.");
			}
			else{

				String[] thisHorse = activeHorses.get(row).split(",");
				SharesPopUpEditor EditHorse = new SharesPopUpEditor(thisHorse);
				EditHorse.setVisible(true);
			}
		}

		else if(e.getSource() == btnAddADaily)//if edit shares button pressed
		{
			int row = table.getSelectedRow();
			if(row ==-1)
			{
				JOptionPane.showMessageDialog(null, "Please select a horse first.");
			}
			else{

				String[] thisHorse = activeHorses.get(row).split(",");
				PopupDailyServices DS = new PopupDailyServices(thisHorse);
				DS.setVisible(true);
			}
		}
		
		else if(e.getSource() == RDButton)//if new race detail button selected
		{
			//does all work through popup 
			int row = table.getSelectedRow();
			if(row ==-1)
			{
				JOptionPane.showMessageDialog(null, "Please select a horse first.");
			}
			else{
				String[] thisHorse = activeHorses.get(row).split(",");
				PopUpRDAdder RDadder = new PopUpRDAdder(thisHorse);
				RDadder.setVisible(true);
			}
		}

		else if(e.getSource() == RRCButton)//if race related charges button pressed
		{
			int row = table.getSelectedRow();
			if(row ==-1)
			{
				JOptionPane.showMessageDialog(null, "Please select a horse first.");
			}
			else{
				String[] thisHorse = activeHorses.get(row).split(",");
				RRCAdderPopUp RRCOwnerPopUp = new RRCAdderPopUp(thisHorse);
				RRCOwnerPopUp.setVisible(true);
			}
		}
		setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}


	/*
	 * works with list selection
	 * @param: any list selection event 
	 */
	public void valueChanged(ListSelectionEvent e) {
		// when a horse is selected from table, displays information to the right
		if (e.getSource() == table.getSelectionModel()) 
		{
			int row = table.getSelectedRow();
			int col = table.getSelectedColumn();
			String value = (String)table.getValueAt(row, col);
			//////System.out.println("The value Is" + value+ ".");

			String[] thisHorse = activeHorses.get(row).split(",");
			fNameLabel.setText("Name: "+ thisHorse[0]);
			//////System.out.println("id" + thisHorse[7]+ ".");
			if(thisHorse[2].equals("0"))
			{
				SexLabel.setText("Sex: Male");
			}
			else
			{
				SexLabel.setText("Sex: Female");
			}
			String a;
			if(thisHorse[6].equals("0"))
			{
				a = "Active";
			}
			else
			{
				a = "Not Active";
			}
			ActiveLabel.setText("Active: "+ a);
			PaceLabel.setText("Pace: "+ thisHorse[3]);
			DamLabel.setText("Dam: "+ thisHorse[5]);
			SireLabel.setText("Sire: "+ thisHorse[4]);
			YOBLabel.setText("Year of Birth: "+ thisHorse[1]);


		}


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
}
