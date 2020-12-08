package Gateways;

import java.util.ArrayList;

/**
 * @author parssa
 */
public class ScheduleGateway {

    /**
     * Gets a list of event names a user has enrolled in
     *
     * @param username username of user
     * @return list of events by event name
     */
    public ArrayList<String> getUserEvents(String username) {
        // TODO parssa
        return new ArrayList<>();
    }

    /**
     * Get's the room an event is in
     * @param eventName name of event
     * @return
     */
    public String getEventRoom(String eventName) {
        // TODO parssa
        return "NotImplementedYet, room_of_event";
    }

    /**
     * Gets the time an event is in
     * @param eventName name of event
     * @return time event starts
     */
    public String getEventTime(String eventName) {
        // TODO parssa
        return "NotImplementedYet, event_start_time";
    }

    /**
     * Gets the duration of an event
     * @param eventName name of event
     * @return duration of event
     */
    public String getEventDuration(String eventName) {
        // TODO parssa, find out how we store duration
        return "NotImplementedYet, event_duration";
    }

    /**
     * Gets the speakers of an event
     * @param eventName name of event
     * @return a list of speakers of event, or empty list of no speakers
     */
    public ArrayList<String> getEventSpeakers(String eventName) {
        // TODO parssa
        return new ArrayList<>();
    }




}
