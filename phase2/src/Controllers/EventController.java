package Controllers;

import Entities.Event;
import Entities.ScheduleEntry;
import Gateways.IGateway;
import Gateways.JsonDatabase;
import UseCases.RoomManager;
import UseCases.ScheduleManager;
import Util.PDFConverter;
import Util.UserType;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author parssa
 */
public class EventController {
    private UserType userType;

    private PDFConverter pdfConverter; // TODO make sure this gets set

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
    public void cancelEnrolment(String eventName, String username) {
            scheduleManager.removeFromEvent(username, eventName);
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
        //TODO finish this method
        return true;
    }

    /**
     * Creates a new Event
     *
     * @param eventName Name of Event that is to be created
     * @param eventCapacity Capacity of Event that is to be created
     * @return true if no other event has that name and capacity is positive and new Event is created
     */
    public boolean createEvent(String eventName, int eventCapacity, String roomName, Calendar time, int duration) {
        if (scheduleManager.eventExists(eventName)) return false;
        if (eventCapacity < 1) return false;
        if (roomManager.getRoomCapacity(roomName) < eventCapacity) return false;
        if (scheduleConflict(roomName, time, duration)) return false;
        return scheduleManager.createEvent(eventName, eventCapacity, roomName, time, duration);
    }


    /**
     * Checks if an event is full
     *
     * @param eventName Event that is being checked
     * @return true if the event is full
     */
    public boolean eventFull(String eventName) {
        return scheduleManager.eventFull(eventName);
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
     *
     */
    public void signUpEvent(String username, String eventName) {
        scheduleManager.signUpForEvent(username, eventName);
    }

    public void convertScheduleToPDF(String filepath, String username) {
        pdfConverter.convertToPDF(filepath, username, scheduleManager.getAttendeeEvents(username));
    }

}
