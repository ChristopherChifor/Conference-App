package UseCases;

import Entities.User;

import java.util.HashMap;
import java.util.List;

/**
 * @author Payam
 */
public class SocialManager {

    ScheduleManager scheduleManager;
    AccountManager accountManager;

    // returns true if two usernames share an event in common
    public boolean inEventTogether(String username, String otherUsername) {
        // TODO
        return false;
    }

    // returns true if attendee is attending speaker's event.
    public boolean isAttendeeForSpeaker(String speaker, String attendee) {
        // TODO
        return false;
    }
}
