package UseCases;

import Entities.Schedule;
import Util.ScheduleTime;

import java.io.Serializable;

/**
 * @author Payam
 */
public class SocialManager implements Serializable {

    ScheduleManager scheduleManager;

    public SocialManager(ScheduleManager scheduleManager) {
        this.scheduleManager = scheduleManager;
    }

    // TODO UNUSED
    // returns true if two usernames share an event in common
    public boolean isInEventTogether(String username, String otherUsername) {

        Schedule attendeeSchedule = scheduleManager.getAttendeeEvents(username);

        return isUserInSchedule(otherUsername, attendeeSchedule);
    }

    /**
     * Method for checking if a user is in a given schedule
     *
     * @param username         Username of the user
     * @param attendeeSchedule The schedule to be checked
     * @return true iff user is in attendeeSchedule
     */
    private boolean isUserInSchedule(String username, Schedule attendeeSchedule) {
        for (ScheduleTime time : attendeeSchedule.getSchedule().keySet()) {
            for (String room : attendeeSchedule.getSchedule().get(time).keySet()) {
                if (scheduleManager.getEvent(attendeeSchedule.getSchedule().get(time).get(room)).getAttendees().contains(username)) {
                    return true;
                }
            }
        }
        return false;
    }

    // returns true if attendee is attending speaker's event.
    // TODO UNUSED
    public boolean isAttendeeForSpeaker(String speaker, String attendee) {
        Schedule speakerSchedule = scheduleManager.getSpeakerEvents(speaker);
        return isUserInSchedule(attendee, speakerSchedule);
    }
}
