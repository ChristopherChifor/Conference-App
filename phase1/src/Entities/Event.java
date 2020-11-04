package Entities;

import java.util.HashMap;

/**
 * @author Parssa
 */
public class Event {
    String name;
    User speaker = null;
    int minutes = 60;

    HashMap<String, User> attendees = null;

    Boolean atCapacity;
    Boolean canModifyEvent;
    Boolean hasHappened;

    public Event(String name) {
        this.name = name;
    }

    public boolean removeAttendeeFromEvent(String username) {
        // TODO discuss with TA
        if (!attendees.containsKey(username)) {
            return false;
        }
        attendees.remove(username);
        return true;
    }

    public boolean addAttendeeToEvent(String username, User user) {
        // TODO discuss with TA
        attendees.put(username, user);
        return true;
    }
}
