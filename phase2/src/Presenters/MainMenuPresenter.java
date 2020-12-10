package Presenters;

import Util.UserType;
import ui.view.MainMenuView;
import ui.view.View;

/**
 * A presenter for the main menu
 */
public class MainMenuPresenter implements Presenter{
    private final UserType type;
    private final String username;

    /**
     * Constructor
     * @param type UserType of the user to whom the presenter belongs.
     * @param username user's username.
     */
    public MainMenuPresenter(UserType type, String username) {
        this.type = type;
        this.username = username;
    }

    public UserType getType() {
        return type;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public View makeView() {
        return new MainMenuView(this);
    }
}
