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
	
	public List<User> getUsers(){
		return users;
	}

}
