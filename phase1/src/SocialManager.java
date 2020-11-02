import java.util.List;

public class SocialManager {

    ScheduleManager scheduleManager;
    AccountManager accountManager;

    // adds friend to users friend list
    public boolean addFriend(String username, User friendUsername){
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
    public List<String> getFriends(String username){
        // TODO
        return null;
    }
}
