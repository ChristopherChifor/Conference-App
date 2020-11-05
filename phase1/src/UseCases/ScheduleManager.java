package UseCases;

import Entities.Event;
import Entities.Room;
import Entities.Schedule;
import Entities.ScheduleTime;

import java.sql.Time;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Haoming
 */
public class ScheduleManager {
    Schedule theSchedule; // should never be given out; its mutable
    AccountManager accounts;

    /**
     *  Adds a new event to the schedule at a certain time in a room
     *
     * @param room Room that the event is going inside
     * @param event Event that is being added
     * @param time The time that the event is taking place
     * @return true if the event was successfully added
     */
    public boolean addNewEvent(Room room, Event event, Time time) {
        return theSchedule.addToSchedule(room, event, time);
    }

    /**
     *  Gets a schedule of all event's an attendee is enrolled in
     * @param username Username of the attendee
     * @return Schedule containing only event's the attendee is in
     */
    protected Schedule getAttendeeEvents(String username) {
        HashMap<Time, HashMap<Room, Event>> schedule = theSchedule.getSchedule();
        Schedule attendeeSchedule = new Schedule();
        for (Map.Entry<Time, HashMap<Room, Event>> timeEntry : schedule.entrySet()) {
            Time time = timeEntry.getKey();
            for (Map.Entry<Room, Event> roomEntry : timeEntry.getValue().entrySet()) {
                Room room = roomEntry.getKey();
                Event event = roomEntry.getValue();
                if (event.getAttendees().contains(username)) {
                    attendeeSchedule.addToSchedule(room, event, time);
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
        HashMap<Time, HashMap<Room, Event>> schedule = theSchedule.getSchedule();
        Schedule speakerSchedule = new Schedule();
        for (Map.Entry<Time, HashMap<Room, Event>> timeEntry : schedule.entrySet()) {
            Time time = timeEntry.getKey();
            for (Map.Entry<Room, Event> roomEntry : timeEntry.getValue().entrySet()) {
                Room room = roomEntry.getKey();
                Event event = roomEntry.getValue();
                if (event.getSpeaker().getUsername().equals(username)) {
                    speakerSchedule.addToSchedule(room, event, time);
                }
            }
        }
        if (! speakerSchedule.getSchedule().isEmpty()) {
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
        // TODO
        //  returns true if event exists
        HashMap<Time, HashMap<Room, Event>> schedule = theSchedule.getSchedule();
        for (Map.Entry<Time, HashMap<Room, Event>> timeEntry : schedule.entrySet()) {
            Time time = timeEntry.getKey();
            if (schedule.get(time).containsValue(event)) {
                return true;
            }
        }
        return false;
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
     *  Checks if an event is full
     * @param event Event that is being checked
     * @return true if the event is full
     */
    public boolean eventFull(Event event) {
        return false;
    }
}
