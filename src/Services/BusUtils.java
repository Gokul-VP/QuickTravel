package Services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Database.QuickTravelApi;
import Model.Bus;
import Model.Seat;

public class BusUtils {
	
	private static Map<Integer,Seat> seatsInfo = null;
	private static int busId;
	static 
	{
		 try {
			 ResultSet rs = QuickTravelApi.getLastRowBusID();
				if(rs.next())
				{
					busId = rs.getInt("BusID");
				}
				else
				{
					busId = 1000;	
				}
						 
			//	getAllBuseInfo();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	
	public static ArrayList<Bus> searchBuses(String startingPoint,String dropPoint) throws SQLException
	{
	   ResultSet rs = QuickTravelApi.searchBuses(startingPoint, dropPoint);
	   ArrayList<Bus> relatedBuses = new ArrayList<Bus>();
	   while(rs.next())
	   {
		   relatedBuses.add(new Bus(rs.getInt("BusID"),rs.getString("BusOperator"),rs.getString("BusType"),rs.getString("BoardingPoint"),rs.getString("DropPoint"),
					rs.getString("Time"),rs.getInt("Price")));
	   }
	   return relatedBuses;
	}
	
	
	
	public	static void addBus(Bus bus,Seat seat) throws SQLException
	{
		seatsInfo = new HashMap<Integer, Seat>();
		if(QuickTravelApi.addBus(bus))
		{
			seatsInfo.put(busId, seat);
			if(QuickTravelApi.addSeats(busId, seat))
				System.out.println("Seat Info Added");
		
			if(QuickTravelApi.addSeatNumber(busId,seat.getSeatNumbers(),seat.getSeatStatus()))
				System.out.println("Seat nmbers added");
			
			System.out.println("Bus data successfully Created");
		}	
		else
			System.out.println("Enter data properly");
	}
	
	
	public static void removeBus(int busId) throws SQLException
	{
		if(QuickTravelApi.removeBus(busId,TravelsUtils.getTravels().getOperatorName()))
		{	
			QuickTravelApi.removeBusSeat(busId);
			QuickTravelApi.removeBusSeatStatus(busId);
			System.out.println("---->Done");
		}	
		else
			System.out.println("Enter id was wrong");
	}
	
	public static int getBusID()
	{
		return ++busId;
	}

	
	public static Bus getBus(int busId) throws SQLException {
		ResultSet rs = QuickTravelApi.getBus(busId);
		Bus bus =null;
		if(rs.next())
			bus = new Bus(rs.getInt("BusID"),rs.getString("BusOperator"),rs.getString("BusType"),rs.getString("BoardingPoint"),rs.getString("DropPoint"),
					rs.getString("Time"),rs.getInt("Price"));
		
		return bus;
	}
	
	
	public static String checkSeatIsAvailable(int busId,String seatNumber) throws SQLException
	{
		String result = QuickTravelApi.checkSeatStatus(busId,seatNumber);
		return result;
	}
	

	
	
    public static void showSeats(int selectedBusId) throws SQLException
    {
    	for(Map.Entry<Integer, Seat>   bs :getBusSeat(selectedBusId).entrySet())
        {
       	 Seat s = ((Seat)bs.getValue());
       	 
       	 System.out.println(s.toString());
       	 System.out.println();
       	 
       	 if(s.getSeatLayout() == 1)
       	 {
       		 String[] seatNumbers = s.getSeatNumbers();
       		 String [] seatStatus = s.getSeatStatus();
       		 int k=0;
       		 for(int i=0;i<9;i++)
       		 {
       			 for(int j=0;j<4;j++)
       			 { 
       				if(j==2)
     					 System.out.print("\t");
       				 System.out.print(seatNumbers[k]+"="+seatStatus[k]+"\t");
       				 k++;
       			 }
       			 System.out.println();
       		 }
       	 }
       	 
       	 else if(s.getSeatLayout() == 2)
      	 {
      		 String[] seatNumbers = s.getSeatNumbers();
      		 String [] seatStatus = s.getSeatStatus();
      		 int k=0;
      		 for(int i=0;i<11;i++)
      		 {
      			 for(int j=0;j<4;j++)
      			 { 
      				 if(j==2)
      					 System.out.print("\t\t");
      				 System.out.print(seatNumbers[k]+"="+seatStatus[k]+"\t");
      				 k++;
      			 }
      			 System.out.println();
      		 }
      		 for(int i=0;i<5;i++)
      		 {
  				 System.out.print(seatNumbers[k]+"="+seatStatus[k]+"\t");
  				 k++;
      		 }	 
      	 }
       	 
       	else if(s.getSeatLayout() == 3)
     	 {
     		 String[] seatNumbers = s.getSeatNumbers();
     		 String [] seatStatus = s.getSeatStatus();
     		 int k=0;
     		 for(int i=0;i<3;i++)
     		 {
     			if(i==1)
					 System.out.print("\t\t\t\t");
 				 System.out.print(seatNumbers[k]+"="+seatStatus[k]+"\t");
 				 k++;

     		 }
     		System.out.println();	 
     		 for(int i=0;i<8;i++)
     		 {
     			 for(int j=0;j<4;j++)
     			 { 
     				 if(j==2)
     					 System.out.print("\t\t");
     				 System.out.print(seatNumbers[k]+"="+seatStatus[k]+"\t");
     				 k++;
     			 }
     			 System.out.println();
     		 }
     		 for(int i=0;i<5;i++)
     		 {
 				 System.out.print(seatNumbers[k]+"="+seatStatus[k]+"\t");
 				 k++;
     		 }	 
     	 }
       	 
       	else if(s.getSeatLayout() == 4)
     	 {
     		 String[] seatNumbers = s.getSeatNumbers();
     		 String [] seatStatus = s.getSeatStatus();
     		 int k=0;
     		 
     		 System.out.println("Lower Seats");
     		 for(int i=0;i<6;i++)
     		 {
     			 for(int j=0;j<3;j++)
     			 { 
     				 if(j==1)
     					 System.out.print("\t");
     				 System.out.print(seatNumbers[k]+"="+seatStatus[k]+"\t");
     				 k++;
     			 }
     			 System.out.println();
     		 }
     		 
     		System.out.println("Upper Seats");
    		 for(int i=0;i<6;i++)
    		 {
    			 for(int j=0;j<3;j++)
    			 { 
    				 if(j==1)
    					 System.out.print("\t");
    				 System.out.print(seatNumbers[k]+"="+seatStatus[k]+"\t");
    				 k++;
    			 }
    			 System.out.println();
    		 }
     		 	 
     	 }
       	 
        }
    }
	
	
	public static Map<Integer, Seat> getBusSeat(int busId) throws SQLException {
		
		seatsInfo = new HashMap<Integer, Seat>();
		ResultSet rs1 = QuickTravelApi.getSeatInfo(busId);
		ResultSet rs2 = QuickTravelApi.getSeatStatus(busId);	
		
		if(rs1.next())
		{
			
			String seatNumbers[] = new String[rs1.getInt("TotalSeats")];
			String seatStatus[]  = new String[rs1.getInt("TotalSeats")];
			int i = 0;
			while(rs2.next())
			{
			  	 seatNumbers[i] = rs2.getString("SeatNumber");
			  	 seatStatus[i]  = rs2.getString("SeatStatus");
			  	 i++;
			}
			
			seatsInfo.put(busId, new Seat(rs1.getInt("SeatLayout"),rs1.getInt("TotalSeats"),rs1.getInt("AavailableSeats"),rs1.getInt("BookedSeats"),seatNumbers,seatStatus));
		}
			
		return seatsInfo;
	}



	
	
}
