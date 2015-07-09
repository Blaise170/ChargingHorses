/*
 * Author: Olivia Alford
 * Date:4/20/15
 * Purpose of the class: creates a pop up when race details need to be added to a horse
 */

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;


public class PopUpRDAdder extends JFrame implements ActionListener {

	private JPanel contentPane;//holds all other content
	private JTextField DesField;//description text field
	private JTextField PurseField;//purse text field
	private JTextField DateMonth;//date text field
	JButton btnAddCharges;//saves information on screen
	Mediator m;//mediator
	String [] h;//holds the information of the horse who took the horse to the race
	ArrayList<String> horses = new ArrayList<String>();//holds all horses
	private JButton CancelButton;//cancels operation
	ArrayList<String>activeHorses ;//holds all active horses
	private JLabel FinishLabel;//labesl the spot whre the horse finished
	private JTextField FinishText;//holds the position the horse finished
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
	 * @param: the info of the owner who took teh horse to the racce
	 */

	public PopUpRDAdder(String[] horse) {
		activeHorses = new ArrayList<String>();
		h = horse;
		try {
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

		//add labesl and text fields
		JLabel DescriptionLabel = new JLabel("Description");
		DescriptionLabel.setBounds(24, 25, 96, 16);
		contentPane.add(DescriptionLabel);

		DesField = new JTextField();
		DesField.setBounds(186, 3, 228, 61);
		contentPane.add(DesField);
		DesField.setColumns(10);

		JLabel PurseLabel = new JLabel("Purse");
		PurseLabel.setBounds(24, 120, 61, 16);
		contentPane.add(PurseLabel);

		PurseField = new JTextField();
		PurseField.setBounds(186, 114, 228, 28);
		contentPane.add(PurseField);
		PurseField.setColumns(10);

		JLabel DateLabel = new JLabel("Date");
		DateLabel.setBounds(24, 160, 61, 16);
		contentPane.add(DateLabel);

		DateMonth = new JTextField();
		DateMonth.setText("MM");
		DateMonth.setBounds(186, 154, 47, 28);
		contentPane.add(DateMonth);
		DateMonth.setColumns(10);


		FinishLabel = new JLabel("Finish");
		FinishLabel.setBounds(24, 80, 61, 16);
		contentPane.add(FinishLabel);

		FinishText = new JTextField();
		FinishText.setBounds(186, 74, 228, 28);
		contentPane.add(FinishText);
		FinishText.setColumns(10);


		//add buttons
		btnAddCharges = new JButton("Save Race Details");
		btnAddCharges.setBounds(129, 225, 141, 29);
		btnAddCharges.addActionListener(this);
		contentPane.add(btnAddCharges);

		CancelButton = new JButton("Cancel");
		CancelButton.setBounds(271, 225, 117, 29);
		CancelButton.addActionListener(this);
		contentPane.add(CancelButton);



		try {
			m.connect();
			horses = m.getAllHorse();
			m.disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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

		//creates drop down horses
		String[] chooserOptions= new String[activeHorses.size()];

		for(int i = 0; i<activeHorses.size(); i++)
		{
			String [] thisHorse=activeHorses.get(i).split(",");
			chooserOptions[i] = thisHorse[0];
		}

		label = new JLabel("/");
		label.setBounds(242, 154, 18, 28);
		contentPane.add(label);

		DateDay = new JTextField();
		DateDay.setText("DD");
		DateDay.setColumns(10);
		DateDay.setBounds(256, 154, 47, 28);
		contentPane.add(DateDay);

		label_1 = new JLabel("/");
		label_1.setBounds(307, 154, 18, 28);
		contentPane.add(label_1);

		DateYear = new JTextField();
		DateYear.setText("YYYY");
		DateYear.setColumns(10);
		DateYear.setBounds(329, 154, 71, 28);
		contentPane.add(DateYear);





	}

	@Override
	/*
	handles all button presses
	@param: any action event
	 */
	public void actionPerformed(ActionEvent e) {
		setCursor (Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		// TODO Auto-generated method stub
		if(e.getSource() == btnAddCharges)//when details are ready to be saved
		{
			try{
				//gathers all information and adds the race detail to the owner
				int finish = Integer.parseInt(FinishText.getText());
				Double purse = Double.parseDouble(PurseField.getText());
				int ownerID = 1;
				int horseID=Integer.parseInt(h[7]);

				String Des = DesField.getText();
				int day = Integer.parseInt(DateDay.getText());
				int month = Integer.parseInt(DateMonth.getText());
				int year = Integer.parseInt(DateYear.getText());

				try {
					m.connect();
					m.insertRD(ownerID, horseID, Des , purse,  finish, day, month,year);
					m.disconnect();
					JOptionPane.showMessageDialog(null, "The race details have been added.");
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
				JOptionPane.showMessageDialog(null, "Make sure you have entered a numeral value for the purse field(this can contain a decimal point), as well as a numeral value for finish, month, day, and year.");
			}
		}

		else if(e.getSource() == CancelButton)//if cancel button is pressed
		{
			dispose();	
		}
		setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}
	
}
