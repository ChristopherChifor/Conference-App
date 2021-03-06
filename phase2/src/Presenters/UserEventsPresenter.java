package Presenters;

import Controllers.EventController;
import Controllers.MessageController;
import Entities.ScheduleEntry;
import Util.UserType;
import ui.view.UserEventsView;
import ui.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author parssa
 */
public class UserEventsPresenter implements Presenter {


    private EventController eventController;

    private MainPresenter mainPresenter;
    private String username;
    private UserType userType;
    private MessageController messageController;

    /**
     *  Constructor
     * @param username username of user
     * @param mainPresenter main presenter
     * @param userType user type
     */
    public UserEventsPresenter(String username, MainPresenter mainPresenter, UserType userType) {
        this.username = username;
        this.mainPresenter = mainPresenter;
        this.userType = userType;
        eventController = new EventController();
        messageController = new MessageController();
    }

    /**
     * sends user schedule to path as pdf
     * @param filepath
     */
    public void userScheduleToPDF(String filepath) {
        eventController.convertScheduleToPDF(filepath, username, userType);
    }

    @Override
    public View makeView() {
        return new UserEventsView(this);
    }

    @Override
    public MainPresenter getMainPresenter() {
        return mainPresenter;
    }

    /**
     * gets username of user
     * @return usernmae
     */
    public String getUsername() {
        return username;
    }

    /**
     * gets user events
     * @return a list of user event names
     */
    public List<String> getUserEvents() {
        if(userType == UserType.ATTENDEE || userType == UserType.VIP)
            return eventController.getAttendeeEvents(username).stream().map(ScheduleEntry::getEventName).collect(Collectors.toList());
        if(userType == UserType.SPEAKER)
            return eventController.getSpeakerEvents(username).stream().map(ScheduleEntry::getEventName).collect(Collectors.toList());
        return new ArrayList<>();
    }

    /**
     * Opens list of all events (vip filter if user is vip)
     */
    public void goToEventsList() {
        EventListPresenter list = new EventListPresenter(username, userType, mainPresenter);
        mainPresenter.addPresenter(list);
    }

    /**
     * Getter for the user type of the user to whom this presenter belongs.
     *
     * @return usertype
     */
    public UserType getUserType() {
        return userType;
    }

    /**
     * Sends message to event names. (Assumes the user can perform this action)
     *
     * @param eventNames list of names of events being messaged
     * @param message    message being sent
     */
    public void messageEvents(List<String> eventNames, String message) {
        for (String event: eventNames){
            messageController.messageAll(event, message, username);
        }

    }

    /**
     * Takes user to eventname's EventPresenter
     *
     * @param eventName the name of the event
     */
    public void goToEvent(String eventName) {
        EventPresenter ep = new EventPresenter(eventName, username, mainPresenter, userType);
        mainPresenter.addPresenter(ep);
    }

    /**
     * Unenrolls the user (assumes attendee) from the list of events (user is enrolled in these events)
     *
     * @param eventNames events being unerolled from
     */
    public void unenroll(List<String> eventNames) {
        eventNames.stream().forEach(name -> eventController.cancelEnrolment(name, username));
    }
}
