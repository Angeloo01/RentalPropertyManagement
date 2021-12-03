import javax.swing.SwingUtilities;

import Controller.LoginController;
import Entity.ListOfUsers;
import Entity.User;
import GUI.LoginGUI;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
        //LoginGUI frame = new LoginGUI(360, 600);
    	ListOfUsers users = ListOfUsers.getInstance();
    	users.add(new User("admin", "admin", 0));
    	users.add(new User("user", "user", 2));
    	SwingUtilities.invokeLater(new Runnable() {
    		public void run() {
    			LoginGUI login = new LoginGUI(null);
    			new LoginController(login);
    			login.getContentPane().repaint();
    		}
    	});
        
    }

}
