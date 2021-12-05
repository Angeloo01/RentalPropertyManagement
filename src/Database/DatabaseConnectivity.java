package Database;
import java.sql.*;

public class DatabaseConnectivity {
    //Data to create MySQL connection:
    public final String DBURL = "jdbc:mysql://localhost/rental_management";
    public final String DBUSERNAME;
    public final String DBPASSWORD;
    public Connection databaseConnection;

    public DatabaseConnectivity (String username, String password) {
        this.DBUSERNAME = username;
        this.DBPASSWORD = password;
        try {
            databaseConnection = DriverManager.getConnection(DBURL, DBUSERNAME, DBPASSWORD);
        } catch (SQLException e) {
            System.err.println("Error initializing database connection. Make sure the username and password are correct.");
            e.printStackTrace();
            System.exit(1);
        }
        System.out.println("Connection successfully initialized.");
    }

    public void closeConnection() {
        try {
            databaseConnection.close();
        } catch (SQLException e) {
            System.err.println("Error closing database connection.");
            e.printStackTrace();
            System.exit(1);
        }
    }
}
