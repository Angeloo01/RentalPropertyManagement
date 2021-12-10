package Entity;

public class FeeModel {
	private int amount, period;
	public static FeeModel instance;
	
	private FeeModel() {
		amount = 0;
		period = 0;
	}
	// getter for the FeeModel instance
	public static FeeModel getInstance() {
		if(instance == null) {
			instance = new FeeModel();
		}
		return instance;
	}
	// getter for the amount
	public int getAmount() {
		return amount;
	}
	// setter for the amount
	public void setAmount(int amount) {
		this.amount = amount;
	}
	// getter for the period
	public int getPeriod() {
		return period;
	}
	// setter for the period
	public void setPeriod(int period) {
		this.period = period;
	}
	
	

}
