package Controllers;

import Entities.Message;
import UseCases.MessageManager;

import java.util.List;


public class MessageController {

    private MessageManager messageManager;

    public MessageController(MessageManager messageManager) {
        this.messageManager = messageManager;
    }

    public List<String> getInbox(String username) {
        return messageManager.getMyInbox(username);
    }

    public List<Message> getConversation(String sender, String otherUser) {
        return messageManager.getMessages(sender, otherUser);
    }

    public boolean conversationIsRead(List<Message> messages) {
        return messageManager.conversationIsRead(messages);
    }

    public void markAsRead(List<Message> conversation) {
        messageManager.markAsRead(conversation);
    }

    public void sendMessage(String username, String recipient, String messageText) {
        messageManager.sendMessage(username, recipient, messageText);
    }

    public void deleteMessages(List<String> messageIds) {
        messageManager.deleteMessages(messageIds);
    }

    public void archiveMessages(List<String> messageIds) {
        messageManager.archiveMessages(messageIds);
    }

    public boolean canMessage(String username, String otherUser) {
        return messageManager.canMessage(username, otherUser);
    }
}
