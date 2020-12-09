package Controllers;

import Entities.Message;
import UseCases.MessageManager;

import java.util.List;


public class MessageController {

    private MessageManager messageManager;
    private String username; //TODO MAKE THIS REFERENCE PROPERLY

    public MessageController(MessageManager messageManager) {
        this.messageManager = messageManager;
    }

    public List<String> getInbox() {
        return messageManager.getMyInbox(username);
    }

    public List<Message> getConversation(String otherUser) {
        return messageManager.getMessages(username, otherUser);
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
}
