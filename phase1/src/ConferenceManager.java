public class ConferenceManager {
    ScheduleManager scheduleManager;
    AccountManager accountManager;

    public boolean signUpForEvent(String username, Event event) {
        // TODO
        return false;
    }

    public boolean cancelEnrolment(String username, Event event) {
        // TODO
        return false;
    }

    /**
     *
     * @param username: username of user
     * @return schedule of events this user is enrolled in
     */
    public Schedule enrolledEvents(String username) {
        // TODO
        return null;
    }

    /**
     *
     * @param speaker: username of speaker
     * @return schedule of events where this user is speaker (empty if there are none/not speaker)
     */
    public Schedule getTalksOf(String speaker){
        // TODO
        return null;
    }

}
