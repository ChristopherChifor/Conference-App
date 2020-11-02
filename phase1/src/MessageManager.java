import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;

// TODO Disregard this class until further notice!!!!

public class MessageManager {
    HashMap<String, ArrayList<Conversation>> database;
    SocialManager socialManager;

    public boolean sendMessage(Message message) {
        /* TODO
           add this message to appropriate convos
        */
        return false;
    }

    public boolean canMessage(String sender, String recipient) {
        // TODO
        // this class MUST consider the rules for attendees, speakers, organizers.
        // return true if sender can message recipient.
        return false;
    }

    public Deque<String> getMessages(String user1, String user2) {
        // TODO
        // returns a deque of messages in the conversation between user 1 and user2
        // as a deque of formatted strings (perhaps "{username}: {messagebody}")
        // this should be equivalent to getMessages(user2, user1)
        return null;
    }

    private Conversation newConversation(String user1, String user2) {
        // TODO
        return null;
    }

}
