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
        accountManager.createUser("admin","admin","admin", User.UserType.ORGANIZER);

        // todo commands is null!
        presenter = new Presenter();
    }

    public void launch(){

    }
}
