// This is essentially the mediator.
// Horsie Team
import java.sql.*;

class TestDB {

	public TestDB() {
		try {
			// (Code shamelessly stolen and modified from: 
			// http://www.h2database.com/html/tutorial.html#connecting_using_jdbc)


			Class.forName("org.h2.Driver");
			//Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");


			Connection conn = DriverManager.getConnection("jdbc:h2:~/horsie", "horsie_admin", "abc123");
			/* The line above makes a horsie.mv.db file in C:<slash>Users<slash>user_name_you_are_logged_in_with
			   if said file (the DB) doesn't exist.  If it already exists, then it just connects to it.*/


			Statement executeMe = conn.createStatement();


			try{
				System.out.println("Creating Table");
				int result = executeMe.executeUpdate("CREATE TABLE Owner(FirstName varchar(255), LastName varchar(255),Address varchar(500), Phone int,  IDNumber int NOT NULL, OutStandingBalance int, PRIMARY KEY (IDNumber))");
				// the previous line is to create a table name owner with specified columns above
				

				System.out.println(result);
			}
			catch(SQLException sqle)
			{
				System.out.println("SQL Exception: " + sqle.toString());
			}



			try{
				int result = executeMe.executeUpdate("insert into horse(Name, OwnerLastName, OwnerFirstName) values('WhatEver', 'ANasty', 'IHave')");
				// the line above is to insert a data into the table horse specifing the columns and corisponding values 				

				System.out.println(result);
			}
			catch(SQLException sqle)
			{
				System.out.println("SQL Exception: " + sqle.toString());
			}


			

			ResultSet moreResults = executeMe.executeQuery("select * from horse");
			while(moreResults.next() == true){
				System.out.println(moreResults.getString("Name"));
			
			}

			   
			/* Put the code to use the connection and the DB here. When you're done with everything, then
			   call the conn.close() method*/   
			   
			   
			   
			   
			conn.close();	
		}
		catch (Exception e) {
			System.out.println("now what... no db, shutdown");
		}
	}
	
	public static void main(String[] args) {
		TestDB theTest = new TestDB();
	}
}