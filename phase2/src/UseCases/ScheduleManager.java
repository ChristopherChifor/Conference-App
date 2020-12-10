package UseCases;

import Entities.Event;
import Entities.ScheduleEntry;
import Gateways.IGateway;
import Gateways.JsonDatabase;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Haoming & Parssa
 */
public class ScheduleManager implements Serializable {
    private IGateway<Event> eventJsonDatabase;
    private IGateway<ScheduleEntry> scheduleEntryJsonDatabase;

    private AccountManager accountManager; // todo make sure this gets set


    public ScheduleManager() {

        accountManager = new AccountManager();
        eventJsonDatabase = new JsonDatabase<>("Event", Event.class);
        scheduleEntryJsonDatabase = new JsonDatabase<>("Schedule Entry", ScheduleEntry.class);
    }

    /**
     * Adds a new event to the schedule at a certain time in a room
     *
     * @param roomName  Room that the event is going inside
     * @param eventName Event that is being added
     * @param time      The time that the event is taking place
     * @return true if the event was successfully added
     */
    private boolean addNewEvent(String roomName, String eventName, Calendar time, int duration) {

        ScheduleEntry scheduleEntry = new ScheduleEntry(eventName, roomName, time, duration);
        scheduleEntryJsonDatabase.write(scheduleEntry, eventName);
        return true; //TODO maybe get rid of bool
    }

    /**
     * Gets a schedule of all event's an attendee is enrolled in
     *
     * @param username Username of the attendee
     * @return list containing event names of event the attendee is in
     */
    public List<ScheduleEntry> getAttendeeEvents(String username) {
        return eventJsonDatabase.filterStream(e->e.getAttendees()
                .contains(username))
                .map(e->scheduleEntryJsonDatabase.read(e.getName()))
                .collect(Collectors.toList());
    }

    /**
     * Gets a list of ScheduleEntries of all event's of a Speaker
     *
     * @param username Username of the speaker
     * @return List of ScheduleEntries containing only event's a Speaker is speaking at
     */
    public List<ScheduleEntry> getSpeakerEvents(String username) {
        return eventJsonDatabase.filterStream(e->e.getSpeakers()
                .contains(username))
                .map(e->scheduleEntryJsonDatabase.read(e.getName()))
                .collect(Collectors.toList());
    }

    /**
     * Checks if an event exists
     *
     * @param eventName Name of event that is being checked
     * @return true if event exists
     */
    public boolean eventExists(String eventName) {
        return eventJsonDatabase.getIds().contains(eventName);
    }

    /**
     * Checks if event has already happened
     *
     * @param eventName Event that is being checked
     * @return true if event has already occurred
     */
    public boolean eventHasHappened(String eventName) {
        if (eventExists(eventName)) {
            return true;
        } else return false; //TODO need to account for if event has actually happened
    }

    /**
     * Checks if an event is full
     *
     * @param eventName Event that is being checked
     * @return true if the event is full
     */
    public boolean eventFull(String eventName) {
        return eventJsonDatabase.read(eventName).isEventFull();
    }

    /**
     * Gets Event from it's name
     *
     * @param eventName Name of Event that is to be taken
     * @return the event if it exists and null otherwise
     */
    public Event getEvent(String eventName) {
        return eventJsonDatabase.read(eventName);
    }

    /**
     * Gets set of attendees (as strings) of an event
     *
     * @param eventName Name of Event that we want attendees from
     * @return the list of attendees of the event (is also empty if the event does not exist)
     */
    public Set<String> getEventAttendees(String eventName) {
        return getEvent(eventName).getAttendees();
    }

    public boolean inEvent(String eventName, String username) {
        return getEvent(eventName).getAttendees().contains(username);
    }

    /**
     * Assigns speaker to an event
     *
     * @param speaker Name of Speaker to be added
     * @param event Name of event
     * @return true if an event exists and speaker is added
     */
    public boolean assignSpeaker(String speaker, String event) {
        return getEvent(event).setSpeaker(event);
    }

    /**
     * Creates a new Event
     *
     * @param eventName Name of Event that is to be created
     * @param eventCapacity Capacity of Event that is to be created
     * @return true if no other event has that name and capacity is positive and new Event is created
     */
    public boolean createEvent(String eventName, int eventCapacity, String roomName, Calendar time, int duration) {
        Event event = new Event(eventName);
        eventJsonDatabase.write(event, eventName);
        setEventCapacity(eventName, eventCapacity);
        addNewEvent(roomName, eventName, time, duration);
        return true;
    }

    /**
     * Setter for the capacity of events
     *
     * @param capacity: capacity of event
     * @return true if capacity is positive and is successfully set
     */
    public boolean setEventCapacity(String event, int capacity) {
        return getEvent(event).setEventCapacity(capacity);
    }


    /**
     * Method for checking if user can sign up for an event.
     *
     * @param username  the user being checked.
     * @param eventName the name of event.
     * @return true iff
     * 1) attendee exists
     * 2) event exists
     * 3) event has not occurred
     * 4) the event is not full
     */
    public boolean canSignUpForEvent(String username, String eventName) {
        return !(accountManager.getUser(username) == null || !eventExists(eventName) ||
                eventHasHappened(eventName) || eventFull(eventName));
    }

    /**
     * Removes Attendee from an event
     * @param username the user being checked.
     * @param event name of event
     * @return true if succesfully removed from event
     */
    public boolean removeFromEvent(String username, String event) {
        if (eventExists(event)) {
            return eventJsonDatabase.read(event).removeAttendeeFromEvent(username);
        }
        return false;
    }

    /**
     * Signs Attendee up for event
     * @param username username of user trying to sign up for event
     * @param event name of event
     * @return true if succesfully signed up for event
     */
    public boolean signUpForEvent(String username, String event) {
        if (canSignUpForEvent(username, event)) {
            return eventJsonDatabase.read(event).addAttendeeToEvent(username);
        }
        return false;
    }

    // todo
    public List<ScheduleEntry> getRoomEvents(String roomID) {
        return null;
    }
}
