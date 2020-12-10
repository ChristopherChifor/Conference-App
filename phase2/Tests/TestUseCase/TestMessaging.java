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
        assertEquals(false, messageController.canMessage("United Kingdom", "CSC207"));
        assertEquals(false, messageController.canMessage("Toronto", "United Kingdom"));
        assertEquals(false, messageController.canMessage("Toronto", "CSC207"));


    }

    @Test
    public void testCanMessageNull() {
        MessageController messageController = new MessageController();
        AccountController accountController = new AccountController();
        accountController.createUser("David","CSC207","123", "123", UserType.ATTENDEE);
        accountController.createUser("Sam","United Kingdom","java program", "java program",
                UserType.SPEAKER);
        assertEquals(false, messageController.canMessage(null, "CSC207"));
//        assertEquals(false, messageController.canMessage(null, null));
//        assertEquals(false, messageController.canMessage("United Kingdom", null));
    }


}
