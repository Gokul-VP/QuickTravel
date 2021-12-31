package Model;

public class Travels {
    private String operatorName;
    private String password;
    private String mobileNumber;
    private int earnings;
	
    
    
	public Travels(String operatorName, String password, String mobileNumber, int earnings) {
		super();
		this.operatorName = operatorName;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.earnings = earnings;
	}
	
	
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
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
	public int getEarnings() {
		return earnings;
	}
	public void setEarnings(int earnings) {
		this.earnings = earnings;
	}
    
     
  
}
