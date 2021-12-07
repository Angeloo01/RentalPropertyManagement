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
        //return whether or not all search criteria fields match the property fields
        return false;
    }
}
