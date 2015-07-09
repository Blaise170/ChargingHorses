/*
 * Author: Olivia Alford
 * Date:4/20/15
 * Purpose of the class: add race related charges to the bill for an owner
 */


import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;


public class RRCAdderPopUp extends JFrame implements ActionListener {

	private JPanel contentPane;//holds all other panes
	private JTextField DesField;//holds description text
	private JTextField CostField;//holds cost text
	private JTextField DateMonth;//holds date text
	JButton btnAddCharges;//adds RRC to owner's bill
	Mediator m;//mediator
	String [] h;//holds info of horse to billl
	ArrayList<String> horses = new ArrayList<String>();//holds all horses
	private JButton CancelButton;//cancels action
	ArrayList<String>activeHorses ;//holds all active horses
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
					RRCAdderPopUp frame = new RRCAdderPopUp(new String [0]);
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

	public RRCAdderPopUp(String[] horse) {
		activeHorses = new ArrayList<String>();
		h = horse;
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

		//adds labels and text fields
		JLabel DescriptionLabel = new JLabel("Description");
		DescriptionLabel.setBounds(24, 25, 96, 16);
		contentPane.add(DescriptionLabel);

		DesField = new JTextField();
		DesField.setBounds(186, 19, 228, 83);
		contentPane.add(DesField);
		DesField.setColumns(10);

		JLabel CostLabel = new JLabel("Cost");
		CostLabel.setBounds(24, 120, 61, 16);
		contentPane.add(CostLabel);

		CostField = new JTextField();
		CostField.setBounds(186, 114, 228, 28);
		contentPane.add(CostField);
		CostField.setColumns(10);

		JLabel DateLabel = new JLabel("Date");
		DateLabel.setBounds(24, 160, 61, 16);
		contentPane.add(DateLabel);

		DateMonth = new JTextField();
		DateMonth.setText("MM");
		DateMonth.setBounds(186, 154, 42, 28);
		contentPane.add(DateMonth);
		DateMonth.setColumns(10);


		try {
			m.connect();
			horses = m.getAllHorse();
			m.disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//sorts horses
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

		//creates drop down of horses
		String[] chooserOptions= new String[activeHorses.size()];

		for(int i = 0; i<activeHorses.size(); i++)
		{
			String [] thisHorse=activeHorses.get(i).split(",");
			chooserOptions[i] = thisHorse[0];
		}


		//adds buttons
		CancelButton = new JButton("Cancel");
		CancelButton.setBounds(271, 225, 117, 29);
		CancelButton.addActionListener(this);
		contentPane.add(CancelButton);

		btnAddCharges = new JButton("Add Charges");
		btnAddCharges.setBounds(129, 225, 117, 29);
		btnAddCharges.addActionListener(this);
		contentPane.add(btnAddCharges);

		label = new JLabel("/");
		label.setBounds(229, 154, 20, 28);
		contentPane.add(label);

		DateDay = new JTextField();
		DateDay.setText("DD");
		DateDay.setColumns(10);
		DateDay.setBounds(252, 154, 42, 28);
		contentPane.add(DateDay);

		label_1 = new JLabel("/");
		label_1.setBounds(301, 154, 20, 28);
		contentPane.add(label_1);

		DateYear = new JTextField();
		DateYear.setText("YYYY");
		DateYear.setColumns(10);
		DateYear.setBounds(319, 154, 69, 28);
		contentPane.add(DateYear);
	}

	@Override
	/*
	 *takes care of any actions
	 *@param: any action event
	 */
	public void actionPerformed(ActionEvent e) {
		setCursor (Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		// TODO Auto-generated method stub
		if(e.getSource() == btnAddCharges)//when charges need to be added
		{
			//collects information and adds those cahrges to the bill of the owner
			int ownerID = 1;
			int horseID=Integer.parseInt(h[7]);
		
			try{
				Double amount = Double.parseDouble(CostField.getText());
				String Des = DesField.getText();
				int day = Integer.parseInt(DateDay.getText());
				int month = Integer.parseInt(DateMonth.getText());
				int year = Integer.parseInt(DateYear.getText());


					try {
						m.connect();
						m.insertRRC(ownerID, horseID, amount,Des, month, day,year);
						m.disconnect();
						JOptionPane.showMessageDialog(null, "The race related charges have been added.");
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

					dispose();
				}
				catch(NumberFormatException NFE)
				{
					JOptionPane.showMessageDialog(null, "Make sure you have entered a numeral value for the cost field(this can contain a decimal point), as well as a numeral value for month, day, and year.");
				}
			
		}

		else if(e.getSource() == CancelButton)//if transaciton is canceled
		{
			dispose();	
		}
		setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
}


