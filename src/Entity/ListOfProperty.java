package Entity;

import java.util.*;

public class ListOfProperty implements Subject{
	private List<Property> properties;
	private static ListOfProperty singleton;
	private List<Observer> observers;


	private ListOfProperty() {
		properties = new ArrayList<Property>();
		observers = new LinkedList<Observer>();
	}
	// returns the instance of ListOfProperties
	public static ListOfProperty getInstance(){
	    if(singleton == null){
	        singleton = new ListOfProperty();
	    }
	    return singleton;
	}
	// adds a property 
	public void add(Property p) {
		properties.add(p);
	}
	// removes a property
	public void remove(Property p) {
		properties.remove(p);
	}
	// getter for the List of Properties
    public List<Property> getProperies(){
		return properties;
	}
	// checks if a property exists based off the property ID
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
	// empties the list
    public void clearList() {
    	properties = new ArrayList<Property>();
    }

	// registers an observer 
	@Override
	public void registerObserver(Observer o) {
		observers.add(o);
		for(Property p : properties) {
			o.update(p);
		}
	}
	// removes an observer 
	@Override
	public void removeObserver(Observer o) {
		observers.remove(o);
		
	}
	// notifies observers when change occurs
	@Override
	public void notifyAllObservers() {
		for(Property p : properties) {
			for(Observer o : observers) {
				o.update(p);
			}
		}
		
	}
}