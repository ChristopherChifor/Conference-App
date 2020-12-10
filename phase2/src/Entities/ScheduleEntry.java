package Entities;

import java.util.Calendar;

public class ScheduleEntry {
    String eventName;
    String roomID;
    Calendar startTime;
    int duration;

    public ScheduleEntry(String eventName, String roomID, Calendar startTime, int duration) {
        this.eventName = eventName;
        this.roomID = roomID;
        this.startTime = startTime;
        this.duration = duration;
    }

    public String getEventName() {
        return eventName;
    }

    public String getRoomID() {
        return roomID;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
