package Controllers;

import Presenters.Presenter;
import UseCases.MessageManager;
import UseCases.ScheduleManager;

import java.util.ArrayList;

public class SpeakerMessageController extends MessageController {
    ScheduleManager scheduleManager;

    public SpeakerMessageController(MessageManager messageManager, String username,
                                    ScheduleManager scheduleManager) {
        super(messageManager, username);
        this.scheduleManager = scheduleManager;
    }

    /**
     * Execute a command based on the String input
     * @param input the input(String) containing information such as the command, the users, and the message body.
     */
    @Override
    protected void executeCommand(String input, Presenter presenter) {
        ArrayList<String> parsedCommand = parseCommand(input);
        switch (parsedCommand.get(0)) {
            case "/open":
                if (parsedCommand.size() < 2) parseInput(input,presenter);
                else openConversation(parsedCommand.get(1),presenter);
                break;

            case "/send":
                if (parsedCommand.size() < 3) parseInput(input,presenter);
                else sendMessage(parsedCommand.get(1), parsedCommand.get(2));
                break;

            case "/inbox":
                getInbox(presenter);
                break;

            case "/sendtoevent(s)":
                if (parsedCommand.size() < 2) parseInput(input,presenter);
                else {
                    ArrayList<String> eventList = new ArrayList(parsedCommand.subList(1, parsedCommand.size()-2));
                    sendToAttendees(eventList, parsedCommand.get(parsedCommand.size() -1));
                }
                break;
        }
    }

    /**
     *  Definitions of commands a speaker can do.
     */
    @Override
    protected void defineCommands() {
        super.defineCommands();
        commands.put("/sendtoevent(s)", "Send message to all attendees in talk(s).");
    }

    /**
     * Sends message to attendees in events
     * @param events events that each list of attendees is being messaged
     * @param messageBody body of message
     */
    protected void sendToAttendees(ArrayList<String> events, String messageBody){
          for(String event : events){
              ArrayList<String> attendees = scheduleManager.getEventAttendees(event);
              sendMessageMany(attendees, messageBody);
          }
    }
}
