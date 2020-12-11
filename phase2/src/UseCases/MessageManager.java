package UseCases;

import Entities.Conversation;
import Entities.Message;
import Entities.User;
import Gateways.JsonDatabase;
import Util.UserType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The use case class for messaging.
 * <p>
 * Maps usernames to lists of conversations. Conversations are mutable objects; user1 and user2 would share
 * the same conversation instance between them. Any two users can only have one conversations between them.
 *
 * @author Chris, Nikita and Parssa
 */
public class MessageManager implements Serializable {
    private JsonDatabase<Conversation> messageDatabase;
    private AccountManager accountManager;

    /**
     * Constructor.
     *
     */
    public MessageManager() {
        this.accountManager = new AccountManager();
        messageDatabase = new JsonDatabase("Messages", Conversation.class);
    }

    /**
     * If participants of this message have a conversation, adds message to conversation. If not,
     * makes a new conversation for sender and recipient and adds message to it.
     *
     * @param sender sender of message
     * @param recipient recipient of message
     * @param messageBody body of message
     * @return boolean if sent
     */
    public boolean sendMessage(String sender, String recipient, String messageBody) {
        boolean hasMessaged = hasMessaged(sender, recipient);
        if (hasMessaged) System.out.println("they have messaged");
        else System.out.println("they have NOTTTTT messaged");
        if (!hasMessaged) newConversation(sender, recipient);
        Message message = new Message(sender, recipient, messageBody);
        Conversation c = getConversation(sender, recipient);
        c.addMessage(message);
        String convoID = getIDFromMessages(getConversationThread(sender, recipient));
        if (convoID == null) {
            System.out.println("gotcha!");
            convoID = sender+"-"+recipient;
        }
        System.out.println("printed convoID as: " + convoID);
        messageDatabase.write(c, convoID);
        return true;
    }

    /**
     * Checks if user1 and user2 had a conversation. Does not matter if users exists or not.
     * Uses JSONDatabase.
     *
     * @param user1 user 1
     * @param user2 user 2
     * @return true iff user1 and user2 have a conversation
     */
    private boolean hasMessaged(String user1, String user2) {
        List<String> conversations = messageDatabase.getIds();
        for (String c : conversations) {
            String u1 = c.substring(0, c.indexOf("-"));
            if (u1.equals(user1) || u1.equals(user2)){
                String u2 = c.substring(c.indexOf("-")+1);
                if (u2.equals(user1) || u2.equals(user2)){
                    return true;
                }
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
     * @param user1 user 1
     * @param user2 user 2
     * @return list of formatted strings; empty string if there is no conversation.
     */
    public ArrayList<Message> getMessages(String user1, String user2) {
        return (ArrayList<Message>) getConversationThread(user1, user2);
    }

    /**
     * Searches for a conversation between two users.
     * @param user1 user1
     * @param user2 user2
     * @return returns that conversation
     */
    private Conversation getConversation(String user1, String user2) {
        List<String> conversations = messageDatabase.getIds();
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA  ");
        System.out.println(conversations);
        for (String c : conversations) {
            System.out.println(c);
            String u1 = c.substring(0, c.indexOf("-")+1);
            if (u1.equals(user1) || u1.equals(user2)){
                String u2 = c.substring(c.indexOf("-")+1);
                if (u2.equals(user1) || u2.equals(user2)){
                    return messageDatabase.read(c);
                }
            }
        }

        // if it's made it here, we don't got a conversation, thus we will make one
        return newConversation(user1, user2);
    }

    /**
     * Searches conversations of user1 to see if there is a conversation with user2.
     *
     * @param user1 user 1
     * @param user2 user 2
     * @return the conversation between user1 and user2; null if user1 or user2 DNE in db, or there's no conversation.
     */
    private List<Message> getConversationThread(String user1, String user2) {
        return getConversation(user1,user2).getMessages();
    }




//    /** todo remove
//     * Returns of a list of usernames this person can message.
//     *
//     * @param user username
//     * @return list of usernames; empty list if user not in database
//     */
//    public ArrayList<String> getMyInbox(String thisUser) {
////        System.out.println("------------------------this was called");
////        List<String> conversations = messageDatabase.getIds();
//
////        System.out.println(conversations);
////        for (String c : conversations) {
////            System.out.println("------------------------1");
////            if (c.contains(user))  {
////                String otherUser = c;
////                otherUser.replace(user,"");
////                otherUser.replace("-","");
////                System.out.println("OTHER USER IS : "+ otherUser);
////                myInbox.add(otherUser);
////            }
////        }
//        ArrayList<String> myInbox = new ArrayList<>();
//        Set<String> allUsernames = accountManager.getUsernames().stream().filter(u->u!=thisUser).filter(u->)
//        // foreach user, canMessage
//
//        return myInbox;
//    }

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
     * @return returns the conversation
     */
    private Conversation newConversation(String user1, String user2) {
        System.out.println("making a new conversation with "+ user1 + " "+ user2);
        Conversation conversation = new Conversation(user1, user2);
        messageDatabase.write(conversation, user1+"-"+user2);
        return conversation;
    }

    /**
     * Checks if the conversation has been read.
     * @param convoID ID of the conversation
     * @return true if the conversation is read, otherwise, false if not read.
     */
    public boolean conversationIsRead(String convoID) {
        return messageDatabase.read(convoID).getIsRead();
    }

    /**
     * Marks a conversation as read.
     * @param conversationID ID of conversation
     */
    public void markAsRead(String conversationID) {
        Conversation c =messageDatabase.read(conversationID);
        c.markAsRead();
        messageDatabase.write(c, conversationID);
    }

    /**
     * Gets a conversation between two users and returns as string.
     * @param messages list of messages
     * @return string of a sender and recipient of a message.
     */
    private String getIDFromMessages(List<Message> messages) {
        if (messages.size() == 0) return null;
        return messages.get(0).getSender()+"-"+ messages.get(0).getRecipient();
    }

    /**
     * Deletes a message
     * @param messageIds list of messages
     */
    public void deleteMessages(List<String> messageIds) {
        for (String a : messageIds) {
            messageDatabase.delete(a);
        }
    }

    /**
     * Takes messages and puts them into the archived list.
     * @param messages list of messages
     */
    public void archiveMessages(List<Message> messages) {
        for (Message a : messages) {
            a.markAsArchived();
        }
    }

    public ArrayList<Message> getArchivedMessages(String sender, String recipient) {
        ArrayList<Message>conversation =  getConversation(sender, recipient).getMessages();
        ArrayList<Message> archived = new ArrayList<>();
        for (Message m : conversation) {
            if (m.getIsArchived()) {
                archived.add(m);
            }
        }
        return archived;
    }

    public String getConvoID(String user1, String user2) {
        List<String> allIDs = messageDatabase.getIds();
        for (String id : allIDs) {
            if (id.contains(user1) && id.contains(user2)) {
                return id;
            }
        }
        return "";
    }
}
