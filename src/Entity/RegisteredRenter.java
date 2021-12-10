package Entity;

import java.util.LinkedList;

public class RegisteredRenter {
    String username;
    String email;
    private Inbox inbox;
    
    String[] columnNames = { "Type", "Bedrooms", "Bathrooms", "Furnished", "City Quadrant" };
	Object[][] data;

    public RegisteredRenter(User u) {
    	username = u.getUsername();
        email = u.getEmail();
        inbox = new Inbox(this);
        
        generateData();
    }
    // getter for inbox
    public Inbox getInbox() {
        return inbox;
    }
    // adds a search criteria to the inbox
    public void addSearchCriteria(String type, int beds, int baths, boolean furnished, String quadrant) {
        inbox.addSearchCriteria(type, beds, baths, furnished, quadrant);
    }
    // removes a search criteria to the inbox
    public void removeSearchCriteria(String type, int beds, int baths, boolean furnished, String quadrant) {
        inbox.removeSearchCriteria(type, beds, baths, furnished, quadrant);
    }
    // getter for the username
    public String getUsername() {
        return username;
    }
    // getter for the column names
    public String[] getColumnNames() {
		return columnNames;
	}
    // getter for the data
	public Object[][] getData() {
		return data;
	}
    // generates the properties that match the search criteria into data
	private void generateData() {
		LinkedList<Object[]> temp = new LinkedList<Object[]>();
		for(Property prop : inbox.getMatchingProperties()) {
				if(prop.getStateOfProperty().equalsIgnoreCase("active")) {
					temp.add(new Object[] {prop.getType(), prop.getNumBed(), prop.getNumBath(), prop.getFurnished(), prop.getQuadrant(), prop.getID(), prop.getLandlordName(), prop.getStateOfProperty()});
				}

			
		}
		data = temp.toArray(new Object[temp.size()][]);
	}
}
