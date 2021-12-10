# ENSF 480 Final Project - Rental Property Management Application
## Group 89 - Angelo Gonzales, Carter Fuchs, Sam Farzamfar, Rajpreet Gill
This application provides a method of organizing real estate listings by keeping track of properties that have been posted, and allowing landlords, renters, and system managers to get all information they need.


## Compiling and running the program
In order to compile the program, navigate to the RentalPropertyManagement folder and run the following command:
```
javac -cp lib/mysql-connector-java-8.0.23.jar -sourcepath src -d build src/Main.java
```
To run the program, you need to have a MySQL connection, and source the database in the database.sql file. Once you have a working MySQL connection with the rental_management database created, you can run the following command:
```
java Main <USERNAME> <PASSWORD>
```
where \<USERNAME> and \<PASSWORD> should be replaced by the username and password of your MySQL connection.

## Testing the program functionality
To test the functionality of users and landlords, you can register a new user or you can use the users that are created by the program. The program automatically creates two users when it's run on a new database instance for the first time. One of them is a registered renter, with the username **user** and the password **user**, and the other is a landlord with the username **landlord** and the password **landlord**.
To test the functionality of the manager use case, you have to log in with the username **admin** and the password **admin**, since a new manager cannot be registered.