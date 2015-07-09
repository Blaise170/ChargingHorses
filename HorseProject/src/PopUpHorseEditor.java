/*
 * Author: Olivia Alford
 * Date:4/9/15
 * Purpose of the class: creates a pop up when a  horse must be edited
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


public class PopUpHorseEditor extends JFrame implements ActionListener, ItemListener{


	private JPanel contentPane;//main pane
	private JTextField FNameText;//holds horse's name
	JComboBox OwnercomboBox;//allows user to select an owner
	private JTextField SireText;//holds sire's name
	private JTextField MareText;// holds mare's name
	JButton SaveButton ;// allows user to same horse
	JButton CancelButton ;//cancels form 
	JComboBox SexComboBox;//allows user to select sex of horse
	private JTextField PaceText;// holds mare's name
	private JTextField YOBText;//holds year of birth text
	Mediator m;//mediator
	String []HorseInfo;//hodls info of horse to be edited
	ArrayList<String> owners = new ArrayList<String>();//holds all owners
	JCheckBox[] items;//holds all items in jcheck combo box, which allows for selection of ownership
	JRadioButton rdbtnActive ;//radio button for active or not
	ArrayList<String>activeOwners ;//holds active owners


	/**
	 * Create the frame.
	 * @param: information of the horse to be edited
	 */
	public PopUpHorseEditor(String [] info) {
		activeOwners = new ArrayList<String>();
		try {
			//gets sin;ge instance of mediator
			m = Mediator.getInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HorseInfo = info;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);


		
		

		getContentPane().setLayout(new BorderLayout(0, 0));

		//adds title
		JPanel TitlePanel = new JPanel();
		getContentPane().add(TitlePanel, BorderLayout.NORTH);
		TitlePanel.setLayout(new BorderLayout(0, 0));

		JLabel TitleLabel = new JLabel("Edit the Horse's Info");
		TitlePanel.add(TitleLabel, BorderLayout.NORTH);

		JPanel MiddlePanel = new JPanel();
		getContentPane().add(MiddlePanel, BorderLayout.CENTER);
		MiddlePanel.setLayout(null);

		//adds panel to hold all text objects
		JPanel InputPanel2 = new JPanel();
		InputPanel2.setBounds(105, 0, 350, 350);
		MiddlePanel.add(InputPanel2);
		InputPanel2.setLayout(null);
		
		
		//addsdrop down to allow for selection of sex
		String[] chooserOptions = {"Male", "Female"};
		SexComboBox = new JComboBox(chooserOptions);
		SexComboBox.setBounds(46, 66, 134, 35);
		InputPanel2.add(SexComboBox);
		
		if(HorseInfo[2].equals("0"))
		{
			SexComboBox.setSelectedItem("Male");
		}
		else
		{
			SexComboBox.setSelectedItem("Female");
		}



		
	

		//gets all shares already present to display
		ArrayList <String> shares= new ArrayList<String>();
		m.connect();
		try 
		{
			owners = m.getAllOwner();
			shares  = m.searchSharesALL("","",-1,Integer.parseInt(HorseInfo[7]),-1,-1,1,1,1,1,1,1);
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

		//selects true for owners who already own a horse
		items = new JCheckBox[activeOwners.size()];

		for (int i = 0; i < activeOwners.size();i++ )
		{
			String o = activeOwners.get(i);
			String[] ownerInfoList = o.split(",");
			String lname = ownerInfoList[0];
			String fname = ownerInfoList[1];
			JCheckBox thisButton = new JCheckBox(lname);
			thisButton.setMnemonic(KeyEvent.VK_C);
			thisButton.addItemListener(this);
			for (int j =0; j<shares.size();j++)
			{
				String [] thisShares = shares.get(j).split(",");
				String Shareln = thisShares[1];
				String Sharefn = thisShares[0];
				if(Shareln.equals(lname)&&Sharefn.equals(fname))
				{
					thisButton.setSelected(true);
				}
			}

			InputPanel2.add(thisButton);
			items[i] = thisButton;

		}


		OwnercomboBox = new JComboCheckBox(items);
		OwnercomboBox.setBounds(46, 39, 134, 27);
		InputPanel2.add(OwnercomboBox);




		ButtonGroup group = new ButtonGroup();


		//adds panel to hold all labels
		JPanel TextPanel = new JPanel();
		TextPanel.setBounds(6, 0, 87, 278);
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

		JLabel YOBLabel = new JLabel("Year Of Birth: ");
		TextPanel.add(YOBLabel);




		FNameText = new JTextField();
		FNameText.setBounds(46, 5, 134, 28);
		FNameText.setText(HorseInfo[0]);
		InputPanel2.add(FNameText);
		FNameText.setColumns(10);

		SireText = new JTextField();
		SireText.setBounds(46, 101, 134, 28);
		SireText.setText(HorseInfo[4]);
		InputPanel2.add(SireText);
		SireText.setColumns(10);

		MareText = new JTextField();
		MareText.setBounds(46, 128, 134, 28);
		MareText.setText(HorseInfo[5]);
		InputPanel2.add(MareText);
		MareText.setColumns(10);


		PaceText = new JTextField();
		PaceText.setBounds(46, 155, 134, 28);
		PaceText.setText(HorseInfo[3]);
		InputPanel2.add(PaceText);
		PaceText.setColumns(10);

		//adds all buttons
		SaveButton = new JButton("Save");
		SaveButton.setBounds(17, 294, 75, 29);
		SaveButton.addActionListener(this);
		InputPanel2.add(SaveButton);
		CancelButton = new JButton("Cancel");
		CancelButton.setBounds(117, 294, 83, 29);
		CancelButton.addActionListener(this);
		InputPanel2.add(CancelButton);
		


		// presents all values in text boxes
		YOBText = new JTextField();
		YOBText.setBounds(46, 185, 134, 28);
		YOBText.setText(HorseInfo[1]);
		InputPanel2.add(YOBText);
		YOBText.setColumns(10);

		rdbtnActive = new JRadioButton("Active");
		rdbtnActive.setBounds(17, 241, 80, 23);
		InputPanel2.add(rdbtnActive);

		JRadioButton rdbtnInactive = new JRadioButton("Inactive");
		rdbtnInactive.setBounds(115, 241, 85, 23);
		InputPanel2.add(rdbtnInactive);
		group.add(rdbtnActive);
		group.add(rdbtnInactive);


		if(HorseInfo[6].equals("0"))
		{
			rdbtnActive.setSelected(true);
		}
		else
		{
			rdbtnInactive.setSelected(true);
		}



	}


	//handles all button presses
	//@param: any action event
	public void actionPerformed(ActionEvent e) 
	{
		setCursor (Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		// TODO Auto-generated method stub
		repaint();
		if(e.getSource() == SaveButton)//if save button pressed
		{
			try{
			ArrayList<String> shares = new ArrayList<String>();
			String fName = FNameText.getText();
			String owner="";
			for (int i = 0; i< items.length; i++)
			{
				if(items[i].isSelected())
				{
					//updates the horses info

					String o = activeOwners.get(i);
					String[] ownerInfoList = o.split(",");
					try {
						m.connect();
						shares= m.searchSharesALL("", "",-1,Integer.parseInt(HorseInfo[7]),-1, Integer.parseInt(ownerInfoList[4]), 0, 0, 0,0,1,0);
						m.disconnect();
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					if(shares.size() ==0)//if no invoice already exists
					{


						try {
							m.connect();
							m. insertShare(ownerInfoList[1], ownerInfoList[0],0, Integer.parseInt(HorseInfo[7]), Integer.parseInt(ownerInfoList[4]));
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
			String dam  = MareText.getText();
			String pace  = PaceText.getText();
			int yob  = Integer.parseInt(YOBText.getText());
			int active;

			if (rdbtnActive.isSelected())
			{
				active = 0;
			}
			else
			{
				active = 1;
			}

			try
			{
				m.connect();
				m.updateHorseTable(Integer.parseInt(HorseInfo[7]), fName,yob, gender,  pace,  sire,  dam,active);
				m.disconnect();
			}
			catch(Exception exception)
			{
			}



			dispose();
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
		setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

	@Override
	/*
	 * recognizes a change in the item
	 * @param: any item event
	 */
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub

	}
}

