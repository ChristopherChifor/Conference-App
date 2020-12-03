package Entities;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * @author Nikita
 */
public class Conversation implements Serializable {
    private String userOne, userTwo;
    private ArrayList<Message> messages = new ArrayList<Message>();
    private boolean isRead;
    public Conversation(String userOne, String userTwo) {
        this.userOne = userOne;
        this.userTwo = userTwo;
        this.isRead = false;
    }

    /**
     * Add passed in message to pre-existing list of messages between the two users (setter)
     *
     * @param msg: message
     */
    public void addMessage(Message msg) {

        messages.add(msg);
    }

    /**
     * Return a list containing messages between the two users (getter)
     *
     * @return an ArrayList messages, which contains all messages between users
     */
    public ArrayList<Message> getMessages() {

        return messages;
    }

    public String getUserOne() {
        return userOne;
    }

    public String getUserTwo() {
        return userTwo;
    }

    public void markAsRead() {
        this.isRead = true;
    }

    public boolean getIsRead() {
        return isRead;
    }
}
