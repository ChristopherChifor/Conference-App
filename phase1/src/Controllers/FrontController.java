package Controllers;

import Entities.User;
import Presenters.Presenter;
import UseCases.AccountManager;

import java.util.ArrayList;

/**
 * Controller used for authenticating users and signing users in.
 *
 * @author Payam
 */
public class FrontController extends AbstractController {
    private AccountManager accountManager;
    private MainController mainController;
    private String username = "";

    public FrontController(AccountManager accountManager, MainController mainController) {
        super();
        this.accountManager = accountManager;
        this.mainController = mainController;
    }

    /**
     * @see Controllers.AbstractController
     * @param command user-entered command
     * @param presenter presenter used for UI
     */
    @Override
    protected void executeCommand(String command,Presenter presenter) {
        ArrayList<String> parsedCommand = parseCommand(command);
        switch (parsedCommand.get(0)) {
            case "/login":
                if (parsedCommand.size() < 3) parseInput(command, presenter);
                else login(parsedCommand.get(1), parsedCommand.get(2), presenter);
                break;

            case "/signup":
                if (parsedCommand.size() < 4) parseInput(command, presenter);
                else signUp(parsedCommand.get(1), parsedCommand.get(2), parsedCommand.get(3), presenter);
                break;
        }
    }

    /**
     * @see Controllers.AbstractController
     * @param presenter presenter used for UI
     */
    @Override
    protected void startUp(Presenter presenter) {
        String startUpMessage = "--- CONFERENCE APP --- \nType /help for options";
        presenter.printLines(startUpMessage);
    }

    /**
     * @see Controllers.AbstractController
     */
    @Override
    protected void defineCommands() {
        commands.put("/login", "Login as an existing user, /login USERNAME PASSWORD");
        commands.put("/signup", "Sign up as a new user, /signup NAME USERNAME PASSWORD");
    }

    /**
     * Logs in the user
     *
     * @param username Username of the user
     * @param password Password of the user
     */
    void login(String username, String password, Presenter presenter) {
        if (!accountManager.userExists(username)) {
            presenter.printLines("User with username " + username + " does not exist");
        } else {
            if (accountManager.authenticateUser(username, password) == null) {
                presenter.printLines("Username or password incorrect");
            } else {
                presenter.printLines("Log in successful");
                username = accountManager.authenticateUser(username, password);
                this.username = username;
                mainController.mainControllerBuilder(username);
                mainController.enter(presenter);
            }
        }
    }

    /**
     * Signs up a new user, by default as an Attendee
     *
     * @param name
     * @param username
     * @param password
     */
    void signUp(String name, String username, String password,Presenter presenter) {
        if (accountManager.canCreateUser(username)) {
            accountManager.createUser(name, username, password, User.UserType.ATTENDEE);
            presenter.printLines("Succesfully created new user: \nNAME: " + name + "\nUSERNAME: " + username + "\nPASSWORD: "+ password);
        } else {
            presenter.printLines("The username " + username + " already exists.");
        }
    }

    /**
     * Getter for the username
     * @return username
     */
    public String getUsername() {
        return username;
    }
}
