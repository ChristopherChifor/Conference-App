package Presenters;

import Controllers.EventController;
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

    public UserEventsPresenter(String username, MainPresenter mainPresenter, UserType userType) {
        System.out.println("created a new presenter");
        this.username = username;
        this.mainPresenter = mainPresenter;
        this.userType = userType;
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


    public UserType getUserType() {
        return userType;
    }
}
