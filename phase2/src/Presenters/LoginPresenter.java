package Presenters;

import Controllers.AccountController;
import Util.UserType;

public class LoginPresenter {

    private AccountController accountController;

    public LoginPresenter() {
        accountController = new AccountController();
    }

    /**
     *  Logs in user
     * @param username
     * @param password
     * @return
     */
    public String tryLogin(String username, String password) {
        return accountController.authenticateUser(username, password);
    }

    /**
     *  Creates a new user
     * @param name name of user
     * @param username username of user
     * @param password password of user
     * @param type usertype of user
     * @return true if user was successfully created
     */
    public boolean createUser(String name, String username, String password, UserType type) {
        return accountController.createUser(name, username, password, type);
    }

}
