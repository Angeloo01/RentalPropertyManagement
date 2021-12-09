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
    public void clearList() {
    	properties = new ArrayList<Property>();
    }

	@Override
	public void registerObserver(Observer o) {
		observers.add(o);
		for(Property p : properties) {
			o.update(p);
		}
	}

	@Override
	public void removeObserver(Observer o) {
		observers.remove(o);
		
	}

	@Override
	public void notifyAllObservers() {
		for(Property p : properties) {
			for(Observer o : observers) {
				o.update(p);
			}
		}
		
	}
}