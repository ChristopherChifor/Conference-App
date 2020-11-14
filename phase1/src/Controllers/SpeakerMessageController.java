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

            case "/sendtoattendees":
        }
    }

    @Override
    protected void defineCommands() {
        super.defineCommands();
        commands.put("/sendtoattendees", "Send message to all attendees in talk(s).");
    }

    protected void sendToAttendees(ArrayList<String> events, String messageBody){
          for(String event : events){
              ArrayList<String> attendees = scheduleManager.getEventAttendees(event);
              super.sendMessageMany(attendees, messageBody);
          }
    }
}
