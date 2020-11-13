package Controllers;

import Presenters.Presenter;
import UseCases.AccountManager;


public class FrontController extends AbstractController{

    private AccountManager accountManager;

    protected FrontController(Presenter presenter) {
        super(presenter);
    }

    @Override
    protected void executeCommand(String command) {

    }

    @Override
    protected void parseInput(String input) {

    }

    @Override
    protected void startUp() {

    }

    @Override
    protected void defineCommands() {

    }
}
