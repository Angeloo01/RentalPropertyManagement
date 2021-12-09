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

    public boolean matches(Property prop) {
    	if(!prop.getStateOfProperty().equalsIgnoreCase("active")) return false;
    	
        if (!type.equalsIgnoreCase(prop.getType())) return false;
       
        if (bedrooms != prop.getNumBed()) return false;
        
        if (bathrooms != prop.getNumBath()) return false;
        
        if (furnished != prop.getFurnished()) return false;
       
        if (!quadrant.equalsIgnoreCase(prop.getQuadrant())) return false;
        
        return true;
    }

    public String toString() {
        return type + " " + bedrooms + " " + bathrooms + " " + quadrant; 
    }
}
