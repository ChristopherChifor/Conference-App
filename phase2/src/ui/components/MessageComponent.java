package ui.components;

import Entities.Message;

import javax.swing.*;
import java.awt.*;

/**
 * A swing panel for displaying a message. Contains a label for sender, label for
 * body, and a check box for selecting.
 */
public class MessageComponent extends Container {
    private Message message;
    private JCheckBox checkBox = new JCheckBox();

    /**
     * Constructor
     *
     * @param message message being displayed.
     */
    public MessageComponent(Message message) {
        this.message = message;

        setLayout(new BorderLayout(50,10));
        add(new JLabel(message.getSender() + "> "), BorderLayout.WEST);
        add(new JLabel(message.getBody()), BorderLayout.CENTER);
        add(checkBox, BorderLayout.EAST);

    }

    /**
     * Gets state of checkbox
     *
     * @return true if selected; false otherwise
     */
    public boolean isSelected() {
        return checkBox.isSelected();
    }

    /**
     * Unchecks checkbox if checked.
     */
    public void deselect() {
        checkBox.setSelected(false);
        checkBox.repaint();
    }

    /**
     * Getter for message
     *
     * @return message
     */
    public Message getMessage() {
        return message;
    }
}
