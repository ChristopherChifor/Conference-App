package Controllers;

import Entities.Event;
import Entities.ScheduleEntry;
import UseCases.RoomManager;
import UseCases.ScheduleManager;
import Util.PDFConverter;
import Util.UserType;
import ui.state.EventBundle;

import java.util.Calendar;
import java.util.List;

/**
 * @author parssa
 */
public class EventController {
    private UserType userType;

    private PDFConverter pdfConverter;

    private ScheduleManager scheduleManager;
    private RoomManager roomManager;


    public EventController() {
        this.scheduleManager = new ScheduleManager();
        this.roomManager = new RoomManager();
        pdfConverter = new PDFConverter();
    }

    /**
     * Cancels enrolment of current user from event.
     * @param eventName name of event.
     */
    public boolean cancelEnrolment(String eventName, String username) {
        if (scheduleManager.eventExists(eventName)) {
            return scheduleManager.removeFromEvent(username, eventName);
        }
        return false;
    }

    /**
     * Gets a schedule of all event's an attendee is enrolled in
     *
     * @param username Username of the attendee
     * @return list containing event names of event the attendee is in
     */
    public List<ScheduleEntry> getAttendeeEvents(String username) {
        return scheduleManager.getAttendeeEvents(username);
    }

    /**
     * Gets a list of ScheduleEntries of all event's of a Speaker
     *
     * @param username Username of the speaker
     * @return List of ScheduleEntries containing only event's a Speaker is speaking at
     */
    public List<ScheduleEntry> getSpeakerEvents(String username) {
        return scheduleManager.getSpeakerEvents(username);
    }

    /**
     * Checks if an event exists
     *
     * @param eventName Name of event that is being checked
     * @return true if event exists
     */
    public boolean eventExists(String eventName) {
        return scheduleManager.eventExists(eventName);
    }

    /**
     * Assigns speaker to an event
     *
     * @param speaker Name of Speaker to be added
     * @param event Name of event
     * @return true if an event exists and speaker is added
     */
    public boolean assignSpeaker(String speaker, String event) {
        if (scheduleManager.eventExists(event)) {
            return scheduleManager.assignSpeaker(speaker, event);
        }
        return false;
    }


    private boolean scheduleConflict(String roomName, Calendar time, int duration) {
        //TODO finish this method, need the method to return filtered ScheduleEntries by roomName
        return false;
    }

    /**
     * Creates a new Event
     *
     * @param eventName Name of Event that is to be created
     * @param eventCapacity Capacity of Event that is to be created
     * @return true if no other event has that name and capacity is positive and new Event is created
     */
    public boolean createEvent(String eventName, int eventCapacity, String roomName, Calendar time, int duration, List<String> speakers, boolean isVIP) {

        if (eventCapacity < 1) return false;

        if (roomManager.getRoomCapacity(roomName) < eventCapacity) return false;

        if (scheduleConflict(roomName, time, duration)) return false;


        return scheduleManager.createEvent(eventName, eventCapacity, roomName, time, duration, speakers, isVIP);
    }

    public boolean attendeeInEvent(String eventName, String username) {
        return scheduleManager.inEvent(eventName, username);
    }

    /**
     * Method for checking if user can sign up for an event (calls scheduleManager.canSignUpForEvent())
     *
     * @param eventName the name of event.
     * @return true iff
     * 1) event exists
     * 2) event has not occurred
     * 3) the event is not full
     */
    public boolean canSignUpForEvent(String eventName) {
        return scheduleManager.canSignUpForEvent(eventName);
    }

    /**
     *  Signs up attendee for event
     * @param eventName name of the event
     * @param username username of attendee
     * @return true if attendee is successfully signed up for event
     *
     */
    public boolean signUpEvent(String username, String eventName) {
        if (canSignUpForEvent(eventName)) {
            System.out.println("yes");
            return scheduleManager.signUpForEvent(username, eventName);
        }
        return false;
    }

    public void convertScheduleToPDF(String filepath, String username) {
        pdfConverter.convertToPDF(filepath, username, scheduleManager.getAttendeeEvents(username));
    }

    /**
     * Returns a list of all events (vip events hidden unles vipFilter is true)
     * @param vipFilter should vip events be shown
     * @return list of event titles.
     */
    public List<String> getEventNames(boolean vipFilter){
        return scheduleManager.getEventNames(vipFilter);
    }

    /**
     * Getter for a list of VIP Event Names
     * @return a list of vip events
     */
    public List<String> getVIPEventNames(){
        return scheduleManager.getVIPEventNames();
    }

    /**
     * Getter for a list of all room names
     * @return a list of all room names
     */
    public List<String> getRoomNames(){
        return roomManager.getRoomNames();
    }

    public Event getEvent(String eventName) {
        return scheduleManager.getEvent(eventName);
    }

    public ScheduleEntry getScheduleEntry(String eventName) {
        return scheduleManager.getScheduleEntry(eventName);
    }

    public EventBundle createEventBundle(String eventName) {
        return scheduleManager.createEventBundle(eventName);
    }
}
