package ui.panels;

import Entities.Message;
import ui.components.MessageComponent;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Panel for displaying MessageComponents. Messages can be selected and removed.
 */
public class MessageDisplayPanel extends JPanel {
    private List<MessageComponent> messageList = new ArrayList<>();
    private BoxLayout layout;

    /**
     * Constructor
     * @param messages list of messages being displayed
     */
    public MessageDisplayPanel(List<Message> messages) {
        layout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
        setLayout(layout);

        for (Message m : messages) {
            MessageComponent c = new MessageComponent(m);
            messageList.add(c);
            add(c);
        }
    }

    /**
     * Returns selected messages (check boxed) without removing them from view.
     * @return list of selected messages
     */
    public List<Message> getSelectedMessages() {
        return getSelectedComponents().stream()
                .map(MessageComponent::getMessage)
                .collect(Collectors.toList());
    }

    /**
     * Removes selected message from the panel. Carried out when deleting or archiving messages.
     */
    public void removeSelectedMessages(){
        for(MessageComponent m : getSelectedComponents()){
            remove(m);
            messageList.remove(m);
        }


    }

    /**
     * Adds a sequence or array of message to the panel
     * @param messages messages being added
     */
    public void addMessages(Message... messages){
        for (Message message : messages) {
            MessageComponent newMessage = new MessageComponent(message);
            messageList.add(newMessage);
            add(newMessage);
        }
        refresh();
    }

    /**
     * Adds a list of messages to the panel.
     * @param messages list of messages.
     */
    public void addMessageList(List<Message> messages){
        Message[] msg = new Message[messages.size()];
        addMessages(messages.toArray(msg));
    }

    /**
     * Deselects selected messages
     */
    public void deselectSelected(){
        for(MessageComponent comp : getSelectedComponents()){
            comp.deselect();
        }

        refresh();

    }

    /**
     * Helper method for getting selected components
     * @return components with ticked off checkbox.
     */
    private List<MessageComponent> getSelectedComponents() {
        return messageList.stream()
                .filter(MessageComponent::isSelected)
                .collect(Collectors.toList());
    }

    /**
     * Refreshes
     */
    private void refresh(){
        repaint();
        revalidate();
    }

}
