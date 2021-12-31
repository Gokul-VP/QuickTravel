package Model;

import Services.CustomerUtils;

public class Customer {
       private int customerId;
       private String name;
       private String password;
       private String mobileNumber;
       
       
       
	public Customer(int customerId, String name, String password, String mobileNumber) {
		this(name,password,mobileNumber);
		this.customerId = customerId;
		this.name = name;
		this.password = password;
		this.mobileNumber = mobileNumber;
	}
	public Customer(String name, String password, String mobileNumber) {
		this.customerId = CustomerUtils.getCustomerID();
		this.name = name;
		this.password = password;
		this.mobileNumber = mobileNumber;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	@Override
	public String toString() {
		return "name:" + name + " mobileNumber:" + mobileNumber;
	}
       
       
	
}
