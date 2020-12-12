package Entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Parssa
 */
public class Event implements Serializable {
    private String name;
    private Set<String> speakers = new HashSet<>();
    private int eventCapacity;

    private boolean VIPOnly;

    private Set<String> attendees = new HashSet<>();
    public enum EventType { TALK, PARTY, PANEL}
    private EventType eventType;

    /**
     * Sets the event type of the event
     * @param eventType: event type of the event
     */
    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    /**
     * Creates new event (Event constructor)
     * @param name: name of the event
     */
    public Event(String name) {
        this.name = name;
    }

    /**
     * Removes specified attendee from event
     * @param username: username of user
     * @return true if user is removed from event
     */
    public boolean removeAttendeeFromEvent(String username) {
        return attendees.remove(username);
    }

    /**
     * Adds specified attendee to event
     * @param username: username of user
     * @return true if username, user is added to attendees of this event
     */
    public boolean addAttendeeToEvent(String username) {
        if (isEventFull()) {
            return false;
        }
        return attendees.add(username);
    }
    /**
     * Adds specified speaker to event
     *
     * @param username: username of speaker
     * @return true if speaker isn't already added and speaker is added to this event
     */
    public boolean setSpeaker(String username) {
        return speakers.add(username);
    }
    /**
     * Removes specified speaker to event
     *
     * @param username: username of speaker
     * @return true if speaker isn't already added and speaker is added to this event
     */
    public boolean removeSpeaker(String username) {
        return speakers.remove(username);
    }
    /**
     * Getter for name
     * @return name of event
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for speakers
     * @return speaker
     */
    public Set<String> getSpeakers() {
        return speakers;
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

    /**
     * Getter for eventType
     * @return the type of event
     */
    public EventType getEventType() {
        return eventType;
    }
}