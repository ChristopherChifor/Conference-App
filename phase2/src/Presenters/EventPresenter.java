package Presenters;

import Controllers.EventController;
import Controllers.MessageController;
import Util.UserType;
import ui.state.EventBundle;
import ui.view.EventView;
import ui.view.View;

public class EventPresenter implements Presenter{
    private EventController eventController;
    private String username;
    private MainPresenter mainPresenter;
    private String eventName;
    private UserType userType;
    private MessageController messageController;

    /**
     *  Constructor
     * @param eventName event name
     * @param username username
     * @param mainPresenter main presenter
     * @param userType user type
     */
    public EventPresenter(String eventName, String username, MainPresenter mainPresenter, UserType userType) {
        this.eventController = new EventController();
        this.username = username;
        this.mainPresenter = mainPresenter;
        this.eventName = eventName;
        this.userType = userType;
        this.messageController = new MessageController();
    }

    /**
     * Cancels enrolment of current user from event.
     * @param eventName name of event.
     */
    public void cancelEnrolment(String eventName) {
        eventController.attendeeInEvent(eventName, username);
    }

    /**
     *  gets if attendee is in event
     * @param eventName name of event
     * @return true iff attendee is in event
     */
    public boolean attendeeInEvent(String eventName) {
        return eventController.attendeeInEvent(eventName, username);
    }

    /**
     *  gets if user can sign up for event
     * @param eventName name of event
     * @return true iff can sign up for event
     */
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

    /**
     * Makes a view
     * @return view it makes
     */
    @Override
    public View makeView() {
        EventBundle eventBundle = eventController.createEventBundle(eventName);
        return new EventView(eventBundle, userType, this);
    }

    /**
     * gets main presenter
     * @return main presenter
     */
    @Override
    public MainPresenter getMainPresenter() {
        return mainPresenter;
    }

    public void eventEdit(View view){
        boolean canEdit = eventController.eventHasHappened(eventName);

        if(!canEdit){
            view.showErrorDialog("Can't Edit This Event! (Event in the past)");
            return;
        }

        EventEditPresenter ep = new EventEditPresenter(eventName, mainPresenter);
        mainPresenter.addPresenter(ep);
    }

    /**
     * Calls controller to send message to speakers
     * @param message message being sent
     */
    public void messageSpeakers(String message){
        for(String speaker: eventController.createEventBundle(eventName).getSpeaker()){
            messageController.sendMessage(username, speaker, message);
        }
    }

    /**
     * Calls controller to message this event (assumes this user can message the event)
     * @param message message being sent
     */
    public void messageEvent(String message){
        messageController.messageAll(eventName, message, username);
    }
}
