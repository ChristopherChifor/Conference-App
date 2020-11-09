package UseCases;

import Entities.Conversation;
import Entities.Message;
import Entities.User;
import Gateways.JsonDatabase;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * The use case class for messaging.
 * <br>
 * Maps usernames to lists of conversations. Conversations are mutable objects; user1 and user2 would share
 * the same conversation instance between them. Any two users can only have one conversations between them.
 * <br>
 * Conversations are stored in JsonDatabase, where each conversation is its own file in the format:
 * conversation-username1-username2.json
 *
 * @author Alex
 */
public class MessageManager {
    private final String ID_DELIMITER = "-";

    private JsonDatabase<Conversation> jsonDatabase
            = new JsonDatabase<>("conversation", Conversation.class);
    private HashMap<String, List<String>> idCache = new HashMap<>();

    private SocialManager socialManager;
    private AccountManager accounts;


    /**
     * Constructor.
     *
     * @param socialManager
     * @param accounts
     */
    public MessageManager(SocialManager socialManager, AccountManager accounts) {
        this.socialManager = socialManager;
        this.accounts = accounts;
        loadIdCache();
    }


    /**
     * Loads idCache from jsonDatabase.
     */
    private void loadIdCache() {
        List<String> ids = jsonDatabase.getIds();
        for (String id : ids) {
            int index = id.indexOf(ID_DELIMITER);
            String u1 = id.substring(0, index);
            String u2 = id.substring(index + 1);

            (idCache.containsKey(u1) ? idCache.get(u1) : newList(u1)).add(id);
            (idCache.containsKey(u2) ? idCache.get(u2) : newList(u2)).add(id);
        }
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

        Conversation convo = getConversation(sender, recipient);

        convo = (convo == null) ? newConversation(sender, recipient) : convo;

        convo.addMessage(message);
        jsonDatabase.write(convo,
                String.format("%s-%s", convo.getUserOne(), convo.getUserTwo()));

        return true;
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
     * Checks if sender can message recipient.
     * <p>
     * They can message iff:
     * - both users exist
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
     * Gets the messages between user1 and user2 as a list of formatted strings in the format:
     * "[ username ] body"
     * <p>
     * Note: getMessages(user1, user2) is equivalent to getMessages(user2, user1)
     *
     * @param user1
     * @param user2
     * @return list of formatted strings; empty list if there is no conversation.
     */
    public List<String> getMessages(String user1, String user2) {
        Conversation c = getConversation(user1, user2);
        if (c == null) return new ArrayList<>(); // returns empty list

        return c.getMessages()
                .stream()
                .map(m -> String.format("[ %s ] %s", m.getSender(), m.getBody()))
                .collect(Collectors.toList());

    }

    /**
     * Returns of a list of usernames this person has messaged.
     *
     * @param user username
     * @return list of usernames.
     */
    public List<String> getMyMessages(String user) {
        return idCache.get(user).stream()
                .flatMap(Pattern.compile("-")::splitAsStream)
                .filter(s -> !s.equals(user))
                .collect(Collectors.toList());

    }

    /**
     * Searches conversations of user1 to see if there is a conversation with user2. Returns conversation
     * if there is one.
     *
     * @param user1
     * @param user2
     * @return the conversation between user1 and user2; null if user1 or user2 DNE in db, or there's no conversation.
     */
    private Conversation getConversation(String user1, String user2) {
        if (idCache.containsKey(user1) && idCache.containsKey(user2)) {
            for (String id : idCache.get(user1)) {
                if (id.contains(user2)) return jsonDatabase.read(id);
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
     * Both users share the same conversation object; it is mutable. The conversation is stored in
     * JsonDatabase.
     *
     * @param user1 user 1
     * @param user2 user 2
     * @return the newly created conversation.
     */
    private Conversation newConversation(String user1, String user2) {
        Conversation conversation = new Conversation(user1, user2);
        String id = String.format("%s-%s", user1, user2);

        (idCache.containsKey(user1) ? idCache.get(user1) : newList(user1)).add(id);
        (idCache.containsKey(user2) ? idCache.get(user2) : newList(user2)).add(id);

        jsonDatabase.write(conversation, id);

        return conversation;
    }

    /**
     * Creates a new list for conversation ids for user and puts it in idCache.
     *
     * @param user the user
     * @return the newly created list
     */
    private List<String> newList(String user) {
        List<String> list = new ArrayList<>();
        idCache.put(user, list);
        return list;
    }

    /**
     * Checks if user1 and user2 had a conversation. Does not matter if users exists or not.
     *
     * @param user1
     * @param user2
     * @return true iff user1 and user2 have a conversation
     */
    private boolean hasMessaged(String user1, String user2) {
        if (idCache.containsKey(user1)) {
            for (String id : idCache.get(user1)) {
                if (id.contains(user2)) return true;
            }
        }

        return false;
    }

}
