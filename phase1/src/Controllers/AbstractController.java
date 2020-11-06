package Controllers;

import Models.AbstractModel;
import Presenters.Presenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract Controller class. Not user specific.
 *
 * WIP
 *
 * @author Alex
 */
public abstract class AbstractController {
    protected final String HELP_COMMAND = "/help";
    protected final String EXIT_COMMAND = "/exit";

    // these need to be defined by extending class:
    protected List<String> commands = new ArrayList<>();
    protected List<String> descriptions = new ArrayList<>();


    /**
     * Starts a loop for controller.
     */
    public final void enter(){
        Presenter presenter = new Presenter(commands, descriptions);
        startUp(presenter);
        String input;
        while(true){
            input = presenter.getInput();
            if (input.equals(EXIT_COMMAND)) break;
            else if (input.equals(HELP_COMMAND)) presenter.printCommandList();
            else if (commands.contains(input)) parseCommand(input, presenter);
            else parseInput(input, presenter);
        }
        presenter.close();
    }

    /**
     * Parses input command (if input is in command list)
     * @param input user input
     * @param presenter presenter for printing
     */
    protected abstract void parseCommand(String input, Presenter presenter);

    /**
     * Parses user input (if input is not in commands list)
     * @param input user input
     * @param presenter presenter for printing
     */
    protected abstract void parseInput(String input, Presenter presenter);

    /**
     * Method for starting up the controller, i.e., printing initial info onto screen
     * @param presenter presenter for printing.
     */
    protected abstract void startUp(Presenter presenter);

    /**
     * Needs to be ran when instantiating controller. Populates commands list and description list.
     */
    protected abstract void defineCommands();



}
