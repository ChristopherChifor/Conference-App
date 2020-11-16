package Controllers;

import Entities.User.UserType;
import Presenters.Presenter;
import UseCases.*;

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
     * @param accountManager account manager
     * @param conferenceManager conference manager
     * @param messageManager message manager
     * @param scheduleManager schedule manager
     * @param socialManager social manager
     */
    public MainController(AccountManager accountManager, ConferenceManager conferenceManager, MessageManager messageManager, ScheduleManager scheduleManager, SocialManager socialManager) {
        super();
        this.accountManager = accountManager;
        this.conferenceManager = conferenceManager;
        this.messageManager = messageManager;
        this.scheduleManager = scheduleManager;
        this.socialManager = socialManager;
    }


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
                userController = new AttendeeController(conferenceManager, scheduleManager, username);
                messageController = new AttendeeMessageController(messageManager, username);
                break;
            case SPEAKER:
                userController = new SpeakerController(scheduleManager, username);
                messageController = new SpeakerMessageController(messageManager, username, scheduleManager);
                break;
            case ORGANIZER:
                userController = new OrganizerController(accountManager, scheduleManager);
                messageController = new OrganizerMessageController(messageManager, username);
                break;
            default:
                throw new IllegalArgumentException();
        }

    }

    /**
     * @see Controllers.AbstractController
     * @param command user-entered command
     * @param presenter presenter used for UI
     */
    @Override
    protected void executeCommand(String command, Presenter presenter) {
        if("/events".equals(command)){
            presenter.clearScreen();
            userController.enter(presenter);
            welcomeBack(presenter);
        } else if ("/message".equals(command)){
            presenter.clearScreen();
            messageController.enter(presenter);
            welcomeBack(presenter);
        } else{
            presenter.printLines(String.format("You have entered an invalid command \"%f\"", command),
                    "Type \"/help\" for a list of commands. ");
        }
    }

    /**
     * @see Controllers.AbstractController
     * @param presenter presenter used for UI
     */
    @Override
    protected void startUp(Presenter presenter) {
        presenter.printLines(String.format("Welcome to ConferenceApp! \"%s\"", MOTD),
                "It looks like you are a(n) " + type + ". If this is incorrect, please contact the administrator.",
                "Type \"/help\" for a list of commands.");
    }

    private void welcomeBack(Presenter presenter){
        // clears screen
        presenter.clearScreen();
        presenter.printLines(" --- MAIN MENU --- ");
    }

    @Override
    protected void defineCommands() {
        commands.put("/exit","Saves and Exits the Program. ");
        commands.put("/events", "Enters events menu. ");
        commands.put("/message", "Enters messaging menu. ");

    }
}
