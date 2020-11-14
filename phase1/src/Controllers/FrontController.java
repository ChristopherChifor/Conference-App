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
    private String username = "";

    public FrontController(Presenter presenter) {
        super(presenter);
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
    protected void parseInput(String input) {
        // TODO: No parseInput money at the moment
    }

    @Override
    protected void startUp() {
        String startUpMessage = "--- CONFERENCE APP --- \nType /help for options";
        presenter.printLines(startUpMessage);
    }

    @Override
    protected void defineCommands() {
        commands.put("/login", "Login as an existing user");
        commands.put("/signup", "Sign up as a new user");
        commands.put("/exit", "Exit the program");
    }

    /**
     *  Logs in the user
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
                // TODO: send to MainController
            }
        }
    }

    /**
     *  Signs up a new user, by default as an Attendee
     * @param name
     * @param username
     * @param password
     */
    void signUp(String name, String username, String password) {
        if (accountManager.canCreateUser(username)) {
            accountManager.createUser(name, username, password, User.UserType.ATTENDEE);
        } else {
            presenter.printLines("The username " + username + " already exists.");
        }
    }

    public String getUsername() {
        return username;
    }
}
