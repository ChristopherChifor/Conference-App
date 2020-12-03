package UseCases;

import Entities.Conversation;
import Entities.Message;
import Entities.User;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The use case class for messaging.
 * <p>
 * Maps usernames to lists of conversations. Conversations are mutable objects; user1 and user2 would share
 * the same conversation instance between them. Any two users can only have one conversations between them.
 *
 * @author Alex
 */
public class MessageManager implements Serializable {
    private HashMap<String, ArrayList<Conversation>> database;
    private AccountManager accounts;

    /**
     * Constructor.
     *
     * @param accounts
     */
    public MessageManager(AccountManager accounts) {
        this.database = new HashMap<>();
        this.accounts = accounts;
    }

    /**
     * If participants of this message have a conversation, adds message to conversation. If not,
     * makes a new conversation for sender and recipient and adds message to it.
     * <p>
     * This method assumes that the sender of the message is allowed to message the recipient, a
     * calling method should first call canMessage().
     * <p>
     * In order for the message to be sent, the fields may not be null. If they are, the message is
     * not sent, and method returns false.
     *
     * @param message the message being sent.
     * @return true if message was sent successfully; false if it wasn't (because message invalid).
     */
    public boolean sendMessage(Message message) {
        String sender = message.getSender();
        String recipient = message.getRecipient();

        if (sender == null || recipient == null || message.getBody() == null) return false;

        Conversation conversation = getConversation(sender, recipient);

        conversation = conversation == null ? newConversation(sender, recipient) : conversation;

        conversation.addMessage(message);
        return true;
    }

    /**
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
        User sender = accounts.getUser(senderUsername);
        User recipient = accounts.getUser(recipientUsername);

        if (sender == null || recipient == null) return false;

        switch (sender.getUserType()) {
            case ATTENDEE:
                switch (recipient.getUserType()) {
                    case ATTENDEE:
                    case SPEAKER:
                        return true;
                    default:
                        return hasMessaged(senderUsername, recipientUsername);
                }
            case SPEAKER:
            case ORGANIZER:
                return true;
            default:
                return hasMessaged(senderUsername, recipientUsername);
        }
    }

    /**
     * Returns a list of all usernames this user can message.
     *
     * @param user
     * @return list of usernames
     */
    public List<String> getContacts(String user) {
        return accounts.getUsernames().stream()
                .filter(s -> canMessage(user, s) && s != user)
                .collect(Collectors.toList());
    }

    /**
     * Checks if user1 and user2 had a conversation. Does not matter if users exists or not.
     *
     * @param user1
     * @param user2
     * @return true iff user1 and user2 have a conversation
     */
    private boolean hasMessaged(String user1, String user2) {
        if (database.containsKey(user1)) {
            for (Conversation c : database.get(user1)) {
                if (c.getUserOne().equals(user2) || c.getUserTwo().equals(user2)) return true;
            }
        }

        return false;
    }


    /**
     * Gets the messages between user1 and user2 as a list of formatted strings in the format:
     * "[ username ] body"
     * <p>
     * Note: getMessages(user1, user2) is equivalent to getMessages(user2, user1)
     *
     * @param user1
     * @param user2
     * @return list of formatted strings; empty string if there is no conversation.
     */
    public ArrayList<String> getMessages(String user1, String user2) {
        ArrayList<String> messages = new ArrayList<>();
        Conversation c = getConversation(user1, user2);

        if (c == null) return messages; // returns empty list

        for (Message m : c.getMessages()) {
            // an example of the format "[ alex ] hello world!"
            messages.add(String.format("[ %s ] %s", m.getSender(), m.getBody()));
        }

        return messages;
    }

    /**
     * Searches conversations of user1 to see if there is a conversation with user2.
     *
     * @param user1
     * @param user2
     * @return the conversation between user1 and user2; null if user1 or user2 DNE in db, or there's no conversation.
     */
    private Conversation getConversation(String user1, String user2) {
        if (database.containsKey(user1) && database.containsKey(user2)) {
            for (Conversation c : database.get(user1)) {
                if (c.getUserOne() == user2 || c.getUserTwo() == user2) return c;
            }
        }

        return null;
    }

    /**
     * Creates a conversation between two users and puts it in the list of their conversations.
     *
     * <b>This should be used iff user1 and user2 don't have conversation (i.e., messaging for the
     * first time).</b>
     * <p>
     * Both users share the same conversation object; it is mutable.
     *
     * @param user1 user 1
     * @param user2 user 2
     * @return the newly created conversation.
     */
    private Conversation newConversation(String user1, String user2) {
        Conversation conversation = new Conversation(user1, user2);

        (database.containsKey(user1) ? database.get(user1) : newList(user1)).add(conversation);
        (database.containsKey(user2) ? database.get(user2) : newList(user2)).add(conversation);

        return conversation;
    }

    /**
     * Creates a new list for conversations for user and puts it in database.
     *
     * @param user the user
     * @return the newly created list
     */
    private ArrayList<Conversation> newList(String user) {
        ArrayList<Conversation> list = new ArrayList<>();
        database.put(user, list);
        return list;
    }

    /**
     * Returns of a list of usernames this person has messaged.
     *
     * @param user username
     * @return list of usernames; empty list if user not in database
     */
    public List<String> getMyInbox(String user) {
        if (!database.containsKey(user)) return new ArrayList<>();
        return database.get(user).stream()
                .map(s -> (s.getUserOne().equals(user) ? s.getUserTwo() : s.getUserOne()))
                .collect(Collectors.toList());

    }

}
