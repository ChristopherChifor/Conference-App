package Entities;

import java.util.Deque;
import java.util.List;

/**
 * @author Nikita
 */
public class Conversation {
    String userOne, userTwo;
    Deque<Message> messages;

    public void addMessage(Message msg) {
        // TODO
    }

    public List<Message> getMessages() {
        // TODO
        throw new UnsupportedOperationException();
    }
}
