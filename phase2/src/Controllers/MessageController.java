package Controllers;

import Entities.Message;
import UseCases.AccountManager;
import UseCases.MessageManager;
import Util.UserType;

import java.util.List;
import java.util.stream.Collectors;


public class MessageController {

    private MessageManager messageManager;
    private AccountManager accountManager;

    public MessageController() {
        messageManager = new MessageManager();
    }

    public List<String> getInbox(String username) {
        return messageManager.getMyInbox(username);
    }

    public List<Message> getConversation(String sender, String otherUser) {
        return messageManager.getMessages(sender, otherUser);
    }

    public boolean conversationIsRead(List<Message> messages) {
        if (messages.size() == 0) return false;
        return messageManager.conversationIsRead(messages);
    }

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

    public void deleteMessages(List<String> messageIds) {
        messageManager.deleteMessages(messageIds);
    }

    public void archiveMessages(List<Message> messageIds) {
        messageManager.archiveMessages(messageIds);
    }

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
