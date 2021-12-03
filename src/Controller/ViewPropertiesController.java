package Controller;

import Entity.PropertiesModel;
import GUI.GUIWindow;
import GUI.ViewPropertiesGUI;

public class ViewPropertiesController implements GUIController{
	GUIWindow view;
	PropertiesModel model;
	
	public ViewPropertiesController(GUIWindow gui, PropertiesModel model) {
		view = gui;
		view.setController(this);
		((ViewPropertiesGUI)view).setTableModel(model.getColumnNames(), model.getData());
		this.model = model;
	}
	
	public void selectProperty(int index) {
		Object[] row = model.getData()[index];
		for(int i =0; i< row.length; i++) {
			System.out.println(row[i]);
		}
	}
	

}
