package Entity;

public class User {
	
	String username;
	String password;
	String email;
	int type; //0: manager 1: landlord 2: renter
	public User(String username, String password, int type) {
		super();
		this.username = username;
		this.password = password;
		this.type = type;
	}
	
	public User(String username, String password, String email, int type) {
		super();
		this.username = username;
		this.password = password;
		this.type = type;
		this.email = email;
	}
	// getter for the username
	public String getUsername() {
		return username;
	}
	// setter for the username
	public void setUsername(String username) {
		this.username = username;
	}
	// getter for the password
	public String getPassword() {
		return password;
	}
	// setter for the password
	public void setPassword(String password) {
		this.password = password;
	}
	// getter for the type
	public int getType() {
		return type;
	}
	// setter for the type
	public void setType(int type) {
		this.type = type;
	}
	// getter for the email
	public String getEmail() {
		return email;
	}
	// setter for the email
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
}
