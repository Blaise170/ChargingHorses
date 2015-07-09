/*
 * Author: Olivia Alford
 * Date:4/9/15
 * Purpose of the class: create pop up when a service must be added to a horse
 */


import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;


public class PopUpServiceAdder extends JFrame implements ActionListener{

	private JPanel contentPane;//holds all content
	JComboBox comboBox;//combo box for selecting type of service
	JButton AddServiceButton;//button to submit form
	private JTextField DateMonth;//date text field
	private JTextField DayTrainedField;//quanitiy text field
	ArrayList <String > services;//holds all types of services
	Mediator m;//mediator
	String [] horseInfo ;//holds the info of thehorse that gets the service
	private JLabel label;
	private JTextField DateDay;
	private JLabel label_1;
	private JTextField DateYear;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//PopUpServiceAdder frame = new PopUpServiceAdder();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param: info of the horse that gets the service
	 */
	public PopUpServiceAdder(String [] hi) {

		horseInfo = hi;
		try {
			m = Mediator.getInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initialize();
	}


	/*
	 * initializes whole panel
	 */
	public void initialize ()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		//add main panel
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//add title label
		JLabel TitleLabel = new JLabel("Select the Service that you wish to add to this horse.");
		TitleLabel.setBounds(28, 6, 416, 31);
		contentPane.add(TitleLabel);

		//add combo box

		try {
			m.connect();
			services = m.getAllTypesOfServices();
			m.disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] chooserOptions= new String[services.size()];

		for(int i = 0; i<services.size(); i++)
		{
			String thisS=services.get(i);
			String [] thisService=thisS.split(",");

			chooserOptions[i] = thisService[0]+ " - $"+ thisService[2];

		}
		comboBox = new JComboBox(chooserOptions);
		comboBox.setBounds(17, 49, 385, 31);
		contentPane.add(comboBox);

		//add  buttons
		AddServiceButton = new JButton("Add Service");
		AddServiceButton.setBounds(154, 210, 117, 29);
		AddServiceButton.addActionListener(this);
		contentPane.add(AddServiceButton);

		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(17, 103, 61, 16);
		contentPane.add(lblDate);

		//add text fields
		DateMonth = new JTextField();
		DateMonth.setText("MM");
		DateMonth.setBounds(137, 92, 41, 28);
		contentPane.add(DateMonth);
		DateMonth.setColumns(10);

		JLabel quantityLabel = new JLabel("Quantity");
		quantityLabel.setBounds(17, 147, 94, 16);
		contentPane.add(quantityLabel);

		DayTrainedField = new JTextField();
		DayTrainedField.setBounds(137, 141, 134, 28);
		contentPane.add(DayTrainedField);
		DayTrainedField.setColumns(10);
		
		label = new JLabel("/");
		label.setBounds(179, 92, 22, 28);
		contentPane.add(label);
		
		DateDay = new JTextField();
		DateDay.setText("DD");
		DateDay.setColumns(10);
		DateDay.setBounds(201, 92, 41, 28);
		contentPane.add(DateDay);
		
		label_1 = new JLabel("/");
		label_1.setBounds(249, 92, 22, 28);
		contentPane.add(label_1);
		
		DateYear = new JTextField();
		DateYear.setText("YYYY");
		DateYear.setColumns(10);
		DateYear.setBounds(267, 92, 72, 28);
		contentPane.add(DateYear);





	}

	//handles pressing of button
	//@param: an action event
	public void actionPerformed(ActionEvent e) 
	{
		setCursor (Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		// TODO Auto-generated method stub
		repaint();
		if(e.getSource() == AddServiceButton)//if service is ready to be added to horse
		{
			try{
			//collects all info and adds the service to the horse
			int selected =comboBox.getSelectedIndex();
			String [] thisService = services.get(selected).split(",");
			String value = thisService[0];
			int quantity = Integer.parseInt(DayTrainedField.getText());
			int day = Integer.parseInt(DateDay.getText());
			int month = Integer.parseInt(DateMonth.getText());
			int year = Integer.parseInt(DateYear.getText());
			
			double unitPrice = 0;
			for (int i =0; i<services.size(); i++)
			{
				String []thisS = services.get(i).split(",");
				if(thisS[0].equals(value))
				{
					unitPrice = Double.parseDouble((thisS[2]));
				}
			}
			double cost = quantity * unitPrice;
			m.connect();
			try {
				m.insertServices(Integer.parseInt(horseInfo[7]), quantity,  month, year, value,  cost);
				JOptionPane.showMessageDialog(null, "Service has been added.");
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
			m.disconnect();
			dispose();
			}
			catch(NumberFormatException NFE)
			{
				JOptionPane.showMessageDialog(null, "Make sure you have entered a numeral value for the quantity, day, month, and year fields.");
			}


		}
		setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
}
