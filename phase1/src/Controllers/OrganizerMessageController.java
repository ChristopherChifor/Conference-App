package Controllers;

import Presenters.Presenter;
import UseCases.MessageManager;

// TODO Paya
//  Also please do the Javadocs on AccountManager
public class OrganizerMessageController extends MessageController{
    public OrganizerMessageController(MessageManager messageManager, String username, Presenter presenter) {
        super(messageManager, username, presenter);
    }
}
