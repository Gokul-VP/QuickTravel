package Services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Database.QuickTravelApi;
import Model.Booking;
import Model.Bus;

public class BookingUtils {

	private static int ticketNumber;
	
	
	static
	{

		 try {
			 ResultSet rs = QuickTravelApi.getLastRowTicketNumber();
				if(rs.next())
				{
					ticketNumber = rs.getInt("TicketNumber");
				}
				else
				{
					ticketNumber = 1200100;
					
				}
						 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	
	
	public static void addBooking(int busId,String seatNo) throws SQLException
	{
		Bus b = BusUtils.getBus(busId);
		int fare = b.getPrice();
		
		
		
		if(CustomerUtils.updateCustomerCash(fare))
			System.out.println("------>Amount debited from your wallet");
		
		String operatorName = b.getBusOperator();
		if(TravelsUtils.updateEarnings(operatorName, fare))
			System.out.println("------>TravelsOperator got the Cash");
		
		QuickTravelApi.updateSeatStatusAsBooked(busId, seatNo);
	
		if(QuickTravelApi.updateSeatinfo(busId))
			System.out.println("------>Seat info updated");
		
		String cusInfo = CustomerUtils.getCustomer().toString();
		Booking booking = new Booking(fare,seatNo,CustomerUtils.getCustomer().getCustomerId(),cusInfo,busId,operatorName,b.loationInfo(),b.getBusType(),b.getTime());
		if(QuickTravelApi.addbooking(booking))
			System.out.println("Your Ticket Was Booked");
		

		
	}
	
	
	
	public static void cancelBooking(int ticketNumber) throws SQLException
	{
		Booking b = getTicketDetails(ticketNumber);
		
		if(TravelsUtils.returnBookingAmount( b.getOperatorName(), b.getFare()))
			System.out.println("------>Debit form operator");
		
	
		QuickTravelApi.setSeatAsAvailable(b.getBusId(), b.getSeatNumber());
	
		if(QuickTravelApi.cancelBookingSeatInfo(b.getBusId()))
			System.out.println("------>Seat info updated");
		
	
		if(QuickTravelApi.removebooking(b.getTicketNumber()))
			System.out.println("Your Ticket Was Cancelled ");
		
		if(CustomerUtils.addMoney(b.getFare()))
			System.out.println("------>Amount add to your wallet");
		
	}
	
	public static Booking getTicketDetails(int ticketNumber) throws SQLException
	{
		ResultSet rs = QuickTravelApi.ticketInfo(ticketNumber);
		if(rs.next())
			return new Booking(rs.getInt("TicketNumber"),rs.getInt("Fare"),rs.getString("SeatNumber"),rs.getInt("CustomerID"),rs.getString("CustomerInfo")
					,rs.getInt("BusID"),rs.getString("OperatorName"),rs.getString("LocationInfo"),rs.getString("BusType"),rs.getString("Time"));
		
		return null;
	}
	
	public static void showCustomerBookingHistory() throws SQLException
	{
		List<Booking>  records = getBookingRecords(1,CustomerUtils.getCustomer().getCustomerId());
		if(records.size()>0)
		{	
			for(Booking r : records)
				System.out.println(r.forCustomerView());
		}
		else
			System.out.println("No Records");
		
	}
	
	
	public static List<Booking> getBookingRecords(int userType,int id) throws SQLException
	{
		ResultSet rs = null;
		if(userType == 1)
			rs = QuickTravelApi.bookingInfo(id);
		else
			rs = QuickTravelApi.getBusBookingDetails(id);
		
		List<Booking> records = new ArrayList<Booking>();
		while(rs.next())
		{
			records.add(new Booking(rs.getInt("TicketNumber"),rs.getInt("Fare"),rs.getString("SeatNumber"),rs.getInt("CustomerID"),rs.getString("CustomerInfo")
					,rs.getInt("BusID"),rs.getString("OperatorName"),rs.getString("LocationInfo"),rs.getString("BusType"),rs.getString("Time")));
		}
		return records;
	}

	
	
	
   public static void getBusBookingDetails(int busId) throws SQLException {
		
	   List<Booking>  records = getBookingRecords(2,busId);
		if(records.size()>0)
		{	
			for(Booking r : records)
				System.out.println(r.forTravelsOperatorView());
		}
		else
			System.out.println("No Records");
	}
	
	public static int getTicketNumber()
	{
		return ++ticketNumber;
		
	}
}
