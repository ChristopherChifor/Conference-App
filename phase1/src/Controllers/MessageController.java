package Controllers;

import Entities.Message;
import Presenters.Presenter;
import UseCases.MessageManager;

import java.util.ArrayList;

/**
 *  Controller for Handling messaging. User specific.
 *
 *  WIP: Need to make subclasses of AttendeeMessage
 *
 *  @author Parssa
 */
public class MessageController extends AbstractController {

    private MessageManager messageManager;
    private final String username;


    public MessageController(MessageManager messageManager, String username, Presenter presenter) {
        super(presenter);
        this.messageManager = messageManager;
        this.username = username;
    }

    @Override
    protected void executeCommand(String input, Presenter presenter) {
        ArrayList<String> parsedCommand = parseCommand(input);
        switch (parsedCommand.get(0)) {
            case "/open":
                if (parsedCommand.size() < 2) parseInput(input, presenter);
                openConversation(parsedCommand.get(1));
            case "/send":
                if (parsedCommand.size() < 2) parseInput(input, presenter);
                sendMessage(parsedCommand.get(1), parsedCommand.get(2));
            case "/inbox": {
                getInbox();
            }
            default:
                break;
        }
    }

    @Override
    protected void parseInput(String input, Presenter presenter) {

    }

    @Override
    protected void startUp(Presenter presenter) {

    }

    @Override
    protected void defineCommands() {
        commands.put("/open", "Opens a conversation.");
        commands.put("/send", "Sends message to a person.");
        commands.put("/inbox", "Displays inbox of all conversations");
    }


    private void openConversation(String otherUser) {
        presenter.printList(messageManager.getMessages(username, otherUser));

    }

    /**
     *  Sends a message from this user to another user
      * @param otherUser the user recieving this message
     * @param messageBody the body of this message
     */
    private void sendMessage(String otherUser, String messageBody) {
        Message message = new Message(username, otherUser, messageBody);
        if (messageManager.canMessage(username, otherUser)) {
            messageManager.sendMessage(message);
        }
    }

    /**
     *  Get's this user's inbox and displays it out
     */
    private void getInbox() {
        presenter.printList(messageManager.getMyMessages(username));
    }

    private void sendMessageMany(ArrayList<String> otherUsers) {
        // TODO LEFT OFF HERE
    }
}
