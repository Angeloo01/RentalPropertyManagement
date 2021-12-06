package Controller;

import Database.DatabaseConnectivity;
import Entity.FeeModel;
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
	
	public float getFeeAmount() {
		return model.getAmount();
	}
	
	public int getFeePeriod() {
		return model.getPeriod();
	}
	
	public boolean setFee(int fee, int period) {
		model.setAmount(fee);
		model.setPeriod(period);
		return DatabaseConnectivity.changeFee();
	}
	
	public boolean setFeeAmount(int fee) {
		model.setAmount(fee);
		return DatabaseConnectivity.changeFee();
	}
	
	public boolean setFeePeriod(int period) {
		model.setPeriod(period);
		return DatabaseConnectivity.changeFee();
	}
	
	public void getSummaryReport() {
		//change return type
	}
	
	public void viewDatabase() {
		//change return type
	}

}
