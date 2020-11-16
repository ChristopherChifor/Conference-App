package Controllers;

import Entities.Message;
import Presenters.Presenter;
import UseCases.MessageManager;

import java.util.ArrayList;

/**
 *  Controller for Handling messaging. User specific.
 *
 *  @author Parssa
 */
public class MessageController extends AbstractController {
    private MessageManager messageManager;
    private final String username;


    /**
     * Constructor
     * @param messageManager message manager
     * @param username username
     */
    public MessageController(MessageManager messageManager, String username) {
        super();
        this.messageManager = messageManager;
        this.username = username;
    }

    /**
     * @see Controllers.AbstractController
     * @param input
     * @param presenter presenter used for UI
     */
    @Override
    protected void executeCommand(String input, Presenter presenter) {
        ArrayList<String> parsedCommand = parseCommand(input);
        switch (parsedCommand.get(0)) {
            case "/open":
                if (parsedCommand.size() < 2) parseInput(input, presenter);
                else openConversation(parsedCommand.get(1), presenter);
                break;

            case "/send":
                if (parsedCommand.size() < 3) parseInput(input,presenter);
                else sendMessage(parsedCommand.get(1), parsedCommand.get(2));
                break;

            case "/inbox":
                getInbox(presenter);
                break;
        }
    }

    /**
     * @see Controllers.AbstractController
     * @param presenter presenter used for UI
     */
    @Override
    protected void startUp(Presenter presenter) {
        String greeting = "--- MESSAGING MENU --- \n Hello " + username + ". \n Type /help for options";
        presenter.printLines(greeting);
    }

    /**
     *  @see Controllers.AbstractController
     */
    @Override
    protected void defineCommands() {
        commands.put("/open", "Opens a conversation.");
        commands.put("/send", "Sends message to a person.");
        commands.put("/inbox", "Displays inbox of all conversations");
    }

    /**
     * Opens a conversation with other user
     * @param otherUser other user they are speaking to
     */
    void openConversation(String otherUser, Presenter presenter) {
        presenter.printLines(messageManager.getMessages(username, otherUser));
    }

    /**
     *  Sends a message from this user to another user
     * @param otherUser the user recieving this message
     * @param messageBody the body of this message
     */
    void sendMessage(String otherUser, String messageBody) {
        if (messageManager.canMessage(username, otherUser)) {
            Message message = new Message(username, otherUser, messageBody);
            messageManager.sendMessage(message);
        }
    }

    /**
     *  Get's this user's inbox and displays it.
     */
    void getInbox(Presenter presenter) {
        presenter.printLines(messageManager.getMyInbox(username));
    }

    /**
     *  Sends to List of user's, only accessible by some users
     * @param otherUsers List of user's to send to
     * @param messageBody Body of message
     */
    void sendMessageMany(ArrayList<String> otherUsers, String messageBody) {
        for (String other : otherUsers) {
            sendMessage(other, messageBody);
        }
    }
}
