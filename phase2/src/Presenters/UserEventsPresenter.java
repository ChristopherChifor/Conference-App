package Presenters;

import Controllers.EventController;
import Controllers.MessageController;
import Entities.ScheduleEntry;
import Util.UserType;
import ui.view.UserEventsView;
import ui.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 *  @author parssa
 */
public class UserEventsPresenter implements Presenter{


    private EventController eventController;

    private MainPresenter mainPresenter;
    private String username;
    private UserType userType;
    private MessageController messageController;

    public UserEventsPresenter(String username, MainPresenter mainPresenter, UserType userType) {
        System.out.println("created a new presenter");
        this.username = username;
        this.mainPresenter = mainPresenter;
        this.userType = userType;
        eventController = new EventController();
        messageController = new MessageController();
    }

    public UserEventsPresenter(EventController eventController) {
        this.eventController = eventController;
    }

    public void userScheduleToPDF(String filepath) {
        eventController.convertScheduleToPDF(filepath, username);
    }

    @Override
    public View makeView() {
        return new UserEventsView(this);
    }

    @Override
    public MainPresenter getMainPresenter() {
        return mainPresenter;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getUserEvents() {
        return eventController.getAttendeeEvents(username).stream().map(ScheduleEntry::getEventName).collect(Collectors.toList());
    }

    /**
     * Opens list of all events (vip filter if user is vip)
     */
    public void goToEventsList(){
        EventListPresenter list = new EventListPresenter(userType==UserType.VIP,mainPresenter);
        mainPresenter.addPresenter(list);
    }

    /**
     * Getter for the user type of the user to whom this presenter belongs.
     * @return usertype
     */
    public UserType getUserType() {
        return userType;
    }

    /**
     * Sends message to event names. (Assumes the user can perform this action)
     * @param eventNames list of names of events being messaged
     * @param message  message being sent
     */
    public void messageEvents(List<String> eventNames, String message){
        //TODO IMPLEMENT
    }

    /**
     * Takes user to eventname's EventPresenter
     * @param eventName the name of the event
     */
    public void goToEvent(String eventName){
        EventPresenter ep = new EventPresenter(eventName,username, mainPresenter, userType);
        mainPresenter.addPresenter(ep);
    }

    /**
     * Unenrolls the user (assumes attendee) from the list of events (user is enrolled in these events)
     * @param eventNames events being unerolled from
     */
    public void unenroll(List<String> eventNames){
        //TODO UNEROLL FROM THESE EVENTS.
    }
}
