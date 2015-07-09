// This file creates tables in the db.
// Need to look at foreign keys.
// Reminder: Drop table table_name before run this file.
// ---------------------------------------------------------------------------
// Related Question: What columns do we need in Share table, what does rela do?
//
//
//
// ----------------------------------------------------------------------------
//
//
// Horsie Team
import java.sql.*;

class CreateTables
{

	public CreateTables()
	{
		try
		{
			// (Code shamelessly stolen and modified from:
			// http://www.h2database.com/html/tutorial.html#connecting_using_jdbc)

			Class.forName("org.h2.Driver");
			// Connection conn = DriverManager.getConnection("jdbc:h2:~/test",
			// "sa", "");

			Connection conn = DriverManager.getConnection("jdbc:h2:~/horsie",
					"horsie_admin", "abc123");
			/*
			 * The line above makes a horsie.mv.db file in
			 * C:<slash>Users<slash>user_name_you_are_logged_in_with if said
			 * file (the DB) doesn't exist. If it already exists, then it just
			 * connects to it.
			 */

			Statement executeMe = conn.createStatement();

			// Create Owner table
			try{
				executeMe.executeUpdate("DROP TABLE Owner");
			} catch (SQLException sqle)
			{
				System.out.println("SQL Exception: " + sqle.toString());
			}
			try
			{
				int result = executeMe
						.executeUpdate("CREATE TABLE Owner(FirstName varchar(255), LastName varchar(255), Address varchar(500), Phone varchar(225),  OwnerID int NOT NULL, Active int NOT NULL, OutStandingBalance DOUBLE, PRIMARY KEY (OwnerID))");
				// the previous line is to create a table name owner with
				// specified columns above

				System.out.println("result of Create new Table Owner: "
						+ result);
			} catch (SQLException sqle)
			{
				System.out.println("SQL Exception: " + sqle.toString());
			}

			// Create Horse table
			try{
				executeMe.executeUpdate("DROP TABLE Horse");
			} catch (SQLException sqle)
			{
				System.out.println("SQL Exception: " + sqle.toString());
			}

			try
			{
				int result = executeMe
						.executeUpdate("CREATE TABLE Horse(HorseName varchar(255) NOT NULL UNIQUE, YearOfBirth int, Gender int, Pace varchar(255), Sire varchar(255), Dam varchar(255), HorseID int NOT NULL, Active int NOT NULL, PRIMARY KEY (HorseID))");
				// the previous line is to create a table name owner with
				// specified columns above

				System.out.println("result of Create new Table Horse: "
						+ result);
			} catch (SQLException sqle)
			{
				System.out.println("SQL Exception: " + sqle.toString());
			}

			// Create TypeService Table
			try{
				executeMe.executeUpdate("DROP TABLE TypeService");
			} catch (SQLException sqle)
			{
				System.out.println("SQL Exception: " + sqle.toString());
			}
			try
			{
				int result = executeMe
						.executeUpdate("CREATE TABLE TypeService(ServiceName varchar(255) NOT NULL, Description varchar(1000), Price DOUBLE, PRIMARY KEY (ServiceName))");
				System.out.println("result of Create new Table TypeService: "
						+ result);
			} catch (SQLException sqle)
			{
				System.out.println("SQL Exception: " + sqle.toString());
			}

			// Create Services table
			try{
				executeMe.executeUpdate("DROP TABLE Services");
			} catch (SQLException sqle)
			{
				System.out.println("SQL Exception: " + sqle.toString());
			}
			try
			{
				int result = executeMe
						.executeUpdate("CREATE TABLE Services(ServiceID int NOT NULL, HorseID int NOT NULL, Day int, Month int, Year int , ServiceName varchar(255) NOT NULL, cost DOUBLE, PRIMARY KEY (ServiceID), FOREIGN KEY (HorseID) REFERENCES Horse(HorseID), FOREIGN KEY (ServiceName) REFERENCES TypeService(ServiceName))");
				// the previous line is to create a table name owner with
				// specified columns above

				System.out.println("result of Create new Table Services: "
						+ result);
			} catch (SQLException sqle)
			{
				System.out.println("SQL Exception: " + sqle.toString());
			}

			// Create Bill table
			try{
				executeMe.executeUpdate("DROP TABLE Bill");
			} catch (SQLException sqle)
			{
				System.out.println("SQL Exception: " + sqle.toString());
			}
			try
			{
				int result = executeMe
						.executeUpdate("CREATE TABLE Bill(File varchar(255), FirstName varchar(255), LastName varchar(255), Month int, Year int, OwnerID int NOT NULL, BillID int NOT NULL, PRIMARY KEY (BillID), FOREIGN KEY (OwnerID) REFERENCES Owner(OwnerID))");
				// the previous line is to create a table name owner with
				// specified columns above

				System.out
						.println("result of Create new Table Bill: " + result);
			} catch (SQLException sqle)
			{
				System.out.println("SQL Exception: " + sqle.toString());
			}



			// Create Invoice table
			try{
				executeMe.executeUpdate("DROP TABLE Invoice");
			} catch (SQLException sqle)
			{
				System.out.println("SQL Exception: " + sqle.toString());
			}
			try
			{
				int result = executeMe
						.executeUpdate("CREATE TABLE Invoice(InvoiceId int NOT NULL, FileName varchar(255), OwnerID int NOT NULL, Day int, Month int, Year int, PRIMARY KEY (InvoiceId), FOREIGN KEY(OwnerID) REFERENCES Owner(OwnerID))");
				System.out
						.println("result of Create new Table Bill: " + result);
			} catch (SQLException sqle)
			{
				System.out.println("SQL Exception: " + sqle.toString());
			}

			// Create Share table
			try{
				executeMe.executeUpdate("DROP TABLE Share");
			} catch (SQLException sqle)
			{
				System.out.println("SQL Exception: " + sqle.toString());
			}
			try
			{
				int result = executeMe
						.executeUpdate("CREATE TABLE Share(FirstName varchar(255), LastName varchar(255), PercentageShare int, HorseID int NOT NULL, ShareID int NOT NULL, OwnerID int NOT NULL, PRIMARY KEY (ShareID), FOREIGN KEY(HorseID) REFERENCES Horse(HorseID), FOREIGN KEY(OwnerID) REFERENCES Owner(OwnerID))");
				// the previous line is to create a table name owner with
				// specified columns above

				System.out.println("result of Create new Table Share: "
						+ result);
			} catch (SQLException sqle)
			{
				System.out.println("SQL Exception: " + sqle.toString());
			}

			// Create Race related Charges table
			try{
				executeMe.executeUpdate("DROP TABLE RRC");
			} catch (SQLException sqle)
			{
				System.out.println("SQL Exception: " + sqle.toString());
			}
			try
			{
				int result = executeMe
						.executeUpdate("CREATE TABLE RRC(RRCID int NOT NULL, OwnerID int NOT NULL, HorseID int NOT NULL, Amount DOUBLE, Description varchar(500), Month int, Day int, Year int,  PRIMARY KEY (RRCID), FOREIGN KEY(OwnerID) REFERENCES Owner(OwnerID), FOREIGN KEY(HorseID) REFERENCES Horse(HorseID))");
				// the previous line is to create a table name owner with
				// specified columns above

				System.out.println("result of Create new Table RRC: " + result);
			} catch (SQLException sqle)
			{
				System.out.println("SQL Exception: " + sqle.toString());
			}

			// Create Race details table
			try{
				executeMe.executeUpdate("DROP TABLE RD");
			} catch (SQLException sqle)
			{
				System.out.println("SQL Exception: " + sqle.toString());
			}
			try
			{
				int result = executeMe
						.executeUpdate("CREATE TABLE RD(RDID int NOT NULL, OwnerID int NOT NULL, HorseID int NOT NULL, RaceDescription varchar(500), Purse DOUBLE, Rank int, Earned DOUBLE, Day int, Month int,  Year int,  PRIMARY KEY (RDID), FOREIGN KEY(OwnerID) REFERENCES Owner(OwnerID), FOREIGN KEY(HorseID) REFERENCES Horse(HorseID))");
				// the previous line is to create a table name owner with
				// specified columns above

				System.out.println("result of Create new Table RD: " + result);
			} catch (SQLException sqle)
			{
				System.out.println("SQL Exception: " + sqle.toString());
			}
			// Create DailyServices  table
			try{
				executeMe.executeUpdate("DROP TABLE DailyServices");
			} catch (SQLException sqle)
			{
				System.out.println("SQL Exception: " + sqle.toString());
			}
			try
			{
	    		int result = executeMe.executeUpdate("CREATE TABLE DailyServices(DSID int NOT NULL AUTO_INCREMENT, StartDate Date, EndDate Date, Description varchar(500), DailyPrice double, HorseID int,  PRIMARY KEY (DSID), FOREIGN KEY(HorseID) REFERENCES Horse(HorseID))");
					// specified columns above

				System.out.println("result of Create new Table DailyServices: " + result);
			} catch (SQLException sqle)
			{
				System.out.println("SQL Exception: " + sqle.toString());
			}

			conn.close();
		} catch (Exception e)
		{
			System.out.println("now what... no db, shutdown");
		}
	}

	public static void main(String[] args)
	{
		CreateTables createTable = new CreateTables();
	}
}
