package Entity;

import java.sql.Date;
import java.util.ArrayList;
import Database.DatabaseConnectivity;

public class SummaryReport {
    private int period;
    private int numPropertiesListed;
    private int numActiveListings;
    private ArrayList<Property> propertiesRented;
    private String startDate;
    private String endDate;

    public SummaryReport(int period) {
        this.period = period;
        numPropertiesListed = DatabaseConnectivity.getNumPropertiesListed(period);
        numActiveListings = DatabaseConnectivity.getNumActiveProperties();
        propertiesRented = DatabaseConnectivity.getRentedProperties(period);
        endDate = (new Date(System.currentTimeMillis())).toString();
        startDate = (new Date(System.currentTimeMillis() - 86400000*period)).toString();
    }

    public int getPeriod() {
        return period;
    }

    public int getNumPropertiesListed() {
        return numPropertiesListed;
    }

    public int getNumActiveListings() {
        return numActiveListings;
    }

    public ArrayList<Property> getPropertiesRented() {
        return propertiesRented;
    }

    public int getNumPropertiesRented() {
        return propertiesRented.size();
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

}
