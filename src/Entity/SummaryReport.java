package Entity;

import java.sql.Date;
import java.util.ArrayList;
import Database.DatabaseConnectivity;

public class SummaryReport {
    private long period;
    private int numPropertiesListed;
    private int numActiveListings;
    private ArrayList<Property> propertiesRented;
    private String startDate;
    private String endDate;

    public SummaryReport(long period) {
        System.out.println(period);
        this.period = period;
        numPropertiesListed = DatabaseConnectivity.getNumPropertiesListed(period);
        numActiveListings = DatabaseConnectivity.getNumActiveProperties();
        propertiesRented = DatabaseConnectivity.getRentedProperties(period);
        endDate = (new Date(System.currentTimeMillis())).toString();
        startDate = (new Date(System.currentTimeMillis() - 86400000*period)).toString();
    }
    // getter for the period
    public long getPeriod() {
        return period;
    }
    // getter for the number of properties listed
    public int getNumPropertiesListed() {
        return numPropertiesListed;
    }   
    // getter for the number of properties active
    public int getNumActiveListings() {
        return numActiveListings;
    }
    // getter for the properties rented 
    public ArrayList<Property> getPropertiesRented() {
        return propertiesRented;
    }
    // getter for the number of properties rented 
    public int getNumPropertiesRented() {
        return propertiesRented.size();
    }
    // getter for the start date
    public String getStartDate() {
        return startDate;
    }
    // getter for the end date
    public String getEndDate() {
        return endDate;
    }

}
