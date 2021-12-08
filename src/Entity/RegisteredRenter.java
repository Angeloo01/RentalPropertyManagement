package Entity;

public class RegisteredRenter {
    String username;
    String email;
    private Inbox inbox;

    public RegisteredRenter(User u) {
        inbox = new Inbox(this);
        username = u.getUsername();
        email = u.getEmail();
    }
    public Inbox getInbox() {
        return inbox;
    }

    public void addSearchCriteria(String type, int beds, int baths, String quadrant) {
        inbox.addSearchCriteria(type, beds, baths, quadrant);
    }

    public String getUsername() {
        return username;
    }
}
