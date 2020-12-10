package Controllers;

import Entities.Message;
import UseCases.AccountManager;
import UseCases.MessageManager;
import Util.UserType;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: Chris, Nikita, Parssa
 */
public class MessageController {

    private MessageManager messageManager;
    private AccountManager accountManager;

    /**
     * Controller for the Messages.
     */
    public MessageController() {
        messageManager = new MessageManager();
    }

    /**
     * Returns the inbox as a List of strings of users in ones inbox.
     * @param username username
     * @return inbox as strings
     */
    public List<String> getInbox(String username) {
        return messageManager.getMyInbox(username);
    }

    /**
     * Gets the conversation between two users.
     * @param sender sender
     * @param otherUser recipient
     * @return returns a list of messages between the two users.
     */
    public List<Message> getConversation(String sender, String otherUser) {
        return messageManager.getMessages(sender, otherUser);
    }

    /**
     * Checks if the conversation is read
     * @param messages list of messages
     * @return true iff conversation is read.
     */
    public boolean conversationIsRead(List<Message> messages) {
        if (messages.size() == 0) return false;
        return messageManager.conversationIsRead(messages);
    }

    /**
     * //todo what does this do??
     * @param conversation conversation
     */
    public void markAsRead(List<Message> conversation) {
        messageManager.markAsRead(conversation);
    }

    /**
     * If participants of this message have a conversation, adds message to conversation. If not,
     * makes a new conversation for sender and recipient and adds message to it.
     * <p>
     * In order for the message to be sent, the fields may not be null. If they are, the message is
     * not sent, and method returns false.
     *
     * @param sender sender of message
     * @param recipient recipient of message
     * @param messageText body of message
     * @return boolean if sent
     */
    public boolean sendMessage(String sender, String recipient, String messageText) {
        if (sender == null || recipient == null || messageText == null) return false;
        if (!canMessage(sender, recipient)) {
            return false;
        }
        messageManager.sendMessage(sender, recipient, messageText);
        return true;
    }

    /**
     * Deletes a message
     * @param messageIds the exact message to delete
     */
    public void deleteMessages(List<String> messageIds) {
        messageManager.deleteMessages(messageIds);
    }

    /**
     * Archives a list of messages
     * @param messageIds the message(s) to be archived
     */
    public void archiveMessages(List<Message> messageIds) {
        messageManager.archiveMessages(messageIds);
    }

    /**
     * Returns a list of archived messages for a user.
     * @param username username
     * @return a list of users
     */
    public List<Message> getArchivedMessages(String username) {
        return messageManager.getArchivedMessages(username);
    }

    /**
     * Returns a list of all usernames this user can message.
     *
     * @param user user
     * @return list of usernames
     */
    public List<String> getContacts(String user) {
        return accountManager.getUsernames().stream()
                .filter(s -> canMessage(user, s) && s != user)
                .collect(Collectors.toList());
    }

    /**
     * TODO UNFINISHED
     * Checks if sender can message recipient.
     * <p>
     * They can message iff:
     * - both users exist, and
     * - sender is speaker or organizer, or
     * - sender is attendee and recipient attendy or speaker, or
     * - sender is attendee and recipient is organizer if they messaged before
     * - otherwise, true if they exist and have messaged before.
     *
     * @param senderUsername    username of sender
     * @param recipientUsername username of recipient
     * @return true if they can message.
     */
    public boolean canMessage(String senderUsername, String recipientUsername) {
        UserType sender = accountManager.getUserType(senderUsername);
        UserType recipient = accountManager.getUserType(recipientUsername);
        // todo @parssa
        if (sender == null || recipient == null) return false;

        switch (sender) {
            case ATTENDEE:
                switch (recipient) {
                    case ATTENDEE:
                        break;
                    case SPEAKER:
                        return true;

                }
            case SPEAKER:
                break;
            case ORGANIZER:
                return true;
        }
        return true;
    }


}
