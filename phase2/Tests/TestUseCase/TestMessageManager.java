package TestUseCase;

import Entities.User;
import UseCases.AccountManager;
import Util.UserType;
import org.junit.*;
import java.util.HashMap;
import UseCases.MessageManager;
import static org.junit.Assert.*;

public class TestMessageManager {


    //TODO Test Cases for the MessageManager Use Case Class

    @Test(timeout=100)
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
    public void testCanMessageNull() {
        MessageManager messageManager = new MessageManager();
        User user = new User("David","CSC207","123", UserType.ATTENDEE);
        User user2 = new User("Sam","United Kingdom","java program", UserType.SPEAKER);
        assertEquals(false, messageManager.canMessage(null, "CSC207"));
        assertEquals(false, messageManager.canMessage(null, null));
        assertEquals(false, messageManager.canMessage("United Kingdom", null));
    }


}
