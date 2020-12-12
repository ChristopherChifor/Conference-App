package Presenters;

import Controllers.AccountController;
import Util.UserType;
import ui.view.LoginView;
import ui.view.View;

public class LoginPresenter implements Presenter{

    private AccountController accountController;
    private MainPresenter mainPresenter;

    public LoginPresenter(MainPresenter mainPresenter) {
        this.mainPresenter = mainPresenter;
        accountController = new AccountController();
    }

    /**
     *  Logs in user
     * @param username username
     * @param password password
     * @return user type
     */
    public UserType tryLogin(String username, String password) {
        return accountController.authenticateUser(username, password);
    }
    /**
     * makes a new
     * @return view.
     */
    @Override
    public View makeView() {
        return new LoginView(this);
    }

    /**
     * gets main presenter
     * @return main presenter
     */
    @Override
    public MainPresenter getMainPresenter() {
        return mainPresenter;
    }
}
