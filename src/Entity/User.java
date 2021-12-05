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
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
}
