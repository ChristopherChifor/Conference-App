package Controllers;

import Entities.ScheduleTime;
import Presenters.Presenter;
import UseCases.ConferenceManager;
import UseCases.ScheduleManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A user specific controller for facilitating actions performed by attendees.
 *
 * @author Haoming & Parssa
 */
public class AttendeeController extends AbstractController {
    private ConferenceManager conferenceManager;
    private ScheduleManager scheduleManager;
    private final String username;


    /**
     * Constructor.
     *
     * @param conferenceManager conference manager
     * @param scheduleManager schedule manager
     * @param username user's username
     */
    public AttendeeController(ConferenceManager conferenceManager, ScheduleManager scheduleManager, String username) {
        super();
        this.conferenceManager = conferenceManager;
        this.scheduleManager = scheduleManager;
        this.username = username;
    }

    /**
     * @see Controllers.AbstractController
     *
     * @param command user-entered command
     * @param presenter presenter used for UI
     */
    @Override
    protected void executeCommand(String command, Presenter presenter) {
        ArrayList<String> parsedCommand = parseCommand(command);
        switch (parsedCommand.get(0)) {
            case "/mainSchedule":
                mainSchedule(presenter);
                break;
            case "/mySchedule":
                mySchedule(presenter);
                break;
            case "/signUpEvent":
                if (parsedCommand.size() < 2) parseInput(command, presenter);
                else signUpEvent(parsedCommand.get(1));
                break;
            case "/cancel":
                if (parsedCommand.size() < 2) parseInput(command, presenter);
                else cancelEnrolment(parsedCommand.get(1));
                break;
        }
    }

    /**
     * @see Controllers.AbstractController
     *
     * @param presenter presenter used for UI.
     */
    @Override
    protected void startUp(Presenter presenter) {
        String startUpMessage = "--- Attendee Account Menu --- \n Hello " + username + ". \n Type /help for options";
        presenter.printLines(startUpMessage);
    }

    /**
     * @see Controllers.AbstractController
     */
    @Override
    protected void defineCommands() {
        commands.put("/mainSchedule", "View the entire schedule");
        commands.put("/mySchedule", "View the schedule events that you're signed up for");
        commands.put("/signUpEvent", "Sign up for an event");
        commands.put("/cancel", "Cancel your enrolment in an event");
    }


    /**
     * Cancels enrolment of current user from event.
     * @param eventName name of event.
     */
    void cancelEnrolment(String eventName) {
        if (scheduleManager.eventExists(eventName)) {
            conferenceManager.cancelEnrolment(username, eventName);
        }
    }

    /**
     * Prints the main schedule.
     * @param presenter used for UI.
     */
    void mainSchedule(Presenter presenter) {
        printSchedule(scheduleManager.getTheSchedule(),presenter);
    }

    /**
     * Prints the attendee's schedule.
     * @param presenter used for UI.
     */
    void mySchedule(Presenter presenter) {
        printSchedule(conferenceManager.enrolledEvents(username).getSchedule(), presenter);
    }

    /**
     *  Signs up attendee for event
     * @param eventName name of the event
     */
    void signUpEvent(String eventName) {
        if (conferenceManager.canSignUpForEvent(username, eventName)) {
            conferenceManager.signUpForEvent(username, eventName);
        }
    }

    /**
     *  Prints a schedule as follows:
     *      At: TIME
     *      (for each event at time)
     *      EVENT in room: ROOM
     * @param schedule schedule being printed
     * @param presenter for UI.
     */
    void printSchedule(HashMap<ScheduleTime, HashMap<String, String>> schedule, Presenter presenter) {
        for (Map.Entry<ScheduleTime, HashMap<String, String>> entry : schedule.entrySet()) {
            String dateTime ="At: " +entry.getKey().getDateTime();
            presenter.printLines(dateTime);

            for (Map.Entry<String, String> event : entry.getValue().entrySet()) {
                String eventInfo = event.getValue() + " in room: " + event.getKey();
                presenter.printLines(eventInfo);
            }
        }
        presenter.printLines();
    }
}
