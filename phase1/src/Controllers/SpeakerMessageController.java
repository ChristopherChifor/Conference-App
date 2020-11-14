package Controllers;

import Presenters.Presenter;
import UseCases.MessageManager;

// TODO Nikita
public class SpeakerMessageController extends MessageController {
    public SpeakerMessageController(MessageManager messageManager, String username, Presenter presenter) {
        super(messageManager, username, presenter);
    }
}
