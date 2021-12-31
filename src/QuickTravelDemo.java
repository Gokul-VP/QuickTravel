import java.sql.SQLException;
import java.util.Scanner;

import Model.Bus;
import Model.Customer;
import Model.Seat;
import Model.Travels;
import Services.BookingUtils;
import Services.BusUtils;
import Services.CustomerUtils;
import Services.TravelsUtils;

public class QuickTravelDemo {

	
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) throws SQLException {
		
		System.out.println("Testing ");
		
		System.out.println("____________________________________________QuickTravelDemo__________________________________________");
		int option = 0;
		while(option!=3)
		{
			
			System.out.println("1.User");
			System.out.println("2.TravelsOperator");
			System.out.println("3.Exit");
		    option = sc.nextInt();
		    
		    if(option==1)
		    	customerView();
		    else if(option == 2)
		    	travelsOperatorView();
		    
		}
		
	}
	
	
	public static void customerView() throws SQLException
	{
		while(true)
		{
			System.out.println("1.LogIn");
			System.out.println("2.SignUp");
			System.out.println("3.Exit");
			int option = sc.nextInt();
			if(option==1)
			{
				if(logIn())
				{
					while(true)
					{
						System.out.println("1.Wallet");
						System.out.println("2.Booking");
						System.out.println("3.CancelBookings");
						System.out.println("4.Booking History");
						System.out.println("5.Exit");
						int selection = sc.nextInt();
						
						if(selection==1)
							WalletFunctions();
						
						else if(selection==2)
						{
							searchingBuses();
						}
						
						else if(selection==3)
						{
							cancelBooking();
						}
						else if(selection==4)
						{
							showBookingHistory();
						}
						else if (selection==5) 
						   break;	
						
					}	
				}
			 }
			else if(option==2)
				  signUp();
			else
				break;
		}	
	}
	
	private static void cancelBooking() throws SQLException {
		showBookingHistory();
		System.out.println("Enter TicketNumber: ");
		int ticketNumber = sc.nextInt();
		BookingUtils.cancelBooking(ticketNumber);
		
	}


	public static void showBookingHistory() throws SQLException
	{
		BookingUtils.showCustomerBookingHistory();
	}
	
	
   public static void booking(int selectedBusId) throws SQLException{
         
         System.out.println("select seat no:");
         String selectedSeatNumber = sc.next();
         
         switch(BusUtils.checkSeatIsAvailable(selectedBusId, selectedSeatNumber))
         {	 
         
         
         case "Available" :
        	 
		         if(CustomerUtils.checkCashAvailability(selectedBusId))
		         {
		        	 BookingUtils.addBooking(selectedBusId, selectedSeatNumber);
		         }
		         else
		         {
		        	 System.out.println("Your Wallet Amount is low to book this one");
		         } 
		         break;
         case "Booked" :
        	    System.out.println("Its already Booked Sorry try available Seets only");
        	    break;
         
         case "Wrong SeatNumber" :
        	 System.out.println("Enter seat number is wrong");
        	  break;
         }
     
         
	}
	
	
	public static void searchingBuses() throws SQLException {
		
		System.out.println("Boarding Point: ");
		String bpoint = sc.next();
		System.out.println("Drop Point: ");
		String dpoint = sc.next();
		boolean flag = true;
		while(flag)
		{	
			if(BusUtils.searchBuses(bpoint, dpoint).size()!=0)	
			{
				for(Bus b :  BusUtils.searchBuses(bpoint, dpoint))
			        {
			        	System.out.println(b.toString());
			        }
			        
			        System.out.println("Enter bus id to see seats");
			        int selectedBusID =sc.nextInt();
			        if(BusUtils.getBus(selectedBusID)!=null)
			        {
			        	BusUtils.showSeats(selectedBusID);
			        	System.out.println("If you want to book press 1 or exit Press 2");
			        	int selection = sc.nextInt();
			        	if(selection==1)
			        	{
			        		booking(selectedBusID);
			        	}
			        	
			        }
			        else
			        {
			        	System.out.println("Entered Id was Wrong Try again");
			        	continue;
			        }
			        
			        System.out.println("Exit Press 1 or Book Again Press 2" );
			        int option = sc.nextInt();
			         if(option==1)
			        	 flag = false;
			}
			else
			{
				System.out.println("Sorry there are no Buses availabe Now");
				flag = false;
			}
		}    
	}
	
	
	
	public static void WalletFunctions() throws SQLException {
		while(true)
		{	
			System.out.println("AvailableCash : "+CustomerUtils.showAvailableCash());
			System.out.println();
			System.out.println("Press 1: Add Cash               Press 2 : Exit");
			int selection = sc.nextInt();
			if(selection==1)
			{
				System.out.println("Enter how much you want to add : ");
			    if(CustomerUtils.addMoney(sc.nextInt()))
			    	System.out.println("----->Added");
			}   
			else
				break;
			    	
		}	
	}
	
	
	public static boolean logIn() throws SQLException {
		System.out.println("Name:");
		String name = sc.next();
		System.out.println("Password:");
		String password = sc.next();
		if(CustomerUtils.checkUserIsExist(name, password)!=-1)
		{
			System.out.println("Login Successfuly ");
			return true;
		}	
		else
		{
			System.out.println("invalid name or password or user doesn't exist");
			return false;
		}	
	}
	
	public static void signUp() throws SQLException
	{
		System.out.println("Enter your name:");
		String name = sc.next();
		System.out.println("Password:");
		String pass = sc.next();
		System.out.println("Mobile NUmber:");
		String mobileNumber = sc.next();
        CustomerUtils.addCustomer(new Customer(name,pass,mobileNumber));
	}
	
	
	
	
	
	//----------------------------------------------------------------------------------
	
	public static void travelsOperatorView() throws SQLException
	{
		while(true)
		{
			System.out.println("1.LogIn");
			System.out.println("2.SignUp");
			System.out.println("3.Exit");
			int option = sc.nextInt();
			if(option==1)
			{
				if(operatorLogIn())
				{
					while(true)
					{
						System.out.println("1.Earnings");
						System.out.println("2.AddBus");
						System.out.println("3.RemoveBus");
						System.out.println("4.ShowBuses");
						System.out.println("5.Booking History");
						System.out.println("6.Exit");
						int selection = sc.nextInt();
						
						if(selection==1)
							earningsTotalEarnings();
						else if(selection==2)
							addBus();
						else if(selection==3)
							removeBus();
						else if(selection==4)
							showBuses();
						else if(selection==5)
							bookingDetails();
						else if (selection==6) 
						   break;	
						
					}	
				}
			 }
			else if(option==2)
				operatorSignUp();
			else
				break;
		}		
	}
	
	
	
	private static void bookingDetails() throws SQLException {
		while(true)
        {
			if(TravelsUtils.showTravelBuses()==1)
			{
				System.out.println("Enter BusID:");
	        	int busId = sc.nextInt();
	        	BookingUtils.getBusBookingDetails(busId);
	        	System.out.println();
		        System.out.println("-->Exit Press 1 -->Contiue Press 2 ");
		       int  option = sc.nextInt();
		        if(option == 1)
		        	break;
			}
	        
			else
				break;
        }	
	}


	private static void removeBus() throws SQLException {
		while(true)
        {
			if(TravelsUtils.showTravelBuses()==1)
			{
				System.out.println("Enter BusID:");
	        	int busId = sc.nextInt();
	        	BusUtils.removeBus(busId);
	        	System.out.println();
		        System.out.println("-->Exit Press 1 -->Contiue Press 2 ");
		       int  option = sc.nextInt();
		        if(option == 1)
		        	break;
			}
	        
			else
				break;
        }	
	}


	private static void showBuses() throws SQLException {
        while(true)
        {
			if(TravelsUtils.showTravelBuses()==1)
			{	
	        
		        System.out.println("If you want to See Seating Layout ---> Press 1 or Exit Press 2 ");
		        int option = sc.nextInt();
		        if(option==1)
		        {
		        	System.out.println("Enter BusID:");
		        	int busId = sc.nextInt();
		        	BusUtils.showSeats(busId);
		        	System.out.println("\n\n");
		        }
		        
		        System.out.println("-->Exit Press 1 -->Contiue Press 2 ");
		        option = sc.nextInt();
		        if(option == 1)
		        	break;
			} 
			else
				break;
        }
	}


	private static void earningsTotalEarnings() {
               
		System.out.println("Total Earnings: "+TravelsUtils.getTravels().getEarnings());
		
	}


	public static boolean operatorLogIn() throws SQLException {
		System.out.println("TravelsName:");
		String name = sc.next();
		System.out.println("Password:");
		String password = sc.next();
		if(TravelsUtils.checkTravelsIsExist(name, password)!=-1)
		{
			System.out.println("Login Successfuly ");
			return true;
		}	
		else
		{
			System.out.println("invalid name or password or user doesn't exist");
			return false;
		}	
	}
	
	public static void operatorSignUp() throws SQLException
	{
		
	  while(true)
	  {  
		System.out.println("Enter your Travels Name:");
		String name = sc.next();
			if(TravelsUtils.checkTavelsName(name)==true)
			{	
			 System.out.println("---->Allready Exist try different one");
			 continue;
			}
		
			System.out.println("Password:");
			String pass = sc.next();
			System.out.println("Mobile NUmber:");
			String mobileNumber = sc.next();
			Travels travels = new Travels(name,pass,mobileNumber,0);
	        if(TravelsUtils.addTravels(travels)==1)
	        {	
	        	System.out.println("--->Added");
	        	break;
	        }
	        	 
		 
	  }    
        	
	}
	
	private static void addBus() throws SQLException {
           
		String operator = TravelsUtils.getTravels().getOperatorName();
		System.out.println("Bus Type:");
		String type = sc.next();
		System.out.println("boardingPoint:");
		String boardingPoint = sc.next();
		System.out.println("dropPoint:");
		String dropPoint = sc.next();
		System.out.println("Time:");
		String time = sc.next();
		System.out.println("price:");
		int price = sc.nextInt();
		Bus b = new Bus(operator,type,boardingPoint,dropPoint,time,price);
		
		if(type.equals("Semi-Sleeper"))
		{
			System.out.println("Layout Model1 : 9rows  4columns  total = 36 Seats");
			System.out.println("Layout Model2 : 12rows 4columns Last row has 1 seat Extra total = 49 Seats");
			System.out.println("Layout Model3 : 10rows 4columns FirstRow has only 3 seats Last row has 1 seat Extra total = 40 Seats");
			System.out.println();
			System.out.println("Enter Which model you want");
			int layout = sc.nextInt();
			setSeatStructure(layout, b);
		}
		else if(type.equals("Sleeper"))
		{
			System.out.println("Layout Model4 : upper and lower has 6rows  3columns  total = 36 Seats");
			System.out.println();
			System.out.println("Enter Which model you want");
			int layout = sc.nextInt();
			setSeatStructure(layout, b);
		}
		
	}


	public static void setSeatStructure(int seatLayout,Bus b) throws SQLException
	{
	  
		if(seatLayout == 1)
		{	
			System.out.println("Enter seat number from right to left");
			String seatNumbers[] = new String[36];
			String seatStatus[] =  new String[36];
			
			for(int i=0;i<36;i++)
			{
				seatNumbers[i] = "A"+i+1;
				seatStatus[i] = "Available";
				
			}
		    
			BusUtils.addBus(b,new Seat(seatLayout, 36, 36, 0, seatNumbers, seatStatus));
		}
		
		else if(seatLayout == 2)
		{
			System.out.println("Enter seat number from right to left");
			String seatNumbers[] = new String[49];
			String seatStatus[] =  new String[49];
			
			for(int i=0;i<49;i++)
			{
				seatNumbers[i] = "B"+i+1;
				seatStatus[i] = "Available";
				
			}
		    
			
			BusUtils.addBus(b, new Seat(seatLayout, 49, 49, 0, seatNumbers, seatStatus));
		}
		
		else if(seatLayout == 3)
		{
			System.out.println("Enter seat number from right to left");
			String seatNumbers[] = new String[40];
			String seatStatus[] =  new String[40];
			
			for(int i=0;i<40;i++)
			{
				seatNumbers[i] = "C"+i+1;
				seatStatus[i] = "Available";
				
			}
		    
			
			BusUtils.addBus(b, new Seat(seatLayout, 40, 40, 0, seatNumbers, seatStatus));
		}
		
		else if(seatLayout == 4)
		{
			String seatNumbers[] = new String[36];
			String seatStatus[] =  new String[36];
			int k=0;
			System.out.println("Enter seat number from right to left for lower layer");
			for(int i=0;i<18;i++)
			{
				seatNumbers[k] = "L"+i+1;
				seatStatus[k] = "Available";
				k++;
			}
			System.out.println("Enter seat number from right to left for upper layer");
			for(int i=0;i<18;i++)
			{
				seatNumbers[k] = "U"+i+1;
				seatStatus[k] = "Available";
				k++;
			}
	
			BusUtils.addBus(b, new Seat(seatLayout, 36, 36, 0, seatNumbers, seatStatus));
		}
	}
	
	
}
