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

    // key is the user's username, value is a list of usernames that are user's friend:
    HashMap<String, List<String>> friendsDatabase;

    // adds friend to users friend list
    public boolean addFriend(String username, User friendUsername) {
        // TODO
        return false;
    }

    //removes friend from friend list
    public boolean removeFriend(String username, String friendUsername) {
        // TODO
        return false;
    }

    // returns true if the two inputed usernames are friends
    public boolean isFriend(String username, String otherUsername) {
        // TODO
        return false;
    }

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

    // returns a list of usernames of the friends of this user
    public List<String> getFriends(String username) {
        // TODO
        return null;
    }
}
