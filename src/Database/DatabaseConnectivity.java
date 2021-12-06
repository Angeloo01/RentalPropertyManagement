package Database;
import java.sql.*;

import Entity.*;;

public class DatabaseConnectivity {
    //Data to create MySQL connection:
    public static final String DBURL = "jdbc:mysql://localhost/rental_management";
    public static String DBUSERNAME;
    public static String DBPASSWORD;
    public static Connection databaseConnection;

    //public DatabaseConnectivity (String username, String password) {
    public static void initializeConnection(String username, String password) {
        DBUSERNAME = username;
        DBPASSWORD = password;
        try {
            databaseConnection = DriverManager.getConnection(DBURL, DBUSERNAME, DBPASSWORD);
        } catch (SQLException e) {
            System.err.println("Error initializing database connection. Make sure the username and password are correct.");
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("Connection successfully initialized.");
    }

    public static void closeConnection() {
        try {
            databaseConnection.close();
        } catch (SQLException e) {
            System.err.println("Error closing database connection.");
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    public static void updateAllEntities() {
    	updateListOfUsers();
    	updateFeeModel();
    }
    
    public static void updateListOfUsers() {
    	Statement stm = null;
    	ResultSet results = null;
		try {
			stm = databaseConnection.createStatement();
			results = stm.executeQuery("SELECT * FROM users");
			ListOfUsers list = ListOfUsers.getInstance();
			while(results.next()) {
				//user is already in list
				if(list.usernameExist(results.getString("username"))) {
					continue;
				}
				//else
				String username = results.getString("username");
				String password = results.getString("password");
				String email = results.getString("email");
				int type = results.getInt("type");
				list.add(new User(username, password, email, type));
			}
			
		} catch (SQLException e) {
			System.err.println("Error selecting from USERS");
		}
		finally {
			if(results != null)
				try {
					results.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(stm != null)
				try {
					stm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
    	
    }
    
    public static boolean addUser(User user) {
    	String query = "INSERT INTO users (username, password, email, type) VALUES (?,?,?,?)";
    	try {
			PreparedStatement stm = databaseConnection.prepareStatement(query);
			stm.setString(1, user.getUsername());
			stm.setString(2, user.getPassword());
			stm.setString(3, user.getEmail());
			stm.setInt(4, user.getType());
			
			int rowCount = stm.executeUpdate();
			stm.close();
			
			DatabaseConnectivity.updateListOfUsers();
			if(rowCount > 0) {			
				return true;
			}
			else {
				return false;
			}
		} catch (SQLException e) {
			System.err.println("Error adding user to DB");
			return false;
		}
    }
    
    public static void updateFeeModel() {
    	Statement stm = null;
    	ResultSet results = null;
		try {
			stm = databaseConnection.createStatement();
			
			FeeModel fee = FeeModel.getInstance();
			
			results = stm.executeQuery("SELECT * FROM int_variables WHERE Name = 'feeamount'");
			if(results.next())
				fee.setAmount(results.getInt("value"));
			results.close();
			
			results = stm.executeQuery("SELECT * FROM int_variables WHERE Name = 'feeperiod'");
			if(results.next())
				fee.setPeriod(results.getInt("value"));
			results.close();
			
			stm.close();
			
		} catch (SQLException e) {
			System.err.println("Error selecting from INT_VARIABLES");
			e.printStackTrace();
		}
		finally {
			if(results != null)
				try {
					results.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if(stm != null)
				try {
					stm.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
    }
    
    public static boolean changeFee() {
    	try {
			Statement stm = databaseConnection.createStatement();
			FeeModel fee = FeeModel.getInstance();
			
			if(stm.executeUpdate("UPDATE int_variables SET value = "+fee.getAmount()+" WHERE name = 'feeamount'") <= 0)
				return false;
			if(stm.executeUpdate("UPDATE int_variables SET value = "+fee.getPeriod()+" WHERE name = 'feePeriod'") <= 0)
				return false;
			stm.close();
			
			DatabaseConnectivity.updateFeeModel();
			return true;
			
		} catch (SQLException e) {
			System.err.println("Error changing Fee in DB");
			e.printStackTrace();
			return false;
		}
    }
}
