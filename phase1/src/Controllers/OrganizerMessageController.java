package Controllers;

import Presenters.Presenter;
import UseCases.MessageManager;

// TODO
public class OrganizerMessageController extends MessageController{
    public OrganizerMessageController(MessageManager messageManager, String username, Presenter presenter) {
        super(messageManager, username, presenter);
    }
}
