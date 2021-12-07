package Entity;

import java.util.*;

public class ListOfProperty{
private List<Property> properties;
private static ListOfProperty singleton;


	private ListOfProperties() {
		properties = new ArrayList<properties>();
	}

public static ListOfProperty getInstance(){
    if(singleton == null){
        singleton = new ListOfProperties();
    }
    return singleton;
}

public void add(Property p) {
		properties.add(p);
	}
	
	public void add(Property p) {
		properties.remove(p);
	}

    public List<Property> getProperies(){
		return properties;
	}

}