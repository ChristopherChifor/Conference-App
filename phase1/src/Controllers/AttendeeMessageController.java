package Controllers;

import Presenters.Presenter;
import UseCases.MessageManager;

// TODO Don't worry
public class AttendeeMessageController extends MessageController{
    public AttendeeMessageController(MessageManager messageManager, String username, Presenter presenter) {
        super(messageManager, username, presenter);
    }
}
