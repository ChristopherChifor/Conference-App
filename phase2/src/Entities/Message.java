package Entities;

import java.io.Serializable;

/**
 * @author Alex and Chris
 */
public class Message implements Serializable {
    private String sender, recipient;
    private String body;
    private boolean isArchived = false;

    /**
     * Constructor for the message Entity.
     * @param sender sender
     * @param recipient recipient
     * @param body body of the message
     */
    public Message(String sender, String recipient, String body) {
        this.sender = sender;
        this.recipient = recipient;
        this.body = body;
    }

    /**
     *  Gets a sender and returns it.
     * @return sender
     */
    public String getSender() {
        return sender;
    }

    /**
     *  Gets a recipient and returns it.
     * @return recipient
     */
    public String getRecipient() {
        return recipient;
    }

    /**
     *  Gets the body of the message and returns it.
     * @return body of the message
     */
    public String getBody() {
        return body;
    }

    /**
     * Marks a conversation as an archived one.
     */
    public void markAsArchived() {
        isArchived = true;
    }

    /**
     * Gets the archived conversation.
     * @return true iff the conversation is archived.
     */
    public boolean getIsArchived() {
        return isArchived;
    }

}
