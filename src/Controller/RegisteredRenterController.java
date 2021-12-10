package Controller;

import java.util.ArrayList;
import java.util.LinkedList;

import Entity.Inbox;
import Entity.ListOfUsers;
import Entity.Property;
import Entity.RegisteredRenter;
import Entity.User;
import GUI.GUIWindow;
import GUI.RegisteredRenterGUI;
import GUI.RenterNotificationGUI;
import GUI.ViewPropertiesGUI;

public class RegisteredRenterController implements GUIController{
    GUIWindow view;
    RegisteredRenter model;
    User renter;

    public RegisteredRenterController(GUIWindow prev, User user) {
        this.model = new RegisteredRenter(user);
        view = new RegisteredRenterGUI(prev, user);
        view.setController(this);
        renter = user;
    }
    // returns an ArrayList with all properties that match search criteria
    public ArrayList<Property> getMatchingProperties() {
        return model.getInbox().getMatchingProperties();
    }
    // adds a search criteria 
    public void addSearchCriteria(String type, int beds, int baths, boolean furnished, String quadrant) {
        model.addSearchCriteria(type, beds, baths, furnished, quadrant);
    }
    // removes a search criteria 
    public void removeSearchCriteria(String type, int beds, int baths, boolean furnished, String quadrant) {
        model.removeSearchCriteria(type, beds, baths, furnished, quadrant);
    }
    // returns an ArrayList of properties taht match the 
    public ArrayList<Property> getInboxProperties() {
        return model.getInbox().getMatchingProperties();
    }
    // getter for the Inbox
    public Inbox getInbox() {
    	return model.getInbox();
    }
    // getter for the user
	public User getUser() {
		return renter;
	}
	// setter for the search model
	public void setSearchModel(RenterNotificationGUI gui) {
		String[] column = {"Type", "beds","baths","furnished", "quadrant"};
		Object[][] searchModel = model.getInbox().getSearchModel();
		gui.setSearchModel(column, searchModel);
		
	}

	
}


