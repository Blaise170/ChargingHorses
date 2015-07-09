/*
 * Author: Olivia Alford
 * Date:4/9/15
 * Purpose of the class: creates a pop up when a type of service needs to be edited
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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class popUpServiceEditor extends JFrame implements ActionListener{
	private JPanel contentPane;//holds all else
	private JTextField DesText;///holds quantity of service 
	private JTextField UPText;///holds unit price of service 
	JButton AddServiceButton; ///allows user to add service
	private JButton ClearButton;//clears form
	Mediator m;//mediator
	String name;//holds name of service
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					popUpServiceEditor frame = new popUpServiceEditor(new String [2]);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param: takes the servie that is to be updated
	 */
	public popUpServiceEditor(String [] info) {
		try {
			//get single instance of the mediator
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

		//adds all labels
		JLabel NameLabel = new JLabel("Service");
		NameLabel.setBounds(9, 5, 92, 16);
		TextPanel.add(NameLabel);

		JLabel DescriptionLabel = new JLabel("Description");
		DescriptionLabel.setBounds(9, 50, 80, 16);
		TextPanel.add(DescriptionLabel);

		JLabel PriceLabel = new JLabel("Price");
		PriceLabel.setBounds(9, 89, 72, 16);
		TextPanel.add(PriceLabel);
		InputPanel.setLayout(null);

		DesText = new JTextField();
		DesText.setBounds(32, 45, 134, 28);
		DesText.setText(info[1]);
		InputPanel.add(DesText);
		DesText.setColumns(10);

		UPText = new JTextField();
		UPText.setBounds(32, 85, 134, 28);
		UPText.setText(info[2]);
		InputPanel.add(UPText);
		UPText.setColumns(10);

		//adds all buttons
		AddServiceButton = new JButton("Save");
		AddServiceButton.addActionListener(this);
		AddServiceButton.setBounds(32, 153, 159, 29);
		InputPanel.add(AddServiceButton);

		ClearButton = new JButton("Clear");
		ClearButton.setBounds(203, 153, 117, 29);
		ClearButton.addActionListener(this);
		InputPanel.add(ClearButton);

		name = info[0];
		JLabel ServiceNameLabel = new JLabel(name);
		ServiceNameLabel.setBounds(32, 6, 134, 16);
		InputPanel.add(ServiceNameLabel);

	}

	//handles all button presses
	//@param : any action event 
	public void actionPerformed(ActionEvent e) 
	{
		setCursor (Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		// TODO Auto-generated method stub
		repaint();
		if(e.getSource() == AddServiceButton)//when service is ready to be updated
		{
			try{
				//uses mediator to update the service in question

				String des = DesText.getText();
				Double UP = Double.parseDouble(UPText.getText());
				//////System.out.println("The Valeus here are "+ des + name + UP + "But still in need of functionality!" );


				m.connect();
				m.updateTypeServiceTable(name, des, UP);
				m.disconnect();
				dispose();
			}
			catch(NumberFormatException NFE)
			{
				JOptionPane.showMessageDialog(null, "Make sure you have entered a numeral value for the price field(this can contail a decimal).");
			}
		}
		else if(e.getSource() == ClearButton)//when clear button hit
		{

			DesText.setText("");
			UPText.setText("");
		}

	}
}
