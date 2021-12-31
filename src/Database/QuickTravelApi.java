package Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.Booking;
import Model.Bus;
import Model.Customer;
import Model.Seat;
import Model.Travels;

public class QuickTravelApi {
	
	private static String url="jdbc:mysql://localhost:3306/QuickTravel?useSSL=false";
    private static String username="root";
    private static String password="";
    private static Connection connection = null;
    private static PreparedStatement pst= null;
    private static ResultSet rs = null;
    private static String query;
    private static Statement st;
    
    static 
    {
    	try {
			    Class.forName("com.mysql.jdbc.Driver");
		    	connection = DriverManager.getConnection(url, username, password);
		    	st=connection.createStatement();
		    	
		      	createTable("Customers", "CustomerID int PRIMARY KEY,Name varchar(20),Password varchar(20),MobileNumber varchar(15)");
		      	createTable("Buses","BusID int PRIMARY KEY,BusOperator varchar(30),BusType varchar(30),BoardingPoint varchar(20),DropPoint varchar(20),Time varchar(20),Price int");
				createTable("Wallet", "CustomerID int,AvailableCash int");
				createTable("Seat", "BusID int,SeatLayout int,TotalSeats int,AavailableSeats int,BookedSeats int");
		    	createTable("SeatStatus", "BusID int,SeatNumber varchar(5),SeatStatus varchar(20)");
			    createTable("Travels", "OperatorName varchar(20) PRIMARY KEY,Password varchar(20),MobileNumber varchar(15),Earnings int");
                createTable("BookingRecords", "TicketNumber int,Fare int,SeatNumber varchar(5),CustomerID int,CustomerInfo varchar(255),BusID int,OperatorName varchar(20),\"\n"
                		+ "						+ \"LocationInfo varchar(255),BusType varchar(15),Time varchar(15)");
			 
			
		} catch (ClassNotFoundException e) {
			System.out.println("Check the driver");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Check the connection and query");
			e.printStackTrace();
		}  
		  
    }
    
