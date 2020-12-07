package UseCases;

import Entities.Event;

import java.util.ArrayList;
import java.util.HashMap;

public class EventManager {
    HashMap<String, Event> events; // should never be given out; its mutable

    /**
     * Constructor for EventManager
     */
    public EventManager() {
        events = new HashMap<>();
    }

    /**
     * Checks if an event exists
     *
     * @param eventName Name of event that is being checked
     * @return true if event exists
     */
    public boolean eventExists(String eventName) {
        return events.containsKey(eventName);
    }

    /**
     * Checks if an event is full
     *
     * @param eventName Event that is being checked
     * @return true if the event is full
     */
    public boolean eventFull(String eventName) {
        return events.get(eventName).isEventFull();
    }

    /**
     * Creates a new Event
     *
     * @param eventName Name of Event that is to be created
     * @param eventCapacity Capacity of Event that is to be created
     * @return true if no other event has that name and capacity is positiv and new Event is created
     */
    public boolean createEvent(String eventName, int eventCapacity) {
        if (getEvent(eventName) != null) return false;
        Event event = new Event(eventName);
        if (event.setEventCapacity(eventCapacity)) {
            return false;
        }
        events.put(eventName, event);
        return true;
    }
    /**
     * Gets Event from it's name
     *
     * @param eventName Name of Event that is to be taken
     * @return the event if it exists and null otherwise
     */
    public Event getEvent(String eventName) {

        return (events.containsKey(eventName)) ? events.get(eventName) : null;
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
}
