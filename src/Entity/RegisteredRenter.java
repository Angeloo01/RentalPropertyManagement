package Entity;

import java.util.LinkedList;

public class RegisteredRenter {
    String username;
    String email;
    private Inbox inbox;
    
    String[] columnNames = { "Type", "Bedrooms", "Bathrooms", "Furnished", "City Quadrant" };
	Object[][] data;

    public RegisteredRenter(User u) {
        inbox = new Inbox(this);
        username = u.getUsername();
        email = u.getEmail();
        generateData();
    }
    public Inbox getInbox() {
        return inbox;
    }

    public void addSearchCriteria(String type, int beds, int baths, boolean furnished, String quadrant) {
        inbox.addSearchCriteria(type, beds, baths, furnished, quadrant);
    }

    public String getUsername() {
        return username;
    }
    
    public String[] getColumnNames() {
		return columnNames;
	}
	public Object[][] getData() {
		return data;
	}
	
	private void generateData() {
		LinkedList<Object[]> temp = new LinkedList<Object[]>();
		for(Property prop : inbox.getMatchingProperties()) {
				if(prop.getStateOfProperty().equalsIgnoreCase("active")) {
					//System.out.println(prop.getType());
					//System.out.println(prop.getStateOfProperty());
					temp.add(new Object[] {prop.getType(), prop.getNumBed(), prop.getNumBath(), prop.getFurnished(), prop.getQuadrant(), prop.getID(), prop.getLandlordName(), prop.getStateOfProperty()});
				}

			
		}
		data = temp.toArray(new Object[temp.size()][]);
	}
}
