package ui.view;

import javax.swing.*;

/**
 * An interface for Views. WIP!
 */
public interface View {
    /**
     * Brings up a dialog with message to notify user of wrong input.
     *
     * @param message message
     */
    default void showIncorrectInputDialog(String message) {
        JOptionPane.showMessageDialog(null, message, "Bad Input!", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Brings up a dialog with message to notify user of general message
     *
     * @param message message
     */
    default void showInfoDialog(String message) {
        JOptionPane.showMessageDialog(null, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Brings up a dialog with message to notify user of error.
     *
     * @param message message
     */
    default void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Brings up a dialog with message to warn user.
     *
     * @param message message
     */
    default void showWarningDialog(String message) {
        JOptionPane.showMessageDialog(null, message, "Warning", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Brings up dialog with message (a yes or no question) to user.
     *
     * @param message a yes or no question
     * @return true if yes; false if no/closed dialog
     */
    default boolean showConfirmDialog(String message) {
        int out = JOptionPane.showConfirmDialog(null, message, "Are you sure?", JOptionPane.YES_NO_OPTION);
        if (out == 0) return true;
        return false;
    }

    /**
     * A method for getting the name of this view.
     *
     * @return the title for this view
     */
    String getViewName();
}
