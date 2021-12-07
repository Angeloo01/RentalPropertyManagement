package Entity;

import java.util.ArrayList;

public class Inbox implements Observer {
    private ArrayList<SearchCriteria> searchCriteria;
    private ArrayList<Property> matchingProperties;

    @Override
    public void update(Property prop) {
        for(SearchCriteria search : searchCriteria) {
            if(search.matches(prop)) {
                matchingProperties.add(prop);
            }
        }
    }
    
}
