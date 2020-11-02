package Entities;

import java.sql.Time;
import java.util.HashMap;

/**
 * @author Andrei
 */
public class Schedule {
    private HashMap<Time, HashMap<Room, Event>> schedule;

    public boolean addToSchedule(Room room, Event event, Time time) {
        // TODO
        return false;
    }

    public HashMap<Time, HashMap<Room, Event>> getSchedule() {
        return schedule;
    }
}
