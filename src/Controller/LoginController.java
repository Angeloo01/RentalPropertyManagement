package Controller;

import java.util.Arrays;
import javax.swing.*;

import Database.DatabaseConnectivity;
import Entity.ListOfUsers;
import Entity.User;
import GUI.GUIWindow;

public class LoginController implements GUIController{
	GUIWindow view;
	ListOfUsers model;
	public LoginController(GUIWindow gui) {
		view = gui;
		view.setController(this);
		this.model = ListOfUsers.getInstance();
		DatabaseConnectivity.updateAllEntities();
	}
	// checks the username and password
	public int checkCredentials(String username, char[] password) {
		DatabaseConnectivity.updateListOfUsers();
		for(User user : model.getUsers()) {
			if (username.equalsIgnoreCase(user.getUsername()) && Arrays.equals(user.getPassword().toCharArray(), password)) {
	            return user.getType();
	        }
		}
		return -1;
	}
	// registers a new user and checks that username is not taken already
	public boolean registerUser(String username, char[] password) {
		for(User user : model.getUsers()) {
			if (username.equalsIgnoreCase(user.getUsername())) {
				JOptionPane.showMessageDialog(view, "Username is taken");
	            return false;
	        }
		}
		

        Object[] options = {"Renter", "Landlord"};
        Object selectionObject = JOptionPane.showInputDialog(view, "Please choose your use case", "Menu", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        
        String email = new String();
        while(!email.contains("@"))
        	email = JOptionPane.showInputDialog(view, "Enter your email:").toString();
        
        for(User user : model.getUsers()) {
			if (email.equalsIgnoreCase(user.getEmail())) {
				JOptionPane.showMessageDialog(view, "Email is taken");
	            return false;
	        }
		}
        User newUser = new User(username, new String(password), email, selectionObject.toString().equals("Renter") ? 2 : 1);
        if(!DatabaseConnectivity.addUser(newUser))
        	return false;
		return true;
	}

}
