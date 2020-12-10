package Controllers;

import Entities.Message;
import UseCases.AccountManager;
import UseCases.MessageManager;

import java.util.List;


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

    public boolean canMessage(String username, String otherUser) {
        return messageManager.canMessage(username, otherUser);
    }

    public List<String> getContacts(String username) {
        return messageManager.getContacts(username);
    }
}
