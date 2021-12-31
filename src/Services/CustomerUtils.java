package Services;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import Database.QuickTravelApi;
import Model.Customer;
import Model.Wallet;

public class CustomerUtils {
	
 	private static Customer customer; 
    private static int customerId;
	private static Map<Integer,Wallet> customer_Wallet = new HashMap<Integer, Wallet>();
	
	static 
	{
		  try {
			   ResultSet rs = QuickTravelApi.getLastRowCustomerID();
						if(rs.next())
							customerId = rs.getInt("CustomerID");
						else
						   customerId = 100;	

				} catch (SQLException e) {
					e.printStackTrace();
				}
	}
	
	public static Customer getCustomer() {
		return customer;
	}
	
	
	
	public	static void addCustomer(Customer customer) throws SQLException
	{
		if(QuickTravelApi.addCustomer(customer))
		{
			QuickTravelApi.createWallet(customer.getCustomerId());
		}	
		else
			System.out.println("Enter data properly");
	}
	
	public static int checkUserIsExist(String name,String password) throws SQLException
	{
		System.out.println("User: "+name+" | pwd: "+password);
		
		ResultSet rs = QuickTravelApi.checkCustomerIsExsit(name, password);
		if(rs.next())
		{
			customer = new Customer(rs.getInt("CustomerID"), rs.getString("Name"), rs.getString("Password"), rs.getString("MobileNumber"));
			getCustomerWallet();
			return 1;
		}
	
		return -1;
	}

	private static void getCustomerWallet() throws SQLException {
		
	    ResultSet rs = QuickTravelApi.getWallet(customer.getCustomerId());
		if(rs.next())
		{
			customer_Wallet.put(customer.getCustomerId(), new Wallet(rs.getInt("AvailableCash")));
		}
		
		
	}
	
  
   public static boolean addMoney(int amount) throws SQLException
	{
		customer_Wallet.get(customer.getCustomerId()).addCash(amount);
		if(QuickTravelApi.updateWallet(customer.getCustomerId(), customer_Wallet.get(customer.getCustomerId()).getAvailableCash()))
			return true;
		return false;
	}
	
   public static int showAvailableCash() {
	  return customer_Wallet.get(customer.getCustomerId()).getAvailableCash();
  }
	
	public static boolean updateCustomerCash(int fare) throws SQLException
	{
		int res = customer_Wallet.get(customer.getCustomerId()).updateAvailableCash(fare);;
		customer_Wallet.get(customer.getCustomerId()).setAvailableCash(res);
		QuickTravelApi.updateWallet(customer.getCustomerId(), res);
		
		return true;
	}
	
	
	public static boolean checkCashAvailability(int busId) throws SQLException
	{
	   if(showAvailableCash()>BusUtils.getBus(busId).getPrice())
		   return true;
	   
	   return false;
	}
   
   public static int getCustomerID()
	{
		return ++customerId;
	}



	
	
}
