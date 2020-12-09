package Presenters;

import Controllers.MessageController;
import Entities.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Chris and Nikita
 */
public class MessagePresenter {
    private MessageController messageController;
    private String username;

    public MessagePresenter(MessageController messageController, String username) {
        this.messageController = messageController;
        this.username = username;
    }

    public List<String> getInbox() {
        return messageController.getInbox(username);
    }

    public List<Message> getConversation(String otherUser) {
        return messageController.getConversation(username, otherUser);
    }

    public boolean isRead(List<Message> conversation) {
        return messageController.conversationIsRead(conversation);
    }

    public void markAsRead(List<Message> conversation) {
        messageController.markAsRead(conversation);
    }

    public void sendMessage(String recipient, String messageText) {
        messageController.sendMessage(username, recipient, messageText);
    }

    public void deleteMessages(List<Message> messages) {
        List<String> messageIds = new ArrayList<>();
        for (Message m: messages) {
            messageIds.add(m.getSender()+"-"+m.getRecipient());
        }
        messageController.deleteMessages(messageIds);

    }

    public void markAsArchived(List<Message> messages) {
        List<String> messageIds = new ArrayList<>();
        for (Message m: messages) {
            messageIds.add(m.getSender()+"-"+m.getRecipient());
        }
        messageController.archiveMessages(messageIds);
    }

    public boolean canMessage(String otherUser) {
        return messageController.canMessage(username, otherUser);
    }

    public List<Message> getArchivedMessages() {
        return messageController.getArchivedMessages(username);

    }
}
