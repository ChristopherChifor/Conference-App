package Controllers;

import UseCases.ScheduleManager;
import Util.PDFConverter;
import Util.UserType;

import java.util.ArrayList;

/**
 * @author parssa
 */
public class EventController {
    private UserType userType;
    private PDFConverter pdfConverter; // TODO make sure this gets set

    private ScheduleManager scheduleManager;

    public EventController() {
        pdfConverter = new PDFConverter();
    }

    public void convertScheduleToPDF(String filepath, String username) {
        // TODO
        ArrayList<String> userEvents = scheduleManager.getAttendeeEvents(username);
        pdfConverter.convertToPDF(filepath, username, userEvents);
    }

}
