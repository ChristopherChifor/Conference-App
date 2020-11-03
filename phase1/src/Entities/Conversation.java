package Entities;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nikita
 */
public class Conversation {
    private String userOne, userTwo;
    private ArrayList<Message> messages = new ArrayList<>();

    public Conversation(String userOne, String userTwo) {
        this.userOne = userOne;
        this.userTwo = userTwo;
    }

    public void addMessage(Message msg) {
        // TODO
    }

    public List<Message> getMessages() {
        // TODO
        throw new UnsupportedOperationException();
    }

    public String getUserOne() {
        return userOne;
    }

    public String getUserTwo() {
        return userTwo;
    }
}
