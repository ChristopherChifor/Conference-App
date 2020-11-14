package Controllers;

import Presenters.Presenter;
import UseCases.MessageManager;
import UseCases.ScheduleManager;

import java.util.ArrayList;

// TODO Nikita
public class SpeakerMessageController extends MessageController {
    ScheduleManager scheduleManager;

    public SpeakerMessageController(MessageManager messageManager, String username, Presenter presenter,
                                    ScheduleManager scheduleManager) {
        super(messageManager, username, presenter);
        this.scheduleManager = scheduleManager;
    }

    @Override
    protected void executeCommand(String input) {
        ArrayList<String> parsedCommand = parseCommand(input);
        switch (parsedCommand.get(0)) {
            case "/open":
                if (parsedCommand.size() < 2) parseInput(input);
                else openConversation(parsedCommand.get(1));

            case "/send":
                if (parsedCommand.size() < 3) parseInput(input);
                else sendMessage(parsedCommand.get(1), parsedCommand.get(2));

            case "/inbox":
                getInbox();

            case "/sendtoevent(s)":
                if (parsedCommand.size() < 2) parseInput(input);
                else {
                    ArrayList<String> eventList = new ArrayList(parsedCommand.subList(1, parsedCommand.size()-2));
                    sendToAttendees(eventList, parsedCommand.get(parsedCommand.size() -1));
                }

        }
    }

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
