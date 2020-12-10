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

    public EventController(ScheduleManager scheduleManager) {
        this.scheduleManager = scheduleManager;
        pdfConverter = new PDFConverter();
    }

    /**
     * Cancels enrolment of current user from event.
     * @param eventName name of event.
     */
    public void cancelEnrolment(String eventName, String username) {
            scheduleManager.removeFromEvent(username, eventName);
    }

    public boolean attendeeInEvent(String eventName, String username) {
        return scheduleManager.inEvent(eventName, username);
    }

    /**
     * Method for checking if user can sign up for an event (calls scheduleManager.canSignUpForEvent())
     *
     * @param username  the user being checked.
     * @param eventName the name of event.
     * @return true iff
     * 1) attendee exists
     * 2) event exists
     * 3) event has not occurred
     * 4) the event is not full
     */
    public boolean canSignUpForEvent(String username, String eventName) {
        return scheduleManager.canSignUpForEvent(username, eventName);
    }

    /**
     *  Signs up attendee for event
     * @param eventName name of the event
     */
    public void signUpEvent(String username, String eventName) {
        scheduleManager.signUpForEvent(username, eventName);
    }

    public void convertScheduleToPDF(String filepath, String username) {
        // TODO
        ArrayList<ScheduleEntry> userEvents = scheduleManager.getAttendeeEvents(username);
        pdfConverter.convertToPDF(filepath, username, userEvents);
    }

}
