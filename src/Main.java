import javax.swing.SwingUtilities;

import Controller.LoginController;
import Entity.User;
import GUI.LoginGUI;
import Database.DatabaseConnectivity;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		//args should be: {<MySQL username>, <MySQL password>}
		if (args.length != 2) {
			System.err.println("Error: Must include 2 command-line arguments to initialize database connection");
			System.exit(1);
		}
		//intialize database connection
		//DatabaseConnectivity dbc = new DatabaseConnectivity(args[0], args[1]);
		DatabaseConnectivity.initializeConnection(args[0], args[1]);
        
    	//ListOfUsers users = ListOfUsers.getInstance();
    	DatabaseConnectivity.addUser(new User("admin", "admin", 0));
    	DatabaseConnectivity.addUser(new User("user", "user", 2));
    	DatabaseConnectivity.updateAllEntities();
    	SwingUtilities.invokeLater(new Runnable() {
    		public void run() {
				
    			LoginGUI login = new LoginGUI(null);
    			new LoginController(login);
    			login.getContentPane().repaint();
    		}
    	});
        
    }

}
