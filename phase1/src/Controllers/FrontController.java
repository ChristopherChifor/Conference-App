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

    protected FrontController(Presenter presenter) {
        super(presenter);
    }

    @Override
    protected void executeCommand(String command) {
        ArrayList<String> parsedCommand = parseCommand(command);
        switch (parsedCommand.get(0)) {
            case "/login":
                if (!accountManager.userExists(parsedCommand.get(1))) {
                    presenter.printLines("User with username " + parsedCommand.get(1) + " does not exist");
                } else {
                    if (accountManager.authenticateUser(parsedCommand.get(1), parsedCommand.get(2)) == null) {
                        presenter.printLines("Username or password incorrect");
                    } else {
                        presenter.printLines("Log in successful");
                        // TODO: send to MainController
                    }
                }
            case "/signup":
                String name = parsedCommand.get(1), username = parsedCommand.get(2),
                        password = parsedCommand.get(3);
                if (accountManager.canCreateUser(parsedCommand.get(2))) {
                    accountManager.createUser(name, username, password, User.UserType.ATTENDEE);
                } else {
                    presenter.printLines("The username " + username + " already exists.");
                }
            case "/exit":
                // TODO: Exit
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
}
