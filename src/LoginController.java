import java.util.Arrays;

import javax.swing.*;

import Entity.ListOfUsers;
import Entity.User;

public class LoginController implements GUIController{
	GUIWindow view;
	ListOfUsers model;
	public LoginController(GUIWindow gui) {
		view = gui;
		view.setController(this);
		this.model = ListOfUsers.getInstance();
	}
	
	public int checkCredentials(String username, char[] password) {
		for(User user : model.getUsers()) {
			if (username.equalsIgnoreCase(user.getUsername()) && Arrays.equals(user.getPassword().toCharArray(), password)) {
	            return user.getType();
	        }
		}
		return -1;
	}
	
	public boolean registerUser(String username, char[] password) {
		for(User user : model.getUsers()) {
			if (username.equalsIgnoreCase(user.getUsername())) {
	            return false;
	        }
		}
		

        Object[] options = {"Renter", "Landlord"};
        Object selectionObject = JOptionPane.showInputDialog(null, "Please choose your use case", "Menu", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

		model.getUsers().add(new User(username, new String(password), selectionObject.toString().equals("Renter") ? 2 : 1));
		return true;
	}

}
