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


public class PopupDailyServices extends JFrame implements ActionListener{

	private JPanel contentPane;//holds all content
	JButton AddServiceButton;//button to submit form
	private JTextField StartDateMonth;//date text field
	private JTextField DescriptionField;//quanitiy text field
	ArrayList <String > services;//holds all types of services
	Mediator m;//mediator
	String [] horseInfo ;//holds the info of thehorse that gets the service
	private JLabel label;//labels"/"
	private JTextField StartDateDay;//holds date day
	private JLabel label_1;//labels"/"
	private JTextField StartDateYear;//holds date year
	private JLabel lblEndDate;
	private JTextField EndDateMonth;
	private JLabel label_2;
	private JTextField EndDateDay;
	private JLabel label_3;
	private JTextField EndDateYear;
	private JLabel lblDailyPrice;
	private JTextField DailyPrice;

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
	public PopupDailyServices(String [] hi) {

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
		JLabel TitleLabel = new JLabel("Add a Daily Service to this Horse");
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

		//add  buttons
		AddServiceButton = new JButton("Add Service");
		AddServiceButton.setBounds(147, 229, 117, 29);
		AddServiceButton.addActionListener(this);
		contentPane.add(AddServiceButton);

		JLabel StartDateLabel = new JLabel("Start Date:");
		StartDateLabel.setBounds(17, 49, 94, 16);
		contentPane.add(StartDateLabel);

		//add text fields
		StartDateMonth = new JTextField();
		StartDateMonth.setText("MM");
		StartDateMonth.setBounds(123, 49, 41, 28);
		contentPane.add(StartDateMonth);
		StartDateMonth.setColumns(10);

		JLabel descriptionLabel = new JLabel("Description");
		descriptionLabel.setBounds(17, 147, 94, 16);
		contentPane.add(descriptionLabel);

		DescriptionField = new JTextField();
		DescriptionField.setBounds(137, 141, 134, 28);
		contentPane.add(DescriptionField);
		DescriptionField.setColumns(10);

		label = new JLabel("/");
		label.setBounds(176, 49, 22, 28);
		contentPane.add(label);

		StartDateDay = new JTextField();
		StartDateDay.setText("DD");
		StartDateDay.setColumns(10);
		StartDateDay.setBounds(186, 49, 41, 28);
		contentPane.add(StartDateDay);

		label_1 = new JLabel("/");
		label_1.setBounds(249, 49, 22, 28);
		contentPane.add(label_1);

		StartDateYear = new JTextField();
		StartDateYear.setText("YYYY");
		StartDateYear.setColumns(10);
		StartDateYear.setBounds(278, 49, 72, 28);
		contentPane.add(StartDateYear);

		lblEndDate = new JLabel("End Date:");
		lblEndDate.setBounds(17, 95, 94, 16);
		contentPane.add(lblEndDate);

		EndDateMonth = new JTextField();
		EndDateMonth.setText("MM");
		EndDateMonth.setColumns(10);
		EndDateMonth.setBounds(123, 89, 41, 28);
		contentPane.add(EndDateMonth);

		label_2 = new JLabel("/");
		label_2.setBounds(176, 89, 22, 28);
		contentPane.add(label_2);

		EndDateDay = new JTextField();
		EndDateDay.setText("DD");
		EndDateDay.setColumns(10);
		EndDateDay.setBounds(186, 89, 41, 28);
		contentPane.add(EndDateDay);

		label_3 = new JLabel("/");
		label_3.setBounds(249, 89, 22, 28);
		contentPane.add(label_3);

		EndDateYear = new JTextField();
		EndDateYear.setText("YYYY");
		EndDateYear.setColumns(10);
		EndDateYear.setBounds(278, 89, 72, 28);
		contentPane.add(EndDateYear);

		lblDailyPrice = new JLabel("Daily Price");
		lblDailyPrice.setBounds(17, 186, 94, 16);
		contentPane.add(lblDailyPrice);

		DailyPrice = new JTextField();
		DailyPrice.setColumns(10);
		DailyPrice.setBounds(137, 181, 134, 28);
		contentPane.add(DailyPrice);





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
			//collects all info and adds the service to the horse

			try{
				String des = DescriptionField.getText();
				int sday = Integer.parseInt(StartDateDay.getText());
				int smonth = Integer.parseInt(StartDateMonth.getText());
				int syear = Integer.parseInt(StartDateYear.getText());
				int eday = Integer.parseInt(EndDateDay.getText());
				int emonth = Integer.parseInt(EndDateMonth.getText());
				int eyear = Integer.parseInt(EndDateYear.getText());
				double daylPrice = Double.parseDouble(DailyPrice.getText());



				m.connect();
				try {
					m.insertDailyServices(smonth, sday, syear,emonth, eday, eyear, des, daylPrice, Integer.parseInt(horseInfo[7]));
					JOptionPane.showMessageDialog(null, "The daily service has been added.");
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
				JOptionPane.showMessageDialog(null, "Make sure you have entered a numeral value for the daily price field(this can contan a decimal), as well as all day, month, and year fields.");
			}

		}
		setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

	}
}
