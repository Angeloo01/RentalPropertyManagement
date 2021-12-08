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
            if(search.matches(prop)) {
                matchingProperties.add(prop);
            }
        }
    }

    public Inbox(RegisteredRenter r) {
        user = r;
        searchCriteria = DatabaseConnectivity.getUserSearchCriteria(user.getUsername());
        matchingProperties = new ArrayList<Property>();
        //search property list for properties that match the search criteria
    }

    public ArrayList<Property> getMatchingProperties() {
        return matchingProperties;
    }
    
    public void addSearchCriteria(String type, int beds, int baths, String quadrant) {
        searchCriteria.add(new SearchCriteria(type, beds, baths, quadrant));
    }
}
