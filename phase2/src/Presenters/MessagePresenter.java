package Presenters;

import Controllers.MessageController;
import Entities.Message;

import java.util.List;

public class MessagePresenter {
    private MessageController messageController;

    public MessagePresenter(MessageController messageController) {
        this.messageController = messageController;
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
}
