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

    public ArrayList<Property> getMatchingProperties() {
        return model.getInbox().getMatchingProperties();
    }

    public void addSearchCriteria(String type, int beds, int baths, boolean furnished, String quadrant) {
        model.addSearchCriteria(type, beds, baths, furnished, quadrant);
    }

    public ArrayList<Property> getInboxProperties() {
        return model.getInbox().getMatchingProperties();
    }
    
    public Inbox getInbox() {
    	return model.getInbox();
    }
	
	public User getUser() {
		return renter;
	}

	
}


