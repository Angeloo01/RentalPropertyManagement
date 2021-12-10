package Entity;

public class SearchCriteria {
    private String type;
    private int bedrooms;
    private int bathrooms;
    private boolean furnished;
    private String quadrant;
    
    public SearchCriteria(String type, int beds, int baths, boolean furnished, String quadrant) {
        this.type = type;
        bedrooms = beds;
        bathrooms = baths;
        this.furnished = furnished;
        this.quadrant = quadrant;
    }
    // checks to see if the property given matches the search criteria
    public boolean matches(Property prop) {
    	if(!prop.getStateOfProperty().equalsIgnoreCase("active")) return false;
    	
        if (!type.equalsIgnoreCase(prop.getType())) return false;
       
        if (bedrooms != prop.getNumBed()) return false;
        
        if (bathrooms != prop.getNumBath()) return false;
        
        if (furnished != prop.getFurnished()) return false;
       
        if (!quadrant.equalsIgnoreCase(prop.getQuadrant())) return false;
        
        return true;
    }
    // returns a string of the type, bedrooms, bathrooms and, quadrant
    public String toString() {
        return type + " " + bedrooms + " " + bathrooms + " " + quadrant; 
    }
    // getter for the type
	public String getType() {
		return type;
	}
    // getter for the bedrooms
	public int getBedrooms() {
		return bedrooms;
	}
    // getter for the bathrooms 
	public int getBathrooms() {
		return bathrooms;
	}
    // getter for the furnished
	public boolean isFurnished() {
		return furnished;
	}
    // getter for the quadrant
	public String getQuadrant() {
		return quadrant;
	}
	public boolean getFurnished() {
		return furnished;
	}
    
    
}
