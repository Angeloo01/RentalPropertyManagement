package Entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import Database.DatabaseConnectivity;

public class Inbox implements Observer {
    private ArrayList<SearchCriteria> searchCriteria;
    private ArrayList<Property> matchingProperties;
    private RegisteredRenter user;

    // updates the user when a 
    @Override
    public void update(Property prop) {
    	
        for(SearchCriteria search : searchCriteria) {
            if(search.matches(prop) && !matchingContains(prop)) {
                matchingProperties.add(prop);
            }
        }
    }
    // checks if a property matches the a property in the list
    public boolean matchingContains(Property prop) {
    	for(Property inlist : matchingProperties) {
    		if(inlist.getID() == prop.getID()) return true;
    	}
    	return false;
    }

    public Inbox(RegisteredRenter r) {
        user = r;
        
        searchCriteria = DatabaseConnectivity.getUserSearchCriteria(user.getUsername());
        matchingProperties = new ArrayList<Property>();
    }

    public ArrayList<Property> getMatchingProperties() {
        return matchingProperties;
    }
    
    public Object[][] getSearchModel() {
    	LinkedList<Object[]> temp = new LinkedList<Object[]>();
    	for(SearchCriteria search : searchCriteria) {
        	temp.add(new Object[] {search.getType(), search.getBedrooms(), search.getBathrooms(), search.getFurnished(), search.getQuadrant()});
            
        }
        return temp.toArray(new Object[temp.size()][]);
    }
    
    public void addSearchCriteria(String type, int beds, int baths, boolean furnished, String quadrant) {

        if(DatabaseConnectivity.addUserSearchCriteria(user.getUsername(), type, beds, baths, furnished, quadrant)) {
	        searchCriteria.add(new SearchCriteria(type, beds, baths, furnished, quadrant));
        }

    }
    
    public void removeSearchCriteria(String type, int beds, int baths, boolean furnished, String quadrant) {

        if(DatabaseConnectivity.removeUserSearchCriteria(user.getUsername(), type, beds, baths, furnished, quadrant)) {
	        searchCriteria.clear();
	        searchCriteria = DatabaseConnectivity.getUserSearchCriteria(user.getUsername());
	        removeMatchingProperty(type, beds, baths, furnished, quadrant);
        }

    }
    
    public void removeMatchingProperty(String type, int beds, int baths, boolean furnished, String quadrant) {
    	Iterator<Property> i = matchingProperties.iterator();
        
        while (i.hasNext()) {
           Property prop = i.next();
           if (prop.getType().equalsIgnoreCase(type) && prop.getNumBath() == baths && prop.getNumBed() == beds &&
        		   prop.getFurnished() == furnished && prop.getQuadrant().equalsIgnoreCase(quadrant)) {
              i.remove();
           }
        }
    }
}
