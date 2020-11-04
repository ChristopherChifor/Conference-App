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
}
