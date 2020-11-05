package UseCases;

import Entities.Room;
import Entities.Schedule;
import Entities.User;

import java.sql.Time;
import java.util.HashMap;
import java.util.List;

/**
 * @author Payam
 */
public class SocialManager {

    ScheduleManager scheduleManager;
    AccountManager accountManager;

    // returns true if two usernames share an event in common
    public boolean isInEventTogether(String username, String otherUsername) {

        Schedule attendeeSchedule = scheduleManager.getAttendeeEvents(username);

        return isUserInSchedule(otherUsername, attendeeSchedule);
    }

    private boolean isUserInSchedule(String otherUsername, Schedule attendeeSchedule) {
        for (Time time: attendeeSchedule.getSchedule().keySet()) {
            for (Room room: attendeeSchedule.getSchedule().get(time).keySet()) {
                if(attendeeSchedule.getSchedule().get(time).get(room).getAttendees().contains(otherUsername)) {
                    return true;
                }
            }
        }
        return false;
    }

    // returns true if attendee is attending speaker's event.
    public boolean isAttendeeForSpeaker(String speaker, String attendee) {

        Schedule speakerSchedule = scheduleManager.getSpeakerEvents(speaker);

        return isUserInSchedule(attendee, speakerSchedule);
    }
}
