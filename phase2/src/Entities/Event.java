package Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Parssa
 */
public class Event implements Serializable {
    private String name;
    private ArrayList<String> speakers = new ArrayList<String>();
    private int minutes;
    private int eventCapacity;

    private boolean VIPOnly;

    private Set<String> attendees = new HashSet<String>();
    public enum EventType { TALK, PARTY, PANEL}
    private EventType eventType;

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    /**
     *  Creates new event
     * @param name: name of the event
     */
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
     * @return true if speaker isn't already added and speaker is added to this event
     */
    public boolean setSpeaker(String username) {
        if (! speakers.contains(username)) {
            return speakers.add(username);
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
    public ArrayList<String> getSpeakers() {
        return speakers;
    }
    /**
     * Setter for the duration of event in minutes
     *
     * @param duration: duration of event in minutes
     * @return true if minutes is positive and duration is set
     */
    public boolean setDuration(int duration) {
        if (duration > 0) {
            minutes = duration;
            return true;
        }
        return false;
    }
    /**
     * Setter for the capacity of events
     *
     * @param capacity: capacity of event
     * @return true if capacity is positive and is successfully set
     */
    public boolean setEventCapacity(int capacity) {
        if (capacity>= 1) {
            eventCapacity = capacity;
            return true;
        }
        return false;
    }
    /**
     * Getter for the capacity of events
     *
     * @return capacity of event
     */
    public int getEventCapacity() {
        return eventCapacity;
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
     * Checks if event is full
     *
     * @return true if event is full
     */
    public boolean isEventFull() {
        return numberOfAttendeesInEvent() ==  eventCapacity;
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

    /**
     *  Getter for isVIPOnly
     * @return true if is vip event
     */
    public boolean isVIPOnly() {
        return VIPOnly;
    }

    /**
     * Setter for isVIPOnly
     * @param VIPOnly changed boolean value of VIPOnly status
     */
    public void setVIPOnly(boolean VIPOnly) {
        this.VIPOnly = VIPOnly;
    }
}