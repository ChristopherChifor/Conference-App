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

    private AccountManager accountManager;
    private ScheduleManager scheduleManager;

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
                if (parsedCommand.size() < 3) parseInput(command);
                else assignToRoom(parsedCommand.get(1), parsedCommand.get(2));

            case "/createEvent":
                if (parsedCommand.size() < 4) parseInput(command);
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
     * @param name other user they are speaking to
     * @param username
     * @param password
     */
    protected void createSpeaker(String name, String username, String password) {
        if (accountManager.canCreateUser(username)) {
            accountManager.createUser(name, username, password, User.UserType.SPEAKER);
        } else {
            presenter.printLines("The username " + username + " already exists.");
        }
    }

    /**
     * Creates a speaker
     * @param roomName other user they are speaking to
     * @param time
     */
    protected void assignToRoom(String roomName, String time){
        scheduleManager.assignSpeaker(roomName, time);
    }

    /**
     * Creates a speaker
     * @param name other user they are speaking to
     * @param username
     * @param password
     */
    protected void createEvent(String eventName){

    }


    @Override
    protected void parseInput(String input) {

    }

    @Override
    protected void startUp() {

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
