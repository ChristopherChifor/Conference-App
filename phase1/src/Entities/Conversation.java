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
        /**
         * Add passed in message to pre-existing list of messages between the two users
         * @param msg: message
         */
        messages.add(msg);
    }

    public List<Message> getMessages() {
        /**
         * Return a list containing messages between the two users
         * @return a new List containing the same elements as the ArrayList messages
         */
        if (!messages.isEmpty()){
            ArrayList<Message> copy = new ArrayList<>();
            for (Message msg : messages) copy.add(new Message(msg.getSender(), msg.getRecipient(), msg.getBody()));
            return copy; //deep copy
            //return new ArrayList<>(messages); //shallow copy
        } else {throw new UnsupportedOperationException();}
    }

    public String getUserOne() {
        return userOne;
    }

    public String getUserTwo() {
        return userTwo;
    }
}
