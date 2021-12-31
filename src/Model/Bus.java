package Model;

import Services.BusUtils;

public class Bus {

	private int busId;
	private String busOperator;
	private String busType;
	private String boardingPoint;
	private String dropPoint;
	private String time;
	private int price;
	public Bus(int busId, String busOperator, String busType, String boardingPoint, String dropPoint, String time,
			int price) {
		super();
		this.busId = busId;
		this.busOperator = busOperator;
		this.busType = busType;
		this.boardingPoint = boardingPoint;
		this.dropPoint = dropPoint;
		this.time = time;
		this.price = price;
	}
	public Bus(String busOperator, String busType, String boardingPoint, String dropPoint, String time, int price) {
		this.busId = BusUtils.getBusID();
		System.out.println(busId+" -------------");
		this.busOperator = busOperator;
		this.busType = busType;
		this.boardingPoint = boardingPoint;
		this.dropPoint = dropPoint;
		this.time = time;
		this.price = price;
	}
	public int getBusId() {
		return busId;
	}
	public void setBusId(int busId) {
		this.busId = busId;
	}
	public String getBusOperator() {
		return busOperator;
	}
	public void setBusOperator(String busOperator) {
		this.busOperator = busOperator;
	}
	public String getBusType() {
		return busType;
	}
	public void setBusType(String busType) {
		this.busType = busType;
	}
	public String getBoardingPoint() {
		return boardingPoint;
	}
	public void setBoardingPoint(String boardingPoint) {
		this.boardingPoint = boardingPoint;
	}
	public String getDropPoint() {
		return dropPoint;
	}
	public void setDropPoint(String dropPoint) {
		this.dropPoint = dropPoint;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public String toString()
	{
		return "BusID:"+busId+
			"	BusOperator:"+busOperator+
			"	BusType:"+busType+
			"	BoadingPoint:"+boardingPoint+
			"	DropPoint:"+dropPoint+
			"	Time:"+time+
			"	Price:"+price;
	}
	
	public String loationInfo()
	{
		return 	"Start:"+boardingPoint+" Drop:"+dropPoint;
	}
}
