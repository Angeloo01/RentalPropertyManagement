package Controller;

import java.util.LinkedList;

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
	
	public void filterModel(String type, Integer bed, Integer bath, Boolean furnished, String quadrant) {
		Object[][] oldArray = model.getData();
		LinkedList<Object[]> newArray = new LinkedList<Object[]>();
		for(Object[] row : oldArray) {
			if(type.equals("Any") || type.equals(row[0])) 
				if(bed == null || bed.equals(row[1])) 
					if(bath == null || bath.equals(row[2])) 
						if(furnished.equals(row[3]))
							if(quadrant.equals("Any") || quadrant.equals(row[4]))
								newArray.add(row);
		}
		Object[][] output = new Object[newArray.size()][];
		((ViewPropertiesGUI)view).setTableModel(model.getColumnNames(), newArray.toArray(output));
	}
	

}
