package Controllers;

import Presenters.Presenter;
import UseCases.MessageManager;

import java.util.ArrayList;

/**
 * Messaging controller for facilitating the messaging capabilities of organizers.
 * @see Controllers.MessageController
 * @author Paya
 */
public class OrganizerMessageController extends MessageController{
    public OrganizerMessageController(MessageManager messageManager, String username) {
        super(messageManager, username);
    }

    /**
     * @see Controllers.AbstractController
     */
    @Override
    protected void defineCommands() {
        commands.put("/open", "Opens a conversation.");
        commands.put("/send", "Sends message to a person.");
        commands.put("/sendToMany", "Sends a message to many people.");
        commands.put("/inbox", "Displays inbox of all conversations");
    }

    /**
     * @see Controllers.AbstractController
     * @param input the input(String) containing information such as the command, the users, and the message body.
     */
    @Override
    protected void executeCommand(String input, Presenter presenter) {
        ArrayList<String> parsedCommand = parseCommand(input);
        switch (parsedCommand.get(0)) {
            case "/open":
                if (parsedCommand.size() < 2) parseInput(input, presenter);
                openConversation(parsedCommand.get(1),presenter);
                break;
            case "/send":
                if (parsedCommand.size() < 2) parseInput(input,presenter);
                sendMessage(parsedCommand.get(1), parsedCommand.get(2));
                break;
            case "/sendToMany":
                if (parsedCommand.size() < 2) parseInput(input,presenter);
                ArrayList<String> usersArrayList = new ArrayList(parsedCommand.subList(1,
                        parsedCommand.size()-2));
                sendMessageMany(usersArrayList, parsedCommand.get(2));
                break;
            case "/inbox": {
                getInbox(presenter);
                break;
            }
        }
    }


}
