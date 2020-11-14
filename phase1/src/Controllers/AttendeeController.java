package Controllers;

import Entities.Event;
import Presenters.Presenter;
import UseCases.ConferenceManager;
import UseCases.ScheduleManager;

import java.util.ArrayList;

// TODO Haoming
public class AttendeeController extends AbstractController {


    private ConferenceManager conferenceManager;
    private ScheduleManager scheduleManager;
    private final String username;


    public AttendeeController(ConferenceManager conferenceManager, ScheduleManager scheduleManager, String username, Presenter presenter) {
        super(presenter);
        this.conferenceManager = conferenceManager;
        this.scheduleManager = scheduleManager;
        this.username = username;
    }

    @Override
    protected void executeCommand(String command) {
        ArrayList<String> parsedCommand = parseCommand(command);
        switch (parsedCommand.get(0)) {
            case "/mainSchedule":
                if (parsedCommand.size() < 2) {
                    parseInput(command);
//                    presenter.printLines(scheduleManager.getTheSchedule());
                } else parseInput(command);
            case "/mySchedule":
                if (parsedCommand.size() < 2) {
                    parseInput(command);
//                    presenter.printLines(conferenceManager.enrolledEvents(username));
                } else parseInput(command);
            case "/signUpEvent":
                if (parsedCommand.size() < 2) {
                    parseInput(command);
                    Event event = scheduleManager.getEvent(parsedCommand.get(1));
                    if (conferenceManager.canSignUpForEvent(username, event)) {
                        conferenceManager.signUpForEvent(username, event);
                    }
                } else parseInput(command);
            case "/cancel":
                if (parsedCommand.size() < 2) {
                    parseInput(command);
                    Event event = scheduleManager.getEvent(parsedCommand.get(1));
                    conferenceManager.cancelEnrolment(username, event);
                } else parseInput(command);
            default:
                break;
        }
    }

    @Override
    protected void parseInput(String input) {

    }

    @Override
    protected void startUp() {
        String startUpMessage = "--- Attendee Account Menu --- \n Hello " + username + ". \n View the Schedule Below \n Type /help for options";
        presenter.printLines(startUpMessage);
//        presenter.printLines(scheduleManager.getTheSchedule());
    }

    @Override
    protected void defineCommands() {
        commands.put("/mainSchedule", "View the entire schedule");
        commands.put("/mySchedule", "View the schedule events that you're signed up for");
        commands.put("/signUpEvent", "Sign up for an event");
        commands.put("/cancel", "Cancel your enrolment in an event");
    }
}
