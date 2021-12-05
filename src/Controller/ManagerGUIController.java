package Controller;

import GUI.GUIWindow;


public class ManagerGUIController implements GUIController {

	GUIWindow view;
	//models: fee, summary, 
	
	public ManagerGUIController(GUIWindow gui) {
		view = gui;
		view.setController(this);
	}
	
	public float getFeeAmount() {
		return 100;
	}
	
	public int getFeePeriod() {
		return 50;
	}
	
	public void setFeeAmount(float fee) {
		
	}
	
	public void setFeePeriod(int period) {
		
	}
	
	public void getSummaryReport() {
		//change return type
	}
	
	public void viewDatabase() {
		//change return type
	}

}
