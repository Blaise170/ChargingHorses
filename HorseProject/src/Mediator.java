//EVENTUALLY WE WILL NEED A GET ALL SERVICE

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Mediator
{
	
	Connection conn;
	private Statement executeStatement;
	private List<Observer> observers = new ArrayList<Observer>();
	
	private static Mediator instance;
	
	private Mediator() throws Exception
	{
		Class.forName("org.h2.Driver");
	}
	
	public static Mediator getInstance() throws Exception
	{
		if (instance == null)
		{
			synchronized (Mediator.class)
			{
				if (instance == null)
				{
					instance = new Mediator();
				}
			}
			
		}
		return instance;
	}
	
	// add observers to the to the list of observers of the mediator
	public void attach(Observer observer)
	{
		observers.add(observer);
	}
	
	// notifies all the observers that there are changes in the db
	public void notifyAllObservers()
	{
		for (Observer observer : observers)
		{
			observer.updateData();
		}
	}
	
	/**
	 * connects to the data base
	 */
	public void connect()
	{
		try
		{
			conn = DriverManager.getConnection("jdbc:h2:~/horsie",
					"horsie_admin", "abc123");
			executeStatement = conn.createStatement();
			////System.out.println("Connecting to Database");
		} catch (Exception e)
		{
			////System.out.println(e.getMessage());
		}
	}
	
	/**
	 * disconnects to the database
	 */
	public void disconnect()
	{
		try
		{
			conn.close();
			////System.out.println("Disconnecting to database");
		} catch (Exception e)
		{
			////System.out.println(e.getMessage());
		}
	}
	
	public int getNextHorseID() throws ClassNotFoundException, SQLException
	{
		int i = getHorseID();
		int j = i + 1;
		return j;
	}
	
	public int getHorseID() throws ClassNotFoundException, SQLException
	{
		String sqlc = "select HorseID from horse";
		int highestID = 0;
		int curID;
		ResultSet moreResults = executeStatement.executeQuery(sqlc);
		while (moreResults.next() == true)
		{
			curID = moreResults.getInt("HorseID");
			if (curID > highestID)
			{
				highestID = curID;
			}
		}
		
		return highestID;
	}
	
	// /OwnerID
	
	public int getNextOwnerID() throws ClassNotFoundException, SQLException
	{
		int i = getOwnerID();
		int j = i + 1;
		return j;
	}
	
	public int getOwnerID() throws ClassNotFoundException, SQLException
	{
		
		String sqlc = "select OwnerID from owner";
		int highestID = 0;
		int curID;
		ResultSet moreResults = executeStatement.executeQuery(sqlc);
		while (moreResults.next() == true)
		{
			curID = moreResults.getInt("OwnerID");
			if (curID > highestID)
			{
				highestID = curID;
			}
		}
		
		return highestID;
	}
	
	// //BILL ID
	
	public int getNextBillID() throws ClassNotFoundException, SQLException
	{
		int i = getBillID();
		int j = i + 1;
		return j;
	}
	
	public int getBillID() throws ClassNotFoundException, SQLException
	{
		
		String sqlc = "select BillID from Bill";
		int highestID = 0;
		int curID;
		ResultSet moreResults = executeStatement.executeQuery(sqlc);
		while (moreResults.next() == true)
		{
			curID = moreResults.getInt("BillID");
			if (curID > highestID)
			{
				highestID = curID;
			}
		}
		
		return highestID;
	}
	
	// /ServicesID
	
	public int getNextServicesID() throws ClassNotFoundException, SQLException
	{
		int i = getServicesID();
		int j = i + 1;
		return j;
	}
	
	public int getServicesID() throws ClassNotFoundException, SQLException
	{
		
		String sqlc = "select ServiceID from Services";
		int highestID = 0;
		int curID;
		ResultSet moreResults = executeStatement.executeQuery(sqlc);
		while (moreResults.next() == true)
		{
			curID = moreResults.getInt("ServiceID");
			if (curID > highestID)
			{
				highestID = curID;
			}
		}
		
		return highestID;
	}
	
	// /RRCID
	
	public int getNextRRCID() throws ClassNotFoundException, SQLException
	{
		int i = getRRCID();
		int j = i + 1;
		return j;
	}
	
	public int getRRCID() throws ClassNotFoundException, SQLException
	{
		
		String sqlc = "select RRCID from RRC";
		int highestID = 0;
		int curID;
		ResultSet moreResults = executeStatement.executeQuery(sqlc);
		while (moreResults.next() == true)
		{
			curID = moreResults.getInt("RRCID");
			if (curID > highestID)
			{
				highestID = curID;
			}
		}
		
		return highestID;
	}
	
	// /Shares
	
	public int getNextShareID() throws ClassNotFoundException, SQLException
	{
		int i = getShareID();
		int j = i + 1;
		return j;
	}
	
	public int getShareID() throws ClassNotFoundException, SQLException
	{
		
		String sqlc = "select ShareID from Share";
		int highestID = 0;
		int curID;
		ResultSet moreResults = executeStatement.executeQuery(sqlc);
		while (moreResults.next() == true)
		{
			curID = moreResults.getInt("ShareID");
			if (curID > highestID)
			{
				highestID = curID;
			}
		}
		
		return highestID;
	}
	
	// Invoice
	public int getNextInvoiceID() throws ClassNotFoundException, SQLException
	{
		int i = getInvoiceID();
		int j = i + 1;
		return j;
	}
	
	public int getInvoiceID() throws ClassNotFoundException, SQLException
	{
		String sqlc = "select InvoiceID from invoice";
		int highestID = 0;
		int curID;
		ResultSet moreResults = executeStatement.executeQuery(sqlc);
		while (moreResults.next() == true)
		{
			curID = moreResults.getInt("InvoiceID");
			if (curID > highestID)
			{
				highestID = curID;
			}
		}
		
		return highestID;
	}
	
	// RD
	public int getNextRDID() throws ClassNotFoundException, SQLException
	{
		int i = getRDID();
		int j = i + 1;
		return j;
	}
	
	public int getRDID() throws ClassNotFoundException, SQLException
	{
		String sqlc = "select RDID from RD";
		int highestID = 0;
		int curID;
		ResultSet moreResults = executeStatement.executeQuery(sqlc);
		while (moreResults.next() == true)
		{
			curID = moreResults.getInt("RDID");
			if (curID > highestID)
			{
				highestID = curID;
			}
		}
		
		return highestID;
	}
	
	/**
	 * functions to insert the data into the database uses stringbuilder to
	 * create comands to store data in table
	 * 
	 * @param HorseName
	 * @param yearOfBirth
	 * @param gender
	 *            , 0 is male, 1 is female
	 * @param pace
	 * @param Sire
	 * @param Dam
	 * @param active
	 *            : 1 is active, 0 is inactive
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public boolean insertHorse(String HorseName, int yearOfBirth, int gender,
			String pace, String Sire, String Dam, int active, int HorseID)
			throws ClassNotFoundException, SQLException
	{
		StringBuilder s = new StringBuilder();
		s.append("insert into horse values ('");
		s.append(HorseName);
		s.append("', ");
		s.append(yearOfBirth);
		s.append(", ");
		s.append(gender);
		s.append(", '");
		s.append(pace);
		s.append("', '");
		s.append(Sire);
		s.append("', '");
		s.append(Dam);
		s.append("', ");
		s.append(HorseID);
		s.append(", ");
		s.append(active);
		s.append(")");
		try
		{
			// If result is 1, it means that it has inserted one value
			// successfully.
			int result = executeStatement.executeUpdate(s.toString());
			this.notifyAllObservers();
			return true;
		} catch (SQLException sqle)
		{
			////System.out.println(sqle.toString());
		}
		return false;
	}
	
	/**
	 * Need to figure out how to get the ownerID
	 */
	public boolean insertBill(String file, String firstName, String LastName,
			int month, int year, int ownerID)
	{
		
		StringBuilder s = new StringBuilder();
		s.append("insert into bill values ('");
		s.append(file);
		s.append("', '");
		s.append(firstName);
		s.append("', '");
		s.append(LastName);
		s.append("', ");
		s.append(month);
		s.append(", ");
		s.append(year);
		s.append(", ");
		s.append(ownerID);
		s.append(")");
		
		try
		{
			int result = executeStatement.executeUpdate(s.toString());
			this.notifyAllObservers();
			return true;
		} catch (SQLException e)
		{
			////System.out.println(e.getMessage());
		}
		return false;
	}
	
	public boolean insertOwner(String firstName, String lastName,
			String address, String phone, int active, Double outstandingbalance)
			throws ClassNotFoundException, SQLException
	{
		
		StringBuilder s = new StringBuilder();
		s.append("insert into owner values ('");
		s.append(firstName);
		s.append("', '");
		s.append(lastName);
		s.append("', '");
		s.append(address);
		s.append("', '");
		s.append(phone);
		s.append("', ");
		s.append(this.getNextOwnerID());
		s.append(", ");
		s.append(active);
		s.append(", ");
		s.append(outstandingbalance);
		s.append(")");
		try
		{
			int result = executeStatement.executeUpdate(s.toString());
			this.notifyAllObservers();
			return true;
		} catch (SQLException e)
		{
			////System.out.println(e.getMessage());
		}
		return false;
	}
	
	public boolean insertRRC(int ownerId, int horseID, double amount,
			String description, int month, int day, int year)
			throws ClassNotFoundException, SQLException
	{
		
		StringBuilder s = new StringBuilder();
		s.append("insert into RRC values (");
		s.append(this.getNextRRCID());
		s.append(", ");
		s.append(ownerId);
		s.append(", '");
		s.append(horseID);
		s.append("', ");
		s.append(amount);
		s.append(", '");
		s.append(description);
		s.append("', ");
		s.append(month);
		s.append(", ");
		s.append(day);
		s.append(", ");
		s.append(year);
		s.append(")");
		
		try
		{
			int result = executeStatement.executeUpdate(s.toString());
			this.notifyAllObservers();
			return true;
		} catch (SQLException e)
		{
			////System.out.println(e.getMessage());
		}
		return false;
	}
	
	public boolean insertTypeService(String serviceName, String description,
			double price)
	{
		StringBuilder s = new StringBuilder();
		s.append("insert into TypeService values ('");
		s.append(serviceName);
		s.append("', '");
		s.append(description);
		s.append("', ");
		s.append(price);
		s.append(")");
		
		try
		{
			int result = executeStatement.executeUpdate(s.toString());
			this.notifyAllObservers();
			return true;
		} catch (SQLException e)
		{
			////System.out.println(e.getMessage());
		}
		return false;
		
	}
	
	public boolean insertServices(int horseID, int day, int month, int year,
			String serviceName, double cost) throws ClassNotFoundException,
			SQLException
	{
		
		StringBuilder s = new StringBuilder();
		s.append("insert into Services values (");
		s.append(this.getNextServicesID());
		s.append(", '");
		s.append(horseID);
		s.append("', ");
		s.append(day);
		s.append(", ");
		s.append(month);
		s.append(", ");
		s.append(year);
		s.append(", '");
		s.append(serviceName);
		s.append("', ");
		s.append(cost);
		s.append(")");
		
		try
		{
			int result = executeStatement.executeUpdate(s.toString());
			this.notifyAllObservers();
			return true;
		} catch (SQLException e)
		{
			////System.out.println(e.getMessage());
		}
		return false;
	}
	
	/**
	 * ----------------------------------------- Need to figure out how to get
	 * the ownerID -----------------------------------------
	 */
	public boolean insertShare(String firstName, String lastName,
			int percentageShare, int horseID, int ownerID)
			throws ClassNotFoundException, SQLException
	{
		
		StringBuilder s = new StringBuilder();
		s.append("insert into Share values ('");
		s.append(firstName);
		s.append("', '");
		s.append(lastName);
		s.append("', ");
		s.append(percentageShare);
		s.append(", '");
		s.append(horseID);
		s.append("', ");
		s.append(this.getNextShareID());
		s.append(", ");
		s.append(ownerID);
		s.append(")");
		
		try
		{
			int result = executeStatement.executeUpdate(s.toString());
			this.notifyAllObservers();
			return true;
		} catch (SQLException e)
		{
			////System.out.println(e.getMessage());
		}
		return false;
	}
	
	public boolean insertInvoice(String OwnerLastName, String OwnerFirstName,
			int OwnerID, int day, int month, int year, int ID, String FileName)
			throws ClassNotFoundException, SQLException
	{
		
		StringBuilder s = new StringBuilder();
		s.append("insert into Invoice values (");
		s.append(ID);
		s.append(", '");
		s.append(FileName);
		s.append("', ");
		s.append(OwnerID);
		s.append(", ");
		s.append(day);
		s.append(", ");
		s.append(month);
		s.append(", ");
		s.append(year);
		s.append(")");
		
		try
		{
			int result = executeStatement.executeUpdate(s.toString());
			this.notifyAllObservers();
			return true;
		} catch (SQLException e)
		{
			////System.out.println(e.getMessage());
		}
		return false;
	}
	
	public boolean insertRD(int ownerID, int horseID, String raceDescription,
			Double purse, int rank, int day, int month, int year)
			throws ClassNotFoundException, SQLException
	{
		
		StringBuilder s = new StringBuilder();
		s.append("insert into RD values (");
		s.append(this.getNextRDID());
		s.append(", ");
		s.append(ownerID);
		s.append(", ");
		s.append(horseID);
		s.append(", '");
		s.append(raceDescription);
		s.append("', ");
		s.append(purse);
		s.append(", ");
		s.append(rank);
		s.append(", ");
		
		if (rank == 1)
		{
			s.append(purse * 0.5);
		} else if (rank == 2)
		{
			s.append(purse * 0.25);
		} else if (rank == 3)
		{
			s.append(purse * 0.12);
		} else if (rank == 4)
		{
			s.append(purse * 0.08);
		} else if (rank == 5)
		{
			s.append(purse * 0.05);
		} else
		{
			s.append(0);
		}
		s.append(", ");
		s.append(day);
		s.append(", ");
		s.append(month);
		s.append(", ");
		s.append(year);
		s.append(")");
		
		try
		{
			int result = executeStatement.executeUpdate(s.toString());
			this.notifyAllObservers();
			return true;
		} catch (SQLException e)
		{
			////System.out.println(e.getMessage());
		}
		return false;
	}
	
	public boolean insertDailyServices(int sMonth, int sDay, int sYear,
			int eMonth, int eDay, int eYear, String description,
			double dailyPrice, int horseID) throws ClassNotFoundException,
			SQLException
	{
		StringBuilder s = new StringBuilder();
		s.append("insert into DailyServices (StartDate, EndDate, Description ,  DailyPrice, HorseID) VALUES ('");
		s.append(sYear);
		s.append("-");
		s.append(sMonth);
		s.append("-");
		s.append(sDay);
		s.append("','");
		s.append(eYear);
		s.append("-");
		s.append(eMonth);
		s.append("-");
		s.append(eDay);
		s.append("','");
		s.append(description);
		s.append("', ");
		s.append(dailyPrice);
		s.append(", ");
		s.append(horseID);
		s.append(")");
		
		try
		{
			int result = executeStatement.executeUpdate(s.toString());
			this.notifyAllObservers();
			return true;
		} catch (SQLException e)
		{
			////System.out.println(e.getMessage());
		}
		return false;
	}
	
	public boolean updateHorseTable(int HorseID, String HorseName,
			int yearOfBirth, int gender, String pace, String Sire, String Dam,
			int active)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE horse SET HorseName = '");
		sb.append(HorseName);
		sb.append("', YearOfBirth = ");
		sb.append(yearOfBirth);
		sb.append(", Gender = ");
		sb.append(gender);
		sb.append(", Pace ='");
		sb.append(pace);
		sb.append("', Sire = '");
		sb.append(Sire);
		sb.append("', Dam = '");
		sb.append(Dam);
		sb.append("', Active = ");
		sb.append(active);
		sb.append(" WHERE HorseID = ");
		sb.append(HorseID);
		sb.append(";");
		
		try
		{
			int result = executeStatement.executeUpdate(sb.toString());
			this.notifyAllObservers();
			return true;
		} catch (SQLException e)
		{
			////System.out.println(e.getMessage());
		}
		return false;
	}
	
	public boolean updateOwnerTable(int OwnerID, String firstName,
			String lastName, String address, String phone, int active,
			double outstandingbalance)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE owner SET FirstName = '");
		sb.append(firstName);
		sb.append("', LastName = '");
		sb.append(lastName);
		sb.append("', Address = '");
		sb.append(address);
		sb.append("', Phone ='");
		sb.append(phone);
		sb.append("', Active = ");
		sb.append(active);
		sb.append(", OutStandingBalance = ");
		sb.append(outstandingbalance);
		sb.append(" WHERE OwnerID = ");
		sb.append(OwnerID);
		sb.append(";");
		
		try
		{
			int result = executeStatement.executeUpdate(sb.toString());
			this.notifyAllObservers();
			return true;
		} catch (SQLException e)
		{
			////System.out.println(e.getMessage());
		}
		return false;
	}
	
	public boolean updateShareTable(String firstName, String lastName,
			int percentageShare, int horseID, int shareID, int ownerID)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE Share SET FirstName = '");
		sb.append(firstName);
		sb.append("', LastName = '");
		sb.append(lastName);
		sb.append("', PercentageShare = ");
		sb.append(percentageShare);
		sb.append(", HorseID =");
		sb.append(horseID);
		sb.append(", OwnerID = ");
		sb.append(ownerID);
		sb.append(" WHERE ShareID = ");
		sb.append(shareID);
		sb.append(";");
		
		try
		{
			int result = executeStatement.executeUpdate(sb.toString());
			this.notifyAllObservers();
			return true;
		} catch (SQLException e)
		{
			////System.out.println(e.getMessage());
		}
		return false;
	}
	
	public boolean updateTypeServiceTable(String serviceName,
			String description, double price)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE TypeService SET Description = '");
		sb.append(description);
		sb.append("', Price = ");
		sb.append(price);
		sb.append(" WHERE ServiceName = '");
		sb.append(serviceName);
		sb.append("';");
		
		try
		{
			int result = executeStatement.executeUpdate(sb.toString());
			this.notifyAllObservers();
			return true;
		} catch (SQLException e)
		{
			////System.out.println(e.getMessage());
		}
		return false;
	}
	
	public boolean updateRDTable(int RDID, int ownerID, int horseID,
			String raceDescription, int purse, int rank, int day, int month,
			int year)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE RD SET OwnerID = ");
		sb.append(ownerID);
		sb.append(", HorseID = ");
		sb.append(horseID);
		sb.append(", RaceDescription = '");
		sb.append(raceDescription);
		sb.append("', Purse = ");
		sb.append(purse);
		sb.append(", Rank = ");
		sb.append(rank);
		sb.append(", Earned = ");
		if (rank == 1)
		{
			sb.append(purse * 0.5);
		} else if (rank == 2)
		{
			sb.append(purse * 0.25);
		} else if (rank == 3)
		{
			sb.append(purse * 0.12);
		} else if (rank == 4)
		{
			sb.append(purse * 0.08);
		} else if (rank == 5)
		{
			sb.append(purse * 0.05);
		} else
		{
			sb.append(0);
		}
		sb.append(", Day = ");
		sb.append(day);
		sb.append(", Month = ");
		sb.append(month);
		sb.append(", Year");
		sb.append(year);
		sb.append(" WHERE RDID = ");
		sb.append(RDID);
		sb.append(";");
		
		try
		{
			int result = executeStatement.executeUpdate(sb.toString());
			this.notifyAllObservers();
			return true;
		} catch (SQLException e)
		{
			////System.out.println(e.getMessage());
		}
		return false;
	}
	
	public boolean updateDailyServices(int dsID, int sMonth, int sDay,
			int sYear, int eMonth, int eDay, int eYear, String description,
			double dailyPrice, int horseID)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE DailyServices SET StartDate = '");
		sb.append(sYear);
		sb.append("-");
		sb.append(sMonth);
		sb.append("-");
		sb.append(sDay);
		sb.append("', EndDate = '");
		sb.append(eYear);
		sb.append("-");
		sb.append(eMonth);
		sb.append("-");
		sb.append(eDay);
		sb.append("', Description = '");
		sb.append(description);
		sb.append("', HorseID = ");
		sb.append(horseID);
		sb.append(" WHERE DSID = ");
		sb.append(dsID);
		sb.append(";");
		try
		{
			int result = executeStatement.executeUpdate(sb.toString());
			this.notifyAllObservers();
			return true;
		} catch (SQLException e)
		{
			////System.out.println(e.getMessage());
		}
		return false;
	}
	
	public ArrayList<String> getAllHorse() throws SQLException
	{
		ArrayList<String> horseList = new ArrayList<String>();
		String sqlc = "select * from Horse";
		ResultSet moreResults = executeStatement.executeQuery(sqlc);
		while (moreResults.next() == true)
		{
			StringBuilder s = new StringBuilder();
			s.append(moreResults.getString("HorseName"));
			s.append(",");
			s.append(moreResults.getInt("YearOfBirth"));
			s.append(",");
			s.append(moreResults.getInt("Gender"));
			s.append(",");
			s.append(moreResults.getString("Pace"));
			s.append(",");
			s.append(moreResults.getString("Sire"));
			s.append(",");
			s.append(moreResults.getString("Dam"));
			s.append(",");
			s.append(moreResults.getInt("Active"));
			s.append(",");
			s.append(moreResults.getInt("HorseID"));
			horseList.add(s.toString());
		}
		return horseList;
	}
	
	public ArrayList<String> getAllOwner() throws SQLException
	{
		ArrayList<String> ownerList = new ArrayList<String>();
		String sqlc = "select * from Owner";
		ResultSet moreResults = executeStatement.executeQuery(sqlc);
		while (moreResults.next() == true)
		{
			StringBuilder s = new StringBuilder();
			s.append(moreResults.getString("LastName"));
			s.append(",");
			s.append(moreResults.getString("FirstName"));
			s.append(",");
			s.append(moreResults.getString("Address"));
			s.append(",");
			s.append(moreResults.getString("Phone"));
			s.append(",");
			s.append(moreResults.getInt("OwnerID"));
			s.append(",");
			s.append(moreResults.getInt("Active"));
			s.append(",");
			s.append(moreResults.getDouble("OutStandingBalance"));
			ownerList.add(s.toString());
		}
		return ownerList;
	}
	
	public ArrayList<String> getAllTypesOfServices() throws SQLException
	{
		ArrayList<String> servicesList = new ArrayList<String>();
		String sqlc = "select * from TypeService";
		ResultSet moreResults = executeStatement.executeQuery(sqlc);
		while (moreResults.next() == true)
		{
			
			StringBuilder s = new StringBuilder();
			s.append(moreResults.getString("ServiceName"));
			s.append(",");
			s.append(moreResults.getString("Description"));
			s.append(",");
			s.append(moreResults.getDouble("Price"));
			servicesList.add(s.toString());
		}
		return servicesList;
	}
	
	// //////////////////SEARCH BILLS
	
	public ArrayList<String> searchBillsALL(String sFILE, String sFN,
			String sLN, int sMON, int sYEA, int sOID, int sBID, int qFILE,
			int qFN, int qLN, int qMON, int qYEA, int qOID, int qBID)
			throws SQLException
	{
		
		/*
		 * ORDER of Input: File Data (Name, Loc, or whatever you're using, as
		 * long as it's a String) The Owner's First Name The Owner's Last Name
		 * Month Year OwnerID BillID Then the Q's for those fields come in the
		 * Same Order.
		 */
		
		/*
		 * ORDER of Output File data (however you are doing it) FirstName Last
		 * Name Month Year OwnerID BillID
		 */
		
		/*
		 * VARIABLE KEY (s means "searchBy", h means "has", q means
		 * "queryWanted", r means "return") s/h/q/r FILE// Refers to the File
		 * Name/Classpath/MemLoc (or whatever you're using) s/h/q/r FN //Refers
		 * to the Owner's FirstName s/h/q/r LN //Refers to the Owner's LastName
		 * s/h/q/r MON //Refers to the Month s/h/q/r YEA //Refers to the Year
		 * s/h/q/r OID //Refers to the OwnerID s/h/q/r BID //Refers to the
		 * BillID
		 */
		
		ArrayList<String> returned = new ArrayList<String>();
		
		// THE SELECT BOOLEANS TELL US WHICH FIELDS (IF ANY) ARE BEING SEARCHED
		// FOR
		boolean hFILE = false;
		boolean hFN = false;
		boolean hLN = false;
		boolean hMON = false;
		boolean hYEA = false;
		boolean hOID = false;
		boolean hBID = false;
		
		// /THE SELECT BOOLEAN END
		
		StringBuilder s = new StringBuilder();
		s.append("SELECT * FROM Bill");
		if (sFILE.equals("") == false)
		{
			hFILE = true;
		}
		if (sFN.equals("") == false)
		{
			
			hFN = true;
		}
		if (sLN.equals("") == false)
		{
			
			hLN = true;
		}
		
		if (sMON != -1)
		{
			hMON = true;
		}
		if (sYEA != -1)
		{
			hYEA = true;
		}
		if (sOID != -1)
		{
			hOID = true;
		}
		if (sBID != -1)
		{
			hBID = true;
		}
		
		// We've found out which terms we are searching on, and which one's we
		// aren't
		
		// /////////////////////////////////////////////////////////////////
		
		if (((((hFILE == false && (hFN == false)) && (hLN == false)) && ((hMON == false) && (hYEA == false))))
				&& ((hOID == false) && (hBID == false)))
		{
			// This triggers if all values have been set to search for null,
			// AKA, a pure * search
		} else
		{
			s.append(" WHERE ");
			if (hFILE)
			{
				s.append("File = ");
				s.append("'");
				s.append(sFILE);
				s.append("'");
				
			}
			if (hFN)
			{
				if (hFILE) // This second IF Block checks to see if there was a
							// field filled in before it, meaning that we need
							// to add an "AND" clause
				{
					s.append(" AND ");
				}
				s.append("FirstName");
				s.append("=");
				s.append("'");
				s.append(sFN);
				s.append("'");
				
			}
			if (hLN)
			{
				if (hFILE || hFN)
				{
					s.append(" AND ");
				}
				s.append("LastName = ");
				s.append("'");
				s.append(sLN);
				s.append("'");
				
			}
			if (hMON)
			{
				if ((hFN || hLN) || hFILE)
				{
					s.append(" AND ");
				}
				s.append("Month = ");
				s.append(sMON);
			}
			if (hYEA)
			{
				if (((hFN || hLN) || hFILE) || hMON)
				{
					s.append(" AND ");
				}
				s.append("YEAR = ");
				s.append(sYEA);
			}
			if (hOID)
			{
				if ((((hFN || hLN) || hFILE) || hMON) || hYEA)
				{
					s.append(" AND ");
				}
				s.append("OwnerID = ");
				s.append(sOID);
			}
			if (hBID)
			{
				if (((((hFN || hLN) || hFILE) || hMON) || hYEA) || hOID)
				{
					s.append(" AND ");
				}
				s.append("BillID = ");
				s.append(sBID);
			}
		}
		// ///////Finished assembling our QueryString, now we pass it to the DB.
		// Note, at this point, we have to enter our Try/Catch block
		
		try
		{
			ResultSet result = executeStatement.executeQuery(s.toString());
			while (result.next() == true)
			{
				StringBuilder element = new StringBuilder(); // Each String
																// being sent
																// back is a
																// StringBuilder
																// (At first)
				
				if (qFILE == 1)
				{
					String rFILE = result.getString("File"); // Gets the File
																// data
					element.append(rFILE); // Add it to the StringBuilder
					element.append(",");
				}
				// Rinse, lather, repeat for each attribute.
				if (qFN == 1)
				{
					String rFN = result.getString("FirstName");
					element.append(rFN);
					element.append(",");
				}
				if (qLN == 1)
				{
					String rLN = result.getString("LastName");
					element.append(rLN);
					element.append(",");
				}
				if (qMON == 1)
				{
					String rMON = result.getString("Month");
					element.append(rMON);
					element.append(",");
				}
				if (qYEA == 1)
				{
					String rYEA = result.getString("YEAR");
					element.append(rYEA);
					element.append(",");
				}
				if (qOID == 1)
				{
					String rOID = result.getString("OwnerID");
					element.append(rOID);
					element.append(",");
				}
				
				if (qBID == 1)
				{
					String rBID = result.getString("BILLID");
					element.append(rBID);
					element.append(",");
				}
				
				String e = element.toString();
				returned.add(e);
				
				// ///We will return all of our strings with an extra comma at
				// the end, you'll just need to string that
				
			}
			
			return returned;
			
		}
		// If our Query fails, we will default to this.
		catch (SQLException sqle)
		{
			////System.out.println(sqle.toString());
			return returned;
		}
		
	}
	
	// /////////////////END SEARCH BILLS
	
	// ///////////////////SEARCH Owners
	
	public ArrayList<String> searchOwnerALL(String sFN, String sLN,
			String sADD, int sPN, int sID, int sOB, int sAB, int qFN, int qLN,
			int qADD, int qPN, int qID, int qOB, int qAB) throws SQLException
	{
		
		/*
		 * ORDER of Input: The Owners First Name The Owners Last Name The Owners
		 * Address (as a String) The Owner Phone Number The Owner's ID The
		 * Owner's Outstanding Balance The Owner's Active Boolean Then the Q's
		 * for those fields come in the Same Order.
		 */
		
		/*
		 * ORDER of Output FirstName Last Name Address Phone# OwnerID
		 * OutStandingBalance Active
		 */
		
		/*
		 * VARIABLE KEY (s means "searchBy", h means "has", q means
		 * "queryWanted", r means "return") s/h/q/r s/h/q/r FN //Refers to the
		 * Owner's FirstName s/h/q/r LN //Refers to the Clients LastName s/h/q/r
		 * ADD //Refers to the Owner's Address s/h/q/r PN //Refers to the
		 * Owner's Phone Number s/h/q/r OID //Refers to the OwnerID s/h/q/r OB
		 * //Refers to the Owner's Outstanding Balance s/h/q/r AB //Refers to
		 * the Owner's Active/Inactive Status
		 */
		
		ArrayList<String> returned = new ArrayList<String>();
		
		// THE SELECT BOOLEANS TELL US WHICH FIELDS (IF ANY) ARE BEING SEARCHED
		// FOR
		boolean hFN = false;
		boolean hLN = false;
		boolean hADD = false;
		boolean hPN = false;
		boolean hID = false;
		boolean hOB = false;
		boolean hAB = false;
		// /THE SELECT BOOLEAN END
		
		StringBuilder s = new StringBuilder();
		s.append("SELECT * FROM Owner");
		if (sFN.equals("") == false)
		{
			hFN = true;
		}
		if (sLN.equals("") == false)
		{
			
			hLN = true;
		}
		
		if (sADD.equals("") == false)
		{
			hADD = true;
		}
		if (sPN != -1)
		{
			hPN = true;
		}
		if (sID != -1)
		{
			hID = true;
		}
		if (sOB != -1)
		{
			hOB = true;
		}
		if (sAB != -1)
		{
			hAB = false;
		}
		// We've found out which terms we are searching on, and which one's we
		// aren't
		
		// /////////////////////////////////////////////////////////////////
		
		if (((((hFN == false) && (hLN == false)) && ((hADD == false) && (hPN == false))))
				&& ((hID == false) && (hOB || hAB)))
		{
			// int result = executeStatement.executeUpdate(s.toString());
			// This triggers if all values have been set to search for null,
			// AKA, a pure * search
		} else
		{
			s.append(" WHERE ");
			if (hFN) // If we are searching on this term
			{
				s.append("FirstName = "); // Get the attribute we are searching
											// against
				s.append("'"); // Add a ' charcter if we need to (for strings)
				s.append(sFN);// Add the Data Piece
				s.append("'"); // Close the '
				
			}
			if (hLN)
			{
				if (hFN) // This IF Block tells us if we need an "and" statement
				{
					s.append(" AND ");
				}
				s.append("LastName");
				s.append("=");
				s.append("'");
				s.append(sLN);
				s.append("'");
				
			}
			if (hADD)
			{
				if (hFN || hLN)
				{
					s.append(" AND ");
				}
				s.append("Address = ");
				s.append("'");
				s.append(sADD);
				s.append("'");
				
			}
			if (hPN)
			{
				if ((hFN || hLN) || hADD)
				{
					s.append(" AND ");
				}
				s.append("Phone = ");
				s.append(sPN);
			}
			if (hID)
			{
				if ((hLN || hFN) || (hADD || hPN))
				{
					s.append(" AND ");
				}
				s.append("OwnerID = ");
				s.append(sID);
			}
			if (hOB)
			{
				if (((hLN || hFN) || (hADD || hPN)) || hID)
				{
					s.append(" AND ");
				}
				s.append("OutStandingBalance = ");
				s.append(sOB);
			}
			if (hAB)
			{
				if ((((hLN || hFN) || (hADD || hPN)) || hID) || hAB)
				{
					s.append(" AND ");
				}
				s.append("Active = ");
				s.append(sAB);
			}
		}
		// Now that We've finished assembling our query string, we send it to
		// the DB. Note,
		// Since there is a chance the Query could fail (if it can't find
		// anythign) we need to start
		// the Try/Catch block now.
		try
		{
			
			ResultSet result = executeStatement.executeQuery(s.toString());
			while (result.next() == true)
			{
				StringBuilder element = new StringBuilder();
				
				if (qFN == 1)
				{
					String rFN = result.getString("FirstName");
					element.append(rFN);
					element.append(",");
				}
				if (qLN == 1)
				{
					String rLN = result.getString("LastName");
					element.append(rLN);
					element.append(",");
				}
				if (qADD == 1)
				{
					String rADD = result.getString("Address");
					element.append(rADD);
					element.append(",");
				}
				if (qPN == 1)
				{
					String rPN = result.getString("Phone");
					element.append(rPN);
					element.append(",");
				}
				if (qID == 1)
				{
					String rID = result.getString("OwnerID");
					element.append(rID);
					element.append(",");
				}
				
				if (qOB == 1)
				{
					String rOB = result.getString("OutStandingBalance");
					element.append(rOB);
					element.append(",");
				}
				if (qAB == 1)
				{
					String rAB = result.getString("Active");
					element.append(rAB);
					element.append(",");
				}
				// /NOTE WE WILL BE RETURNING AN UNNESSARY EXTRA COMMA!!!!
				String e = element.toString();
				returned.add(e);
				
				// ///We will return all of our strings with an extra comma at
				// the end, you'll just need to string that
				
			}
			
			return returned;
			
		}
		// If the Search Fails, we return an empty array list
		catch (SQLException sqle)
		{
			////System.out.println(sqle.toString());
			return returned;
		}
		
	}
	
	// ///////////////END SEARCH Owners
	
	// ///////////////////////Search HORSE
	
	public ArrayList<String> searchHorseALL(String sHN, int sYOB, int sG,
			String sP, String sS, String sD, int sHID, int sAB, int qHName,
			int qYOB, int qG, int qPace, int qSire, int qDam, int qHID, int qAB)
			throws SQLException
	{
		
		/*
		 * ORDER of Input: The Horse's Name The Horse's Year of Birth The
		 * Horse's Gender (0 for female, 1 for male) The Horse's Pace The
		 * Horse's Sire The Horse's Dam The Horse's ID The Horse's Active Bool
		 * (0 for inactive, 1 for active) Then the Q's for those fields come in
		 * the Same Order.
		 */
		
		/*
		 * ORDER of Output HorseName YearOfBirth gender Pace Sire Dam HorseID
		 * Active
		 */
		
		/*
		 * VARIABLE KEY (s means "searchBy", h means "has", q means
		 * "queryWanted", r means "return")
		 * 
		 * s/h/q/r HN //Refers to the Horse's Name s/h/q/r YOB //Year of Birth
		 * s/h/q/r G //Gender s/h/q/r P //Pace s/h/q/r S //Sire s/h/q/r D //Dam
		 * s/h/q/r HID //Horse's ID s/h/q/r AB //Refers to the Owner's
		 * Active/Inactive Status
		 */
		
		ArrayList<String> returned = new ArrayList<String>();
		
		// THE SELECT BOOLEANS TELL US WHICH FIELDS (IF ANY) ARE BEING SEARCHED
		// FOR
		boolean hName = false;
		boolean hYOB = false;
		boolean hGen = false;
		boolean hPace = false;
		boolean hSire = false;
		boolean hDam = false;
		boolean hHID = false;
		boolean hAB = false;
		// /THE SELECT BOOLEAN END
		
		StringBuilder s = new StringBuilder();
		s.append("SELECT * FROM horse");
		if (sHN.equals("") == false)
		{
			hName = true;
		}
		if (sYOB != -1)
		{
			hYOB = true;
		}
		
		if (sG != -1)
		{
			hGen = true;
		}
		if (sP.equals("") == false)
		{
			hPace = true;
		}
		if (sS.equals("") == false)
		{
			hSire = true;
		}
		if (sD.equals("") == false)
		{
			hDam = true;
		}
		if (sHID != -1)
		{
			hHID = true;
		}
		if (sAB != -1)
		{
			hAB = true;
		}
		
		// We've found out which terms we are searching on, and which one's we
		// aren't
		
		// /////////////////////////////////////////////////////////////////
		
		if ((((((hName == false) && (hYOB == false)) && ((hGen == false) && (hPace == false)))) && ((hSire == false) && (hDam == false)))
				&& ((hHID == false) && (hAB == false)))
		{
			// This triggers if all values have been set to search for null,
			// AKA, a pure * search
		} else
		{
			s.append(" WHERE ");
			if (hName)
			{
				s.append("HORSENAME = ");
				s.append("'");
				s.append(sHN);
				s.append("'");
				
			}
			if (hYOB)
			{
				if (hName)
				{
					s.append(" AND ");// This second IF Block checks to see if
										// there was a field filled in before
										// it, meaning that we need to add an
										// "AND" clause
				}
				s.append("YEAROFBIRTH");
				s.append("=");
				s.append(sYOB);
			}
			if (hGen)
			{
				if (hName || hYOB)
				{
					s.append(" AND ");
				}
				s.append("Gender = ");
				s.append(sG);
			}
			if (hPace)
			{
				if ((hName || hYOB) || hGen)
				{
					s.append(" AND ");
				}
				s.append("PACE = ");
				s.append("'");
				s.append(sP);
				s.append("'");
				
			}
			if (hSire)
			{
				if ((hName || hYOB) || (hGen || hPace))
				{
					s.append(" AND ");
				}
				s.append("SIRE = ");
				s.append("'");
				s.append(sS);
				s.append("'");
				
			}
			if (hDam)
			{
				if ((hName || hYOB) || ((hGen || hPace) || hSire))
				{
					s.append(" AND ");
				}
				
				s.append("DAM = ");
				s.append("'");
				s.append(sD);
				s.append("'");
				
			}
			if (hHID)
			{
				if (((hName || hYOB) || ((hGen || hPace) || hSire)) || hDam)
				{
					s.append(" AND ");
				}
				s.append("HorseID =");
				s.append(sHID);
			}
			if (hAB)
			{
				if ((((hName || hYOB) || ((hGen || hPace) || hSire)) || hDam)
						|| hAB)
				{
					s.append(" AND ");
				}
				s.append("Active =");
				s.append(sAB);
			}
		}
		
		// Now that the Query String has been made, we need to send it to the
		// DB. Thus, we need the
		// TRY/CATCH Block in case the search fails.
		try
		{
			
			ResultSet result = executeStatement.executeQuery(s.toString());
			////System.out.println(result);
			while (result.next() == true)
			{
				StringBuilder element = new StringBuilder();
				
				if (qHName == 1) // Checks to see if we searched on that
									// attribute
				{
					String nameOHorse = result.getString("HorseName"); // Gets
																		// the
																		// result
																		// of
																		// that
																		// attribute
																		// for
																		// each
																		// return
																		// entry
					element.append(nameOHorse); // Appends it to the string
					element.append(",");
				}
				// Rinse, Lather, Repeat
				if (qYOB == 1)
				{
					String yOfBirth = result.getString("YearOfBirth");
					element.append(yOfBirth);
					element.append(",");
				}
				if (qG == 1)
				{
					String gENDER = result.getString("gender");
					element.append(gENDER);
					element.append(",");
				}
				if (qPace == 1)
				{
					String pACE = result.getString("Pace");
					element.append(pACE);
					element.append(",");
				}
				if (qSire == 1)
				{
					String sIRE = result.getString("Sire");
					element.append(sIRE);
					element.append(",");
				}
				if (qDam == 1)
				{
					String dAM = result.getString("Dam");
					element.append(dAM);
					element.append(",");
				}
				if (qHID == 1)
				{
					String rHID = result.getString("HorseID");
					element.append(rHID);
					element.append(",");
				}
				if (qAB == 1)
				{
					String rAB = result.getString("Active");
					element.append(rAB);
					element.append(",");
					
				}
				String e = element.toString();
				returned.add(e);
				
				// ///We will return all of our strings with an extra comma at
				// the end, you'll just need to string that
				
			}
			
			return returned;
			
		} catch (SQLException sqle)
		{
			////System.out.println(sqle.toString());
			return returned;
		}
		
	}
	
	// ////////////////END SEARCH HORSE
	// //////////////SEARCH RRC
	
	public ArrayList<String> searchRRCALL(int sRID, int sOID, int sHN,
			double sAM, String sDES, int sDAY, int sMON, int sYEA, int qRID,
			int qOID, int qPS, int qHN, int qAM, int qDES, int qDAY, int qMON,
			int qYEA) throws SQLException
	{
		
		/*
		 * ORDER of Input: The Race Related Charges ID The Owner's ID The
		 * HorseID //At somepoint, I'll change the Varib Name to HID, but I'm
		 * too lazy The Amount of the Charge The Description The Day of the Race
		 * The Month of the Race The Year of the Race
		 * 
		 * Then the Q's for those fields come in the Same Order.
		 */
		
		/*
		 * ORDER of Output RRCID Owner ID HorseID AMOUNT Description Day Month
		 * Year
		 */
		
		/*
		 * VARIABLE KEY (s means "searchBy", h means "has", q means
		 * "queryWanted", r means "return")
		 * 
		 * s/h/q/r RID //Race Related Charge ID s/h/q/r OID //Owner ID s/h/q/r
		 * HN //HorseID (Was HorseName, but I changed it, without changing Varib
		 * Names) s/h/q/r AM //Amount s/h/q/r DES //Description s/h/q/r DAY
		 * //Day s/h/q/r MON //Month s/h/q/r YEA //Year
		 */
		
		ArrayList<String> returned = new ArrayList<String>();
		
		// THE SELECT BOOLEANS TELL US WHICH FIELDS (IF ANY) ARE BEING SEARCHED
		// FOR
		boolean hRID = false;
		boolean hOID = false;
		boolean hHN = false;
		boolean hAM = false;
		boolean hDES = false;
		boolean hDAY = false;
		boolean hMON = false;
		boolean hYEA = false;
		// /THE SELECT BOOLEAN END
		
		StringBuilder s = new StringBuilder();
		s.append("SELECT * FROM RRC");
		if (sRID != -1)
		{
			hRID = true;
		}
		if (sOID != -1)
		{
			
			hOID = true;
		}
		
		if (sHN != -1)
		{
			hHN = true;
		}
		if (sAM != -1.0)
		{
			hAM = true;
		}
		if (sDES.equals("") == false)
		{
			hDES = true;
		}
		if (sDAY != -1)
		{
			hDAY = true;
		}
		
		if (sMON != -1)
		{
			hMON = true;
		}
		if (sYEA != -1)
		{
			hYEA = true;
		}
		// Now that we know which results we are searching on
		// /////////////////////////////////////////////////////////////////
		
		// If we aren't searching on anything, we just do a * search
		if (((((hRID == false) && (hOID == false)) && ((hHN == false) && (hDES == false))))
				&& ((hDAY == false) && ((hYEA == false) && (hMON == false)))
				&& (hAM == false))
		{
			// This triggers if all values have been set to search for null,
			// AKA, a pure * search
		}
		
		else
		// A real search
		{
			s.append(" WHERE ");
			if (hRID) // Check if we are searching on this
			{
				s.append("RRCID = "); // Assemble the search string
				s.append(sRID);
			}
			if (hOID)
			{
				if (hRID) // If we have searched on previous things, we need to
							// add a "AND" clause
				{
					s.append(" AND ");
				}
				s.append("OwnerID");
				s.append("=");
				s.append(sOID);
			}
			if (hHN)
			{
				if (hOID || hRID)
				{
					s.append(" AND ");
				}
				s.append("HorseID = ");
				s.append(sHN);
				
			}
			if (hAM)
			{
				if ((hRID || hOID) || hHN)
				{
					s.append(" AND ");
				}
				s.append(" AMOUNT =");
				s.append(sAM);
			}
			if (hDES)
			{
				if ((hRID || hOID) || (hHN || hAM))
				{
					s.append(" AND ");
				}
				s.append("Description = ");
				s.append("'");
				s.append(sDES);
				s.append("'");
				
			}
			if (hDAY)
			{
				if (((hRID || hOID) || (hHN || hAM)) || hDES)
				{
					s.append(" AND ");
				}
				s.append("DAY = ");
				s.append(sDAY);
			}
			if (hMON)
			{
				if (((hRID || hOID) || (hHN || hAM)) || (hDES || hDAY))
				{
					s.append(" AND ");
				}
				s.append("MONTH = ");
				s.append(sMON);
			}
			if (hYEA)
			{
				if (((hRID || hOID) || (hHN || hAM)) || (hDES || hDAY) || hMON)
				{
					s.append(" AND ");
				}
				s.append("YEAR = ");
				s.append(sYEA);
			}
		}
		// Now that we have assembled our search String, we need to run it
		// through the DB
		// Since there is a chance the search could fail, we need a Try/Catch
		// block
		try
		
		{
			ResultSet result = executeStatement.executeQuery(s.toString());
			while (result.next() == true)
			{
				StringBuilder element = new StringBuilder();
				
				if (qRID == 1)// If we are returning that attribute
				{
					String rRID = result.getString("RRCID");// Get the attribute
															// for each returned
															// result
					element.append(rRID);// Append it to the string
					element.append(",");
				}
				// Rinse, Lather, Repeat
				if (qOID == 1)
				{
					String rOID = result.getString("OWNERID");
					element.append(rOID);
					element.append(",");
				}
				
				if (qHN == 1)
				{
					String rHN = result.getString("HorseID");
					element.append(rHN);
					element.append(",");
				}
				if (qAM == 1)
				{
					String rAM = result.getString("AMOUNT");
					element.append(rAM);
					element.append(",");
				}
				if (qDES == 1)
				{
					String rDES = result.getString("Description");
					element.append(rDES);
					element.append(",");
				}
				if (qDAY == 1)
				{
					String rDAY = result.getString("DAY");
					element.append(rDAY);
					element.append(",");
				}
				if (qMON == 1)
				{
					String rMON = result.getString("MONTH");
					element.append(rMON);
					element.append(",");
				}
				if (qYEA == 1)
				{
					String rYEA = result.getString("YEAR");
					element.append(rYEA);
					element.append(",");
				}
				
				// /NOTE WE WILL BE RETURNING AN UNNESSARY EXTRA COMMA!!!!
				String e = element.toString();
				returned.add(e);
				
				// ///We will return all of our strings with an extra comma at
				// the end, you'll just need to string that
				
			}
			
			return returned;
			
		} catch (SQLException sqle)
		{
			////System.out.println(sqle.toString());
			return returned;
		}
		
	}
	
	// /////////////END RRC SHARES
	
	// ////////////SEARCH Shares
	public ArrayList<String> searchSharesALL(String sFN, String sLN, int sPS,
			int sHID, int sID, int sOID, int qFN, int qLN, int qPS, int qHID,
			int qID, int qOID) throws SQLException
	{
		
		/*
		 * ORDER of Input: The Owner's First Name The Owner's Last Name That
		 * Owner's Share in the Horse The HorseID The ShareID OwnerID Then the
		 * Q's for those fields come in the Same Order.
		 */
		
		/*
		 * ORDER of Output FirstName Last Name Percentage Share HorseID ShareID
		 * OwnerID
		 */
		
		/*
		 * VARIABLE KEY (s means "searchBy", h means "has", q means
		 * "queryWanted", r means "return") s/h/q/r FN //Refers to the Owner's
		 * FirstName s/h/q/r LN //Refers to the Owner's LastName s/h/q/r PS
		 * //Refers to the Share that owner has in the Horse s/h/q/r HID
		 * //Refers to the HorseID s/h/q/r OID //Refers to the OwnerID s/h/q/r
		 * ID //Refers to the ShareID
		 */
		
		ArrayList<String> returned = new ArrayList<String>();
		
		// THE SELECT BOOLEANS TELL US WHICH FIELDS (IF ANY) ARE BEING SEARCHED
		// FOR
		boolean hFN = false;
		boolean hLN = false;
		boolean hPS = false;
		boolean hHID = false;
		boolean hID = false;
		boolean hOID = false;
		// /THE SELECT BOOLEAN END
		
		StringBuilder s = new StringBuilder();
		s.append("SELECT * FROM Share");
		if (sFN.equals("") == false)
		{
			hFN = true;
		}
		if (sLN.equals("") == false)
		{
			
			hLN = true;
		}
		
		if (sPS != -1)
		{
			hPS = true;
		}
		
		if (sHID != -1)
		{
			
			hHID = true;
		}
		if (sID != -1)
		{
			hID = true;
		}
		if (sOID != -1)
		{
			hOID = true;
		}
		// Now that we know which terms we are searching on
		// /////////////////////////////////////////////////////////////////
		
		if (((((hFN == false) && (hLN == false)) && ((hPS == false) && (hHID == false))))
				&& ((hID == false) && hOID == false))
		{
			// This triggers if all values have been set to search for null,
			// AKA, a pure * search
		} else
		{
			s.append(" WHERE ");
			if (hFN)// Check if we are searching on that term
			{
				s.append("FirstName = '"); // Assemble the Search term
				s.append(sFN);
				s.append("'");
			}
			// Rinse, Lather, Repeat
			if (hLN)
			{
				if (hFN) // If we have searched on previous terms, we need to
							// add an "AND" clause
				{
					s.append(" AND ");
				}
				s.append("LastName");
				s.append("= '");
				s.append(sLN);
				s.append("'");
			}
			if (hPS)
			{
				if (hFN || hLN)
				{
					s.append(" AND ");
				}
				s.append("PercentageShare = ");
				s.append(sPS);
			}
			if (hHID)
			{
				if ((hFN || hLN) || hPS)
				{
					s.append(" AND ");
				}
				s.append("HorseID = ");
				s.append(sHID);
			}
			if (hOID)
			{
				if ((hLN || hFN) || (hPS || hHID))
				{
					s.append(" AND ");
				}
				s.append("OwnerID = ");
				s.append(sOID);
			}
			if (hID)
			{
				if (((hLN || hFN) || ((hPS || hHID)) || hOID))
				{
					s.append(" AND ");
				}
				s.append("ShareID = ");
				s.append(sID);
			}
			s.append(";");
		}
		// Since there is a chance our query will fail, we need a try/catch
		// block
		try
		{
			ResultSet result = executeStatement.executeQuery(s.toString());
			while (result.next() == true)
			{
				StringBuilder element = new StringBuilder();
				
				if (qFN == 1) // If we are returning this result
				{
					String rFN = result.getString("FirstName");
					element.append(rFN);// Add it to the string
					element.append(",");
				}
				if (qLN == 1)
				{
					String rLN = result.getString("LastName");
					element.append(rLN);
					element.append(",");
				}
				if (qPS == 1)
				{
					String rPS = result.getString("PercentageShare");
					element.append(rPS);
					element.append(",");
				}
				if (qHID == 1)
				{
					String rHID = result.getString("HorseID");
					element.append(rHID);
					element.append(",");
				}
				if (qID == 1)
				{
					String rID = result.getString("ShareID");
					element.append(rID);
					element.append(",");
				}
				if (qOID == 1)
				{
					String rOID = result.getString("OwnerID");
					element.append(rOID);
					element.append(",");
				}
				
				// /NOTE WE WILL BE RETURNING AN UNNESSARY EXTRA COMMA!!!!
				String e = element.toString();
				returned.add(e); // Add the string to the ArrayList
				
				// ///We will return all of our strings with an extra comma at
				// the end, you'll just need to string that
				
			}
			
			return returned;
			
		} catch (SQLException sqle)
		{
			////System.out.println(sqle.toString());
			return returned;
		}
	}
	
	// ///END SEARCH Shares
	
	// ////////////SEARCH Services
	
	public ArrayList<String> searchServices(int sSID, int sHN, int sDAY,
			int sMON, int sYEA, String sSVN, double sCST/* , String sDES */,
			int qSID, int qHN, int qDAY, int qMON, int qYEA, int qSVN, int qCST/*
																				 * ,
																				 * int
																				 * qDES
																				 */)
			throws SQLException
	{
		
		// NOTE:THIS FUNCTION USED TO BE ABLE TO SEARCH BY DESCRIPTION, AND I
		// HAVE
		// LEFT IN THE FUNCTIONALITY FOR THAT (BUT COMMENTED OUT)
		
		/*
		 * ORDER of Input: The ServiceID The HorseID (HN used to mean HorseName,
		 * but then it was chagned to HorseID) Day of Service Month of Service
		 * Year of Service Service Name Service Cost Then the Q's for those
		 * fields come in the Same Order.
		 */
		
		/*
		 * ORDER of Output ServiceID HorseID Day Month Year ServiceName Cost
		 */
		
		/*
		 * VARIABLE KEY (s means "searchBy", h means "has", q means
		 * "queryWanted", r means "return") s/h/q/r SID //ShareID s/h/q/r HN
		 * //HorseID (it used to be HorseName, but it was changed, and I need to
		 * fix all the variables) s/h/q/r Day //Day of Service s/h/q/r Month
		 * //Month of the Service s/h/q/r YEA //Year of the Service s/h/q/r SVN
		 * //ServiceName s/h/q/r ID //Cost of the Service
		 */
		
		ArrayList<String> returned = new ArrayList<String>();
		
		// THE SELECT BOOLEANS TELL US WHICH FIELDS (IF ANY) ARE BEING SEARCHED
		// FOR
		boolean hSID = false;
		boolean hHN = false;
		boolean hDAY = false;
		boolean hMON = false;
		boolean hYEA = false;
		boolean hSVN = false;
		boolean hCST = false;
		// boolean hDES = false;
		
		// /THE SELECT BOOLEAN END
		
		StringBuilder s = new StringBuilder();
		s.append("SELECT * FROM Services");
		if (sSID != -1)
		{
			hSID = true;
		}
		if (sHN != -1)
		{
			
			hHN = true;
		}
		if (sDAY != -1)
		{
			
			hDAY = true;
		}
		
		if (sMON != -1)
		{
			hMON = true;
		}
		if (sYEA != -1)
		{
			hYEA = true;
		}
		if (sSVN.equals("") == false)
		{
			
			hSVN = true;
		}
		
		if (sCST != -1.0)
		{
			hCST = true;
		}
		
		/*
		 * if (sDES.equals("") == false) {
		 * 
		 * hDES = true; }
		 */
		
		// Once we know what we want to search on
		
		// /////////////////////////////////////////////////////////////////
		
		if ((((((hSID == false) && (hHN == false)) && ((hDAY == false) && (hMON == false)))) && ((hYEA == false)))
				&& ((hSVN == false) && (hCST == false)) /* && (hDES == false) */)
		{
			// This triggers if all values have been set to search for null,
			// AKA, a pure * search
		} else
		
		// Build the Query String
		{
			s.append(" WHERE ");
			if (hSID) // If we are searching on this thing
			{
				s.append("ServiceID = ");
				s.append(sSID);
			}
			if (hHN)
			{
				if (hSID)
				{
					s.append(" AND "); // If we have searched on previous things
				}
				s.append("HorseID");
				s.append("=");
				s.append(sHN);
				
			}
			if (hDAY)
			{
				if (hSID || hHN)
				{
					s.append(" AND ");
				}
				s.append("Day = ");
				
				s.append(sDAY);
			}
			if (hMON)
			{
				if ((hSID || hHN) || hDAY)
				{
					s.append(" AND ");
				}
				s.append("Month = ");
				s.append(sMON);
			}
			if (hYEA)
			{
				if ((hSID || hHN) || (hDAY || hMON))
				{
					s.append(" AND ");
				}
				s.append("Year = ");
				s.append(sYEA);
			}
			if (hSVN)
			{
				if (((hSID || hHN) || (hDAY || hMON)) || hYEA)
				{
					s.append(" AND ");
				}
				s.append("ServiceName = ");
				s.append("'");
				s.append(sSVN);
				s.append("'");
				
			}
			if (hCST)
			{
				if (((hSID || hHN) || ((hDAY || hMON) || hYEA)) || hSVN)
				{
					s.append(" AND ");
				}
				s.append("cost = ");
				s.append(sCST);
			}
			/*
			 * if (hDES) { if ((((hSID || hHN) || (hDAY || hMON)) || hYEA) ||
			 * (hSVN || hCST)) { s.append(" AND "); }
			 * s.append("Description = "); s.append("'"); s.append(sDES);
			 * s.append("'");
			 * 
			 * }
			 */
		}
		// Since the query could fail, we need a Try/Catch Block
		try
		{
			ResultSet result = executeStatement.executeQuery(s.toString());
			
			while (result.next() == true)
			{
				StringBuilder element = new StringBuilder();
				
				if (qSID == 1)
				{
					String rSID = result.getString("ServiceID");
					element.append(rSID);
					element.append(",");
				}
				if (qHN == 1)
				{
					String rHN = result.getString("HorseID");
					element.append(rHN);
					element.append(",");
				}
				if (qDAY == 1)
				{
					String rDAY = result.getString("Day");
					element.append(rDAY);
					element.append(",");
				}
				if (qMON == 1)
				{
					String rMON = result.getString("Month");
					element.append(rMON);
					element.append(",");
				}
				if (qYEA == 1)
				{
					String rYEA = result.getString("Year");
					element.append(rYEA);
					element.append(",");
				}
				if (qSVN == 1)
				{
					String rSVN = result.getString("ServiceName");
					element.append(rSVN);
					element.append(",");
				}
				if (qCST == 1)
				{
					String rCST = result.getString("COST");
					element.append(rCST);
					element.append(",");
				}
				
				/*
				 * if (qDES == 1) { String rDES =
				 * result.getString("Description"); element.append(rDES);
				 * element.append(","); }
				 */
				// /NOTE WE WILL BE RETURNING AN UNNESSARY EXTRA COMMA!!!!
				String e = element.toString();
				returned.add(e);
				
				// ///We will return all of our strings with an extra comma at
				// the end, you'll just need to string that
				
			}
			
			return returned;
			
		} catch (SQLException sqle)
		{
			////System.out.println(sqle.toString());
			return returned;
		}
		
	}
	
	// ///END SEARCH Services
	
	// ///////////////SEARCH Invoices
	
	public ArrayList<String> searchInvoicesALL(int sIID, String sFILE,
			int sOID, int sDAY, int sMON, int sYEA, int qIID, int qFILE,
			int qOID, int qDAY, int qMON, int qYEA) throws SQLException
	{
		
		ArrayList<String> returned = new ArrayList<String>();
		
		/*
		 * ORDER of Input: The InvoiceID The Invoice File (whatever that is, and
		 * how it's being used) The OwnerID The Day The Month The Year Then the
		 * Q's for those fields come in the Same Order.
		 */
		
		/*
		 * ORDER of Output The InvoiceID The Invoice File (whatever that is, and
		 * how it's being used) The OwnerID The Day The Month The Year
		 */
		
		/*
		 * VARIABLE KEY (s means "searchBy", h means "has", q means
		 * "queryWanted", r means "return") s/h/q/r IID //The InvoiceID s/h/q/r
		 * FILE//The File String s/h/q/r OID//The OwnerID s/h/q/r DAY//The Day
		 * s/h/q/r MON//The Month s/h/q/r YEA//The Year
		 */
		
		// THE SELECT BOOLEANS TELL US WHICH FIELDS (IF ANY) ARE BEING SEARCHED
		// FOR
		boolean hIID = false;
		boolean hFILE = false;
		boolean hOID = false;
		boolean hDAY = false;
		boolean hMON = false;
		boolean hYEA = false;
		// /THE SELECT BOOLEAN END
		
		StringBuilder s = new StringBuilder();
		s.append("SELECT * FROM Invoice");
		if (sIID != -1)
		{
			hIID = true;
		}
		if (sFILE.equals("") == false)
		{
			
			hFILE = true;
		}
		if (sOID != -1.0)
		{
			hOID = true;
		}
		if (sDAY != -1)
		{
			hDAY = true;
		}
		if (sMON != -1)
		{
			
			hMON = true;
		}
		if (sYEA != -1.0)
		{
			hYEA = true;
		}
		// Now that we know what fields we are searching for
		// /////////////////////////////////////////////////////////////////
		
		if (((hIID == false && (hFILE == false)) && (hOID == false))
				&& ((hDAY == false && (hMON == false)) && (hYEA == false)))
		{
			// This triggers if all values have been set to search for null,
			// AKA, a pure * search
		} else
		{
			s.append(" WHERE ");
			if (hIID) // Check whether or not we are searching by this attribute
			{
				s.append("InvoiceId = ");
				s.append(sIID);
			}
			if (hIID)
			{
				if (hFILE)
				{
					s.append(" AND "); // If we have searched on previous
										// attributes
				}
				s.append("FileName ");
				s.append("=");
				s.append("'");
				s.append(sFILE);
				s.append("'");
				
			}
			if (hOID)
			{
				if (hIID || hFILE)
				{
					s.append(" AND ");
				}
				s.append("OwnerID = ");
				s.append(sOID);
				
			}
			if (hDAY)
			{
				if ((hIID || hFILE) || hOID)
				{
					s.append(" AND ");
				}
				s.append("DAY = ");
				s.append(sDAY);
				
			}
			if (hMON)
			{
				if (((hIID || hFILE) || hOID) || hDAY)
				{
					s.append(" AND ");
				}
				s.append("MONTH = ");
				s.append(sMON);
				
			}
			if (hYEA)
			{
				if ((((hIID || hFILE) || hOID) || hDAY) || hMON)
				{
					s.append(" AND ");
				}
				s.append("MONTH = ");
				s.append(sMON);
				
			}
		}
		// /Now that we have assembled our string, we send it to the DB. Since
		// there's a chance our DB fails, we need a TRY/CATCH Block
		try
		{
			ResultSet result = executeStatement.executeQuery(s.toString());
			while (result.next() == true)
			{
				StringBuilder element = new StringBuilder();
				
				if (qIID == 1)
				{
					String rIID = result.getString("InvoiceId");
					element.append(rIID);
					element.append(",");
				}
				if (qFILE == 1)
				{
					String rFILE = result.getString("FileName");
					element.append(rFILE);
					element.append(",");
				}
				if (qOID == 1)
				{
					String rOID = result.getString("OwnerID");
					element.append(rOID);
					element.append(",");
				}
				
				if (qDAY == 1)
				{
					String rDAY = result.getString("DAY");
					element.append(rDAY);
					element.append(",");
				}
				if (qMON == 1)
				{
					String rMON = result.getString("Month");
					element.append(rMON);
					element.append(",");
				}
				if (qYEA == 1)
				{
					String rYEA = result.getString("Year");
					element.append(rYEA);
					element.append(",");
				}
				
				// /NOTE WE WILL BE RETURNING AN UNNESSARY EXTRA COMMA!!!!
				String e = element.toString();
				returned.add(e);
			}
			// ///We will return all of our strings with an extra comma at the
			// end, you'll just need to string that
			
			return returned;
		} catch (SQLException sqle)
		{
			////System.out.println(sqle.toString());
			
			return returned;
		}
		
	}
	
	// ////////////END SEARCH Invoices
	
	// ////////SEARCH TYPE-SERVICES
	
	public ArrayList<String> searchTServeALL(String sSN, String sSD, double sP,
			int qSN, int qSD, int qP) throws SQLException
	{
		
		ArrayList<String> returned = new ArrayList<String>();
		
		/*
		 * ORDER of Input: The Service Name The Service Description The Service
		 * Price Then the Q's for those fields come in the Same Order.
		 */
		
		/*
		 * ORDER of Output Service Name Service Description Price
		 */
		
		/*
		 * VARIABLE KEY (s means "searchBy", h means "has", q means
		 * "queryWanted", r means "return") s/h/q/r SN //Service Name s/h/q/r SD
		 * //Service Description (it used to be HorseName, but it was changed,
		 * and I need to fix all the variables) s/h/q/r P //Price
		 */
		
		// THE SELECT BOOLEANS TELL US WHICH FIELDS (IF ANY) ARE BEING SEARCHED
		// FOR
		boolean hSN = false;
		boolean hSD = false;
		boolean hP = false;
		
		// /THE SELECT BOOLEAN END
		
		StringBuilder s = new StringBuilder();
		s.append("SELECT * FROM TypeService");
		if (sSN.equals("") == false)
		{
			hSN = true;
		}
		if (sSD.equals("") == false)
		{
			
			hSD = true;
		}
		if (sP != -1.0)
		{
			hP = true;
		}
		// Now that we know what fields we are searching for
		// /////////////////////////////////////////////////////////////////
		
		if ((hSN == false && (hSD == false)) && (hP == false))
		{
			// This triggers if all values have been set to search for null,
			// AKA, a pure * search
		} else
		{
			s.append(" WHERE ");
			if (hSN) // Check whether or not we are searching by this attribute
			{
				s.append("ServiceName = ");
				s.append("'");
				s.append(sSN);
				s.append("'");
			}
			if (hSD)
			{
				if (hSN)
				{
					s.append(" AND "); // If we have searched on previous
										// attributes
				}
				s.append("Description ");
				s.append("=");
				s.append("'");
				s.append(sSD);
				s.append("'");
				
			}
			if (hP)
			{
				if (hSN || hSD)
				{
					s.append(" AND ");
				}
				s.append("Price = ");
				s.append(sP);
				
			}
		}
		// /Now that we have assembled our string, we send it to the DB. Since
		// there's a chance our DB fails, we need a TRY/CATCH Block
		try
		{
			ResultSet result = executeStatement.executeQuery(s.toString());
			while (result.next() == true)
			{
				StringBuilder element = new StringBuilder();
				
				if (qSN == 1)
				{
					String rSN = result.getString("ServiceName");
					element.append(rSN);
					element.append(",");
				}
				if (qSD == 1)
				{
					String rSD = result.getString("Description");
					element.append(rSD);
					element.append(",");
				}
				if (qP == 1)
				{
					String rP = result.getString("Price");
					element.append(rP);
					element.append(",");
				}
				
				// /NOTE WE WILL BE RETURNING AN UNNESSARY EXTRA COMMA!!!!
				String e = element.toString();
				returned.add(e);
			}
			// ///We will return all of our strings with an extra comma at the
			// end, you'll just need to string that
			
			return returned;
		} catch (SQLException sqle)
		{
			////System.out.println(sqle.toString());
			
			return returned;
		}
		
	}
	
	// ////////////END SEARCH TYPE-SERVICES
	
	/*
	 * SEARCH RACE REWARDS TABLE
	 */
	public ArrayList<String> searchRaceDetails(int sRID, int sOID, int sHID,
			String sDES, double sPU, int sRA, double sEA, int sDAY, int sMON,
			int sYEA, int qRID, int qOID, int qHID, int qDES, int qPU, int qRA,
			int qEA, int qDAY, int qMON, int qYEA) throws SQLException
	{
		
		/*
		 * ORDER of Input: The Race Details ID The Owner ID The HorseID The
		 * Description The Purse The Horse's Rank The Horse's Earnings Date of
		 * Race Month of Race Year of Race Then the Q's for those fields come in
		 * the Same Order.
		 */
		
		/*
		 * ORDER of Output RDID OwnerID HorseID Race Description Purse Rank
		 * Earned Day Month Year
		 */
		
		/*
		 * VARIABLE KEY (s means "searchBy", h means "has", q means
		 * "queryWanted", r means "return") s/h/q/r RID //Race Details ID
		 * s/h/q/r OID //OwnerID s/h/q/r HID //HorseID s/h/q/r DES //Race
		 * Description s/h/q/r OU //Purse s/h/q/r RA //Rank s/h/q/r EA //Earned
		 * s/h/q/r DAY //Day s/h/q/r Month //Month s/h/q/r YEA // Year
		 */
		
		ArrayList<String> returned = new ArrayList<String>();
		
		// THE SELECT BOOLEANS TELL US WHICH FIELDS (IF ANY) ARE BEING SEARCHED
		// FOR
		boolean hRID = false;
		boolean hOID = false;
		boolean hHID = false;
		boolean hDES = false;
		boolean hPU = false;
		boolean hRA = false;
		boolean hEA = false;
		boolean hDAY = false;
		boolean hMON = false;
		boolean hYEA = false;
		// /THE SELECT BOOLEAN END
		
		StringBuilder s = new StringBuilder();
		s.append("SELECT * FROM RD");
		if (sRID != -1)
		{
			hRID = true;
		}
		
		if (sOID != -1)
		{
			
			hOID = true;
		}
		
		if (sHID != -1)
		{
			
			hHID = true;
		}
		
		if (sDES.equals("") == false)
		{
			
			hDES = true;
		}
		
		if (sPU != -1.0)
		{
			hPU = true;
		}
		if (sRA != -1)
		{
			hRA = true;
		}
		if (sEA != -1.0)
		{
			
			hEA = true;
		}
		
		if (sDAY != -1)
		{
			hDAY = true;
		}
		
		if (sMON != -1)
		{
			hMON = true;
		}
		if (sYEA != -1)
		{
			hYEA = true;
		}
		// Now that we know what we are searching on
		// /////////////////////////////////////////////////////////////////
		
		if (((((hRID == false && (hOID == false)) && (hHID == false))
				&& ((hPU == false) && (hRA == false)) && (hEA == false)) && (hDAY == false))
				&& ((hMON == false) && (hYEA == false)))
		{
			// This triggers if all values have been set to search for null,
			// AKA, a pure * search
		} else
		{
			s.append(" WHERE ");
			if (hRID) // If We are searching on that
			{
				s.append("RDID = ");
				s.append(sRID);
			}
			if (hOID)
			{
				if (hRID) // If we have searched on something previously, we
							// need a "AND"
				{
					s.append(" AND ");
				}
				s.append("OwnerID");
				s.append("=");
				s.append(sOID);
				
			}
			if (hHID)
			{
				if (hRID || hOID)
				{
					s.append(" AND ");
				}
				s.append("HorseID = ");
				s.append(sHID);
				
			}
			if (hDES)
			{
				if ((hRID || hOID) || hHID)
				{
					
					s.append(" AND ");
					
				}
				s.append("RaceDescription = '");
				s.append(sDES);
				s.append("'");
			}
			
			if (hPU)
			{
				if (((hRID || hOID) || hHID) || hDES)
				{
					
					s.append(" AND ");
				}
				s.append("Purse = ");
				s.append(sPU);
				
			}
			if (hRA)
			{
				if ((((hRID || hOID) || hHID) || hDES) || hPU)
				{
					
					s.append(" AND ");
				}
				s.append("Rank = ");
				s.append(sRA);
				
			}
			if (hEA)
			{
				if (((((hRID || hOID) || hHID) || hDES) || hPU) || hRA)
				{
					s.append(" AND ");
				}
				s.append("Earned = ");
				s.append(sEA);
				
			}
			
			if (hDAY)
			{
				if ((((((hRID || hOID) || hHID) || hDES) || hPU) || hRA) || hEA)
				{
					
					s.append(" AND ");
				}
				s.append("Day = ");
				s.append(sDAY);
				
			}
			if (hMON)
			{
				if (((((((hRID || hOID) || hHID) || hDES) || hPU) || hRA) || hEA)
						|| hDAY)
				{
					
					s.append(" AND ");
				}
				s.append("MONTH = ");
				s.append(sMON);
				
			}
			if (hYEA)
			{
				if ((((((((hRID || hOID) || hHID) || hDES) || hPU) || hRA) || hEA) || hDAY)
						|| hYEA)
				{
					
					s.append(" AND ");
				}
				s.append("YEAR = ");
				s.append(sYEA);
				
			}
			
		}
		// /Now that we have assembled our QueryString, we pass it to the DB.
		// Since there is a chance the query could fail, we need to add a
		// TRY/CATCH block
		try
		{
			ResultSet result = executeStatement.executeQuery(s.toString());
			while (result.next() == true)
			{
				StringBuilder element = new StringBuilder();
				
				if (qRID == 1)
				{
					String rRID = result.getString("RDID"); // Get the Attribute
															// with that name
					element.append(rRID); // Append it to the stringbuilder
					element.append(",");
				}
				if (qOID == 1)
				{
					String rOID = result.getString("OwnerID");
					element.append(rOID);
					element.append(",");
				}
				if (qHID == 1)
				{
					String rHID = result.getString("HorseID");
					element.append(rHID);
					element.append(",");
				}
				
				if (qDES == 1)
				{
					String rDES = result.getString("RaceDescription");
					element.append(rDES);
					element.append(",");
				}
				
				if (qPU == 1)
				{
					String rPU = result.getString("Purse");
					element.append(rPU);
					element.append(",");
				}
				
				if (qRA == 1)
				{
					String rRA = result.getString("Rank");
					element.append(rRA);
					element.append(",");
				}
				
				if (qEA == 1)
				{
					String rEA = result.getString("Earned");
					element.append(rEA);
					element.append(",");
				}
				
				if (qDAY == 1)
				{
					String rDAY = result.getString("Day");
					element.append(rDAY);
					element.append(",");
				}
				if (qMON == 1)
				{
					String rMON = result.getString("Month");
					element.append(rMON);
					element.append(",");
				}
				if (qYEA == 1)
				{
					String rYEA = result.getString("Year");
					element.append(rYEA);
					element.append(",");
				}
				// /NOTE WE WILL BE RETURNING AN UNNESSARY EXTRA COMMA!!!!
				String e = element.toString();
				returned.add(e);
			}
			// ///We will return all of our strings with an extra comma at the
			// end, you'll just need to string that
			
			return returned;
		} catch (SQLException sqle)
		{
			////System.out.println(sqle.toString());
			
			return returned;
		}
		
	}
	
	// //////////FINISH SEARCH RACE REWARDS
	
	// ////////////////SearchDailySearchs
	
	public ArrayList<String> searchDailyServices(int sHID, int qDSI, int qSD,
			int qED, int qDES, int qDP, int qHID) throws SQLException
	{
		
		ArrayList<String> returned = new ArrayList<String>();
		
		/*
		 * ORDER of Input: HorseID is the only input for searching
		 * 
		 * The q's are as follows DailyServicesID StartDate EndDate Description
		 * DailyPrice HorseID
		 */
		
		/*
		 * ORDER of Output DailyServicesID StartDate EndDate Description
		 * DailyPrice HorseID
		 */
		
		/*
		 * VARIABLE KEY (s means "searchBy", h means "has", q means
		 * "queryWanted", r means "return") s/h/q/r HID //HorseID. The only
		 * thing we will be searching on.
		 */
		
		// THE SELECT BOOLEANS TELL US WHICH FIELDS (IF ANY) ARE BEING SEARCHED
		// FOR
		boolean hHID = false;
		// /THE SELECT BOOLEAN END
		
		StringBuilder s = new StringBuilder();
		s.append("SELECT * FROM DailyServices");
		if (sHID != -1)
		{
			hHID = true;
		}
		// Now that we know what fields we are searching for
		// /////////////////////////////////////////////////////////////////
		
		if (hHID == false)
		{
			// This triggers if all values have been set to search for null,
			// AKA, a pure * search
		} else
		{
			s.append(" WHERE ");
			if (hHID) // Check whether or not we are searching by this attribute
			{
				s.append("HorseID = ");
				s.append(sHID);
			}
			
		}
		// /Now that we have assembled our string, we send it to the DB. Since
		// there's a chance our DB fails, we need a TRY/CATCH Block
		try
		{
			ResultSet result = executeStatement.executeQuery(s.toString());
			while (result.next() == true)
			{
				StringBuilder element = new StringBuilder();
				
				if (qDSI == 1)
				{
					String rDSI = result.getString("DSID");
					element.append(rDSI);
					element.append(",");
				}
				if (qSD == 1)
				{
					String rSD = result.getString("StartDate");
					element.append(rSD);
					element.append(",");
				}
				if (qED == 1)
				{
					String rED = result.getString("EndDate");
					element.append(rED);
					element.append(",");
				}
				
				if (qDES == 1)
				{
					String rDSI = result.getString("Description");
					element.append(rDSI);
					element.append(",");
				}
				if (qDP == 1)
				{
					String rDP = result.getString("DailyPrice");
					element.append(rDP);
					element.append(",");
				}
				if (qHID == 1)
				{
					String rHID = result.getString("HorseID");
					element.append(rHID);
					element.append(",");
				}
				
				// /NOTE WE WILL BE RETURNING AN UNNESSARY EXTRA COMMA!!!!
				String e = element.toString();
				returned.add(e);
			}
			// ///We will return all of our strings with an extra comma at the
			// end, you'll just need to string that
			
			return returned;
		} catch (SQLException sqle)
		{
			////System.out.println(sqle.toString());
			
			return returned;
		}
		
	}
	
	public ArrayList<String> SearchInvoice(int sday, int smonth, int syear,
			int eday, int emonth, int eyear, int ownerID) throws SQLException
	{
		ArrayList<String> invoiceList = new ArrayList<String>();
		StringBuilder sqlc = new StringBuilder();
		sqlc.append("select * from Invoice ");
		if (ownerID != 0)
		{
			sqlc.append(" WHERE OwnerID = ");
			sqlc.append(ownerID);
		}
		if (sday != 0 && smonth != 0 && syear != 0)
		{
			if (ownerID != 0)
			{
				sqlc.append(" And ");
			} else
			{
				sqlc.append(" WHERE ");
			}
			sqlc.append("(( Year > ");
			sqlc.append(syear);
			sqlc.append(") OR (Year = ");
			sqlc.append(syear);
			sqlc.append(" AND Month > ");
			sqlc.append(smonth);
			sqlc.append(") OR (Year = ");
			sqlc.append(syear);
			sqlc.append(" AND Month = ");
			sqlc.append(smonth);
			sqlc.append(" AND Day >= ");
			sqlc.append(sday);
			sqlc.append("))");
			
		}
		if (eday != 0 && emonth != 0 && eyear != 0)
		{
			if (ownerID != 0 || (sday != 0 && smonth != 0 && syear != 0))
			{
				sqlc.append(" And ");
			} else
			{
				sqlc.append(" WHERE ");
			}
			sqlc.append("(( Year < ");
			sqlc.append(eyear);
			sqlc.append(") OR (Year = ");
			sqlc.append(eyear);
			sqlc.append(" AND Month < ");
			sqlc.append(emonth);
			sqlc.append(") OR (Year = ");
			sqlc.append(eyear);
			sqlc.append(" AND Month = ");
			sqlc.append(emonth);
			sqlc.append(" AND Day <= ");
			sqlc.append(eday);
			sqlc.append("))");
		}
		// ////System.out.println(sqlc.toString());
		ResultSet moreResults = executeStatement.executeQuery(sqlc.toString());
		while (moreResults.next() == true)
		{
			StringBuilder s = new StringBuilder();
			s.append(moreResults.getInt("InvoiceId"));
			s.append(",");
			s.append(moreResults.getString("FileName"));
			s.append(",");
			s.append(moreResults.getInt("OwnerID"));
			s.append(",");
			s.append(moreResults.getInt("Day"));
			s.append(",");
			s.append(moreResults.getInt("Month"));
			s.append(",");
			s.append(moreResults.getInt("Year"));
			invoiceList.add(s.toString());
		}
		
		return invoiceList;
	}
	
	public ArrayList<String> SearchService(int sday, int smonth, int syear,
			int eday, int emonth, int eyear, int horseID) throws SQLException
	{
		ArrayList<String> ServiceList = new ArrayList<String>();
		StringBuilder sqlc = new StringBuilder();
		sqlc.append("select * from Service ");
		if (horseID != 0)
		{
			sqlc.append(" WHERE HorseID = ");
			sqlc.append(horseID);
		}
		if (sday != 0 && smonth != 0 && syear != 0)
		{
			if (horseID != 0)
			{
				sqlc.append(" And ");
			} else
			{
				sqlc.append(" WHERE ");
			}
			sqlc.append("(( Year > ");
			sqlc.append(syear);
			sqlc.append(") OR (Year = ");
			sqlc.append(syear);
			sqlc.append(" AND Month > ");
			sqlc.append(smonth);
			sqlc.append(") OR (Year = ");
			sqlc.append(syear);
			sqlc.append(" AND Month = ");
			sqlc.append(smonth);
			sqlc.append(" AND Day >= ");
			sqlc.append(sday);
			sqlc.append("))");
			
		}
		if (eday != 0 && emonth != 0 && eyear != 0)
		{
			if (horseID != 0 || (sday != 0 && smonth != 0 && syear != 0))
			{
				sqlc.append(" And ");
			} else
			{
				sqlc.append(" WHERE ");
			}
			sqlc.append("(( Year < ");
			sqlc.append(eyear);
			sqlc.append(") OR (Year = ");
			sqlc.append(eyear);
			sqlc.append(" AND Month < ");
			sqlc.append(emonth);
			sqlc.append(") OR (Year = ");
			sqlc.append(eyear);
			sqlc.append(" AND Month = ");
			sqlc.append(emonth);
			sqlc.append(" AND Day <= ");
			sqlc.append(eday);
			sqlc.append("))");
		}
		// ////System.out.println(sqlc.toString());
		ResultSet moreResults = executeStatement.executeQuery(sqlc.toString());
		while (moreResults.next() == true)
		{
			StringBuilder s = new StringBuilder();
			s.append(moreResults.getInt("ServiceID"));
			s.append(",");
			s.append(moreResults.getInt("HorseID"));
			s.append(",");
			s.append(moreResults.getInt("Day"));
			s.append(",");
			s.append(moreResults.getInt("Month"));
			s.append(",");
			s.append(moreResults.getInt("Year"));
			s.append(",");
			s.append(moreResults.getString("ServiceName"));
			s.append(",");
			s.append(moreResults.getInt("cost"));
			s.append(",");
			s.append(moreResults.getInt("Day"));
			s.append(",");
			s.append(moreResults.getInt("Month"));
			s.append(",");
			s.append(moreResults.getInt("Year"));
			ServiceList.add(s.toString());
		}
		return ServiceList;
	}
	
	public ArrayList<String> SearchRRC(int sday, int smonth, int syear,
			int eday, int emonth, int eyear, int ownerID) throws SQLException
	{
		ArrayList<String> RRCList = new ArrayList<String>();
		StringBuilder sqlc = new StringBuilder();
		sqlc.append("select * from RRC ");
		if (ownerID != 0)
		{
			sqlc.append(" WHERE OwnerID = ");
			sqlc.append(ownerID);
		}
		if (sday != 0 && smonth != 0 && syear != 0)
		{
			if (ownerID != 0)
			{
				sqlc.append(" And ");
			} else
			{
				sqlc.append(" WHERE ");
			}
			sqlc.append("(( Year > ");
			sqlc.append(syear);
			sqlc.append(") OR (Year = ");
			sqlc.append(syear);
			sqlc.append(" AND Month > ");
			sqlc.append(smonth);
			sqlc.append(") OR (Year = ");
			sqlc.append(syear);
			sqlc.append(" AND Month = ");
			sqlc.append(smonth);
			sqlc.append(" AND Day >= ");
			sqlc.append(sday);
			sqlc.append("))");
			
		}
		if (eday != 0 && emonth != 0 && eyear != 0)
		{
			if (ownerID != 0 || (sday != 0 && smonth != 0 && syear != 0))
			{
				sqlc.append(" And ");
			} else
			{
				sqlc.append(" WHERE ");
			}
			sqlc.append("(( Year < ");
			sqlc.append(eyear);
			sqlc.append(") OR (Year = ");
			sqlc.append(eyear);
			sqlc.append(" AND Month < ");
			sqlc.append(emonth);
			sqlc.append(") OR (Year = ");
			sqlc.append(eyear);
			sqlc.append(" AND Month = ");
			sqlc.append(emonth);
			sqlc.append(" AND Day <= ");
			sqlc.append(eday);
			sqlc.append("))");
		}
		// ////System.out.println(sqlc.toString());
		ResultSet moreResults = executeStatement.executeQuery(sqlc.toString());
		while (moreResults.next() == true)
		{
			StringBuilder s = new StringBuilder();
			s.append(moreResults.getInt("RRCID"));
			s.append(",");
			s.append(moreResults.getInt("OwnerID"));
			s.append(",");
			s.append(moreResults.getInt("HorseID"));
			s.append(",");
			s.append(moreResults.getDouble("Amount"));
			s.append(",");
			s.append(moreResults.getString("Description"));
			s.append(",");
			s.append(moreResults.getInt("Month"));
			s.append(",");
			s.append(moreResults.getInt("Day"));
			s.append(",");
			s.append(moreResults.getInt("Year"));
			RRCList.add(s.toString());
		}
		return RRCList;
	}
	
	public ArrayList<String> SearchRD(int sday, int smonth, int syear,
			int eday, int emonth, int eyear, int ownerID) throws SQLException
	{
		ArrayList<String> RDList = new ArrayList<String>();
		StringBuilder sqlc = new StringBuilder();
		sqlc.append("select * from RD ");
		if (ownerID != 0)
		{
			sqlc.append(" WHERE OwnerID = ");
			sqlc.append(ownerID);
		}
		if (sday != 0 && smonth != 0 && syear != 0)
		{
			if (ownerID != 0)
			{
				sqlc.append(" And ");
			} else
			{
				sqlc.append(" WHERE ");
			}
			sqlc.append("(( Year > ");
			sqlc.append(syear);
			sqlc.append(") OR (Year = ");
			sqlc.append(syear);
			sqlc.append(" AND Month > ");
			sqlc.append(smonth);
			sqlc.append(") OR (Year = ");
			sqlc.append(syear);
			sqlc.append(" AND Month = ");
			sqlc.append(smonth);
			sqlc.append(" AND Day >= ");
			sqlc.append(sday);
			sqlc.append("))");
			
		}
		if (eday != 0 && emonth != 0 && eyear != 0)
		{
			if (ownerID != 0 || (sday != 0 && smonth != 0 && syear != 0))
			{
				sqlc.append(" And ");
			} else
			{
				sqlc.append(" WHERE ");
			}
			sqlc.append("(( Year < ");
			sqlc.append(eyear);
			sqlc.append(") OR (Year = ");
			sqlc.append(eyear);
			sqlc.append(" AND Month < ");
			sqlc.append(emonth);
			sqlc.append(") OR (Year = ");
			sqlc.append(eyear);
			sqlc.append(" AND Month = ");
			sqlc.append(emonth);
			sqlc.append(" AND Day <= ");
			sqlc.append(eday);
			sqlc.append("))");
		}
		// ////System.out.println(sqlc.toString());
		ResultSet moreResults = executeStatement.executeQuery(sqlc.toString());
		while (moreResults.next() == true)
		{
			StringBuilder s = new StringBuilder();
			s.append(moreResults.getInt("RDID"));
			s.append(",");
			s.append(moreResults.getInt("OwnerID"));
			s.append(",");
			s.append(moreResults.getInt("HorseID"));
			s.append(",");
			s.append(moreResults.getString("RaceDescription"));
			s.append(",");
			s.append(moreResults.getDouble("Purse"));
			s.append(",");
			s.append(moreResults.getInt("Rank"));
			s.append(",");
			s.append(moreResults.getDouble("Earned"));
			s.append(",");
			s.append(moreResults.getInt("Day"));
			s.append(",");
			s.append(moreResults.getInt("Month"));
			s.append(",");
			s.append(moreResults.getInt("Year"));
			RDList.add(s.toString());
		}
		return RDList;
	}
	
	public static void main(String[] args) throws Exception
	{
		// Mediator a = new Mediator();
		// a.SearchInvoice(2, 3, 1220, 1, 10, 1221, 321);
	}
}