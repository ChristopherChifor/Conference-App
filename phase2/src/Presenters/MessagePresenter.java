package Presenters;

import Controllers.MessageController;
import Entities.Message;
import ui.view.MessageView;
import ui.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chris and Nikita
 */
public class MessagePresenter implements Presenter{
    private MessageController messageController;
    private String username;
    private MainPresenter mainPresenter;

    /**
     * Constructor for Message presenter implements Presenter. Communicates with the view
     * @param username username
     * @param mainPresenter instance of Presenter
     */
    public MessagePresenter(String username, MainPresenter mainPresenter) {
        this.username = username;
        messageController = new MessageController();
        this.mainPresenter = mainPresenter;
    }

    /**
     * Gets the inbox for a user and outputs a list of messages
     * @return list of messages
     */
    public List<String> getInbox() {
        return messageController.getInbox(username);
    }

    /**
     * Gets a conversation between two users
     * @param otherUser another user
     * @return returns conversation as a list of messages
     */
    public List<Message> getConversation(String otherUser) {
        return messageController.getConversation(username, otherUser);
    }

    /**
     * Gets the archived messagew
     * @return returns a list of messages from the archived message
     */
    public List<Message> getArchivedMessages() {
        return messageController.getArchivedMessages(username);
    }

    /**
     * Gets the contacts and returns them as a String
     * @return List of Strings of the contacts
     */
    public List<String> getContacts() {
        return messageController.getContacts(username);
    }

    /**
     * Read/Unread
     * @param otherUser a conversation between two users.
     * @return true iff the conversation has been read
     */
    public boolean isRead(String otherUser) {
        return messageController.conversationIsRead(username, otherUser);
    }

    /**
     * Marks a conversation as read
     * @param conversation conversation between two users
     */
    public void markAsRead(String otherUser) {
        messageController.markAsRead(username, otherUser);
    }

    /**
     * Sends the message to a recipient
     * @param recipient recipient of the message
     * @param messageBody body of the message
     */
    public void sendMessage(String recipient, String messageBody) {
        //TODO change how the recipients are displayed
        String otherUser = recipient.replace(username,"");
        otherUser.replace("-","");
        messageController.sendMessage(username, otherUser, messageBody);
    }

    /**
     * Deletes a message
     * @param messages list of messages
     */
    public void deleteMessages(List<Message> messages) {
        List<String> messageIds = new ArrayList<>();
        for (Message m: messages) {
            messageIds.add(m.getSender()+"-"+m.getRecipient());
        }
        messageController.deleteMessages(messageIds);
    }

    /**
     * Marks a message as archived
     * @param messages list of messages
     */
    public void markAsArchived(List<Message> messages) {
        messageController.archiveMessages(messages);
    }

    /**
     * The user can be messaged or not
     * @param otherUser the user being messaged
     * @return true iff a user can message another user
     */
    public boolean canMessage(String otherUser) {
        return messageController.canMessage(username, otherUser);
    }


    /**
     * Overrides a message view for the UI
     * @return View
     */
    @Override
    public View makeView() {
        return new MessageView(this);
    }

    /**
     * Getter for username
     * @return username of the user to whom this presenter belongs.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Makes a view
     * @return view
     */
    @Override
    public MainPresenter getMainPresenter() {
        return mainPresenter;
    }
}
