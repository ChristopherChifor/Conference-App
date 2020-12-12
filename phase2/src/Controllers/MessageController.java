package Controllers;

import Entities.Message;
import UseCases.AccountManager;
import UseCases.MessageManager;
import UseCases.ScheduleManager;
import Util.UserType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: Chris, Nikita, Parssa
 */
public class MessageController {

    private MessageManager messageManager;
    private AccountManager accountManager;
    private ScheduleManager scheduleManager;

    /**
     * Controller for the Messages.
     */
    public MessageController() {
        messageManager = new MessageManager();
        accountManager = new AccountManager();
    }

    /**
     * Returns the inbox as a List of strings of usernames that this user can message.
     * @param username username
     * @return inbox as strings
     */
    public List<String> getInbox(String username) {
        return accountManager
                .getUsernames()
                .stream()
                .filter(u->!u.equals(username))
                .filter(u->canMessage(username, u))
                .collect(Collectors.toList());
    }

    /**
     * Gets a list of messages between two users.
     * @param sender sender
     * @param otherUser recipient
     * @return returns a list of messages between the two users.
     */
    public List<Message> getConversation(String sender, String otherUser) {
        if (messageManager.getConvoID(sender, otherUser) == null) {
            messageManager.newConversation(sender, otherUser);
        }
        return messageManager.getMessages(sender, otherUser);
    }

    /**
     * Checks if the conversation is read
     * @param thisUser this user
     * @param otherUser other user in convo
     * @return true iff conversation is read.
     */
    public boolean conversationIsRead(String thisUser, String otherUser) {

        return messageManager.conversationIsRead(messageManager.getConvoID(thisUser, otherUser));
    }

    /**
     * Marks a conversation as read
     * @param thisUser this user
     * @param otherUser other user in convo
     *
     */
    public void markAsRead(String thisUser, String otherUser) {
        String convoID = messageManager.getConvoID(thisUser, otherUser);
        messageManager.markAsRead(convoID);
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
     * Archives a list of messages
     * @param messageIds the message(s) to be archived
     */
    public void archiveMessages(List<Message> messageIds) {
        messageManager.archiveMessages(messageIds);
    }

    /**
     * Deletes a message
     * @param messages the exact message to delete
     */
    public void deleteMessages(List<Message> messages) {
        if (messages.size() == 0) return;
        List<Integer> messageIds = new ArrayList<>();
        for (Message msg : messages) {
            messageIds.add(msg.getId());
        }
        messageManager.deleteMessages(messageIds, messages.get(0).getSender(), messages.get(0).getRecipient());
    }

    /**
     * TODO DEPRECATE
     *
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
     * Returns a list of archived messages for a user.
     * @param username username
     * @return a list of users
     */
    public List<Message> getArchivedMessages(String username) {
        List<String> otherPeople = getInbox(username);
        List<Message> allArchived = new ArrayList<>();
        for (String person : otherPeople) {
            allArchived.addAll(messageManager.getArchivedMessages(username, person));
        }
        return allArchived;
    }

    /**
     *
     * Checks if sender can message recipient.
     * <p>
     * They can message iff:
     * - both users exist, and
     * - sender is speaker or organizer, or
     * - sender is attendee and recipient attendee or speaker, or
     * - sender is attendee and recipient is organizer if they messaged before
     * - otherwise, true if they exist and have messaged before.
     *
     * @param senderUsername    username of sender
     * @param recipientUsername username of recipient
     * @return true if they can message.
     */
    public boolean canMessage(String senderUsername, String recipientUsername) {
        if (senderUsername == null || recipientUsername == null || senderUsername.equals(recipientUsername)) {
            return false;
        }
        UserType sender = accountManager.getUserType(senderUsername);
        UserType recipient = accountManager.getUserType(recipientUsername);

        switch (sender) {
            case VIP:
            case ATTENDEE:
                switch (recipient) {
                    case ATTENDEE:
                    case SPEAKER:
                        return true;
                    default:
                        return false;
                }
            case SPEAKER:
            case ORGANIZER:
                break;
        }
        return true;
    }

    /**
     * A message to send to all of the attendees attending an event
     * @param eventName Name of an event
     * @param message a message to send to all attendees
     */
    public void messageAll(String eventName, Message message){
        scheduleManager.getEventAttendees(eventName);
    }
}


