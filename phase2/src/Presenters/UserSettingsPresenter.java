package Presenters;

import Controllers.AccountController;
import Util.UserType;
import ui.view.UserSettingsView;
import ui.view.View;

public class UserSettingsPresenter implements Presenter{
    private boolean organizerMode;
    private String username;
    private MainPresenter mainPresenter;
    private AccountController accountController;

    /**
     * Constructor
     * @param organizerMode is this presenter being accessed by organizer?
     * @param username username of user BEING accessed.
     */
    public UserSettingsPresenter(boolean organizerMode, String username, MainPresenter mainPresenter) {
        this.organizerMode = organizerMode;
        this.username = username;
        this.mainPresenter=mainPresenter;
        accountController = new AccountController();
    }

    /**
     * Getter for the user's name (i.e., real name)
     * @return user's real name
     */
    public String getUserRealName(){
        return accountController.getUserRealName(username);
    }

    /**
     * Getter for user's password
     * @return users password
     */
    public String getPassword(){
        return accountController.getPassword(username);
    }

    /**
     * Getter for user Type
     * @return users type
     */
    public UserType getUserType(){
        return accountController.getUserType(username);
    }

    /**
     * Takes user to the messages of this user
     */
    public void goMessages(){
        MessagePresenter mp = new MessagePresenter(username, mainPresenter);
        mainPresenter.addPresenter(mp);
    }

    public void goEvents(){
        UserEventsPresenter up = new UserEventsPresenter(username, mainPresenter, accountController.getUserType(username));
        mainPresenter.addPresenter(up);
    }

    public void saveChanges(String password, UserType type){
        accountController.changeUserType(username, type);
        accountController.changeUserPassword(username, password);
    }

    @Override
    public View makeView() {
        return new UserSettingsView(this);
    }

    public boolean isOrganizerMode() {
        return organizerMode;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public MainPresenter getMainPresenter() {
        return mainPresenter;
    }
}
