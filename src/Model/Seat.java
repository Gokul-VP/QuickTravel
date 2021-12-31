package Model;


public class Seat {
	
	private int seatLayout;
	private int totalSeats;
	private int availableSeats;
	private int totalBookedSeats;
	private String seatNumbers[];
	private String seatStatus[];
	
	
	public Seat(int seatLayout,int totalSeats,int availableSeats,int totalBookedSeats,String[] seatNumbers,String seatStatus[])
	{
		this.seatLayout = seatLayout;
		this.totalSeats = totalSeats;
		this.availableSeats = availableSeats;
		this.totalBookedSeats = totalBookedSeats;
		this.seatNumbers = seatNumbers;
		this.seatStatus = seatStatus;
	}
	
	
	
	
	public int getSeatLayout() {
		return seatLayout;
	}




	public void setSeatLayout(int seatLayout) {
		this.seatLayout = seatLayout;
	}




	public int getTotalSeats() {
		return totalSeats;
	}




	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}




	public int getAvailableSeats() {
		return availableSeats;
	}




	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}




	public int getTotalBookedSeats() {
		return totalBookedSeats;
	}




	public void setTotalBookedSeats(int totalBookedSeats) {
		this.totalBookedSeats = totalBookedSeats;
	}




	public String[] getSeatNumbers() {
		return seatNumbers;
	}




	public void setSeatNumbers(String[] seatNumbers) {
		this.seatNumbers = seatNumbers;
	}




	public String[] getSeatStatus() {
		return seatStatus;
	}




	public void setSeatStatus(String[] seatStatus) {
		this.seatStatus = seatStatus;
	}




	public String toString()
	{
		return "totalSeats:"+getTotalSeats()+
			"	availableSeats:"+getAvailableSeats()+
			"	totalBookedSeats:"+getTotalBookedSeats()+"";
	}
	
}
