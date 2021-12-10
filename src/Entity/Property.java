package Entity;

public class Property{
	private int id;
    private String type;
    private String address;
    private int numBeds;
    private int numBaths;
    private boolean furnished;
    private String quadrant;
    private String landlordName; 
    private String stateOfProperty; 
    //these dates are stored in yyyy-mm-dd format
    private String registerDate;
    private String rentDate;


    public Property(int i, String t, String addr, int nBed, int nBath, boolean f, String q, String name, String state, String regDate, String rentDate){
        id = i;
        type = t;
        address = addr;
        numBaths = nBath;
        numBeds = nBed;
        furnished = f;
        quadrant = q;
        landlordName=name;
        stateOfProperty= state; 
        registerDate = regDate;
        this.rentDate = rentDate;
    }

    public Property(int i, String t, String addr, int nBed, int nBath, boolean f, String q, String name, String state, String regDate){
        id = i;
        type = t;
        address = addr;
        numBaths = nBath;
        numBeds = nBed;
        furnished = f;
        quadrant = q;
        landlordName=name;
        stateOfProperty= state; 
        registerDate = regDate;
    }

    public Property(String t, String addr, int nBed, int nBath, boolean f, String q, String name, String state, String regDate){
        type = t;
        address = addr;
        numBaths = nBath;
        numBeds = nBed;
        furnished = f;
        quadrant = q;
        landlordName=name;
        stateOfProperty= state; 
        registerDate = regDate;
    }
    // getter for the ID
    public int getID() {
        return id;
    }
    // getter for the type
    public String getType(){
        return type;
    }
    // getter for the address
    public String getAddress() {
        return address;
    }
    // getter for the number of baths
    public int getNumBath(){
        return numBaths;
    }
    // getter for the number of beds
    public int getNumBed(){
        return numBeds;
    }
    // getter for the Furnished boolean
    public boolean getFurnished(){
        return furnished;
    }
    // getter for the quadrant
    public String getQuadrant(){
        return quadrant;
    }
    // getter for the landlord's name
    public String getLandlordName()
    {
        return landlordName; 
    }
    // getter for the state of the property
    public String getStateOfProperty()
    {
        return stateOfProperty; 
    }
    // getter for the registration date
    public String getRegisterDate() {
        return registerDate;
    }
    // getter for the date of the rental
    public String getRentDate() {
        return rentDate;
    }

}
