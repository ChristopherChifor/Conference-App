package Presenters;

import Controllers.EventController;
import Util.UserType;
import ui.state.EventBundle;
import ui.view.EventView;
import ui.view.View;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EventPresenter implements Presenter{
    private EventController eventController;
    private String username;
    private MainPresenter mainPresenter;
    private String eventName;
    private UserType userType;

    public EventPresenter(String eventName, String username, MainPresenter mainPresenter, UserType userType) {
        this.eventController = new EventController();
        this.username = username;
        this.mainPresenter = mainPresenter;
        this.eventName = eventName;
        this.userType = userType;
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
        List<String> speakers = new ArrayList<>();
        speakers.add("IMPLEMENT SPEAKERRR");
        Calendar time = Calendar.getInstance();
        EventBundle eventBundle = new EventBundle(eventName, "TODOOOOO", speakers, "IMPLEMENT ROOOOOm", time, "1:00", 10, false); //todo get vip status
        return new EventView(eventBundle, userType, this);
    }

    @Override
    public MainPresenter getMainPresenter() {
        return mainPresenter;
    }
}
