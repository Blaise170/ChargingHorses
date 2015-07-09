/*
 * Author: Olivia Alford
 * Date:4/2/15
 * Purpose of the class: Holds all tabbed panes
 */


import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;


public class SharesPopUpEditor extends JFrame implements ActionListener{

	private JPanel contentPane;//holds all else
	JButton SaveButton;//saves info
	JButton CancelButton;//cancels transactions
	Mediator m;//mediator
	JLabel [] Labels;//holds all lables for owner names
	JTextField [] textFields;//holds all share text fields
	ArrayList <String> shares;//holds all shares
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SharesPopUpEditor frame = new SharesPopUpEditor(new String [10]);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param: information of the horse who's ownership we would like to see
	 */
	public SharesPopUpEditor(String[] thisHorse) {
		try {
			//get single instance of the mediator
			m = Mediator.getInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] Horse = thisHorse;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel DisplayPanel = new JPanel();
		DisplayPanel.setBounds(6, 6, 438, 217);
		contentPane.add(DisplayPanel);
		DisplayPanel.setLayout(null);

		//adds title labels
		JLabel lblHorsename = new JLabel(Horse[0]);
		lblHorsename.setBounds(155, 6, 155, 16);
		DisplayPanel.add(lblHorsename);

		//adds panels to hold text fields and labels
		JPanel OwnerNames = new JPanel();
		OwnerNames.setBounds(6, 21, 122, 190);
		DisplayPanel.add(OwnerNames);

		JPanel ShareValues = new JPanel();
		ShareValues.setBounds(186, 21, 214, 190);
		DisplayPanel.add(ShareValues);

		//adds buttons to button panel
		JPanel ButtonPanel = new JPanel();
		ButtonPanel.setBounds(6, 230, 438, 42);
		contentPane.add(ButtonPanel);

		SaveButton = new JButton("Save");
		SaveButton.addActionListener(this);
		ButtonPanel.add(SaveButton);


		CancelButton = new JButton("Cancel");
		CancelButton.addActionListener(this);
		ButtonPanel.add(CancelButton);


		//gets all shares adn then creates and adds labesl and text fields for those shares
		shares= new ArrayList<String>();

		try {
			m.connect();
			shares  = m.searchSharesALL("","",-1,Integer.parseInt(thisHorse[7]),-1,-1,1,1,1,1,1,1);


			//return first, last, percentage , horse name, share id, owner id
			m.disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int j = 0; j< shares.size(); j++)
		{
			String thisS= shares.get(j);
			////System.out.println(thisS);
		}


		Labels = new JLabel [shares.size()];
		textFields = new JTextField [shares.size()];
		//brings all labels and text fields into a list
		for (int i = 0; i< shares.size(); i++)
		{
			//add jlabel
			String [] thisShare = shares.get(i).split(",");
			JLabel thisLabel = new JLabel(thisShare[0]+" "+thisShare[1]);
			thisLabel.setPreferredSize(new Dimension(100,20));
			Labels[i] = thisLabel;


			//add text field
			JTextField thisShareText = new JTextField();
			thisShareText.setColumns(10);
			thisShareText.setText(thisShare[2]);
			textFields[i]= thisShareText;
		}

		//adds all labesl and text firelds
		for (int i = 0; i< shares.size(); i++)
		{
			OwnerNames.add(Labels[i]);
			ShareValues.add(textFields[i]);
		}

	}


	@Override
	/*
	 * handles all actions prefrmed
	 * @param: and action event
	 */
	public void actionPerformed(ActionEvent e) {
		setCursor (Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		try{
			if (e.getSource() == SaveButton) //save button used
			{//gathers information and saves it inot table
				////System.out.println("save");
				for (int i = 0; i< shares.size(); i++)
				{
					String [] thisShare = shares.get(i).split(",");
					JTextField thistextField = textFields[i];
					m.connect();
					m.updateShareTable(thisShare[0],thisShare[1], Integer.parseInt(thistextField.getText()), Integer.parseInt(thisShare[3]), Integer.parseInt(thisShare[4]), Integer.parseInt(thisShare[5]));
					m.disconnect();
					dispose();
				}
			}
			else if (e.getSource() == CancelButton) //cancel button used
			{//cancels all transactions
				////System.out.println("Cancel");
				dispose();
			}
			setCursor (Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
		catch(NumberFormatException NFE)
		{
			JOptionPane.showMessageDialog(null, "Make sure you have entered a numeral value for all shares(these CANNOT contain a decimals).");
		}
	}
}
