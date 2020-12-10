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
     * @param username
     * @param password
     * @return
     */
    public UserType tryLogin(String username, String password) {
        return accountController.authenticateUser(username, password);
    }

    @Override
    public View makeView() {
        return new LoginView(this);
    }

    @Override
    public MainPresenter getMainPresenter() {
        return mainPresenter;
    }
}
