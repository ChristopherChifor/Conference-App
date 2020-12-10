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

    public UserEventsPresenter(String username) {
        System.out.println("created a new presenter");
        this.username = username;
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
}
