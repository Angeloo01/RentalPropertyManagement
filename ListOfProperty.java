package Entity;

import java.util.*;

public class ListOfProperty{
private List<Property> properties;
private static ListOfProperty singleton;


	private ListOfProperty() {
		properties = new ArrayList<Property>();
	}

	public static ListOfProperty getInstance(){
	    if(singleton == null){
	        singleton = new ListOfProperty();
	    }
	    return singleton;
	}

	public void add(Property p) {
		properties.add(p);
	}
	
	public void remove(Property p) {
		properties.remove(p);
	}

    public List<Property> getProperies(){
		return properties;
	}
    public boolean propertyExist(int propertyid)
    {
        for(Property property : properties)
        {
            if(propertyid==property.getID())
            {
                return true;
            }
        }
        return false;
    }
}