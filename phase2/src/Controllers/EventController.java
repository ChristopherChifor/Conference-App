package Controllers;

import Entities.ScheduleEntry;
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

    /**
     * Cancels enrolment of current user from event.
     * @param eventName name of event.
     */
    void cancelEnrolment(String eventName, String username) {
            scheduleManager.removeFromEvent(username, eventName);
    }

    /**
     *  Signs up attendee for event
     * @param eventName name of the event
     */
    public void signUpEvent(String eventName, String username) {
        scheduleManager.signUpForEvent(username, eventName);
    }

    public void convertScheduleToPDF(String filepath, String username) {
        // TODO
        ArrayList<ScheduleEntry> userEvents = scheduleManager.getAttendeeEvents(username);
        pdfConverter.convertToPDF(filepath, username, userEvents);
    }

}
