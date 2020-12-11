package Presenters;

import Controllers.EventController;
import ui.view.View;

public class SchedulePresenter implements Presenter {
    private EventController eventController;
    private String username;
    private MainPresenter mainPresenter;

    public SchedulePresenter(String eventName, String username, MainPresenter mainPresenter) {
        this.eventController = new EventController();
        this.username = username;
        this.mainPresenter = mainPresenter;
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
        return eventController.canSignUpForEvent(eventName);
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
//         return new EventView(); // TODO ALEX WILL DEAL WITH THAT
        return null;
    }

    @Override
    public MainPresenter getMainPresenter() {
        return mainPresenter;
    }
}
