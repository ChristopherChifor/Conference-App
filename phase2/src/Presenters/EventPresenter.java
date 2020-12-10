package Presenters;

import Controllers.EventController;
import ui.view.EventView;
import ui.view.View;

public class EventPresenter implements Presenter{
    private EventController eventController;
    private String username;

    public EventPresenter(String username) {
        this.eventController = new EventController();
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

    @Override
    public View makeView() {
        // return new EventView(); //TODO FIGURE THIS OUT ASAP
        return null;
    }
}
