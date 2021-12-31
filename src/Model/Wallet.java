package Model;

public class Wallet {
	
	private int availableCash;
	
	
	
	public Wallet(int availableCash) {
		this.availableCash = availableCash;
	}

	public void addCash(int amount)
	{
		availableCash +=amount;
	}
	
	public int updateAvailableCash(int amount)
	{
		return availableCash -= amount;
	}

	public int getAvailableCash() {
		return availableCash;
	}

	public void setAvailableCash(int availableCash) {
		this.availableCash = availableCash;
	}
	
	

}
