/*Jean VanNoppen
*Dave Toth - csc336
*Horse Billing
*04/13/15
*Generate a printable Invoice for an Owner
*
** Things:
*	caluclates daily fees for multiple daily service entries, but without descriptions
*	calculating for previously active horses within that month
*/

import java.io.*;//BufferedWriter, FileWriter, File, IOException

import java.util.*;//Date, Calendar, Locale, ArrayList


//72x80 character page dimensions
public class invoice
{
	//file to write to
	private BufferedWriter writer = null;
	private String fileName;

	//owners firstname
	private String fName;
	//owner lastName
	private String lName;
	private int ownerID;

	private double monthDue;
	private double unpaid;

	private int invNum;
	private String dateStart;
	private String dateEnd;
	private int totalInvDays;


	//total for each horse
	private double horseTotal;
	//total number of horses
	private int horseNum;

	//onwer of invoice pays for all charges
	private double raceRelatedTotal;

	//number of days for training etc.
	private int numberOfDays;
	private String dailyDescription;
	private String dailyStart;
	private String dailyEnd; 
	private double dailyCost;
	private double dailyTotal;

	Mediator m;


	/*
	*constructor
	*@peram: ownerLastname, ownerFirstname, beginning date invoiced through, ending date, invoice number
	*/
	public invoice(String lName1, String fName1, String dateStart, String dateEnd, int vnum)
	{
		fName = fName1;
		lName = lName1;
		invNum = vnum;
		this.dateStart = dateStart;
		this.dateEnd = dateEnd;
		horseNum = 0;

		
		String [] dateStartParts = dateStart.split("/");
		String [] dateEndParts = dateEnd.split("/");
		int startY = Integer.parseInt(dateStartParts[2]);
		int startM = Integer.parseInt(dateStartParts[0]);
		int startD = Integer.parseInt(dateStartParts[1]);
		int endY = Integer.parseInt(dateEndParts[2]);
		int endM = Integer.parseInt(dateEndParts[0]);
		int endD = Integer.parseInt(dateEndParts[1]);
		totalInvDays = getNumDays(startD, startM, startY, endD, endM, endY);


		try
		{
			m = Mediator.getInstance();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		fileName = "invoice"+lName+vnum+".txt";
		

		try
		{
		//create new file
		File file = new File (fileName);
		file.createNewFile();

		//create file/buffered writer
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		writer = new BufferedWriter(fw);

		//header *format later
		writer.write("Foley Stables"+"\r\n"+"Pete & Lynne Foley"+"\r\n"+"236 Old Brown Road Salvisa, KY 40372"+"\r\n"+"Home: 859-865-2128 Fax: 859-865-2747 Cell: 859-608-3385"+"\r\n"+"Email: outbackharness@msn.com");
		writer.write("\r\n"+"------------------------------------------------------------------------------"+"\r\n");

		//write top info
		writer.write(ownerInfo());
		writer.write("\r\n"+"- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - "+"\r\n");

		//write horse info
		writer.write(horseInfo());


		//write bottom totals
		writer.write("\r\n\r\n\t\t\tTotal This Month For: "+fName+" "+lName+" "+horseNum+" Horses\t\t$"+monthDue+"0");
		writer.write("\r\n\t\t\t\t\t\t\t\tPast Due:\t$"+unpaid+"0");
		writer.write("\r\n\t\t\t\t\t\t\tTotal Amount Now Due:\t$"+(unpaid+monthDue)+"0");



		writer.write("\r\n\r\n--  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --  --\r\n");
		writer.write("Please Make Checks Payable To: 'Pete Foley' or 'Lynne Foley' Thank You" + "\r\n");
		writer.close();

		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	/*get the file name of the invoice file
	*/
	public String getFileName(){
		return fileName;
	}



	/*
	*get owner info for top of invoice & date
	*@return: string of info for file
	*/
	private String ownerInfo()
	{
		//get owner info
		ArrayList <String> ownerString = new ArrayList<String>();
		try
		{
			m.connect();
			ownerString = m.searchOwnerALL(fName, lName, "", -1, -1, -1, -1, 1, 1, 1, 1, 1, 1, 1);
			//"firstName,lastName,address,phonenumber,id,outstanding,"
			m.disconnect();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		//System.out.println("OWNERSTRING: "+ownerString);
		String[] info = (ownerString.get(0)).split(",");

		ownerID = Integer.parseInt(info[4]);
		unpaid =  Double.parseDouble(info[5]);
		unpaid = Double.parseDouble(info[5]);

		String infoPart = null;

		//get format date
		Calendar calendar = Calendar.getInstance();
		String date = ""+ calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US) +", "+ calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.US) +" "+ calendar.get(Calendar.DAY_OF_MONTH) +", "+ calendar.get(Calendar.YEAR);

		infoPart = info[0]+" "+info[1]+"\t\t\t\t\t\t"+date+"\r\n"+info[2]+"\r\n"+info[3];

		return infoPart;

	}


	/*get all info for one horse
	*
	*
	*/
	private String horseInfo()
	{

		//shareTable(fName, lName, %, horseID, shareID, ownerID, active)
		ArrayList <String> shareString = new ArrayList<String>();
		try
		{
			m.connect();
			shareString = m.searchSharesALL(fName, lName, -1, -1, -1, -1, 1, 1, 1, 1, 1, 1);
			//"firstName,lastName,percent,horseid,shareid,ownerid,active"
			m.disconnect();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		String finalHorse = "\r\n";

		//System.out.println("SHARES "+shareString);
		
		//variables for calculating totals
		int percent;
		int horseID;
		
		
		String currShare;
		String[] shareParts;//pieces of share info
		String[] horseParts;//pieces of horse info
		String horseName;

		

		//go through all shares, print info
		for (int i = 0; i < shareString.size(); i++)
		{
			horseNum++;
			horseTotal = 0.0;
			numberOfDays = totalInvDays;
			raceRelatedTotal = 0.0;
			double horseDaily = 0.0;
			//initalize dates on invoice through to be invoice range entered
			dailyStart = dateStart;
			dailyEnd = dateEnd;
			currShare = shareString.get(i);
			shareParts = currShare.split(",");

			//System.out.println("currShare: "+currShare);
			percent = Integer.parseInt(shareParts[2]); //get share
			
			horseID = Integer.parseInt(shareParts[3]); //get horseid

			//get info on horse
			ArrayList <String> horseString = new ArrayList <String> (); //*MEDIATOR.search(Horse, horseID);
			try
			{
				m.connect();
				horseString = m.searchHorseALL("", -1, -1, "", "", "", horseID, -1, 1, 1, 1, 1, 1, 1, 1, 1);

				//"horsename,yob,gender,pace,sire,dam,horseID, active"
				m.disconnect();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}

			//System.out.println("CURRHORSE STRING: "+horseString);
			horseParts = (horseString.get(0)).split(",");
			horseID = Integer.parseInt(horseParts[6]);
			horseName = horseParts[0];

			//print info for each horse
			finalHorse = finalHorse+"  "+horseParts[0]+":\r\n";
			
			//getDailyParts
			numberOfDays = getDailyCost(horseID);
			finalHorse = finalHorse + "   "+dailyStart+"  Through  "+dailyEnd+"  "+numberOfDays+"    Days "+dailyDescription+" @    $"+dailyCost+"0   PerDay\t$"+dailyTotal+"0\r\n";

			//get service descriptions
			finalHorse = finalHorse+ getServiceInfo(horseID);

			//get RRC
			finalHorse = finalHorse+getRRCharges(horseID);

			//get race rewards
			finalHorse = finalHorse + getRRRewards(horseID, percent);
			
			//add horsetotal to month due
			horseTotal = horseTotal+dailyTotal+raceRelatedTotal;	
			monthDue = monthDue + (horseTotal*((double)percent/100));
			finalHorse = finalHorse + "\t\t\t\t\t\t\t\t100% Total:\t$"+horseTotal+"0\r\n";
			finalHorse = finalHorse + "\t\t\t\t\tTotal for: "+horseName+"\t"+percent+"%\t\t$"+(horseTotal*((double)percent/100))+"0\r\n";



		}

		return finalHorse;

	}


	/*
	**************	FULL OF REPEATED CODE *************
	*
	*/
	public int getDailyCost(int horseid)
	{

		dailyTotal = 0.0;

		//get horse string
		ArrayList <String> dailyString = new ArrayList <String> (); //*MEDIATOR.search(Horse, horseID);
			try
			{
				m.connect();
				dailyString = m.searchDailyServices(horseid, 1, 1, 1, 1, 1, 1);
				//"servicesID,startDate(yyy-mm-dd),endDate,Description,price,horseID"
				m.disconnect();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}

			//if no daily charges
			if (dailyString.size()==0){
				return numberOfDays;
			}

			int endD;
			int endM;
			int endY;
			int startD;
			int startM;
			int startY;
			String[] dateEndParts;
			String[] dateStartParts;
			String [] dailyParts;

			//for each daily service entry
			for (int i = 0; i<dailyString.size(); i++)
			{
				//System.out.println("DAILY STRING: "+dailyString);
				dailyParts = (dailyString.get(i)).split(",");
				dailyDescription =  dailyParts[3];

				//cost 
				dailyCost = Double.parseDouble(dailyParts[4]);

				//ending date parts
				dateEndParts = (dailyParts[2]).split("-");
				endY = Integer.parseInt(dateEndParts[0]);
				endM = Integer.parseInt(dateEndParts[1]);
				endD = Integer.parseInt(dateEndParts[2]);

				//starting date parts
				dateStartParts = (dailyParts[1]).split("-");
				startY = Integer.parseInt(dateStartParts[0]);
				startM = Integer.parseInt(dateStartParts[1]);
				startD = Integer.parseInt(dateStartParts[2]);


				//check dates
				if ((isWithinRange(endM, endD, endY)) || (isWithinRange(startM, startD, startY))) 
				{
					//get daily start and end dates
					dailyStart = firstDateStart(startM, startD, startY);
					dailyEnd = firstDateEnd(endM, endD, endY);
					dateStartParts = dailyStart.split("/");
					dateEndParts = dailyEnd.split("/");
					startY = Integer.parseInt(dateStartParts[2]);
					startM = Integer.parseInt(dateStartParts[0]);
					startD = Integer.parseInt(dateStartParts[1]);
					endY = Integer.parseInt(dateEndParts[2]);
					endM = Integer.parseInt(dateEndParts[0]);
					endD = Integer.parseInt(dateEndParts[1]);

					//get/set num days
					numberOfDays = getNumDays(startD, startM, startY, endD, endM, endY);
					//return cost of days
					dailyTotal = numberOfDays*dailyCost;

				}//end if
			}//end for
		return numberOfDays;
	}


	/*
	*get the charges from a race
	*
	*/
	public String getRRCharges(int horseid)
	{
		int raceID;
		double raceTotalCost;
		String[] raceParts;//pieces of service info
		//dates of serice
		int raceDay;
		int raceMonth;
		int raceYear;

		String dateofRace;

		String returned="  Race Related Charges:\r\n";
		//get race charges
		ArrayList <String> raceString = new ArrayList <String> (); 
		try
		{
			m.connect();
			raceString = m.searchRRCALL(-1, -1, horseid, -1.0, "", -1, -1, -1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
			//"racerelatedID,ownerID,horseID,amount,description,day,month,year,"
			m.disconnect();
		}
		catch(Exception e)
		{
		
			e.printStackTrace();
		}
		//System.out.println("RRC STRING: "+raceString);

		if (raceString.size() == 0)
		{
			return "";
		}

		//for every charge
		for (int j = 0; j < raceString.size(); j++)
		{
			//get id and dates of the service
			raceParts = (raceString.get(j)).split(",");
			raceID = Integer.parseInt(raceParts[0]);
			raceDay = Integer.parseInt(raceParts[5]);
			raceMonth = Integer.parseInt(raceParts[6]);
			raceYear = Integer.parseInt(raceParts[7]);
				//if in days invoiced
			if (isWithinRange(raceMonth, raceDay, raceYear))
			{
				dateofRace = raceMonth+"/"+raceDay+"/"+raceYear;
				//get cost of race
				double raceInt = Double.parseDouble(raceParts[3]);
				raceRelatedTotal = raceRelatedTotal + raceInt;
				returned = returned+"    "+dateofRace+"\t"+raceParts[4]+"\t\t\t\t\t\t\t$"+raceInt+"0\r\n";
			}
		}
		return returned;	
	}



	/*
	*get the rewards for a horse
	*
	*/
	public String getRRRewards(int horseid, int percent)
	{
		
		//get race rewards
		ArrayList <String> rewString = new ArrayList <String> (); 
		try
		{
			m.connect();
			rewString = m.searchRaceDetails(-1, -1, horseid,"", -1.0,1, -1.0, -1, -1, -1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
			//RD: 0id,1ownerid,2horseid,3raceDescip,4purse,5rank,6earned,7day,8month,9year
			m.disconnect();
		}
		catch(Exception e)
		{
		
			e.printStackTrace();
		}
		//System.out.println("RD STRING: "+rewString);

		//if no race
		if (rewString.isEmpty()){
			return "";
		}

		String returned = "";
		String [] rewParts;
		int day;
		int month;
		int year;
		String desc;
		double earned;
		double purse;

		for (int i = 0; i < rewString.size(); i++)
		{

			rewParts = (rewString.get(i)).split(",");

			day = Integer.parseInt(rewParts[7]);
			month = Integer.parseInt(rewParts[8]);
			year = Integer.parseInt(rewParts[9]);
			earned = Double.parseDouble(rewParts[6]);
			earned = earned*((double)percent/100);

			//if no earnings or not within dates
			if ((earned==0.0) || !(isWithinRange(day, month, year)))
			{
				break;
			}

			//if won
			else
			{
				desc = rewParts[3];
				//purse = (double) (Integer.parseInt(rewParts[4]));
				returned = returned + "    "+month+"/"+day+"/"+year+"\t"+desc+" ••••••••• Earnings: $"+earned+"0\r\n";
			}//end else
		}//end for

		return "  Race Rewards:\r\n"+returned;
	}


	/*
	*get the services for a given horse
	*
	*/
	public String getServiceInfo(int horsID)
	{
		int serviceID;
		String[] serviceParts;//pieces of service info
		//dates of serice
		int serviceDay;
		int serviceMonth;
		int serviceYear;

		String dateofService;

		String returned="";
		//get services for each horse
		ArrayList <String> serviceString = new ArrayList <String> (); 
		try
		{
			m.connect();
			serviceString = m.searchServices(-1, horsID, -1, -1, -1, "", -1, 1, 1, 1, 1, 1, 1, 1);
			//"serviceID,horseID,day,month,year,serviceName,serviceCost,"
			m.disconnect();
		}
		catch(Exception e)
		{
		
			e.printStackTrace();
		}
		//System.out.println("SERVICE STRING: "+serviceString);

		//for every service
		for (int j = 0; j < serviceString.size(); j++)
		{
			//get id and dates of the service
			serviceParts = (serviceString.get(j)).split(",");
			serviceID = Integer.parseInt(serviceParts[0]);
			serviceDay = Integer.parseInt(serviceParts[2]);
			serviceMonth = Integer.parseInt(serviceParts[3]);
			serviceYear = Integer.parseInt(serviceParts[4]);
				//if in days invoiced
			if (isWithinRange(serviceMonth, serviceDay, serviceYear))
			{
				dateofService = serviceMonth+"/"+serviceDay+"/"+serviceYear;
				String serviceName = serviceParts[5];
				//get cost of service
				double servInt = Double.parseDouble(serviceParts[6]);
				horseTotal = horseTotal + servInt;
				returned = returned+"    "+dateofService+"\t"+serviceName+":\t\t"+getServiceDescriptions(serviceName) +"\t"+servInt+"0\r\n";
			}
		}
		return returned;

	}


	/*
	*get the descriptions for each service
	*
	*/
	public String getServiceDescriptions(String sn)
	{
		//get services for each horse
		ArrayList <String> typeString = new ArrayList <String> (); 
		try
		{
			m.connect();
			typeString = m.searchTServeALL(sn, "", -1, 1, 1, 1);
			//"servicename,serviceDescription,price,"
			m.disconnect();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//System.out.println("TYPE STRING: "+typeString);
		String[] typeParts = (typeString.get(0)).split(",");
		return typeParts[1]+"\t\t\t\t";
	}


	/*
	*check if a date is within invoice range
	*
	*/
	public boolean isWithinRange(int month, int day, int year)
	{
		
		Calendar first = Calendar.getInstance();
		Calendar last = Calendar.getInstance();
		Calendar check = Calendar.getInstance();

		String [] firstParts = dateStart.split("/");
		String [] lastParts = dateEnd.split("/");



		first.set(Integer.parseInt(firstParts[2]), Integer.parseInt(firstParts[0]), Integer.parseInt(firstParts[1]));
		last.set(Integer.parseInt(lastParts[2]), Integer.parseInt(lastParts[0]), Integer.parseInt(lastParts[1]));
		check.set(year, month, day);
		
		
		boolean between = !(check.before(first) || check.after(last));
		//System.out.println("total invoice days boolean: "+between);
		return between;


	}


	/*
	*	To see which date is most recent, date entered or invoice date
	*	@peram: date to be tested
	*	@return: most recent date, date entered or START
	*/
	public String firstDateStart(int month, int day, int year)
	{

		//if date is after start date of invoice
		if (isWithinRange(month, day, year))
		{
			return month+"/"+day+"/"+year;
		}

		//if date is before date of invoice
		else
		{
			return dateStart;
		}
	}


	/*
	*	to see if date is before end date
	*
	*/
	public String firstDateEnd(int month, int day, int year)
	{

		//if date is after start date of invoice
		if (isWithinRange(month, day, year))
		{
			return month+"/"+day+"/"+year;
		}

		//if date is before date of invoice
		else
		{
			return dateEnd;
		}


	}


	/*
	*get the number of days between two dates
	*@peram: earlier(dd, mm, yyyy), later(dd, mm, yyyy)
	*@return: number of days between earlier and later dates
	*/
	public int getNumDays(int day1, int month1, int year1, int day2, int month2, int year2)
	{

		Calendar a = Calendar.getInstance();
		Calendar b = Calendar.getInstance();
		a.set(year1, month1, day1);
		b.set(year2, month2, day2);


		long DAY_IN_MILLIS = 1000 * 60 * 60 * 24;

		int diffInDays = (int) ((b.getTimeInMillis() - a.getTimeInMillis())/ DAY_IN_MILLIS );
		//System.out.println("Days: "+diffInDays);

		return diffInDays;

	}



	public static void main(String [] args)
	{

		invoice test = new invoice("VanNoppen", "Jean", "12/9/2015", "1/20/2016", 5); 
	}


}

