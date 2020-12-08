package Controllers;

import Entities.User;
import UseCases.ScheduleManager;
import Util.PDFConverter;

import java.util.ArrayList;

/**
 * @author parssa
 */
public class EventController {

    private String username; // TODO MAKE THIS REFERENCE PROPERLY
    private User.UserType userType;
    private PDFConverter pdfConverter; // TODO make sure this gets set

    private ScheduleManager scheduleManager;

    public EventController() {
        pdfConverter = new PDFConverter();
        this.username = "Parssa Kyanzadeh";
    }

    public void convertScheduleToPDF(String filepath) {
        // TODO
//        ArrayList<String> userEvents = scheduleManager.getAttendeeEvents(username);
        pdfConverter.convertToPDF(filepath, username);
    }

}
