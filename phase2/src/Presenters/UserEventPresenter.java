package Presenters;

import Controllers.EventController;

/**
 *  @author parssa
 */
public class UserEventPresenter {

    private EventController eventController;
    private String username;

    public UserEventPresenter(String username) {
        System.out.println("created a new presenter");
        this.username = username;
    }

    public UserEventPresenter(EventController eventController) {
        this.eventController = eventController;
    }

    public void userScheduleToPDF(String filepath) {
        eventController.convertScheduleToPDF(filepath, username);
    }
}
