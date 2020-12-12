package Presenters;

import Util.UserType;
import ui.view.MainMenuView;
import ui.view.UserEventsView;
import ui.view.View;

/**
 * A presenter for the main menu
 */
public class MainMenuPresenter implements Presenter {
    private final UserType type;
    private final String username;
    private MainPresenter mainPresenter;

    /**
     * Constructor
     *
     * @param type     UserType of the user to whom the presenter belongs.
     * @param username user's username.
     */
    public MainMenuPresenter(UserType type, String username, MainPresenter mainPresenter) {
        this.type = type;
        this.username = username;
        this.mainPresenter = mainPresenter;
    }

    /**
     * Gets type
     * @return usertype
     */
    public UserType getType() {
        return type;
    }

    /**
     * gets username
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Creates an UserEventsPresenter for this user and adds it to mainPresenter
     */
    public void goEvents(){
        UserEventsPresenter up = new UserEventsPresenter(username, mainPresenter, type);
        mainPresenter.addPresenter(up);
    }

    /**
     * Creates an MessagePresenter for this user and adds it to mainPresenter
     */
    public void goMessages(){
        MessagePresenter mp = new MessagePresenter(username, mainPresenter);
        mainPresenter.addPresenter(mp);
    }

    /**
     * Creates an OrganizerPresenter for this user and adds it to mainPresenter.
     * This method only gets called if this user can do this action.
     */
    public void goOrganizer(){
        OrganizerPresenter op = new OrganizerPresenter(username, mainPresenter);
        mainPresenter.addPresenter(op);
    }

    /**
     * Creates an UserSettingsPresenter for this user and adds it to mainPresenter (not organizer mode)
     */
    public void goAccount(){
        UserSettingsPresenter usp = new UserSettingsPresenter(false, username, mainPresenter);
        mainPresenter.addPresenter(usp);
    }


    @Override
    public View makeView() {
        return new MainMenuView(this);
    }

    @Override
    public MainPresenter getMainPresenter() {
        return mainPresenter;
    }
}
