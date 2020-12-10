package Controllers;

import Entities.ScheduleEntry;
import Entities.User;
import UseCases.ScheduleManager;
import Util.PDFConverter;

import java.util.ArrayList;

/**
 * @author parssa
 */
public class EventController {
    private User.UserType userType;
    private PDFConverter pdfConverter; // TODO make sure this gets set

    private ScheduleManager scheduleManager;

    public EventController() {
        pdfConverter = new PDFConverter();
    }

    public void convertScheduleToPDF(String filepath, String username) {
        // TODO
        ArrayList<ScheduleEntry> userEvents = scheduleManager.getAttendeeEvents(username);
        pdfConverter.convertToPDF(filepath, username, userEvents);
    }

}
