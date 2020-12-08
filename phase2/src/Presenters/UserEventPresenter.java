package Presenters;

import Controllers.EventController;

/**
 *  @author parssa
 */
public class UserEventPresenter {

    private EventController eventController;

    public UserEventPresenter() {
        System.out.println("created a new presenter");
        this.eventController = new EventController();
    }

    public UserEventPresenter(EventController eventController) {
        this.eventController = eventController;
    }

    public void userScheduleToPDF(String filepath) {
        eventController.convertScheduleToPDF(filepath);
    }
}
