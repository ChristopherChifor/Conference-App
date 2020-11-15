import Controllers.FrontController;
import Controllers.MainController;
import Entities.User;
import Presenters.Presenter;
import UseCases.*;

import java.io.Serializable;

public class ConferenceApp implements Serializable {
    private AccountManager accountManager;
    private ConferenceManager conferenceManager;
    private MessageManager messageManager;
    private ScheduleManager scheduleManager;
    private SocialManager socialManager;

    /**
     * Constructor for creating an empty ConferenceApp
     */
    public ConferenceApp() {
        // instantiation of empty usecases
        accountManager = new AccountManager();
        messageManager = new MessageManager(accountManager);
        scheduleManager = new ScheduleManager();
        conferenceManager = new ConferenceManager(scheduleManager, accountManager);
        socialManager = new SocialManager(scheduleManager);

        // the organizer to create organizers
        accountManager.createUser("admin", "admin", "admin", User.UserType.ORGANIZER);

    }

    public void launch() {
        // launching main controller for the user
        MainController main = new MainController(
                accountManager, conferenceManager,
                messageManager, scheduleManager, socialManager);

        Presenter presenter = new Presenter();
        // let user sign in or create account
        FrontController front = new FrontController(accountManager, main);
        front.enter(presenter);
    }
}
