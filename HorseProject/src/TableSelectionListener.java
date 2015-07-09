/*
 * Author: Olivia Alford
 * Date:4/9/15
 * Purpose of the class: acts as the action listener for all tables in program
 */
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class TableSelectionListener implements ListSelectionListener {
	JTable table;// table to act as action listener for
	String value;//value selected on table
	public TableSelectionListener (JTable t) 
	{
		table = t;
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	//overridden method of the ListSelectionListener class
	//@param: ListSelectionEvent
	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == table.getSelectionModel()) 
		{
			int row = table.getSelectedRow();
			int col = table.getSelectedColumn();
			 value = (String)table.getValueAt(row, col);
			////System.out.println("The value Is" + value+ ". But we still need to implement funtionality here!!!!!");
		} 
	}
	
	//@return: value selected in table
	public String getValue()
	{
		return value;
	}

}
