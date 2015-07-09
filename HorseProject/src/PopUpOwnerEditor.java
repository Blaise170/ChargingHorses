/*
 * Author: Olivia Alford
 * Date:4/9/15
 * Purpose of the class: creates a pop up when an owner's info must be updated
 */


import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;


public class PopUpOwnerEditor extends JFrame implements ActionListener{

	private JPanel contentPane;//main content pane
	private JTextField FNameText;// holds text of first name
	private JTextField LNameText;// holds text of last name
	private JTextField PhpneText;// holds text of title of person
	private JTextField StreetAdressText;// holds text of street adress
	private JTextField CityText;//holds the city text
	private JTextField StateText;//holds the state text
	private JTextField ZipText;//holds the zip text
	private JTextField BalanceText;// holds text of outstanding balance
	JButton SaveButton ;//button to save owner info
	JButton CancelButton ;// button to cancel edit
	Mediator m;//mediator
	String []ownerInfo;//owner infor of the ower to be edited
	JRadioButton rdbtnActive;//active or not radio button

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PopUpOwnerEditor frame = new PopUpOwnerEditor(new String [0]);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PopUpOwnerEditor(String [] info) {
		try {
			//gets the single instance of the mediator
			m = Mediator.getInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ownerInfo = info;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);




		getContentPane().setLayout(new BorderLayout(0, 0));

		//add a title panel with a title
		JPanel TitlePanel = new JPanel();
		getContentPane().add(TitlePanel, BorderLayout.NORTH);
		TitlePanel.setLayout(new BorderLayout(0, 0));

		JLabel TitleLabel = new JLabel("Edit Your Owner's Info");
		TitlePanel.add(TitleLabel, BorderLayout.NORTH);

		//add middle panel
		JPanel MiddlePanel = new JPanel();
		getContentPane().add(MiddlePanel, BorderLayout.CENTER);
		MiddlePanel.setLayout(null);


		//add input panel to hold all text fields
		JPanel InputPanel = new JPanel();
		InputPanel.setBounds(134, 0, 310, 278);
		MiddlePanel.add(InputPanel);
		InputPanel.setLayout(new BorderLayout(0, 0));

		//adds panel to hold all of the text
		JPanel InputPanel2 = new JPanel();
		InputPanel.add(InputPanel2, BorderLayout.CENTER);
		InputPanel2.setLayout(null);

		FNameText = new JTextField();
		FNameText.setBounds(46, 5, 134, 28);
		FNameText.setText(ownerInfo[1]);
		InputPanel2.add(FNameText);
		FNameText.setColumns(10);

		LNameText = new JTextField();
		LNameText.setBounds(46, 38, 134, 28);
		LNameText.setText(ownerInfo[0]);
		InputPanel2.add(LNameText);
		LNameText.setColumns(10);

		PhpneText = new JTextField();
		PhpneText.setBounds(46, 71, 134, 28);
		PhpneText.setText(ownerInfo[3]);
		InputPanel2.add(PhpneText);
		PhpneText.setColumns(10);

		BalanceText = new JTextField();
		BalanceText.setBounds(46, 111, 134, 28);
		BalanceText.setText(ownerInfo[6]);
		InputPanel2.add(BalanceText);
		BalanceText.setColumns(10);

		//splits address info so that it can be auto entered into the text fields
		String [] address = ownerInfo[2].split("~");

		StreetAdressText = new JTextField();
		StreetAdressText.setBounds(46, 141, 134, 28);
		StreetAdressText.setText(address[0]);
		InputPanel2.add(StreetAdressText);
		StreetAdressText.setColumns(10);


		CityText = new JTextField();
		CityText.setBounds(46, 171, 134, 28);
		CityText.setText(address[1]);
		InputPanel2.add(CityText);
		CityText.setColumns(10);

		StateText = new JTextField();
		StateText.setBounds(46, 211, 134, 28);
		StateText.setText(address[2]);
		InputPanel2.add(StateText);
		StateText.setColumns(10);


		ZipText = new JTextField();
		ZipText.setBounds(46, 251, 134, 28);
		ZipText.setText(address[3]);
		InputPanel2.add(ZipText);
		ZipText.setColumns(10);

		//creates button group for radio active/inactive buttons
		ButtonGroup group = new ButtonGroup();

		JPanel panel = new JPanel();
		panel.setBounds(134, 331, 310, 90);
		MiddlePanel.add(panel);
		panel.setLayout(null);


		rdbtnActive = new JRadioButton("Active");
		rdbtnActive.setBounds(6, 6, 141, 23);
		panel.add(rdbtnActive);
		group.add(rdbtnActive);

		JRadioButton rdbtnInactive = new JRadioButton("Inactive");
		rdbtnInactive.setBounds(163, 6, 141, 23);
		panel.add(rdbtnInactive);
		group.add(rdbtnInactive);

		//selects correct radio button
		if(ownerInfo[5].equals("0"))
		{
			rdbtnActive.setSelected(true);
		}
		else
		{
			rdbtnInactive.setSelected(true);
		}

		//adds buttons
		SaveButton = new JButton("Save");
		SaveButton.setBounds(22, 55, 75, 29);
		panel.add(SaveButton);
		CancelButton = new JButton("Cancel");
		CancelButton.setBounds(178, 55, 86, 29);
		panel.add(CancelButton);

		//add test panel to hold all labels
		JPanel TextPanel = new JPanel();
		TextPanel.setBounds(28, 0, 108, 278);
		MiddlePanel.add(TextPanel);
		TextPanel.setLayout(null);

		JLabel fNameLabel = new JLabel("First Name:");
		fNameLabel.setBounds(0, 0, 91, 30);
		TextPanel.add(fNameLabel);

		JLabel LNameLabel = new JLabel("Last Name");
		LNameLabel.setBounds(0, 41, 91, 30);
		TextPanel.add(LNameLabel);

		JLabel PhoneLabel = new JLabel("Phone");
		PhoneLabel.setBounds(0, 72, 91, 30);
		TextPanel.add(PhoneLabel);

		JLabel BalanceLabel = new JLabel("Balance Due:");
		BalanceLabel.setBounds(0, 102, 91, 30);
		TextPanel.add(BalanceLabel);

		JLabel AdressLabel = new JLabel("Street Address");
		AdressLabel.setBounds(0, 144, 91, 30);
		TextPanel.add(AdressLabel);

		JLabel cityLabel = new JLabel("City");
		cityLabel.setBounds(0, 176, 91, 30);
		TextPanel.add(cityLabel);

		JLabel stateLabel = new JLabel("State");
		stateLabel.setBounds(0, 206, 91, 30);
		TextPanel.add(stateLabel);

		JLabel zipLabel = new JLabel("Zip");
		zipLabel.setBounds(0, 248, 91, 30);
		TextPanel.add(zipLabel);
		CancelButton.addActionListener(this);
		SaveButton.addActionListener(this);










	}

