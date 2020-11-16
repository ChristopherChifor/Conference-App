package Controllers;

import UseCases.MessageManager;

/**
 * A MessageController for facilitating the attendee messaging capabilities.
 * While this class does not implement anything on its own, it is a separate class for
 * future-proofing.
 *
 * @see Controllers.AbstractController
 * @author Parssa
 */
public class AttendeeMessageController extends MessageController{
    public AttendeeMessageController(MessageManager messageManager, String username) {
        super(messageManager, username);
    }
}
