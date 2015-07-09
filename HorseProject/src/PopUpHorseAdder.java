/*
 * Author: Olivia Alford
 * Date:4/9/15
 * Purpose of the class: creats a pop up when a new horse must be added
 */


import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class PopUpHorseAdder extends JFrame implements ActionListener, ItemListener{

	private JPanel contentPane;//main pane
	private JTextField FNameText;//holds horse's name
	JComboCheckBox OwnercomboBox;//allows user to select an owner
	private JTextField SireText;//holds sire's name
	private JTextField MareText;// holds mare's name
	private JTextField PaceText;// holds horse's pace
	JButton SaveButton ;// allows user to same horse
	JButton CancelButton ;//cancels form 
	JComboBox SexComboBox;//allows user to select sex of horse
	JButton ClearButton ;//clears form 
	Mediator m ;//mediator to talk to DB
	private JTextField YOBText;//holds year of birth text
	ArrayList<String> owners = new ArrayList<String>();//holds all owners
	JCheckBox[] items;//items to be in the drop down owner chekc boxes
	JRadioButton rdbtnActive ;//radio button for active and inactive
	int HorseID;//holds the hrose's id number in the data base
	ArrayList<String>activeOwners ;//holds all active owners

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PopUpHorseAdder frame = new PopUpHorseAdder();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 */
	public PopUpHorseAdder() throws Exception{
		activeOwners = new ArrayList<String>();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		try {
			//gets single instance of mediator
			m = Mediator.getInstance();
			m.connect();
			//creates a new id for this horse
			HorseID = m.getNextHorseID();
			m.disconnect();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		contentPane.setLayout(null);



		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel TitlePanel = new JPanel();
		TitlePanel.setBounds(5, 5, 490, 16);
		getContentPane().add(TitlePanel);
		TitlePanel.setLayout(new BorderLayout(0, 0));

		JLabel TitleLabel = new JLabel("Add the Horse's Info");
		TitlePanel.add(TitleLabel, BorderLayout.NORTH);

		JPanel MiddlePanel = new JPanel();
		MiddlePanel.setBounds(5, 21, 490, 452);
		getContentPane().add(MiddlePanel);
		MiddlePanel.setLayout(null);

		//adds chooser for sex
		String[] chooserOptions = {"Male", "Female"};




		//adds panel to hold all text fields
		JPanel InputPanel2 = new JPanel();
		InputPanel2.setBounds(110, 16, 270, 350);
		MiddlePanel.add(InputPanel2);
		InputPanel2.setLayout(null);

		//gets all owners and all active owners to add to owner dropdown check box
		m.connect();
		try 
		{
			owners = m.getAllOwner();
		} 
		catch (SQLException e) 
		{
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


		items = new JCheckBox[activeOwners.size()];

		for (int i = 0; i < activeOwners.size();i++ )
		{
			String o = activeOwners.get(i);
			String[] ownerInfoList = o.split(",");
			String lname = ownerInfoList[0];
			JCheckBox thisButton = new JCheckBox(lname);
			thisButton.setMnemonic(KeyEvent.VK_C);
			thisButton.addItemListener(this);
			InputPanel2.add(thisButton);
			items[i] = thisButton;

		}


		OwnercomboBox = new JComboCheckBox(items);
		OwnercomboBox.setBounds(46, 39, 134, 27);
		InputPanel2.add(OwnercomboBox);



		ButtonGroup group = new ButtonGroup();




		FNameText = new JTextField();
		FNameText.setBounds(46, 5, 134, 28);
		FNameText.setText("Name");
		InputPanel2.add(FNameText);
		FNameText.setColumns(10);

		SireText = new JTextField();
		SireText.setBounds(46, 97, 134, 28);
		SireText.setText("Sire");
		InputPanel2.add(SireText);
		SireText.setColumns(10);

		MareText = new JTextField();
		MareText.setBounds(46, 128, 134, 28);
		MareText.setText("Dam");
		InputPanel2.add(MareText);
		MareText.setColumns(10);


		PaceText = new JTextField();
		PaceText.setBounds(46, 155, 134, 28);
		PaceText.setText("Pace");
		InputPanel2.add(PaceText);
		PaceText.setColumns(10);

		YOBText = new JTextField();
		YOBText.setBounds(46, 185, 134, 28);
		YOBText.setText("YOB");
		InputPanel2.add(YOBText);
		YOBText.setColumns(10);


		//adds buttons
		SaveButton = new JButton("Save");
		SaveButton.setBounds(6, 262, 75, 29);
		SaveButton.addActionListener(this);
		InputPanel2.add(SaveButton);

		CancelButton = new JButton("Cancel");
		CancelButton.setBounds(178, 262, 86, 29);
		CancelButton.addActionListener(this);
		InputPanel2.add(CancelButton);

		ClearButton = new JButton("Clear");
		ClearButton.setBounds(101, 262, 65, 29);
		ClearButton.addActionListener(this);
		InputPanel2.add(ClearButton);
		SexComboBox = new JComboBox(chooserOptions);
		SexComboBox.setBounds(46, 66, 134, 35);
		InputPanel2.add(SexComboBox);

		//adds radio buttons
		rdbtnActive = new JRadioButton("Active");
		rdbtnActive.setBounds(6, 227, 80, 23);
		InputPanel2.add(rdbtnActive);
		rdbtnActive.setSelected(true);

		JRadioButton rdbtnInactive = new JRadioButton("Inactive");
		rdbtnInactive.setBounds(147, 227, 85, 23);
		InputPanel2.add(rdbtnInactive);
		group.add(rdbtnActive);
		group.add(rdbtnInactive);

		//adds panel to hold all labels
		JPanel TextPanel = new JPanel();
		TextPanel.setBounds(6, 16, 92, 278);
		MiddlePanel.add(TextPanel);
		TextPanel.setLayout(new GridLayout(9, 1, 0, 0));

		JLabel fNameLabel = new JLabel("Name:");
		TextPanel.add(fNameLabel);

		JLabel OwnerLabel = new JLabel("Owner(s)");
		TextPanel.add(OwnerLabel);

		JLabel SexLabel = new JLabel("Sex");
		TextPanel.add(SexLabel);

		JLabel SireLabel = new JLabel("Sire");
		TextPanel.add(SireLabel);

		JLabel MareLabel = new JLabel("Dam");
		TextPanel.add(MareLabel);

		JLabel PaceLabel = new JLabel("Pace");
		TextPanel.add(PaceLabel);

		JLabel YearOfBirthLabel = new JLabel("Year of Birth");
		TextPanel.add(YearOfBirthLabel);

	}

	//handles all button presses
	//@param: any action event

	public void actionPerformed(ActionEvent e) 
	{
		setCursor (Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		// TODO Auto-generated method stub
		if(e.getSource() == SaveButton)//if save button pressed
		{
			try{
				//adds horse into data base thorugh mediator
				String HorseName = FNameText.getText();


				String owner="";
				for (int i = 0; i< items.length; i++)
				{
					if(items[i].isSelected())
					{
						owner= owner + (items[i].getText()) + ":";
					}
				}

				String sex = SexComboBox.getSelectedItem().toString();
				int gender;
				if (sex.equals("Male"))
				{
					gender = 0;
				}
				else
				{
					gender = 1;
				}
				String sire = SireText.getText();
				String mare  = MareText.getText();
				String pace  = PaceText.getText();
				int yearOfBirth = Integer.parseInt(YOBText.getText());

				int active;
				if (rdbtnActive.isSelected())
				{
					active = 0;
				}
				else
				{
					active =1;
				}
				////System.out.println("Save");

				if(horseInDB(HorseName))
				{
					JOptionPane.showMessageDialog(null, "This horse name matches a horse already in your data bease, please select a new name.");
				}
				else
				{
					try {
						m.connect();
						m.insertHorse(HorseName, yearOfBirth,  gender, pace, sire, mare,active,HorseID);
						m.disconnect();
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace(); 
					}




					//inserts owners for a horse
					// m.insertShare(String firstName, String lastName, int percentageShare, String horseID, int ownerID)

					for ( int i=0; i< items.length; i++)
					{

						if(items[i].isSelected())
						{
							////System.out.println(items[i].getText());
							String[] thisOwner = owners.get(i).split(",");
							try {
								m.connect();
								m.insertShare(thisOwner[1],thisOwner[0], 0,HorseID, Integer.parseInt(thisOwner[4]));
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
					}
					dispose();
				}
			}
			catch(NumberFormatException NFE)
			{
				JOptionPane.showMessageDialog(null, "Make sure you have entered a numeral value for the year of birth of this horse.");
			}

		}
		else if(e.getSource() == CancelButton)// if cancel button pressed
		{
			dispose();
		}
		else if(e.getSource() == ClearButton)//if clear button pressed
		{
			////System.out.println("CLear!");
			FNameText.setText("");
			SireText.setText("");
			MareText.setText("");
			PaceText.setText("");

		}

		setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

	public boolean horseInDB(String HorseName)
	{
		//check to see if name already exists
		ArrayList<String> horses = new ArrayList <String> ();
		m.connect();
		try {
			horses = m.getAllHorse();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		m.disconnect();

		for(int i = 0; i<horses.size(); i++)
		{
			String [] thisHorse  = horses.get(i).split(",")	;
			String name = thisHorse[0];
			if(name.equals(HorseName))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub

	}
}

