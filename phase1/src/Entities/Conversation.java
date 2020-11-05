package Entities;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nikita
 */
public class Conversation {
    private String userOne, userTwo;
    private ArrayList<Message> messages = new ArrayList<Message>();

    public Conversation(String userOne, String userTwo) {
        this.userOne = userOne;
        this.userTwo = userTwo;
    }

    public void addMessage(Message msg) {
        /**
         * Add passed in message to pre-existing list of messages between the two users (setter)
         *
         * @param msg: message
         */
        messages.add(msg);
    }

    public ArrayList<Message> getMessages() {
        /**
         * Return a list containing messages between the two users (getter)
         *
         * @return an ArrayList messages, which contains all messages between users
         */
        return messages;
    }

    public String getUserOne() {
        return userOne;
    }

    public String getUserTwo() {
        return userTwo;
    }
}
