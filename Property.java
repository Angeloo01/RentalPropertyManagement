package Entity;

public class Property{
    private String type;
    private int numBeds;
    private int numBaths;
    private boolean furnished;
    private String quadrant;
    private String landlordName; 
    private String stateOfProperty; 

public Property(String t, int nBed, int nBath, boolean f, String q){
    type = t;
    numBaths = nBath;
    numBeds = nBed;
    furnished = f;
    quadrant = q;
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
    return Furnished;
}

public String getQuadrant(){
    return quadrant;
}

}