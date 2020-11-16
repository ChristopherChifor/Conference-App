package UseCases;

import Entities.Event;
import Entities.Room;
import Entities.Schedule;
import Entities.ScheduleTime;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Haoming & Parssa
 */
public class ScheduleManager implements Serializable {
    Schedule theSchedule; // should never be given out; its mutable
    HashMap<String, Event> events; // should never be given out; its mutable
    HashMap<String, Room> rooms; // should never be given out; its mutable

    public ScheduleManager() {
        theSchedule = new Schedule();
        events = new HashMap<>();
        rooms = new HashMap<>();
    }

    public HashMap<ScheduleTime, HashMap<String, String>> getTheSchedule() {
        return theSchedule.getSchedule();
    }

//    /**
//     *  Adds a new event to the schedule at a certain time in a room
//     *
//     * @param room Room that the event is going inside
//     * @param event Event that is being added
//     * @param time The time that the event is taking place
//     * @return true if the event was successfully added
//     */
//    public boolean addNewEvent(Room room, Event event, ScheduleTime time) {
//        return theSchedule.addToSchedule(room, event, time);
//    }

    /**
     *  Adds a new event to the schedule at a certain time in a room
     *
     * @param room Room that the event is going inside
     * @param event Event that is being added
     * @param time The time that the event is taking place
     * @return true if the event was successfully added
     */
    public boolean addNewEvent(Room room, Event event, ScheduleTime time) {
        return theSchedule.addToSchedule(room, event, time);
    }

    /**
     *  Gets a schedule of all event's an attendee is enrolled in
     * @param username Username of the attendee
     * @return Schedule containing only event's the attendee is in
     */
    protected Schedule getAttendeeEvents(String username) {
        HashMap<ScheduleTime, HashMap<String, String>> schedule = theSchedule.getSchedule();
        Schedule attendeeSchedule = new Schedule();
        for (Map.Entry<ScheduleTime, HashMap<String, String>> timeEntry : schedule.entrySet()) {
            ScheduleTime time = timeEntry.getKey();
            for (Map.Entry<String, String> roomEntry : timeEntry.getValue().entrySet()) {
                String room = roomEntry.getKey();
                String event = roomEntry.getValue();
                if (event != null) {
                    if (getEvent(event).getAttendees().contains(username)) {
                        attendeeSchedule.addToSchedule(room, event, time);
                    }
                }

            }
        }
        return attendeeSchedule;
    }

    /**
     *  Gets a schedule of all event's of a Speaker
     * @param username Username of the speaker
     * @return Schedule containing only event's a Speaker is speaking at
     */
    protected Schedule getSpeakerEvents(String username) {
        // TODO
        //  return a schedule of events where username is speaker, if there are none, return empty.
        HashMap<ScheduleTime, HashMap<String, String>> schedule = theSchedule.getSchedule();
        Schedule speakerSchedule = new Schedule();

        for (Map.Entry<ScheduleTime, HashMap<String, String>> timeEntry : schedule.entrySet()) {
            ScheduleTime time = timeEntry.getKey();

            for (Map.Entry<String, String> roomEntry : timeEntry.getValue().entrySet()) {
                String room = roomEntry.getKey();
                String event = roomEntry.getValue();
                if (getEvent(event).getSpeaker().getUsername().equals(username)) {
                    speakerSchedule.addToSchedule(room, event, time);
                }
            }
        }
        if (!speakerSchedule.getSchedule().isEmpty()) {
            return speakerSchedule;
        }
        return null;
    }

    /**
     *  Checks if an event exists
     * @param event Event that is being checked
     * @return true if event exists
     */
    public boolean eventExists(Event event) {
        return events.containsValue(event);
    }

    /**
     *  Checks if an event exists
     * @param eventName Name of event that is being checked
     * @return true if event exists
     */
    public boolean eventExists(String eventName) {
        return events.containsKey(eventName);
    }

    /**
     *  Checks if event has already happened
     * @param event Event that is being checked
     * @return true if event has already occurred
     */
    public boolean eventHasHappened (Event event) {
        // TODO Discuss with TA
        return false;
    }

    /**
     *  Checks if event has already happened
     * @param eventName Event that is being checked
     * @return true if event has already occurred
     */
    public boolean eventHasHappened (String eventName) {
        // TODO Discuss with TA
        if (eventExists(eventName)) {
            return true;
        } else return false;
    }

    /**
     *  Checks if an event is full
     * @param event Event that is being checked
     * @return true if the event is full
     */
    public boolean eventFull(Event event) {
        // TODO
        return false;
    }

    /**
     *  Checks if an event is full
     * @param eventName Event that is being checked
     * @return true if the event is full
     */
    public boolean eventFull(String eventName) {
        // TODO
        return false;
    }


    public boolean createEvent(String eventName) {
        if (getEvent(eventName) != null) return false;
        Event event = new Event(eventName);
        events.put(eventName, event);
        return true;
    }

    public boolean createRoom(String roomName) {
        if (getRoom(roomName) != null) return false;
        Room room = new Room(roomName);
        rooms.put(roomName, room);
        return true;
    }

    public Event getEvent(String eventName) {

        return (events.containsKey(eventName)) ? events.get(eventName) : null;
    }

    public Room getRoom(String roomName) {
        return (rooms.containsKey(roomName)) ? rooms.get(roomName) : null;
    }

    public ArrayList<String> getEventAttendees(String eventName) {
        ArrayList<String> attendees = new ArrayList<>();

        if (eventExists(eventName)) {
            for (String e : getEvent(eventName).getAttendees()) {
                attendees.add(e);
            }
        }

        return attendees;
    }


    public boolean assignSpeaker(String speaker, String room, String time) {

        // TODO
        return false;
    }

}
