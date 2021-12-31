package Model;

import Services.BookingUtils;

public class Booking {
	
	private int ticketNumber;
	private int fare;
	private String seatNumber;
	
	private int customerId;
	private String customerInfo;
	
	private int busId;
	private String operatorName;
	private String locationInfo;
	private String busType;
	private String time;
		
	public Booking(int ticketNumber, int fare, String seatNumber, int customerId, String customerInfo, int busId,
			String operatorName, String locationInfo, String busType, String time) {
		super();
		this.ticketNumber = ticketNumber;
		this.fare = fare;
		this.seatNumber = seatNumber;
		this.customerId = customerId;
		this.customerInfo = customerInfo;
		this.busId = busId;
		this.operatorName = operatorName;
		this.locationInfo = locationInfo;
		this.busType = busType;
		this.time = time;
	}
	public Booking(int fare, String seatNumber, int customerId, String customerInfo, int busId,
			String operatorName, String locationInfo, String busType, String time) {
		
		this.ticketNumber = BookingUtils.getTicketNumber();
		this.fare = fare;
		this.seatNumber = seatNumber;
		this.customerId = customerId;
		this.customerInfo = customerInfo;
		this.busId = busId;
		this.operatorName = operatorName;
		this.locationInfo = locationInfo;
		this.busType = busType;
		this.time = time;
	}
	public int getTicketNumber() {
		return ticketNumber;
	}
	public void setTicketNumber(int ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	public int getFare() {
		return fare;
	}
	public void setFare(int fare) {
		this.fare = fare;
	}
	public String getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerInfo() {
		return customerInfo;
	}
	public void setCustomerInfo(String customerInfo) {
		this.customerInfo = customerInfo;
	}
	public int getBusId() {
		return busId;
	}
	public void setBusId(int busId) {
		this.busId = busId;
	}
	public String getOperatorName() {
		return operatorName;
	}
	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
	public String getLocationInfo() {
		return locationInfo;
	}
	public void setLocationInfo(String locationInfo) {
		this.locationInfo = locationInfo;
	}
	public String getBusType() {
		return busType;
	}
	public void setBusType(String busType) {
		this.busType = busType;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
    
    
    public String forCustomerView()
    {
    	return "TicketNumber:"+ticketNumber+
    			"	SeatNumber:"+seatNumber+
    			"	Fare:"+fare+
    			"	OperatorName:"+operatorName+
    			"	BusType:"+busType+
    			"	Time:"+time+
    			"	BoardingPoint & Destination:"+locationInfo;
    }
    
    public String forTravelsOperatorView()
    {
    	return "TicketNumber:"+ticketNumber+
    			"   BusID:"+busId+
    			"	SeatNumber:"+seatNumber+
    			"	Fare:"+fare+
    			"	CustomerInfo:"+customerInfo+
    			"	BusType:"+busType+
    			"	Time:"+time+
    			"	BoardingPoint & Destination:"+locationInfo;
    }
}
