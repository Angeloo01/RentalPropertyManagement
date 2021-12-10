package Entity;

import java.util.LinkedList;
import java.util.List;

import Database.DatabaseConnectivity;

public class PropertiesModel {
	String[] columnNames = { "Type", "Address", "Bedrooms", "Bathrooms", "Furnished", "City Quadrant" };
	Object[][] data;
	List<Property> list;
	public PropertiesModel(boolean activeOnly, List<Property> list) {
		DatabaseConnectivity.updatePropertyStatus();
		this.list = list;
		generateData(activeOnly);
	}
	// getter for the column names
	public String[] getColumnNames() {
		return columnNames;
	}
	// getter for the data
	public Object[][] getData() {
		return data;
	}
	// generates the list of properties wanted based on if we want the active 
	// properties only or all
	private void generateData(boolean activeOnly) {
		LinkedList<Object[]> temp = new LinkedList<Object[]>();
		for(Property prop : list) {
			if(activeOnly) {
				if(prop.getStateOfProperty().equalsIgnoreCase("active")) {
					temp.add(new Object[] {prop.getType(), prop.getAddress(), prop.getNumBed(), prop.getNumBath(), prop.getFurnished(), prop.getQuadrant(), prop.getID(), prop.getLandlordName(), prop.getStateOfProperty()});
				}
			}
			else
				temp.add(new Object[] {prop.getType(), prop.getAddress(), prop.getNumBed(), prop.getNumBath(), prop.getFurnished(), prop.getQuadrant(), prop.getID(), prop.getLandlordName(), prop.getStateOfProperty()});
			
		}
		data = temp.toArray(new Object[temp.size()][]);
	}
	// getter for the colums with all the data inside of it
	public Object[][] getColumns(LinkedList<Integer> cols) {
		LinkedList<Object[]> temp = new LinkedList<Object[]>();
		for(Object [] o : data) {
			LinkedList<Object> row = new LinkedList<Object>();
			for(int ind : cols) {
				row.push(o[ind]);
			}
			temp.push(row.toArray());
		}
		return temp.toArray(new Object[temp.size()][]);
	}

	
}
