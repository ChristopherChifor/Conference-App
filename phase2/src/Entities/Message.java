package Entities;

import java.io.Serializable;

/**
 * @author Alex
 */
public class Message implements Serializable {
    private String sender, recipient;
    private String body;
    private boolean isArchived = false;

    public Message(String sender, String recipient, String body) {
        this.sender = sender;
        this.recipient = recipient;
        this.body = body;
    }

    public String getSender() {
        return sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getBody() {
        return body;
    }

    public void markAsArchived() {
        isArchived = true;
    }

    public boolean getIsArchived() {
        return isArchived;
    }

}
