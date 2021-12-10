package Controller;

import Database.DatabaseConnectivity;
import Entity.FeeModel;
import Entity.SummaryReport;
import GUI.*;


public class ManagerGUIController implements GUIController {

	GUIWindow view;
	//models: fee, summary
	FeeModel model;
	public ManagerGUIController(GUIWindow gui) {
		view = gui;
		view.setController(this);
		model = FeeModel.getInstance();
		DatabaseConnectivity.updateFeeModel();
		((ManagerGUI)view).showFee(model.getAmount(), model.getPeriod());
	}
	// getter for the fee amount
	public float getFeeAmount() {
		return model.getAmount();
	}
	// getter for the fee period
	public int getFeePeriod() {
		return model.getPeriod();
	}
	// setter for the fee amount and period
	public boolean setFee(int fee, int period) {
		model.setAmount(fee);
		model.setPeriod(period);
		return DatabaseConnectivity.changeFee();
	}
	// setter for the fee amount
	public boolean setFeeAmount(int fee) {
		model.setAmount(fee);
		return DatabaseConnectivity.changeFee();
	}
	// setter for the fee period
	public boolean setFeePeriod(int period) {
		model.setPeriod(period);
		return DatabaseConnectivity.changeFee();
	}
	// returns a summary report from a selected period
	public SummaryReport getSummaryReport(long period) {
		return new SummaryReport(period);
	}
}
