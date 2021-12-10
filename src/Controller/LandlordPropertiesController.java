package Controller;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import Database.DatabaseConnectivity;
import Entity.FeeModel;
import Entity.ListOfProperty;
import Entity.Property;
import Entity.User;
import GUI.GUIWindow;
import GUI.LandlordPropertiesGUI;

public class LandlordPropertiesController implements GUIController{

	GUIWindow view;
	User landlord;
	Object[][] props;
	public LandlordPropertiesController(GUIWindow gui) {
		view = gui;
		view.setController(this);
		
	}
	
	public LandlordPropertiesController(GUIWindow gui, User user) {
		this(gui);
		this.landlord =user;
		updatePropertyModel();
		updateFeeModel();
	}
	
	public void updatePropertyModel() {
		DatabaseConnectivity.updatePropertyStatus();
		
		List<Property> properties = ListOfProperty.getInstance().getProperies();
		LinkedList<Object[]> model = new LinkedList<Object[]>();
		for(Property prop : properties) {
			if(prop.getLandlordName().equalsIgnoreCase(landlord.getUsername())) {
				model.add(new Object[] {prop.getType(), prop.getAddress(), prop.getNumBed(), prop.getNumBath(), prop.getFurnished(), prop.getQuadrant(), prop.getStateOfProperty(), prop.getID()});
			}
		}
		props = model.toArray(new Object[model.size()][]);
		((LandlordPropertiesGUI)view).setTableModel(props);
	}
	
	public void updateFeeModel() {
		DatabaseConnectivity.updateFeeModel();
		((LandlordPropertiesGUI)view).setFeeModel("Activation Fee: $"+String.valueOf(FeeModel.getInstance().getAmount())+" for "+String.valueOf(FeeModel.getInstance().getPeriod()) +" days");
	}
	
	public boolean changePropertyState(int selectedRow, String state) {
		System.out.println(state);
		boolean changed = DatabaseConnectivity.changePropertyState((Integer)props[selectedRow][7], state);
		updatePropertyModel();
		return changed;
	}
	
	public boolean registerProperty(String type, String address, int bed, int bath, boolean furnished, String quad) {
		Property property = new Property(type, address, bed, bath, furnished, quad, landlord.getUsername(), "registered", (new Date(System.currentTimeMillis())).toString());
		boolean added = DatabaseConnectivity.addProperty(property);
		updatePropertyModel();
		return added;
	}

}
