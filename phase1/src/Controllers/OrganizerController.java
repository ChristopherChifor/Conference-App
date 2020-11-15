package Controllers;

import Entities.Schedule;
import Entities.User;
import Presenters.Presenter;
import UseCases.AccountManager;
import UseCases.ScheduleManager;

import java.util.ArrayList;

/**
 * @author Christopher
 */
public class OrganizerController extends AbstractController{

    private final AccountManager accountManager;
    private final ScheduleManager scheduleManager;

    protected OrganizerController(Presenter presenter, AccountManager accountManager, ScheduleManager scheduleManager) {
        super(presenter);
        this.accountManager = accountManager;
        this.scheduleManager = scheduleManager;

    }

    @Override
    protected void executeCommand(String command) {
        ArrayList<String> parsedCommand = parseCommand(command);

        switch (parsedCommand.get(0)){
            case "/createRoom":
                if (parsedCommand.size() < 2) parseInput(command);
                else createRoom(parsedCommand.get(1));

            case "/createSpeaker":
                if (parsedCommand.size() < 4) parseInput(command);
                else createSpeaker(parsedCommand.get(1), parsedCommand.get(2), parsedCommand.get(3));

            case "/assignToRoom":
                if (parsedCommand.size() < 4) parseInput(command);
                else assignToRoom(parsedCommand.get(1), parsedCommand.get(2), parsedCommand.get(3));

            case "/createEvent":
                if (parsedCommand.size() < 2) parseInput(command);
                else createEvent(parsedCommand.get(1));
        }
    }

    /**
     * Creates a room where a speaker is able to give a talk
     * @param roomName other user they are speaking to
     */
    protected void createRoom(String roomName){
        scheduleManager.createRoom(roomName);
    }

    /**
     * Creates a speaker
     * @param name Name of the speaker
     * @param username Username of speaker
     * @param password Password of speaker
     */
    protected void createSpeaker(String name, String username, String password) {
        if (accountManager.canCreateUser(username)) {
            accountManager.createUser(name, username, password, User.UserType.SPEAKER);
        } else {
            presenter.printLines("The username " + username + " already exists.");
        }
    }

    /**
     * Assigns a speaker to a room
     * @param speaker Speaker name
     * @param roomName Name of the room
     * @param time Time of the talk
     */
    protected void assignToRoom(String speaker, String roomName, String time){
        scheduleManager.assignSpeaker(speaker,roomName, time);
    }

    /**
     * Creates an event
     * @param eventName Name of the event
     */
    protected void createEvent(String eventName){
        scheduleManager.createEvent(eventName);
    }

    @Override
    protected void startUp() {
        String startUpMessage = "--- Organizer Menu --- \n Hello. \n Type help for options";
        presenter.printLines(startUpMessage);

    }

    /**
     *  Definitions of commands they can do.
     */
    @Override
    protected void defineCommands() {
        commands.put("/createRoom", "Creates a new room");
        commands.put("/createSpeaker", "Creates a speaker account");
        commands.put("/assignToRoom", "Assigns a speaker to a room");
        commands.put("/createEvent", "Creates an event");

    }
}
