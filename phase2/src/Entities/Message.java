package Entities;

import java.io.Serializable;

/**
 * @author Alex
 */
public class Message implements Serializable {
    private String sender, recipient;
    private String body;

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
}
