package Controllers;

import Presenters.Presenter;
import UseCases.*;

/**
 * @author Alex
 */
public class MainController extends AbstractController {
    private String username;
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

        switch(this.accountManager.getUserType(username)){
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
    protected void parseInput(String input) {

    }

    @Override
    protected void startUp() {

    }

    @Override
    protected void defineCommands() {

    }
}
