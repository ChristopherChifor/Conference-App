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

    public MainController(String username, Presenter presenter, AccountManager accountManager, ConferenceManager conferenceManager, MessageManager messageManager, ScheduleManager scheduleManager, SocialManager socialManager) {
        super(presenter);
        this.accountManager = accountManager;
        this.conferenceManager = conferenceManager;
        this.messageManager = messageManager;
        this.scheduleManager = scheduleManager;
        this.socialManager = socialManager;

        this.username = username;
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
