import java.util.HashMap;

public class Event {
    String name;
    User speaker = null;
    double duration = 1d;

    HashMap<String, User> attendees = null;

    Boolean atCapacity;
    Boolean canModifyEvent;
    Boolean hasHappened;

    public Event(String name) {
        this.name = name;
    }
}
