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


    public boolean addNewEvent(Room room, Event event, Time time) {
        // TODO
        //  Add new event to the schedule
        return theSchedule.addToSchedule(room, event, time);
    }

    protected Schedule getAttendeeEvents(String username) {
        // TODO
        //  return a schedule of events where this user is attendee
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

    public boolean eventHasHappened (Event event) {
        // TODO
        return false;
    }

    public boolean eventFull(Event event) {
        return false;
    }
}
