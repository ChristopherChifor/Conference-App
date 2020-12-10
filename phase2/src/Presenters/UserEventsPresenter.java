package Presenters;

import Controllers.EventController;
import ui.view.UserEventsView;
import ui.view.View;

/**
 *  @author parssa
 */
public class UserEventsPresenter implements Presenter{

    private EventController eventController;
    private String username;
    private MainPresenter mainPresenter;

    public UserEventsPresenter(String username, MainPresenter mainPresenter) {
        System.out.println("created a new presenter");
        this.username = username;
        this.mainPresenter = mainPresenter;
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
}
