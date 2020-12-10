package Presenters;

import Controllers.AccountController;
import Util.UserType;
import ui.view.SignupView;
import ui.view.View;

public class SignUpPresenter implements Presenter{
    private AccountController accountController;
    private MainPresenter mainPresenter;

    public SignUpPresenter(MainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
        accountController = new AccountController();
    }
    /**
     *  Creates a new user
     * @param name name of user
     * @param username username of user
     * @param password password of user
     * @return true if user was successfully created
     */
    public boolean signUp(String name, String username, String password, String retype) {
        return accountController.createUser(name, username, password, retype, UserType.ATTENDEE);
    }

    @Override
    public View makeView() {
        return new SignupView(this);
    }

    @Override
    public MainPresenter getMainPresenter() {
        return mainPresenter;
    }
}
