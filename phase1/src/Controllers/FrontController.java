package Controllers;

import Entities.User;
import Presenters.Presenter;
import UseCases.AccountManager;

import java.util.ArrayList;

/**
 * @author Payam
 */

public class FrontController extends AbstractController {

    private AccountManager accountManager;
    private MainController mainController;
    private String username = "";

    public FrontController(Presenter presenter, AccountManager accountManager, MainController mainController) {
        super(presenter);
        this.accountManager = accountManager;
        this.mainController = mainController;
    }

    @Override
    protected void executeCommand(String command) {
        ArrayList<String> parsedCommand = parseCommand(command);
        switch (parsedCommand.get(0)) {
            case "/login":
                if (parsedCommand.size() < 3) parseInput(command);
                else login(parsedCommand.get(1), parsedCommand.get(2));

            case "/signup":
                if (parsedCommand.size() < 4) parseInput(command);
                else signUp(parsedCommand.get(1), parsedCommand.get(2), parsedCommand.get(3));
        }
    }

    @Override
    protected void startUp() {
        String startUpMessage = "--- CONFERENCE APP --- \nType /help for options";
        presenter.printLines(startUpMessage);
    }

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
    void login(String username, String password) {
        if (!accountManager.userExists(username)) {
            presenter.printLines("User with username " + username + " does not exist");
        } else {
            if (accountManager.authenticateUser(username, password) == null) {
                presenter.printLines("Username or password incorrect");
            } else {
                presenter.printLines("Log in successful");
                username = accountManager.authenticateUser(username, password);
                this.username = username;
                mainController.setUsername(username);
                mainController.enter();
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
    void signUp(String name, String username, String password) {
        if (accountManager.canCreateUser(username)) {
            accountManager.createUser(name, username, password, User.UserType.ATTENDEE);
            presenter.printLines("Succesfully created new user: \nNAME: " + name + "\nUSERNAME: " + username + "\nPASSWORD: "+ password);
        } else {
            presenter.printLines("The username " + username + " already exists.");
        }
    }

    public String getUsername() {
        return username;
    }
}
