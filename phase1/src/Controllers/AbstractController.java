package Controllers;

import Presenters.Presenter;

import java.util.*;

/**
 * Abstract Controller class. Not user specific.
 *
 * WIP
 *
 * @author Alex & Parssa
 */
public abstract class AbstractController {
    protected final String HELP_COMMAND = "/help";
    protected final String EXIT_COMMAND = "/exit";
    protected final Presenter presenter;
    // these need to be defined by extending class:
//    protected List<String> commands = new ArrayList<>();
//    protected List<String> descriptions = new ArrayList<>();

    protected Map<String, String> commands = new TreeMap<>();

    protected AbstractController(Presenter presenter) {
        defineCommands();
        this.presenter = presenter;
    }

    /**
     * Starts a loop for controller.
     */
    public final void enter(){
        Presenter presenter = new Presenter();
        String input;
        while(true){
            input = presenter.getInput();
            if (input.equals(EXIT_COMMAND)) break;
            else if (input.equals(HELP_COMMAND)) presenter.printCommandList(commands);
            else if (commands.containsKey(input)) executeCommand(input);
            else parseInput(input);
        }
        presenter.close();
    }

    /**
     * Executes input command (if input is in command list)
     *
     * This will never be a HELP or EXIT command
     *
     * @param command user-entered command
     */
    protected abstract void executeCommand(String command);

    /**
     * Parses user input (if input is not in commands list)
     * @param input user input
     */
    protected abstract void parseInput(String input);

    /**
     * Method for starting up the controller, i.e., printing initial info onto screen
     */
    protected abstract void startUp();

    /**
     * Needs to be ran when instantiating controller. Populates commands list and description list.
     */
    protected abstract void defineCommands();

    /**
     *  Takes in user's input and parses it to return command in array of
     *  [0] /command
     *  [1-inf] individual paramaters, separated by spaces, and sections in quotations
     *          count as an indivdiual parameter
     * @param input
     * @return
     */
    protected ArrayList<String> parseCommand(String input) {

        ArrayList<String> cleanInput = new ArrayList<>();

        char[] charArray = input.toCharArray();
        boolean inQuotes = false;
        String currInput = "";
        for (char c : charArray) {
            if (inQuotes) {
                if (c == (char)34) {  // (char)34 is " in ASCII
                    // ENTRY ENDED
                    inQuotes = false;
                    cleanInput.add(currInput);
                    currInput = "";
                } else {
                    currInput += c;
                }
            } else {
                switch(c) {
                    case (char)34: { // (char)34 is " in ASCII
                        inQuotes = true;
                    }
                    case (char)32: { // (char)32 is SPACE in ASCII
                        cleanInput.add(currInput);
                        currInput = "";
                    } default: {
                        currInput += c;
                    }
                }
            }
        }
        return cleanInput;
    }

}
