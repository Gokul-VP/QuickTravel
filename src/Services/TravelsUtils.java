package Services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Database.QuickTravelApi;
import Model.Bus;
import Model.Travels;

public class TravelsUtils {
	
	private static Travels travels;
	
	public	static int addTravels(Travels travels) throws SQLException
	{
	   if(QuickTravelApi.addTravels(travels))
		   return 1;
	return 0;
	
		   
	}
	
	public static int checkTravelsIsExist(String name,String password) throws SQLException
	{
		
		ResultSet rs = QuickTravelApi.checkTravelsIsExsit(name, password);
		if(rs.next())
		{
			travels = new Travels(rs.getString("OperatorName"), rs.getString("Password"), rs.getString("MobileNumber"),rs.getInt("Earnings"));
			return 1;
		}
	
		return -1;
	}
	
	public static boolean checkTavelsName(String name) throws SQLException
	{
		return (QuickTravelApi.checkTravelsIsAllreadyExsit(name)==1)?true:false;
		 
		 
	}

	public static boolean updateEarnings(String operatorName,int amount) throws SQLException
	{
		int res =  QuickTravelApi.getTravelsCurrentEarnings(operatorName)+amount;
		 return QuickTravelApi.updateTravelsEarnings(operatorName, res);
		
	}
	
	public static boolean returnBookingAmount(String operatorName,int amount) throws SQLException
	{
		int res =  QuickTravelApi.getTravelsCurrentEarnings(operatorName)-amount;
		 return QuickTravelApi.updateTravelsEarnings(operatorName, res);
	}
	
	public static int showTravelBuses() throws SQLException
	{
	  List<Bus> buses =	getAllTravelsBuses();
	  if(buses.size()>0)
	  {  
		for(Bus b: buses)
			System.out.println(b.toString());
	  }
	  else
	  {
		  System.out.println("--> No buses go to menu add bus");
		  return 0;
	  }	  
	  return 1;
	}
	
	
	private static List<Bus> getAllTravelsBuses() throws SQLException
	{
		List<Bus> relatedBuses = new ArrayList<Bus>();
		ResultSet rs = QuickTravelApi.getTravelsBuses(travels.getOperatorName());
		while(rs.next())
		{
			relatedBuses.add(new Bus(rs.getInt("BusID"),rs.getString("BusOperator"),rs.getString("BusType"),rs.getString("BoardingPoint"),rs.getString("DropPoint"),
					rs.getString("Time"),rs.getInt("Price")));
		}
		
		return relatedBuses;
	}
	
	

	public static Travels getTravels() {
		return travels;
	}
	
	

}
