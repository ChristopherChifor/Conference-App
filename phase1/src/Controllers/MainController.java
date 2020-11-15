package Controllers;

import Entities.User.UserType;
import Presenters.Presenter;
import UseCases.*;

import java.util.ArrayList;

/**
 * @author Alex
 */
public class MainController extends AbstractController {
    private static final String MOTD = "At least it's better than 2020!"; // a cheeky message

    private String username = "";
    private UserType type;

    private AccountManager accountManager;
    private ConferenceManager conferenceManager;
    private MessageManager messageManager;
    private ScheduleManager scheduleManager;
    private SocialManager socialManager;

    private AttendeeController attendeeController = null;
    private MessageController messageController = null;
    private OrganizerController organizerController = null;
    private SpeakerController speakerController = null;

    private AbstractController userController;

    /**
     * Constructor for main controller. Calls super.
     *
     * YOU NEED TO RUN mainControllerBuilder() BEFORE ENTERING MAIN CONTROLLER.
     *
     * @param presenter the presenter
     * @param accountManager account manager
     * @param conferenceManager conference manager
     * @param messageManager message manager
     * @param scheduleManager schedule manager
     * @param socialManager social manager
     */
    public MainController(Presenter presenter, AccountManager accountManager, ConferenceManager conferenceManager, MessageManager messageManager, ScheduleManager scheduleManager, SocialManager socialManager) {
        super(presenter);
        this.accountManager = accountManager;
        this.conferenceManager = conferenceManager;
        this.messageManager = messageManager;
        this.scheduleManager = scheduleManager;
        this.socialManager = socialManager;
    }

//    /**
//     * Builder method for MainController that generates other controllers from the entered username.
//     * This method can and should only be ran once if you try to run it multiple times, the attempts
//     * after the first one will do nothing.
//     *
//     * @param username the users username.
//     */
//    public void mainControllerBuilder(String username) {
//        if(!this.username.equals("")) return;
//
//        this.username = username;
//        this.type = accountManager.getUserType(username);
//
//        switch (this.type) {
//            case ATTENDEE:
//                messageController = new AttendeeMessageController(messageManager, username, presenter);
//                break;
//            case SPEAKER:
//                messageController = new SpeakerMessageController(messageManager, username, presenter, scheduleManager);
//                break;
//            case ORGANIZER:
//                messageController = new OrganizerMessageController(messageManager, username, presenter);
//                break;
//            default:
//                throw new IllegalArgumentException();
//        }
//
//        attendeeController = new AttendeeController(conferenceManager, scheduleManager, username, presenter);
//        organizerController = type == UserType.ORGANIZER ? new OrganizerController(presenter, accountManager, scheduleManager) : null;
//        speakerController = type == UserType.SPEAKER ? new SpeakerController(scheduleManager, username, presenter) : null;
//
//    }
    /**
     * Builder method for MainController that generates other controllers from the entered username.
     * This method can and should only be ran once if you try to run it multiple times, the attempts
     * after the first one will do nothing.
     *
     * @param username the users username.
     */
    public void mainControllerBuilder(String username) {
        if(!this.username.equals("")) return;

        this.username = username;
        this.type = accountManager.getUserType(username);

        switch (this.type) {
            case ATTENDEE:
                userController = new AttendeeController(conferenceManager, scheduleManager, username, presenter);
                messageController = new AttendeeMessageController(messageManager, username, presenter);
                break;
            case SPEAKER:
                userController = new SpeakerController(scheduleManager, username, presenter);
                messageController = new SpeakerMessageController(messageManager, username, presenter, scheduleManager);
                break;
            case ORGANIZER:
                userController = new OrganizerController(presenter, accountManager, scheduleManager);
                messageController = new OrganizerMessageController(messageManager, username, presenter);
                break;
            default:
                throw new IllegalArgumentException();
        }

        commands = userController.commands;
        commands.put("/message", "Goes to messaging menu");
    }

    @Override
    protected void executeCommand(String command) {
        userController.executeCommand(command);
    }

    @Override
    protected void startUp() {
        presenter.printLines(String.format("Welcome to ConferenceApp! \"%s\"", MOTD),
                "It looks like you are a(n) " + type + ". If this is incorrect, please contact the administrator.",
                "Type \"/help\" for a list of commands.");
    }

    @Override
    protected void defineCommands() {

    }
}
