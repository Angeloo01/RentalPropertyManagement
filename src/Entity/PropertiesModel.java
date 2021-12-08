package Entity;

import java.util.LinkedList;

import Database.DatabaseConnectivity;

public class PropertiesModel {
	String[] columnNames = { "Type", "Bedrooms", "Bathrooms", "Furnished", "City Quadrant" };
	Object[][] data;
	ListOfProperty list;
	public PropertiesModel(boolean activeOnly) {
		DatabaseConnectivity.updatePropertyStatus();
		list = ListOfProperty.getInstance();
		generateData(activeOnly);
	}
	public String[] getColumnNames() {
		return columnNames;
	}
	public Object[][] getData() {
		return data;
	}

	private void generateData(boolean activeOnly) {
		LinkedList<Object[]> temp = new LinkedList<Object[]>();
		for(Property prop : list.getProperies()) {
			if(activeOnly) {
				if(prop.getStateOfProperty().equalsIgnoreCase("active")) {
					//System.out.println(prop.getType());
					//System.out.println(prop.getStateOfProperty());
					temp.add(new Object[] {prop.getType(), prop.getNumBed(), prop.getNumBath(), prop.getFurnished(), prop.getQuadrant(), prop.getID(), prop.getLandlordName(), prop.getStateOfProperty()});
				}
			}
			else
				temp.add(new Object[] {prop.getType(), prop.getNumBed(), prop.getNumBath(), prop.getFurnished(), prop.getQuadrant(), prop.getID(), prop.getLandlordName(), prop.getStateOfProperty()});
			
		}
		data = temp.toArray(new Object[temp.size()][]);
	}

	
}
