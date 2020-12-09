package Presenters;

import Controllers.MessageController;
import Entities.Message;

import java.util.List;

/**
 * @author chris
 */
public class MessagePresenter {
    private MessageController messageController;
    private String username;

    public MessagePresenter(MessageController messageController, String username) {
        this.messageController = messageController;
        this.username = username;
    }

    public List<String> getInbox() {
        return messageController.getInbox();
    }

    public List<Message> getConversation(String otherUser) {
        return messageController.getConversation(otherUser);
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
}
