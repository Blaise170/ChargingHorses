/*
 * Author: Olivia Alford
 * Date:4/2/15
 * Purpose of the class: acts as a container for all subtabs under the  detail tab
 */

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import java.awt.BorderLayout;


public class DetailsTabbedPane extends JPanel {

	/**
	 * Create the panel.
	 * @throws Exception 
	 */
	public DetailsTabbedPane() throws Exception {
		setLayout(new BorderLayout(0, 0));
		//add center panel
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane, BorderLayout.CENTER);

		//add owner subtab
		JPanel OwnerTab = new JPanel();
		tabbedPane.addTab("Owner", null, OwnerTab, null);
		OwnerTab.setLayout(new BorderLayout(0, 0));

		OwnerSubtab own = new OwnerSubtab();
		OwnerTab.add(own);

		//add horse subtab
		JPanel HorseTab = new JPanel();
		tabbedPane.addTab("Horse", null, HorseTab, null);
		HorseTab.setLayout(new BorderLayout(0, 0));

		HorseSubtab horse = new HorseSubtab();
		HorseTab.add(horse);

		//add service subtab
		JPanel ServiceTab = new JPanel();
		tabbedPane.addTab("Service", null, ServiceTab, null);
		ServiceTab.setLayout(new BorderLayout(0, 0));

		ServiceSubtab service = new ServiceSubtab();
		ServiceTab.add(service);


	}

}
