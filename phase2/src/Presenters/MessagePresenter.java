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

    public MessagePresenter(String username, MainPresenter mainPresenter) {
        this.username = username;
        messageController = new MessageController();
        this.mainPresenter = mainPresenter;
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
        messageController.archiveMessages(messages);
    }

    public boolean canMessage(String otherUser) {
        return messageController.canMessage(username, otherUser);
    }

    public List<Message> getArchivedMessages() {
        return messageController.getArchivedMessages(username);
    }

    public List<String> getContacts() {
        return messageController.getContacts(username);
    }

    @Override
    public View makeView() {
        return new MessageView(this);
    }

    @Override
    public MainPresenter getMainPresenter() {
        return mainPresenter;
    }
}
