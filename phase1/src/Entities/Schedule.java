package Entities;

import java.sql.Time;
import java.util.HashMap;

/**
 * @author Andrei
 */
public class Schedule {
    private HashMap<Time, HashMap<Room, Event>> schedule;

    public boolean addToSchedule(Room room, Event event, Time time) {

        if (schedule.get(time).containsKey(room)) return false;
        else {
            HashMap<Room, Event> innerMap = new HashMap<Room, Event>();
            schedule.put(time, innerMap);
            innerMap.put(room, event);
            return true;
        }
    }

    public HashMap<Time, HashMap<Room, Event>> getSchedule() {
        return schedule;
    }
}
