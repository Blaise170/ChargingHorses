/*
 * Author: Olivia Alford
 * Date:4/2/15
 * Purpose of the class: Holds all tabbed panes
 */



import java.awt.*;
import java.awt.event.KeyEvent;

import javax.swing.*;



public class TabbedPane {

	
	private JFrame frmHorseBillingTeam;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TabbedPane window = new TabbedPane();
					window.frmHorseBillingTeam.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TabbedPane() {

		try {
			initialize();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frmHorseBillingTeam.setResizable(false);
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws Exception 
	 */
	private void initialize() throws Exception {
		frmHorseBillingTeam = new JFrame();
		frmHorseBillingTeam.setTitle("Foley Stables Billing");
		frmHorseBillingTeam.setBounds(400, 400, 650, 600);
		frmHorseBillingTeam.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmHorseBillingTeam.getContentPane().add(tabbedPane, BorderLayout.CENTER);
				
						//add home pane
						JPanel HomeTab = new JPanel();
						tabbedPane.addTab("Home", null, HomeTab, null);
						
						
								HomePane home = new HomePane();
								HomeTab.add(home);
				
				
						//add dataTablePanel
						JPanel DetailsTab = new JPanel();
						tabbedPane.addTab("Details", null, DetailsTab, null);
						DetailsTab.setLayout(new BorderLayout(0, 0));
						
						DetailsTabbedPane details = new DetailsTabbedPane();
						//details.setBounds(314, 5, 1, 1);
						DetailsTab.add(details);
		
		
		
				//add invoice pane
				JPanel InvoiceTab = new JPanel();
				InvoiceTab.setToolTipText("");
				tabbedPane.addTab("Create Invoice", null, InvoiceTab, null);
						InvoiceTab.setLayout(null);
				
				
						InvoicePane invoice = new InvoicePane();
						invoice.setBounds(110, 6, 513, 413);
						InvoiceTab.add(invoice);



		// add mailing label pane
		JPanel MailingLabelTab = new JPanel();
		MailingLabelTab.setToolTipText("");
		tabbedPane.addTab("Create Mailing Labels", null, MailingLabelTab, null);
		MailingLabelTab.setLayout(new BorderLayout(0, 0));

		MailLabelPane mail = new MailLabelPane();
		MailingLabelTab.add(mail);
		
		
		//add inactivesPanel
		JPanel InactivesTab = new JPanel();
		tabbedPane.addTab("Inactives", null, InactivesTab, null);
		
		JPanel inactivePanel = new JPanel();
		InactivesTab.setLayout(null);
		inactivesPane inactive = new inactivesPane();
		inactive.setBounds(6, 5, 617, 478);
		
		InactivesTab.add(inactive);

		//add search panel
		JPanel SearchTab = new JPanel();
		tabbedPane.addTab("Search", null, SearchTab, null);

		JPanel searchPanel = new JPanel();
		SearchTab.setLayout(null);
		SearchPane searching = new SearchPane();
		searching.setBounds(6, 5, 617, 446);
		SearchTab.add(searching);


	}

}
