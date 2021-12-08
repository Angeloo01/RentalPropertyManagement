package Database;
import java.sql.*;
import java.util.ArrayList;

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
    
    public static boolean sendMail(String message, User sender, User receiver) {
    	String query = "INSERT INTO mail (sender, receiver, message) VALUES (?,?,?)";
    	try {
			PreparedStatement stm = databaseConnection.prepareStatement(query);
			
			stm.setString(1, sender.getUsername());
			System.out.println(sender.getUsername());
			System.out.println(receiver.getUsername());
			stm.setString(2, receiver.getUsername());
			stm.setString(3, message);
			
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
			e.printStackTrace();
			System.err.println("Error sending mail to DB");
			return false;
		}
    }

    public static ArrayList<SearchCriteria> getUserSearchCriteria(String username) {
        String sql = "SELECT * FROM search_criteria WHERE Username = " + username;
        ArrayList<SearchCriteria> resultArray = new ArrayList<SearchCriteria>();
        try {
            Statement stmt = databaseConnection.createStatement();
            ResultSet results = stmt.executeQuery(sql);
            while(results.next()) {
                resultArray.add(new SearchCriteria(results.getString(2), results.getInt(3), results.getInt(4), results.getString(5)));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving search criteria with username " + username);
            e.printStackTrace();
        }
        return resultArray;
    }
//int i, String t, int nBed, int nBath, boolean f, String q, String name, String state
    public static boolean addProperty(Property property) {
        String query = "INSERT INTO property (propertyid, type, bedrooms, bathrooms,furnished, quadrant, landlord, status) VALUES (?,?,?,?,?,?,?,?)";
    	try {
			PreparedStatement stm = databaseConnection.prepareStatement(query);
			stm.setInt(1, property.getID());
			stm.setString(2, property.getType());
            stm.setInt(3, property.getNumBed());
			stm.setInt(4, property.getNumBath());
            stm.setBoolean(5, property.getFurnished());
            stm.setString(6, property.getQuadrant());
            stm.setString(7, property.getLandlordName());
            stm.setString(8, property.getStateOfProperty());

			int rowCount = stm.executeUpdate();
			stm.close();
			
			DatabaseConnectivity.updatePropertyStatus();
			if(rowCount > 0) {			
				return true;
			}
			else {
				return false;
			}
		} catch (SQLException e) {
			System.err.println("Error adding property to DB");
			return false;
		}

        
    }
//int i, String t, int nBed, int nBath, boolean f, String q, String name, String state
    public static void updatePropertyStatus() {
    	Statement stm = null;
    	ResultSet results = null;
		try {
			stm = databaseConnection.createStatement();
			results = stm.executeQuery("SELECT * FROM property");
			ListOfProperty list = ListOfProperty.getInstance();
			while(results.next()) {
				//property is already in list
				if(list.propertyExist(results.getInt("propertyid"))) {
					continue;
				}
				//else
				int propertyid = results.getString("propertyid");
				String type = results.getString("type");
				int bedrooms = results.getString("bedrooms");
				int bathrooms = results.getInt("bathrooms");
                int furnished = results.getInt("furnished");
                int quadrant = results.getInt("quadrant");
                int landlord = results.getInt("landlord");
                int status = results.getInt("status");
				list.add(new Property(propertyid, type, bedrooms, bathrooms, furnished, quadrant, landlord, status));
			}
			
		} catch (SQLException e) {
			System.err.println("Error selecting from Property");
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



}