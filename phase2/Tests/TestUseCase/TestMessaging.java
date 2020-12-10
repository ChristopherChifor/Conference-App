package TestUseCase;

import Controllers.MessageController;
import Entities.User;
import UseCases.AccountManager;
import Util.UserType;
import org.junit.*;
import java.util.HashMap;
import UseCases.MessageManager;
import static org.junit.Assert.*;

public class TestMessaging {


    //TODO Test Cases for the MessageManager Use Case Class

    @Test
    public void testSendMessageNull() {
        MessageManager messageManager = new MessageManager();
        User user = new User("David","CSC207","123", UserType.ATTENDEE);
        User user2 = new User("Sam","United Kingdom","java program", UserType.SPEAKER);
        assertEquals(false, messageManager.sendMessage(null, "CSC207", "Hello"));
        assertEquals(false, messageManager.sendMessage(null, null, "Hello"));
        assertEquals(false, messageManager.sendMessage(null, null, null));
        assertEquals(false, messageManager.sendMessage("United Kingdom",null, null));
        assertEquals(false, messageManager.sendMessage("United Kingdom",null, "Hi"));
    }

    @Test
    public void testCanMessage() {
        MessageController messageController = new MessageController();
        User user = new User("David","CSC207","123", UserType.ATTENDEE);
        User user2 = new User("Sam","United Kingdom","java program", UserType.SPEAKER);
        User user3 = new User("John","Toronto","Clean Architecture", UserType.ORGANIZER);
        assertEquals(false, messageController.canMessage("United Kingdom", "CSC207"));
        assertEquals(false, messageController.canMessage("Toronto", "United Kingdom"));
        assertEquals(false, messageController.canMessage("Toronto", "CSC207"));


    }

    @Test
    public void testCanMessageNull() {
        MessageController messageController = new MessageController();
        User user = new User("David","CSC207","123", UserType.ATTENDEE);
        User user2 = new User("Sam","United Kingdom","java program", UserType.SPEAKER);
        assertEquals(false, messageController.canMessage(null, "CSC207"));
        assertEquals(false, messageController.canMessage(null, null));
        assertEquals(false, messageController.canMessage("United Kingdom", null));
    }


}
