package UseCases;

import Entities.Event;
import Entities.ScheduleEntry;
import Gateways.JsonDatabase;

import java.io.Serializable;
import java.util.*;

/**
 * @author Haoming & Parssa
 */
public class ScheduleManager implements Serializable {
    private JsonDatabase<Event> eventJsonDatabase;
    private JsonDatabase<ScheduleEntry> scheduleEntryJsonDatabase;
    private AccountManager accountManager; // todo make sure this gets set
    private RoomManager roomManager; // todo make sure this gets set

    public ScheduleManager() {
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
        if (!eventExists(eventName)) {
            return false;
        }
        if (getEvent(eventName).getEventCapacity() > roomManager.getRoom(roomName).getRoomCapacity()) {
            return false;
        }
        ScheduleEntry scheduleEntry = new ScheduleEntry(eventName, roomName, time, duration);
        scheduleEntryJsonDatabase.write(scheduleEntry, eventName);
        return true;
    }

    /**
     * Gets a schedule of all event's an attendee is enrolled in
     *
     * @param username Username of the attendee
     * @return list containing event names of event the attendee is in
     */
    public ArrayList<ScheduleEntry> getAttendeeEvents(String username) {
        ArrayList<String> allEvents = (ArrayList<String>) eventJsonDatabase.getIds();
        ArrayList<ScheduleEntry> userEvents = new ArrayList<>();

        for (String eventName : allEvents) {
            if (eventJsonDatabase.read(eventName).getAttendees().contains(username)) {
                userEvents.add(scheduleEntryJsonDatabase.read(eventName));
            }
        }
        return userEvents;
    }

    /**
     * Gets a schedule of all event's of a Speaker
     *
     * @param username Username of the speaker
     * @return Schedule containing only event's a Speaker is speaking at
     */
    protected ArrayList<ScheduleEntry> getSpeakerEvents(String username) {
        ArrayList<String> allEvents = (ArrayList<String>) eventJsonDatabase.getIds();
        ArrayList<ScheduleEntry> speakerEvents = new ArrayList<>();

        for (String eventName : allEvents) {
            if (eventJsonDatabase.read(eventName).getSpeakers().contains(username)) {
                speakerEvents.add(scheduleEntryJsonDatabase.read(eventName));
            }
        }
        return speakerEvents;
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
        } else return false;
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
     * Gets list of attendees (as strings) of an event
     *
     * @param eventName Name of Event that we want attendees from
     * @return the list of attendees of the event (is also empty if the event does not exist)
     */
    public ArrayList<String> getEventAttendees(String eventName) {
        ArrayList<String> attendees = new ArrayList<>();

        if (eventExists(eventName)) {
            for (String e : getEvent(eventName).getAttendees()) {
                attendees.add(e);
            }
        }

        return attendees;
    }

    /**
     * Assigns speaker to an event
     *
     * @param speaker Name of Speaker to be added
     * @param event Name of event
     * @return true if an event exists and speaker is added
     */
    public boolean assignSpeaker(String speaker, String event) {
        if (eventExists(event)) {
            return getEvent(event).setSpeaker(event);
        }
        return false;
    }

    /**
     * Creates a new Event
     *
     * @param eventName Name of Event that is to be created
     * @param eventCapacity Capacity of Event that is to be created
     * @return true if no other event has that name and capacity is positive and new Event is created
     */
    public boolean createEvent(String eventName, int eventCapacity, String roomName, Calendar time, int duration) {
        if (eventExists(eventName)) return false;
        Event event = new Event(eventName);
        if (!event.setEventCapacity(eventCapacity)) {
            return false;
        }
        eventJsonDatabase.write(event, eventName);
        addNewEvent(roomName, eventName, time, duration);
        return true;
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
     *
     * @param username username of user trying to sign up for event
     * @param event name of event
     * @return true if succesfully signed up for event
     */
    public boolean signUpForEvent(String username, String event) {
        if (canSignUpForEvent(username, event)) {
            eventJsonDatabase.read(event).addAttendeeToEvent(username);
            return true;
        }
        return false;
    }
}
