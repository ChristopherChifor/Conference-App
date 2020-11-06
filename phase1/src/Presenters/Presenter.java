package Presenters;

import Models.AbstractModel;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Abstract presenter class. WIP
 *
 * @author Alex
 */
public class Presenter {
    private Scanner s = new Scanner(System.in);
    private List<String> commands;
    private List<String> descriptions;

    /**
     * Constructor for presenter.
     *
     * @param commands list of accepted commands
     * @param descriptions list of descriptions of accepted commands
     */
    public Presenter(List<String> commands, List<String> descriptions) {
        this.commands = commands;
        this.descriptions = descriptions;
    }

    /**
     * Closes the presenter. Pls run this when you don't need this presenter anymore.
     */
    public void close() {
        s.close();

        // clears screen
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Gets input from user as a string.
     *
     * @return user input as string
     */
    public String getInput() {
        return s.nextLine();
    }

    /**
     * Takes param model and prints its content
     *
     * @param model model being printed
     */
    public void update(AbstractModel model) {
        System.out.println(model.getContent());
    }

    /**
     * Prints the list of acceptable CLI commands and their descriptions. Printed when help command is entered.
     */
    public void printCommandList() {
        for (int i = 0; i < commands.size(); i++) {
            System.out.printf("%s : %s", commands.get(i), descriptions.get(i));
        }
    }

    /**
     * Getter for commands
     *
     * @return list of commands
     */
    public List<String> getCommands() {
        return commands;
    }
}
