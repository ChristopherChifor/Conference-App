package Controllers;

import Presenters.Presenter;

import java.io.Serializable;
import java.util.*;

/**
 * Abstract Controller class. Not user specific.
 *
 * WIP
 *
 * @author Alex & Parssa
 */
public abstract class AbstractController implements Serializable {
    protected final String HELP_COMMAND = "/help";
    protected final String EXIT_COMMAND = "/exit";
    // protected final Presenter presenter;

    protected Map<String, String> commands = new TreeMap<>();

    protected AbstractController() {
        defineCommands();
    }

    /**
     * Starts a loop for controller.
     */
    public final void enter(Presenter presenter){
        presenter.clearScreen();
        startUp(presenter);
        String input;
        while(true){
            input = presenter.getInput();

            if (input.equals(EXIT_COMMAND)) break;
            else if (input.equals(HELP_COMMAND)) presenter.printCommandList(commands);
            else if (commands.containsKey(parseCommand(input).get(0))) executeCommand(input, presenter);
            else parseInput(input, presenter);
        }
    }

    /**
     * Executes input command (if input is in command list)
     *
     * This will never be a HELP or EXIT command
     *
     * @param command user-entered command
     */
    protected abstract void executeCommand(String command,Presenter presenter);

    /**
     * Parses user input (if input is not in commands list)
     * @param input user input
     */
    protected void parseInput(String input, Presenter presenter) {
        presenter.printLines("Invalid command, typed:" + input+ ". Write /help for options.");
    }

    /**
     * Method for starting up the controller, i.e., printing initial info onto screen
     */
    protected abstract void startUp(Presenter presenter);

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
        int currIndex = 0;
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
                if (currIndex == charArray.length-1) {
                    currInput += c;
                    cleanInput.add(currInput);
                }
                else {
                    switch(c) {
                        case (char)34: { // (char)34 is " in ASCII
                            inQuotes = true;
                        }
                        case (char)32: { // (char)32 is SPACE in ASCII
                            if (currInput != "") cleanInput.add(currInput);
                            currInput = "";
                        } default: {
                            if (c != (char)32 && c != (char)34) currInput += c;
                        }
                    }
                }
            }
            currIndex++;
        }

        if (cleanInput.size() == 0) cleanInput.add(""); // if the user hits enter with no input
        return cleanInput;
    }

}
