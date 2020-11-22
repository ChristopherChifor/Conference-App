package Entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Parssa
 */
public class Event  implements Serializable {
    private String name;
    private String speaker = null;
    private int minutes = 60;

    private Set<String> attendees = new HashSet<String>();

    // Are these needed?
//    Boolean atCapacity;
//    Boolean canModifyEvent;
//    Boolean hasHappened;

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
        if (!attendees.contains(username)) {
            return false;
        }
        attendees.remove(username);
        return true;
    }

    /**
     *
     * @param username: username of user
     * @return true if username, user is added to attendees of this event
     */
    public boolean addAttendeeToEvent(String username) {
        // TODO discuss with TA
        if (attendees.contains(username)) {
            return false;
        }
        attendees.add(username);
        return true;
    }
    /**
     * Setter for speaker
     *
     * @param username: username of speaker
     * @return true if no speaker already exists and speaker is added to this event
     */
    public boolean setSpeaker(String username) {
        if (speaker == null) {
            speaker = username;
            return true;
        }
        return false;
    }
    /**
     * Getter for name
     * @return name of event
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for speaker name
     * @return speaker
     */
    public String getSpeaker() {
        return speaker;
    }

    /**
     * Getter for the duration of event
     *
     * @return duration in minutes
     */
    public int getDuration() {
        return minutes;
    }

    /**
     * Returns the number of attendees signed up for this event.
     *
     * @return number of attendees signed up for this event.
     */
    public int numberOfAttendeesInEvent(){
        return attendees.size();
    }

    /**
     * Getter for attendees' usernames.
     *
     * @return a set of attendees' usernames
     */
    public Set<String> getAttendees() {
        return attendees;
    }
}
