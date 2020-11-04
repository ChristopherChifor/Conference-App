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

    /**
     *
     * @param username: username of user
     * @return true if user is removed from event
     */
    public boolean removeAttendeeFromEvent(String username) {
        // TODO discuss with TA
        if (!attendees.containsKey(username)) {
            return false;
        }
        attendees.remove(username);
        return true;
    }

    /**
     *
     * @param username: username of user
     * @param user: user
     * @return true if username, user is added to attendees of this event
     */
    public boolean addAttendeeToEvent(String username, User user) {
        // TODO discuss with TA
        if (attendees.containsKey(username)) {
            return false;
        }
        attendees.put(username, user);
        return true;
    }
}
