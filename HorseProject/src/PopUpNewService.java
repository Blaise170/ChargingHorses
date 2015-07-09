/*
 * Author: Olivia Alford
 * Date:4/9/15
 * Purpose of the class: creates pop up for when a new service must be added to the service types for later use
 */


import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class PopUpNewService extends JFrame implements ActionListener{

	private JPanel contentPane;//holds all else
	private JTextField NameText;///holds service description
	private JTextField DesText;///holds quantity of service 
	private JTextField UPText;///holds unit price of service 
	JButton AddServiceButton; ///allows user to add service
	private JButton ClearButton;//clears form
	Mediator m;//mediator


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PopUpNewService frame = new PopUpNewService();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * and initializes everything in it
	 */
	public PopUpNewService() {
		try {
			//gets single instance of mediator
			m = Mediator.getInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//adds panel to hold all lables
		JPanel TextPanel = new JPanel();
		TextPanel.setBounds(6, 6, 89, 244);
		contentPane.add(TextPanel);

		//adds panel to hold all text imput
		JPanel InputPanel = new JPanel();
		InputPanel.setBounds(107, 6, 337, 244);
		contentPane.add(InputPanel);
		TextPanel.setLayout(null);

		//adds labels
		JLabel NameLabel = new JLabel("Service Name");
		NameLabel.setBounds(9, 5, 92, 16);
		TextPanel.add(NameLabel);

		JLabel DescriptionLabel = new JLabel("Description");
		DescriptionLabel.setBounds(9, 50, 80, 16);
		TextPanel.add(DescriptionLabel);

		JLabel PriceLabel = new JLabel("Price");
		PriceLabel.setBounds(9, 89, 72, 16);
		TextPanel.add(PriceLabel);
		InputPanel.setLayout(null);



		//adds all text fields
		NameText = new JTextField();
		NameText.setBounds(32, 5, 134, 28);
		NameText.setText("Name");
		InputPanel.add(NameText);
		NameText.setColumns(10);

		DesText = new JTextField();
		DesText.setBounds(32, 45, 134, 28);
		DesText.setText("Description");
		InputPanel.add(DesText);
		DesText.setColumns(10);

		UPText = new JTextField();
		UPText.setBounds(32, 85, 134, 28);
		UPText.setText("Price");
		InputPanel.add(UPText);
		UPText.setColumns(10);

		//adds buttons
		AddServiceButton = new JButton("Add New Service");
		AddServiceButton.addActionListener(this);
		AddServiceButton.setBounds(32, 153, 159, 29);
		InputPanel.add(AddServiceButton);

		ClearButton = new JButton("Clear");
		ClearButton.setBounds(203, 153, 117, 29);
		ClearButton.addActionListener(this);
		InputPanel.add(ClearButton);
	}

	//handles all button presses
	//@param : any action event 
	public void actionPerformed(ActionEvent e) 
	{
		setCursor (Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		// TODO Auto-generated method stub
		repaint();
		if(e.getSource() == AddServiceButton)//when add serveice button pressed
		{
			try{
			String name =  NameText.getText();
			String des = DesText.getText();
			Double UP =  Double.parseDouble(UPText.getText());//////System.out.println("The Valeus here are "+ des + quan + UP + "But still in need of functionality!" );

//connects to mediator and adds service
			m.connect();
			m. insertTypeService(name, des, UP);
			m.disconnect();
			dispose();
			}
			catch(NumberFormatException NFE)
			{
				JOptionPane.showMessageDialog(null, "Make sure you have entered a numeral value for the price field(this can contail a decimal).");
			}
		}
		
		else if(e.getSource() == ClearButton)//whne clear button pressed
		{
			NameText.setText("");
			DesText.setText("");
			UPText.setText("");
		}
		setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

	

}
