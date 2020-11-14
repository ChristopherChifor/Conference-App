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

    private Presenter presenter;

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

        presenter = new Presenter();
    }

    public void launch() {
        // let user sign in or create account
        FrontController front = new FrontController(presenter, accountManager);
        front.enter();

        // username of the user after authentication
        String username = front.getUsername();

        // launching main controller for the user
        MainController main = new MainController(username,
                presenter, accountManager, conferenceManager,
                messageManager, scheduleManager, socialManager);
        main.enter();
    }
}
