package Controller;

import java.util.ArrayList;

import Entity.Property;
import Entity.RegisteredRenter;
import Entity.User;
import GUI.GUIWindow;
import GUI.RegisteredRenterGUI;

public class RegisteredRenterController {
    GUIWindow view;
    RegisteredRenter model;

    public RegisteredRenterController(GUIWindow prev, User user) {
        this.model = new RegisteredRenter(user);
        view = new RegisteredRenterGUI(prev, user);
    }

    public ArrayList<Property> getMatchingProperties() {
        return model.getInbox().getMatchingProperties();
    }

    public void addSearchCriteria(String type, int beds, int baths, boolean furnished, String quadrant) {
        model.addSearchCriteria(type, beds, baths, furnished, quadrant);
    }
}


