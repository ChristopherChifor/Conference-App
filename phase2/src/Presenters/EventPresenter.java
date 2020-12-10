package Presenters;

import Controllers.EventController;

public class EventPresenter {
    private EventController eventController;
    private String username;

    public EventPresenter(String username, EventController eventController) {
        this.eventController = eventController;
        this.username = username;
    }

    /**
     * Cancels enrolment of current user from event.
     * @param eventName name of event.
     */
    public void cancelEnrolment(String eventName) {
        eventController.attendeeInEvent(eventName, username);
    }

    public boolean attendeeInEvent(String eventName) {
        return eventController.attendeeInEvent(eventName, username);
    }

    public boolean canSignUpForEvent(String eventName) {
        return eventController.canSignUpForEvent(username, eventName);
    }

    /**
     *  Signs up attendee for event
     * @param eventName name of the event
     */
    public void signUpEvent(String eventName) {
        eventController.signUpEvent(username, eventName);
    }

}
