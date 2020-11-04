package UseCases;

import Entities.Event;
import Entities.Schedule;
import Entities.User;

/**
 * @author Chris
 */
public class ConferenceManager {
    ScheduleManager scheduleManager;
    AccountManager accountManager;

    public ConferenceManager(ScheduleManager scheduleManager, AccountManager accountManager) {
        this.scheduleManager = scheduleManager;
        this.accountManager = accountManager;
    }

    /**
     *
     * @return true iff
     *      1) attendee exists
     *      2) event exists
     *      3) event has not occurred
     *      4) the event is not full
     *
     */
    public boolean canSignUpForEvent(String username, Event event){
        return !(accountManager.getUser(username) == null || !scheduleManager.eventExists(event) ||
                scheduleManager.eventHasHappened(event) || scheduleManager.eventFull(event));
    }

    /**
     * @param event: the event the user will sign up for
     * @param username: username of the user
     * @return true if user is added to the event
     */
    public boolean signUpForEvent(String username, Event event) {
        User thisUser = accountManager.getUser(username);
        return event.addAttendeeToEvent(username, thisUser);
    }

    /**
     * @param event: the event the user cancells enrollment to
     * @param username: username of the user
     * @return true if user removed from event
     */
    public boolean cancelEnrolment(String username, Event event) {
        return event.removeAttendeeFromEvent(username);
    }

    /**
     *
     * @param username: username of user
     * @return schedule of events this user is enrolled in
     */
    public Schedule enrolledEvents(String username) {
        return scheduleManager.getAttendeeEvents(username);
    }


}
