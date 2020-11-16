package Entities;

import java.io.Serializable;
import java.util.HashMap;

import Util.ScheduleTime;
/**
 * @author Andrei
 */
public class Schedule implements Serializable {
    private HashMap<ScheduleTime, HashMap<String, String>> schedule;

    public boolean addToSchedule(Room room, Event event, ScheduleTime time) {
        if (schedule.get(time).containsKey(room)) return false;
        else {
            HashMap<String, String> innerMap = new HashMap<>();
            innerMap.put(room.getName(), event.getName());
            schedule.put(time, innerMap);
            return true;
        }
    }

    public boolean addToSchedule(String room, String event, ScheduleTime time) {

        if (schedule.get(time).containsKey(room)) return false;
        else {
            HashMap<String, String> innerMap = new HashMap<>();
            innerMap.put(room, event);
            schedule.put(time, innerMap);
            return true;
        }
    }

    public HashMap<ScheduleTime, HashMap<String, String>> getSchedule() {
        return schedule;
    }
}
