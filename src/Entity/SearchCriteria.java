package Entity;

public class SearchCriteria {
    private String type;
    private int bedrooms;
    private int bathrooms;
    private String quadrant;
    
    public SearchCriteria(String type, int beds, int baths, String quadrant) {
        this.type = type;
        bedrooms = beds;
        bathrooms = baths;
        this.quadrant = quadrant;
    }

    public boolean matches(Property prop) {
        if (type != prop.getType()) return false;
        if (bedrooms != prop.getNumBed()) return false;
        if (bathrooms != prop.getNumBath()) return false;
        if (!quadrant.equals(prop.getQuadrant())) return false;
        return true;
    }

    public String toString() {
        return type + " " + bedrooms + " " + bathrooms + " " + quadrant; 
    }
}