    private static void createTable(String tableName,String members)
    {
    	query = "CREATE TABLE IF NOT EXISTS "+tableName+"("+members+")";
    	try {
			pst = connection.prepareStatement(query);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    

    private static void inserIntoTable(String tableName,String variable,String values)
    {
    	query = "insert into "+tableName+"("+variable+") values ("+values+")";
        try {
			st.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("check insert Query");
			e.printStackTrace();
		}
    }
    
    public static boolean addCustomer(Customer customer) throws SQLException
    {
    	query = "insert into Customers(CustomerID,Name,Password,MobileNumber) values ('"+customer.getCustomerId()+"','"+customer.getName()+"',"
    			+ "'"+customer.getPassword()+"','"+customer.getMobileNumber()+"')";
    	
        int result = st.executeUpdate(query);
		return (result>0)?true:false;
    }
    
    
    public static ResultSet checkCustomerIsExsit(String name,String password) throws SQLException
    {
		query = "SELECT * FROM Customers where Name = '"+name+"' and Password = '"+password+"' ";
		pst = connection.prepareStatement(query);
		rs = pst.executeQuery();
		return rs;
    }

    public static void createWallet(int customerID) throws SQLException
    {
    	query = "insert into Wallet(CustomerID,AvailableCash) values ('"+customerID+"','"+0+"')";
        st.executeUpdate(query);
    }
    
    public static ResultSet getWallet(int customerID) throws SQLException
    {
    	query = "SELECT * FROM Wallet where CustomerID="+customerID+"";
    	pst = connection.prepareStatement(query);
		rs = pst.executeQuery();
    	return rs;
    }
    public static boolean updateWallet(int customerID,int amount) throws SQLException
    {
    	query = "UPDATE Wallet set AvailableCash="+amount+" where CustomerID="+customerID+"";
    	int result = st.executeUpdate(query);
    	return (result>0)?true:false;
    }

    //----------------------------------------------bus----------------------------------------------------------
    public static boolean addBus(Bus b) throws SQLException
    {
    	query = "insert into Buses(BusID,BusOperator,BoardingPoint,DropPoint,BusType,Time,Price) values ('"+b.getBusId()+"','"+b.getBusOperator()+"',"
    			+ "'"+b.getBoardingPoint()+"','"+b.getDropPoint()+"','"+b.getBusType()+"','"+b.getTime()+"','"+b.getPrice()+"')";
    	
        int result = st.executeUpdate(query);
		return (result>0)?true:false;
    }
    
    public static boolean removeBusSeat(int busId) throws SQLException {
		query = "delete from Seat where BusID = "+busId+" ";
		int result = st.executeUpdate(query);
    	return (result>0)?true:false;
    				
	}
    
    public static boolean removeBusSeatStatus(int busId) throws SQLException {
		query = "delete from Buses where BusID = "+busId+" ";
		int result = st.executeUpdate(query);
    	return (result>0)?true:false;
    				
	}
    
    public static boolean removeBus(int busId,String operatorName) throws SQLException {
		query = "delete from Buses where BusID = "+busId+" and BusOperator = '"+operatorName+"' ";
		int result = st.executeUpdate(query);
    	return (result>0)?true:false;
    				
	}
    
    
    public static ResultSet getBus(int busId) throws SQLException
    {
		query = "SELECT * FROM Buses where BusID ="+busId+"";
		pst = connection.prepareStatement(query);
		rs = pst.executeQuery();
		return rs;
    }
    
    public static boolean addSeats(int busId,Seat s) throws SQLException
    {
    	query = "insert into Seat(BusID,SeatLayout,TotalSeats,AavailableSeats,BookedSeats) values ('"+busId+"',"
    			+ "'"+s.getSeatLayout()+"','"+s.getTotalSeats()+"','"+s.getAvailableSeats()+"','"+s.getTotalBookedSeats()+"')";
    	
        int result = st.executeUpdate(query);
		return (result>0)?true:false;
    }
    public static boolean addSeatNumber(int busId,String[] seatNumbers,String[] seatStatus) throws SQLException
    {
    	int result=0;
    	for(int i=0;i<seatNumbers.length;i++)
    	{
	    	query = "insert into SeatStatus(BusID,SeatNumber,SeatStatus) values ('"+busId+"','"+seatNumbers[i]+"','"+seatStatus[i]+"')";  	
	    	result = st.executeUpdate(query);
    	}
    	return (result>0)?true:false;
    }

    public static  ResultSet getSeatInfo(int busId) throws SQLException {
    	query = "select * from Seat where  BusID ="+busId+" ";
		pst = connection.prepareStatement(query);
		rs = pst.executeQuery();
		return rs;
    }

    public static  ResultSet getSeatStatus(int busId) throws SQLException {
    	query = "select * from SeatStatus where  BusID ="+busId+" ";
		pst = connection.prepareStatement(query);
		rs = pst.executeQuery();
		return rs;
    }
    
    public static boolean updateSeatStatusAsBooked(int busId,String seatNumber) throws SQLException
    {
    	query = "UPDATE SeatStatus set SeatStatus='Booked' where BusID ="+busId+" and SeatNumber = '"+seatNumber+"' ";
    	int result = st.executeUpdate(query);
    	return (result>0)?true:false;
    }
    
   
    
    public static boolean setSeatAsAvailable(int busId,String seatNumber) throws SQLException
    {
    	query = "UPDATE SeatStatus set SeatStatus='Available' where BusID ="+busId+" and SeatNumber = '"+seatNumber+"' ";
    	int result = st.executeUpdate(query);
    	return (result>0)?true:false;
    }
    
	public static ResultSet getLastRowBusID() throws SQLException {
		query = "select BusID from Buses order by BusID DESC LIMIT 1";
		pst = connection.prepareStatement(query);
		rs = pst.executeQuery();
		return rs;
	}
	
	public static ResultSet getLastRowCustomerID() throws SQLException {
		query = "select CustomerID from Customers order by CustomerID DESC LIMIT 1";
		pst = connection.prepareStatement(query);
		rs = pst.executeQuery();
		return rs;
	}
//-----------------------------------------------------------------Travels-----------------------------------------------------------------------	
	public static boolean addTravels(Travels t) throws SQLException
    {
    	query = "insert into Travels(OperatorName,Password,MobileNumber,Earnings) values ('"+t.getOperatorName()+"','"+t.getPassword()+"',"
    			+ "'"+t.getMobileNumber()+"','"+t.getEarnings()+"')";
    	
        int result = st.executeUpdate(query);
		return (result>0)?true:false;
    }
    
	public static ResultSet checkTravelsIsExsit(String name,String password) throws SQLException
    {
		query = "SELECT * FROM Travels where OperatorName = '"+name+"' and Password = '"+password+"' ";
		pst = connection.prepareStatement(query);
		rs = pst.executeQuery();
		return rs;
    }
	public static int checkTravelsIsAllreadyExsit(String name) throws SQLException
    {
		query = "SELECT * FROM Travels where OperatorName = '"+name+"' ";
		pst = connection.prepareStatement(query);
		rs = pst.executeQuery();
		if(rs.next())
			return 1;
		return 0;
    }
	
	public static boolean updateTravelsEarnings(String operatorName,int amount) throws SQLException
    {
    	query = "UPDATE Travels set Earnings="+amount+" where OperatorName ='"+operatorName+"'";
    	int result = st.executeUpdate(query);
    	return (result>0)?true:false;
    }
    
	public static ResultSet getTravels(int busId) throws SQLException
    {
		query = "SELECT * FROM Buses where BusID ="+busId+"";
		pst = connection.prepareStatement(query);
		rs = pst.executeQuery();
		return rs;
    }
    
	public static int getTravelsCurrentEarnings(String operatorName) throws SQLException
    {
		query = "SELECT Earnings FROM Travels where OperatorName ='"+operatorName+"' ";
		pst = connection.prepareStatement(query);
		rs = pst.executeQuery();
		if(rs.next())	
		 return rs.getInt("Earnings");
		return 0;
    }
	
	
	public static ResultSet getTravelsBuses(String operatorName) throws SQLException
	{
		query = "SELECT * FROM Buses where BusOperator ='"+operatorName+"' ";
		pst = connection.prepareStatement(query);
		rs = pst.executeQuery();
		return rs;
	}
	//---------------------------------------------------------Booking------------------------------------------------------

	public static ResultSet searchBuses(String boardingpoint,String droppoint) throws SQLException
    {
		query = "SELECT * FROM Buses where BoardingPoint = '"+boardingpoint+"' and DropPoint = '"+droppoint+"' ";
		pst = connection.prepareStatement(query);
		rs = pst.executeQuery();
		return rs;
    }

	public static ResultSet getLastRowTicketNumber() throws SQLException {
		query = "select TicketNumber from BookingRecords order by TicketNumber DESC LIMIT 1";
		pst = connection.prepareStatement(query);
		rs = pst.executeQuery();
		return rs;
	}
	

	public static boolean addbooking(Booking b) throws SQLException {
		
		query = "insert into BookingRecords(TicketNumber,Fare,SeatNumber,CustomerID,CustomerInfo,BusID,OperatorName,LocationInfo,BusType,Time) values ('"+b.getTicketNumber()+"',"
				+ "'"+b.getFare()+"','"+b.getSeatNumber()+"','"+b.getCustomerId()+"','"+b.getCustomerInfo()+"','"+b.getBusId()+"','"+b.getOperatorName()+"','"+b.getLocationInfo()+"',"
						+ "'"+b.getBusType()+"','"+b.getTime()+"')";
		int result = st.executeUpdate(query);
		return (result>0)?true:false;
	}

	public static ResultSet bookingInfo(int customerId) throws SQLException {
		query = "select * from BookingRecords where CustomerID = "+customerId+" ";
		pst = connection.prepareStatement(query);
		rs = pst.executeQuery();
		return rs;
	}
	public static ResultSet ticketInfo(int ticketNumber) throws SQLException {
		query = "select * from BookingRecords where TicketNumber = "+ticketNumber+" ";
		pst = connection.prepareStatement(query);
		rs = pst.executeQuery();
		return rs;
	}

	public static boolean updateSeatinfo(int busId) throws SQLException {
		query = "UPDATE Seat set AavailableSeats=AavailableSeats-1 , BookedSeats=BookedSeats+1 where BusID ="+busId+" ";
    	int result = st.executeUpdate(query);
    	return (result>0)?true:false;
		
	}

	 public static boolean cancelBookingSeatInfo(int busId) throws SQLException
	    {
		 query = "UPDATE Seat set AavailableSeats=AavailableSeats+1 , BookedSeats=BookedSeats-1 where BusID ="+busId+" ";
	    	int result = st.executeUpdate(query);
	    	return (result>0)?true:false;
	    }
	
	public static String checkSeatStatus(int busId, String seatNumber) throws SQLException {
		query = "select SeatStatus from SeatStatus where BusID = '"+busId+"' and SeatNumber = '"+seatNumber+"'  ";
		pst = connection.prepareStatement(query);
		rs = pst.executeQuery();
		if(rs.next())
			return rs.getString("SeatStatus");
		else return "Wrong SeatNumber";
		
		
	}


	public static boolean removebooking(int ticketNumber) throws SQLException {
		query = "delete from BookingRecords where TicketNumber = "+ticketNumber+" ";
		int result = st.executeUpdate(query);
    	return (result>0)?true:false;
    				
	}
	
	public static ResultSet getBusBookingDetails(int busId) throws SQLException
	{
		query = "select * from BookingRecords where BusID = "+busId+" ";
		pst = connection.prepareStatement(query);
		rs = pst.executeQuery();
		return rs;
	}
	
	
	}
