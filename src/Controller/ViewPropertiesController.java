package Controller;

import java.util.LinkedList;

import Entity.ListOfUsers;
import Entity.PropertiesModel;
import Entity.User;
import GUI.GUIWindow;
import GUI.ViewPropertiesGUI;

public class ViewPropertiesController implements GUIController{
	GUIWindow view;
	PropertiesModel model;
	User user;
	
	public ViewPropertiesController(GUIWindow gui, PropertiesModel model) {
		view = gui;
		view.setController(this);
		((ViewPropertiesGUI)view).setTableModel(model.getColumnNames(), model.getData());
		this.model = model;
	}
	
	public ViewPropertiesController(GUIWindow gui, PropertiesModel model, User user) {
		this(gui, model);
		this.user =user;
	}
	
	public void selectProperty(int index) {
		Object[] row = model.getData()[index];
		for(int i =0; i< row.length; i++) {
			System.out.println(row[i]);
		}
	}
	
	public User getUser() {
		return user;
	}
	public User getLandlord(int index) {
		Object[] row = model.getData()[index];
		System.out.println(row[6]);
		return ListOfUsers.getInstance().getUser((String)row[6]);
	}
	
	public void filterModel(String type, Integer bed, Integer bath, Boolean furnished, String quadrant) {
		Object[][] oldArray = model.getData();
		LinkedList<Object[]> newArray = new LinkedList<Object[]>();
		for(Object[] row : oldArray) {
			if(type.equals("Any") || type.equalsIgnoreCase((String)row[0])) 
				if(bed == null || bed.equals(row[1])) 
					if(bath == null || bath.equals(row[2])) 
						if(furnished.equals(row[3]))
							if(quadrant.equals("Any") || quadrant.equalsIgnoreCase((String)row[4]))
								newArray.add(row);
		}
		Object[][] output = new Object[newArray.size()][];
		((ViewPropertiesGUI)view).setTableModel(model.getColumnNames(), newArray.toArray(output));
	}
	

}
