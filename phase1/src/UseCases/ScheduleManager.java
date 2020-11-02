package UseCases;

import Entities.Event;
import Entities.Schedule;

/**
 * @author Haoming
 */
public class ScheduleManager {
    Schedule theSchedule; // should never be given out; its mutable
    AccountManager accounts;


    public boolean addNewEvent(String speaker, Event event) {
        // TODO
        //  Add new event to the schedule
        return false;
    }

    protected Schedule getAttendeeEvents(String username) {
        // TODO
        //  return a schedule of events where this user is attendee
        return null;
    }

    protected Schedule getSpeakerEvents(String username) {
        // TODO
        //  return a schedule of events where username is speaker, if there are none, return empty.
        return null;
    }
}
