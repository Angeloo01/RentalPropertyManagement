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
	
	public boolean usernameExist(String username) {
		for(User user: users) {
			if(username.equalsIgnoreCase(user.getUsername())) {
				return true;
			}
		}
		return false;
	}

}
