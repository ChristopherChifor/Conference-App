package Presenters;

import Controllers.EventController;

/**
 *  @author parssa
 */
public class UserEventPresenter {

    private EventController eventController;

    public UserEventPresenter(EventController eventController) {
        this.eventController = eventController;
    }

    public void userScheduleToPDF() {
        eventController.convertScheduleToPDF();
    }
}
