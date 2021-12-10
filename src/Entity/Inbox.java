package Entity;

import java.util.ArrayList;

import Database.DatabaseConnectivity;

public class Inbox implements Observer {
    private ArrayList<SearchCriteria> searchCriteria;
    private ArrayList<Property> matchingProperties;
    private RegisteredRenter user;

    @Override
    public void update(Property prop) {
    	
        for(SearchCriteria search : searchCriteria) {
        	System.out.println(prop.getType()+prop.getQuadrant());
        	System.out.println(search.matches(prop) && !matchingContains(prop));
            if(search.matches(prop) && !matchingContains(prop)) {
            	//System.out.println("update");
                matchingProperties.add(prop);
            }
        }
    }
    
    public boolean matchingContains(Property prop) {
    	//System.out.println(prop.getID());
    	for(Property inlist : matchingProperties) {
    		//System.out.println(inlist.getID());
    		if(inlist.getID() == prop.getID()) return true;
    	}
    	return false;
    }

    public Inbox(RegisteredRenter r) {
        user = r;
        
        searchCriteria = DatabaseConnectivity.getUserSearchCriteria(user.getUsername());
        matchingProperties = new ArrayList<Property>();
        //search property list for properties that match the search criteria
    }

    public ArrayList<Property> getMatchingProperties() {
    	//System.out.println("getting matched");
        return matchingProperties;
    }
    
    public void addSearchCriteria(String type, int beds, int baths, boolean furnished, String quadrant) {

        if(DatabaseConnectivity.addUserSearchCriteria(user.getUsername(), type, beds, baths, furnished, quadrant)) {
	    	System.out.println("adding search");
	        searchCriteria.add(new SearchCriteria(type, beds, baths, furnished, quadrant));
        }

    }
    
    public void removeSearchCriteria(String type, int beds, int baths, boolean furnished, String quadrant) {

        if(DatabaseConnectivity.removeUserSearchCriteria(user.getUsername(), type, beds, baths, furnished, quadrant)) {
	    	System.out.println("removing search");
	        searchCriteria.clear();
	        searchCriteria = DatabaseConnectivity.getUserSearchCriteria(user.getUsername());
        }

    }
}
