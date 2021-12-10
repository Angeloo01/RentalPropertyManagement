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

    public int getID() {
        return id;
    }

    public String getType(){
        return type;
    }

    public String getAddress() {
        return address;
    }

    public int getNumBath(){
        return numBaths;
    }

    public int getNumBed(){
        return numBeds;
    }
    public boolean getFurnished(){
        return furnished;
    }

    public String getQuadrant(){
        return quadrant;
    }

    public String getLandlordName()
    {
        return landlordName; 
    }

    public String getStateOfProperty()
    {
        return stateOfProperty; 
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public String getRentDate() {
        return rentDate;
    }

}
