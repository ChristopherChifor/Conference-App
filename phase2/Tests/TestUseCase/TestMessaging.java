package TestUseCase;

import Controllers.AccountController;
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
        MessageController messageController = new MessageController();
        AccountController accountController = new AccountController();
        accountController.createUser("David","CSC207","123", "123", UserType.ATTENDEE);
        accountController.createUser("Sam","United Kingdom","java program", "java program",
                UserType.SPEAKER);
        assertEquals(false, messageController.sendMessage(null, "CSC207", "Hello"));
        assertEquals(false, messageController.sendMessage(null, null, "Hello"));
        assertEquals(false, messageController.sendMessage(null, null, null));
        assertEquals(false, messageController.sendMessage("United Kingdom",null, null));
        assertEquals(false, messageController.sendMessage("United Kingdom",null, "Hi"));
    }

    @Test
    public void testCanMessage() {
        MessageController messageController = new MessageController();
        AccountController accountController = new AccountController();
        accountController.createUser("David","CSC207","123", "123", UserType.ATTENDEE);
        accountController.createUser("Sam","United Kingdom","java program", "java program",
                UserType.SPEAKER);
        accountController.createUser("John","Toronto","Clean Architecture", "Clean Architecture",
                UserType.ORGANIZER);
        assertEquals(true, messageController.canMessage("United Kingdom", "CSC207"));
        assertEquals(true, messageController.canMessage("Toronto", "United Kingdom"));
        assertEquals(true, messageController.canMessage("Toronto", "CSC207"));


    }

    @Test
    public void testCanMessageNull() {
        MessageController messageController = new MessageController();
        AccountController accountController = new AccountController();
        accountController.createUser("David","CSC207","123", "123", UserType.ATTENDEE);
        accountController.createUser("Sam","United Kingdom","java program", "java program",
                UserType.SPEAKER);
        assertEquals(false, messageController.canMessage(null, "CSC207"));
        assertEquals(false, messageController.canMessage(null, null));
        assertEquals(false, messageController.canMessage("United Kingdom", null));
    }

    @Test
    public void testGetMessages() {
        MessageController messageController = new MessageController();
        AccountController accountController = new AccountController();
        accountController.createUser("David","CSC207","123", "123", UserType.ATTENDEE);
        accountController.createUser("Sam","United Kingdom","java program", "java program",
                UserType.SPEAKER);
        assertEquals(false, messageController.sendMessage("United Kingdom", "CSC207", "Hello"));
//        assertEquals("United Kingdom", messageController.getConversation("United Kingdom", "CSC207"));

    }

    @Test
    public void testGetMessages2() {
        MessageController messageController = new MessageController();
        AccountController accountController = new AccountController();
        accountController.createUser("Bob","bob","123", "123", UserType.ATTENDEE);
        accountController.createUser("Baba","baba","java program", "java program",
                UserType.ATTENDEE);
        assertEquals(true, messageController.sendMessage("United Kingdom", "CSC207", "Hello"));
//        assertEquals("United Kingdom", messageController.getConversation("United Kingdom", "CSC207"));

    }


}
