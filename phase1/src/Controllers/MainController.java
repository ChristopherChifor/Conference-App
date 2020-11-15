package Controllers;

import Entities.User;
import Presenters.Presenter;
import UseCases.*;

/**
 * @author Alex
 */
public class MainController extends AbstractController {
    private static final String MOTD = "At least it's better than 2020!"; // a cheeky message

    private String username;
    private User.UserType type;

    private AccountManager accountManager;
    private ConferenceManager conferenceManager;
    private MessageManager messageManager;
    private ScheduleManager scheduleManager;
    private SocialManager socialManager;

    private AttendeeController attendeeController;
    private MessageController messageController;
    private OrganizerController organizerController;
    private SpeakerController speakerController;

    public MainController(String username, Presenter presenter, AccountManager accountManager, ConferenceManager conferenceManager, MessageManager messageManager, ScheduleManager scheduleManager, SocialManager socialManager) {
        super(presenter);
        this.accountManager = accountManager;
        this.conferenceManager = conferenceManager;
        this.messageManager = messageManager;
        this.scheduleManager = scheduleManager;
        this.socialManager = socialManager;

        this.username = username;
        this.type = accountManager.getUserType(username);

        switch(this.type){
            case ATTENDEE:
                messageController = new AttendeeMessageController(messageManager, username, presenter);
            case SPEAKER:
                messageController = new SpeakerMessageController(messageManager, username, presenter, scheduleManager);
            case ORGANIZER:
                messageController = new OrganizerMessageController(messageManager, username, presenter);
        }


    }

    @Override
    protected void executeCommand(String command) {

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
