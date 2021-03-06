package Presenters;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Presenter for UI. Encapsulates System.out and Scanner functionalities.
 *
 * @author Alex & Parssa
 */
public class Presenter implements Serializable {
    private Scanner s = new Scanner(System.in);

    /**
     * Closes the presenter. Pls run this when you don't need this presenter anymore.
     */
    public void close() {
        s.close();
        clearScreen();
    }

    /**
     * Clears the screen of previous output. Works only when running in standalone console.
     */
    public void clearScreen(){
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
     * Prints the list of acceptable CLI commands and their descriptions. Printed when help command is entered.
     *
     * @param commands The commands passed in through AbstractController
     */
    public void printCommandList(Map<String, String> commands) {
        for (Map.Entry<String, String> command : commands.entrySet()) {
            System.out.printf("%s : %s%n", command.getKey(), command.getValue());
        }
    }

    /**
     * Prints a given list out for the user
     *
     * @param list the list to print out
     */
    public void printLines(List<String> list) {
        list.forEach(System.out::println);
    }

    /**
     * Prints lines on screen.
     * You can pass any sequence of strings, or an array of strings. e.g.:
     *
     * @param lines a sequence of strings OR an array of strings.
     */
    public void printLines(String... lines) {
        if (lines == null) {
            System.out.println("Nothing to print");
            return;
        }

        for (String line : lines) {
            System.out.println(line);
        }
    }
}
