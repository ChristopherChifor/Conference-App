package UseCases;

import Entities.Schedule;
import Entities.ScheduleTime;

/**
 * @author Payam
 */
public class SocialManager {

    ScheduleManager scheduleManager;

    public SocialManager(ScheduleManager scheduleManager) {
        this.scheduleManager = scheduleManager;
    }

    // returns true if two usernames share an event in common
    public boolean isInEventTogether(String username, String otherUsername) {

        Schedule attendeeSchedule = scheduleManager.getAttendeeEvents(username);

        return isUserInSchedule(otherUsername, attendeeSchedule);
    }

    private boolean isUserInSchedule(String otherUsername, Schedule attendeeSchedule) {
        for (ScheduleTime time: attendeeSchedule.getSchedule().keySet()) {
            for (String room: attendeeSchedule.getSchedule().get(time).keySet()) {
                if(scheduleManager.getEvent(attendeeSchedule.getSchedule().get(time).get(room)).getAttendees().contains(otherUsername)) {
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
