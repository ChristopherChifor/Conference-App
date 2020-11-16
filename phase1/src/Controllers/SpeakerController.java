package Controllers;


import Presenters.Presenter;
import Util.ScheduleTime;
import UseCases.ScheduleManager;
import UseCases.ConferenceManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Andrei
 */
public class SpeakerController extends AbstractController {

    private ScheduleManager scheduleManager;
    private final String username;

    protected SpeakerController(ScheduleManager scheduleManager, String username) {
        super();
        this.scheduleManager = scheduleManager;
        this.username = username;
    }

    @Override
    protected void executeCommand(String command, Presenter presenter) {
        ArrayList<String> parsedCommand = parseCommand(command);
        switch (parsedCommand.get(0)) {
            case"/viewMyTalks":
                viewMyTalks();
        }
    }

    @Override
    protected void startUp(Presenter presenter) {
        String startUpMessage = "--- Speaker Account Menu --- \n Hello " + username + ". \n Type /help for options";
        presenter.printLines(startUpMessage);
    }

    @Override
    protected void defineCommands() {
        commands.put("/viewMyTalks", "View my talks.");
    }

    /**
     * Prints the talks the speaker speaks at.
     */
    void viewMyTalks() {

    }


    /**
     *  (copied from AttendeeController)
     *  Prints a schedule as follows:
     *      At: TIME
     *      (for each event at time)
     *      EVENT in room: ROOM
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