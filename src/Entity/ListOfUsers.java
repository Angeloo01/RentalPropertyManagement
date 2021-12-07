package Entity;

import java.util.*;

public class ListOfUsers {
	List<User> users;
	private static ListOfUsers singleton;

	private ListOfUsers() {
		users = new ArrayList<User>();
	}
	
	public static ListOfUsers getInstance() {
		if(singleton == null) {
			singleton = new ListOfUsers();
		}
		return singleton;
	}
	
	public void add(User user) {
		users.add(user);
	}
	
	public void remove(User user) {
		users.remove(user);
	}
	
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
	
	public List<User> getUsers(){
		return users;
	}
	
	public User getUser(String username) {
		for(User user : users) {
			if(user.getUsername().equalsIgnoreCase(username)) {
				return user;
			}
		}
		return null;
	}
	
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
	
	public void printList() {
		for(User user : users) {
			System.out.println(user.getUsername() + " " + user.getEmail());
		}
	}
	
	public boolean usernameExist(String username) {
		for(User user: users) {
			if(username.equalsIgnoreCase(user.getUsername())) {
				return true;
			}
		}
		return false;
	}

}
