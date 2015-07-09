/*
 * Author: Olivia Alford
 * Date:4/9/15
 * Purpose of the class: creates a pop up when an owner's info must be added
 */


import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class PopUpOwnerAdder extends JFrame implements ActionListener{

	private JPanel contentPane;//main content pane
	private JTextField FNameText;// holds text of first name
	private JTextField LNameText;// holds text of last name
	private JTextField PhoneText;// holds text of phone of person
	private JTextField StreetAdressText;// holds text of street adress
	private JTextField CityText;// holds text of city
	private JTextField StateText;// holds text of state
	private JTextField ZipText;// holds text of zip
	JButton SaveButton ;//button to save owner info
	JButton CancelButton ;// button to cancel edit
	JButton ClearButton ;//clears form 
	Mediator m;//mediator
	JRadioButton rdbtnActive ;//active or not radio button
	JTextField PhpneText = new JTextField();//phone input text
	JTextField BalanceText = new JTextField();//balance input text



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PopUpOwnerAdder frame = new PopUpOwnerAdder();
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
	public PopUpOwnerAdder() throws Exception{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		//gets single instance of mediator
		m = Mediator.getInstance();




		getContentPane().setLayout(new BorderLayout(0, 0));

		//adds inside panels
		JPanel TitlePanel = new JPanel();
		getContentPane().add(TitlePanel, BorderLayout.NORTH);
		TitlePanel.setLayout(new BorderLayout(0, 0));

		JLabel TitleLabel = new JLabel("Add Owner's Info");
		TitlePanel.add(TitleLabel, BorderLayout.NORTH);

		JPanel MiddlePanel = new JPanel();
		getContentPane().add(MiddlePanel, BorderLayout.CENTER);
		MiddlePanel.setLayout(null);



		//add input panel to hold all text fields
		JPanel InputPanel = new JPanel();
		InputPanel.setBounds(134, 0, 310, 278);
		MiddlePanel.add(InputPanel);
		InputPanel.setLayout(new BorderLayout(0, 0));

		//add test panel to hold all labels
		JPanel TextPanel = new JPanel();
		InputPanel.add(TextPanel, BorderLayout.WEST);
		TextPanel.setLayout(new GridLayout(9, 1, 0, 0));

		JLabel fNameLabel = new JLabel("First Name:");
		TextPanel.add(fNameLabel);

		JLabel LNameLabel = new JLabel("Last Name");
		TextPanel.add(LNameLabel);

		JLabel PhoneLabel = new JLabel("Phone");
		TextPanel.add(PhoneLabel);

		JLabel StreeAdressLabel = new JLabel("Street Address");
		TextPanel.add(StreeAdressLabel);

		JLabel CityLabel = new JLabel("City");
		TextPanel.add(CityLabel);

		JLabel StateLabel = new JLabel("State");
		TextPanel.add(StateLabel);

		JLabel ZipLabel = new JLabel("Zip");
		TextPanel.add(ZipLabel);



		//adds panel for all text fields

		JPanel InputPanel2 = new JPanel();
		InputPanel2.setSize(new Dimension(500,500));
		InputPanel.add(InputPanel2, BorderLayout.CENTER);
		InputPanel2.setLayout(null);

		FNameText = new JTextField();
		FNameText.setBounds(46, 5, 134, 28);
		FNameText.setText("First Name");
		InputPanel2.add(FNameText);
		FNameText.setColumns(10);

		LNameText = new JTextField();
		LNameText.setBounds(46, 38, 134, 28);
		LNameText.setText("Last Name");
		InputPanel2.add(LNameText);
		LNameText.setColumns(10);


		PhpneText.setBounds(46, 71, 134, 28);
		PhpneText.setText("Phone Number");
		InputPanel2.add(PhpneText);
		PhpneText.setColumns(10);

		StreetAdressText = new JTextField();
		StreetAdressText.setBounds(46, 104, 134, 28);
		StreetAdressText.setText("Street Adress");
		InputPanel2.add(StreetAdressText);
		StreetAdressText.setColumns(10);


		CityText = new JTextField();
		CityText.setBounds(46, 137, 134, 28);
		CityText.setText("City");
		InputPanel2.add(CityText);
		CityText.setColumns(10);

		StateText = new JTextField();
		StateText.setBounds(46, 170, 134, 28);
		StateText.setText("State");
		InputPanel2.add(StateText);
		StateText.setColumns(10);


		ZipText = new JTextField();
		ZipText.setBounds(46, 203, 134, 28);
		ZipText.setText("Zip");
		InputPanel2.add(ZipText);
		ZipText.setColumns(10);

		//creates button group and adds radio buttons
		ButtonGroup group = new ButtonGroup();

		JPanel ButtonPanel = new JPanel();
		ButtonPanel.setBounds(134, 284, 310, 94);
		MiddlePanel.add(ButtonPanel);
		ButtonPanel.setLayout(null);
		
		JRadioButton rdbtnInactive = new JRadioButton("Inactive");
		rdbtnInactive.setBounds(195, 24, 98, 23);
		ButtonPanel.add(rdbtnInactive);
		group.add(rdbtnInactive);
		rdbtnActive = new JRadioButton("Active");
		rdbtnActive.setBounds(41, 24, 98, 23);
		ButtonPanel.add(rdbtnActive);
		rdbtnActive.setSelected(true);
		group.add(rdbtnActive);
		

		//adds buttons

		SaveButton = new JButton("Save");
		SaveButton.setBounds(25, 59, 75, 29);
		ButtonPanel.add(SaveButton);
		
		CancelButton = new JButton("Cancel");
		CancelButton.setBounds(195, 59, 86, 29);
		ButtonPanel.add(CancelButton);
		
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
		if(e.getSource() == SaveButton)//if save button pressed
		{
//gathers all owner infor and puts it in teh data base
			String fName = FNameText.getText();
			String lName = LNameText.getText();
			String phone = PhpneText.getText();
			String Street = StreetAdressText.getText();
			String city = CityText.getText();
			String state = StateText.getText();
			String zip = ZipText.getText();
			String address = Street + "~"+ city + "~"+ state + "~"+zip;


			int active;
			if (rdbtnActive.isSelected())
			{
				active = 0;
			}
			else
			{
				active =1;
			}
			dispose();

			//////System.out.println("Here are the values"+ fName + lName+ title+ Street + city + state + zip + "But we still need functionality!!!!!!!!");
			m.connect();
			try {
				m.insertOwner(fName, lName, address,phone,active,0.00);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			m.disconnect();
		}
		else if(e.getSource() == CancelButton)//if cancel button pressed
		{
			dispose();
		}
		else if(e.getSource() == ClearButton)//if clear button pressed 
		{
			FNameText.setText("");
			LNameText.setText("");
			PhoneText.setText("");
			StreetAdressText.setText("");
			CityText.setText("");
			StateText.setText("");
			ZipText.setText("");
		}
		setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
}