	//handles button presses
	//@param: an action event
	public void actionPerformed(ActionEvent e) 
	{
		setCursor (Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		// TODO Auto-generated method stub
		repaint();
		if(e.getSource() == SaveButton)//if owner info is ready to be updated
		{
			String fName = new String();
			String lName = new String();
			String phone = new String();
			Double balance = 0.0;
			int OwnerID =0;
			int active =0;
			String Street = new String();
			String city = new String();
			String state = new String();
			String zip = new String();
			String address = new String();
			try{
				//gathers info and updates owner in table
				fName = FNameText.getText();
				lName = LNameText.getText();
				phone = PhpneText.getText();
				balance = Double.parseDouble(BalanceText.getText());
				OwnerID = Integer.parseInt(ownerInfo[4]);

				Street = StreetAdressText.getText();
				city = CityText.getText();
				state = StateText.getText();
				zip = ZipText.getText();
				address = Street + "~"+ city + "~"+ state + "~"+zip;

				if (rdbtnActive.isSelected())
				{
					active = 0;
				}
				else
				{
					active = 1;
				}
				//////System.out.println("Active passed in:"+active);
				try{
					m.connect();
					m.updateOwnerTable(OwnerID, fName, lName,address, phone, active, balance);

					m.disconnect();
				}
				catch(Exception exception)
				{
				}
				dispose();
			}
			catch(NumberFormatException NFE)
			{
				JOptionPane.showMessageDialog(null, "Make sure you have entered a numeral value for the Balance Due field(this can contain a decimal point).");
			}





		}
		else if(e.getSource() == CancelButton)//if cancel button is pressed
		{
			dispose();
		}
		setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
}

