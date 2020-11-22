package Controllers;

import Entities.Schedule;
import Entities.User;
import Presenters.Presenter;
import UseCases.AccountManager;
import UseCases.ScheduleManager;

import java.util.ArrayList;

/**
 * Controller for facilitating the functions that can be carried out by organizing (i.e., organizing).
 *
 * @author Christopher
 */
public class OrganizerController extends AbstractController{

    private final AccountManager accountManager;
    private final ScheduleManager scheduleManager;

    /**
     * Constructor
     * @param accountManager accountmanager
     * @param scheduleManager schedule manager
     */
    public OrganizerController(AccountManager accountManager, ScheduleManager scheduleManager) {
        super();
        this.accountManager = accountManager;
        this.scheduleManager = scheduleManager;
    }

    /**
     * @see Controllers.AbstractController
     * @param command user-entered command
     * @param presenter presenter used for UI
     */
    @Override
    protected void executeCommand(String command,Presenter presenter) {
        ArrayList<String> parsedCommand = parseCommand(command);
        String rawCommand = parsedCommand.get(0);
        switch (rawCommand) {
            case "/createRoom":
                if (parsedCommand.size() < 2) parseInput(command, presenter);
                else createRoom(parsedCommand.get(1), presenter);
                break;
            case "/createSpeaker":
                if (parsedCommand.size() < 4) parseInput(command,presenter);
                else createSpeaker(parsedCommand.get(1), parsedCommand.get(2), parsedCommand.get(3), presenter);
                break;
            case "/assignToRoom":
                if (parsedCommand.size() < 4) parseInput(command, presenter);
                else assignToRoom(parsedCommand.get(1), parsedCommand.get(2), parsedCommand.get(3), presenter);
                break;
            case "/createEvent":
                if (parsedCommand.size() < 4) parseInput(command, presenter);
                else createEvent(parsedCommand.get(1), parsedCommand.get(2), parsedCommand.get(3), presenter);
                break;
        }
    }

    /**
     * Creates a room where a speaker is able to give a talk
     * @param roomName other user they are speaking to
     */
    protected void createRoom(String roomName, Presenter presenter){
        if (scheduleManager.createRoom(roomName)) {
            presenter.printLines("Succesfully created new room: " + roomName);
        } else {
            presenter.printLines("Could not create room");
        }
    }

    /**
     * Creates a speaker
     * @param name Name of the speaker
     * @param username Username of speaker
     * @param password Password of speaker
     */
    protected void createSpeaker(String name, String username, String password, Presenter presenter) {

        if (accountManager.createUser(name, username, password, User.UserType.SPEAKER)) {
            presenter.printLines("Succesfully created new speaker " + name);
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
    protected void assignToRoom(String speaker, String roomName, String time, Presenter presenter){
        presenter.printLines("Assigned "+ speaker +" to room "+roomName + " at " + time);
        scheduleManager.assignSpeaker(speaker,roomName, time);
    }

    /**
     * Creates an event
     * @param eventName Name of the event
     */
    protected void createEvent(String eventName, String roomName, String time, Presenter presenter){
        if (scheduleManager.addNewEvent(roomName, eventName, time)) {
            presenter.printLines("Succesfully created new event: " + eventName + " in room " + roomName + " at "+ time);
        } else presenter.printLines("Could not create new event");
    }

    /**
     * @see Controllers.AbstractController
     * @param presenter presenter used for UI
     */
    @Override
    protected void startUp(Presenter presenter) {
        String startUpMessage = "--- Organizer Menu --- \n Hello. \n Type help for options";
        presenter.printLines(startUpMessage);

    }

    /**
     *  @see Controllers.AbstractController
     */
    @Override
    protected void defineCommands() {
        commands.put("/createRoom", "Creates a new room, /createRoom ROOM_NAME");
        commands.put("/createSpeaker", "Creates a speaker account, /createSpeaker NAME USERNAME PASSWORD");
        commands.put("/createEvent", "Creates an event, /createEvent EVENT_NAME ROOM_NAME HH:MM");
        commands.put("/assignToRoom", "Assigns a speaker to a room at a given time, /assignToRoom SPEAKER_USERNAME ROOM_NAME HH:MM");

    }
}
