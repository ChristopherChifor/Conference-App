package UseCases;

import Entities.Schedule;

import java.io.Serializable;

/**
 * Use-case class for signing users up for events and cancelling their enrollment in events.
 *
 * @author Christopher
 */
public class ConferenceManager implements Serializable {
    ScheduleManager scheduleManager;
    AccountManager accountManager;

    public ConferenceManager(ScheduleManager scheduleManager, AccountManager accountManager) {
        this.scheduleManager = scheduleManager;
        this.accountManager = accountManager;
    }

    /**
     * Method for checking if user can sign up for an event.
     *
     * @param username  the user being checked.
     * @param eventName the name of event.
     * @return true iff
     * 1) attendee exists
     * 2) event exists
     * 3) event has not occurred
     * 4) the event is not full
     */
    public boolean canSignUpForEvent(String username, String eventName) {
        return !(accountManager.getUser(username) == null || !scheduleManager.eventExists(eventName) ||
                scheduleManager.eventHasHappened(eventName) || scheduleManager.eventFull(eventName));
    }

    /**
     * Signs up user for event.
     *
     * @param eventName: the event the user will sign up for
     * @param username:  username of the user
     * @return true if user is added to the event
     */
    public boolean signUpForEvent(String username, String eventName) {
        if (canSignUpForEvent(username, eventName)) {
            return scheduleManager.getEvent(eventName).addAttendeeToEvent(username);
        } else return false;
    }

    /**
     * Cancels this user's enrollment to event.
     *
     * @param eventName: the name of the event the user cancels enrollment to
     * @param username:  username of the user
     * @return true if user removed from event
     */
    public boolean cancelEnrolment(String username, String eventName) {
        if (scheduleManager.eventExists(eventName)) {
            return scheduleManager.getEvent(eventName).removeAttendeeFromEvent(username);
        } else return false;
    }

    /**
     * Gets a schedule of the events this user is enrolled to attend.
     *
     * @param username: username of user
     * @return schedule of events this user is enrolled in
     */
    public Schedule enrolledEvents(String username) {
        return scheduleManager.getAttendeeEvents(username);
    }


}
