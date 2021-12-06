package Entity;

public class FeeModel {
	private int amount, period;
	public static FeeModel instance;
	
	private FeeModel() {
		amount = 0;
		period = 0;
	}
	
	public static FeeModel getInstance() {
		if(instance == null) {
			instance = new FeeModel();
		}
		return instance;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}
	
	

}
