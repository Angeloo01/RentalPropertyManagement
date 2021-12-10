package Entity;

import java.util.*;

public class ListOfUsers {
	List<User> users;
	private static ListOfUsers singleton;

	private ListOfUsers() {
		users = new ArrayList<User>();
	}
	// returns instance of ListOfUsers
	public static ListOfUsers getInstance() {
		if(singleton == null) {
			singleton = new ListOfUsers();
		}
		return singleton;
	}
	// adds a user
	public void add(User user) {
		users.add(user);
	}
	// removes a user
	public void remove(User user) {
		users.remove(user);
	}
	// removes a user based off of a username
	public void remove(String username) {
		ListIterator<User> itr = users.listIterator();
		while(itr.hasNext()) {
			User user = itr.next();
			if(user.username.equalsIgnoreCase(username)) {
				itr.remove();
				return;
			}
		}
	}
	// getter for the List of users
	public List<User> getUsers(){
		return users;
	}
	// returns a user based off of a username
	public User getUser(String username) {
		for(User user : users) {
			if(user.getUsername().equalsIgnoreCase(username)) {
				return user;
			}
		}
		return null;
	}
	// returns a user based off of email
	public User getUserFromEmail(String email) {
		for(User user : users) {
			if(user.getEmail() == null) {
				continue;
			}
			if(user.getEmail().equalsIgnoreCase(email)) {
				return user;
			}
		}
		return null;
	}
	// print function that prints the list of users
	public void printList() {
		for(User user : users) {
			System.out.println(user.getUsername() + " " + user.getEmail());
		}
	}
	// checks the list of users to see if a username exists
	public boolean usernameExist(String username) {
		for(User user: users) {
			if(username.equalsIgnoreCase(user.getUsername())) {
				return true;
			}
		}
		return false;
	}

}
