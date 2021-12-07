package Entity;

public class Property{
    private String type;
    private int numBeds;
    private int numBaths;
    private boolean furnished;
    private String quadrant;
    private String landlordName; 
    private String stateOfProperty; 

public Property(String t, int nBed, int nBath, boolean f, String q, String name, String state){
    type = t;
    numBaths = nBath;
    numBeds = nBed;
    furnished = f;
    quadrant = q;
    landlordName=name;
    stateOfProperty= state; 
}

public String getType(){
    return type;
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

}