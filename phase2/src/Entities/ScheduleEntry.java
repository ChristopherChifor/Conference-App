package Entities;

import java.util.Calendar;

public class ScheduleEntry {
    String eventName;
    String roomID;
    Calendar startTime;
    int duration;

    /**
     * Constructor for an entry into the schedule.
     * @param eventName name of the event
     * @param roomID id of the room
     * @param startTime starting time of the event
     * @param duration length of the event
     */
    public ScheduleEntry(String eventName, String roomID, Calendar startTime, int duration) {
        this.eventName = eventName;
        this.roomID = roomID;
        this.startTime = startTime;
        this.duration = duration;
    }

    /**
     * Returns the name of the event as a string
     * @return eventName (String)
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * Gets the id of the room as a string.
     * @return id of the room (String)
     */
    public String getRoomID() {
        return roomID;
    }

    /**
     * Gets the start time of an event.
     * @return calendar
     */
    public Calendar getStartTime() {
        return startTime;
    }

    /**
     * Gets the duration of an event
     * @return duration of the event
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Sets the id of a room
     * @param roomID id of a room
     */
    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }
}
