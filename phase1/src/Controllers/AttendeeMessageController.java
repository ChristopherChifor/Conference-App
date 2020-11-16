package Controllers;

import UseCases.MessageManager;

public class AttendeeMessageController extends MessageController{
    public AttendeeMessageController(MessageManager messageManager, String username) {
        super(messageManager, username);
    }
}
