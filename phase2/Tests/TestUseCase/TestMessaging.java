package TestUseCase;

import Controllers.AccountController;
import Controllers.MessageController;
import Entities.User;
import UseCases.AccountManager;
import Util.UserType;
import org.junit.*;
import java.util.HashMap;
import java.util.List;

import UseCases.MessageManager;
import static org.junit.Assert.*;

public class TestMessaging {


    //TODO Test Cases for Messaging

    @Test
    public void testSendMessageNull() {
        MessageController messageController = new MessageController();
        AccountController accountController = new AccountController();
        accountController.createUser("David","CSC207","123", "123", UserType.ATTENDEE);
        accountController.createUser("Sam","United Kingdom","java program", "java program",
                UserType.SPEAKER);
        accountController.createUser("John","Toronto","Clean Architecture", "Clean Architecture",
                UserType.ORGANIZER);
        accountController.createUser("Alex","Computer","100", "100", UserType.VIP);
        assertEquals(false, messageController.sendMessage(null, "CSC207", "Hello"));
        assertEquals(false, messageController.sendMessage(null, null, "Hello"));
        assertEquals(false, messageController.sendMessage(null, null, null));
        assertEquals(false, messageController.sendMessage("United Kingdom",null, null));
        assertEquals(false, messageController.sendMessage("United Kingdom",null, "Hi"));
        assertEquals(false, messageController.sendMessage("Computer",null, "Hi"));
        assertEquals(false, messageController.sendMessage(null,"Computer", "Hi"));
        assertEquals(false, messageController.sendMessage("Toronto",null, "Hi"));
        assertEquals(false, messageController.sendMessage(null,"Toronto", "Hi"));
    }

    @Test
    public void testSendMessageFromAttendee() {
        MessageController messageController = new MessageController();
        AccountController accountController = new AccountController();
        accountController.createUser("David","CSC207","123", "123", UserType.ATTENDEE);
        accountController.createUser("Alex","Computer","100", "100", UserType.ATTENDEE);
        accountController.createUser("Chris","baba","789", "789",
                UserType.VIP);
        accountController.createUser("Sam","United Kingdom","java program", "java program",
                UserType.SPEAKER);
        accountController.createUser("John","Toronto","Clean Architecture", "Clean Architecture",
                UserType.ORGANIZER);
        assertEquals(true, messageController.sendMessage("CSC207", "United Kingdom", "Hello"));
        assertEquals(true, messageController.sendMessage("CSC207", "Computer", "Hello"));
        assertEquals(false, messageController.sendMessage("CSC207", "Toronto", "Hello"));
        assertEquals(true, messageController.sendMessage("CSC207", "baba", "Hello"));

    }

    @Test
    public void testSendMessageFromVIP() {
        MessageController messageController = new MessageController();
        AccountController accountController = new AccountController();
        accountController.createUser("David","CSC207","123", "123", UserType.ATTENDEE);
        accountController.createUser("Sam","United Kingdom","java program", "java program",
                UserType.SPEAKER);
        accountController.createUser("John","Toronto","Clean Architecture", "Clean Architecture",
                UserType.ORGANIZER);
        accountController.createUser("Chris","baba","789", "789",
                UserType.VIP);
        accountController.createUser("Alex","Computer","100", "100", UserType.VIP);
        assertEquals(true, messageController.sendMessage("Computer", "United Kingdom", "Hello"));
        assertEquals(true, messageController.sendMessage("Computer", "CSC207", "Hello"));
        assertEquals(true, messageController.sendMessage("Computer", "baba", "Hello"));
        assertEquals(false, messageController.sendMessage("Computer", "Toronto", "Hello"));


    }

    @Test
    public void testSendMessageFromSpeaker() {
        MessageController messageController = new MessageController();
        AccountController accountController = new AccountController();
        accountController.createUser("David","CSC207","123", "123", UserType.ATTENDEE);
        accountController.createUser("Chris","baba","789", "789",
                UserType.VIP);
        accountController.createUser("Sam","United Kingdom","java program", "java program",
                UserType.SPEAKER);
        accountController.createUser("Alex","Computer","100", "100", UserType.SPEAKER);
        accountController.createUser("John","Toronto","Clean Architecture", "Clean Architecture",
                UserType.ORGANIZER);
        assertEquals(true, messageController.sendMessage("United Kingdom", "CSC207", "Hello"));
        assertEquals(true, messageController.sendMessage("United Kingdom", "Computer", "Hello"));
        assertEquals(true, messageController.sendMessage("United Kingdom", "Toronto", "Hello"));
        assertEquals(true, messageController.sendMessage("United Kingdom", "baba", "Hello"));


    }

    @Test
    public void testSendMessageFromOrganizer() {
        MessageController messageController = new MessageController();
        AccountController accountController = new AccountController();
        accountController.createUser("David","CSC207","123", "123", UserType.ATTENDEE);
        accountController.createUser("Chris","baba","789", "789",
                UserType.VIP);
        accountController.createUser("Sam","United Kingdom","java program", "java program",
                UserType.SPEAKER);
        accountController.createUser("John","Toronto","Clean Architecture", "Clean Architecture",
                UserType.ORGANIZER);
        accountController.createUser("Alex","Computer","100", "100", UserType.ORGANIZER);
        assertEquals(true, messageController.sendMessage("Toronto", "United Kingdom", "Hello"));
        assertEquals(true, messageController.sendMessage("Toronto", "CSC207", "Hello"));
        assertEquals(true, messageController.sendMessage("Toronto", "Computer", "Hello"));
        assertEquals(true, messageController.sendMessage("Toronto", "baba", "Hello"));



    }


    @Test
    public void testSendMessageToThemself() {
        MessageController messageController = new MessageController();
        AccountController accountController = new AccountController();
        accountController.createUser("David","CSC207","123", "123", UserType.ATTENDEE);
        accountController.createUser("Sam","United Kingdom","java program", "java program",
                UserType.SPEAKER);
        accountController.createUser("John","Toronto","Clean Architecture", "Clean Architecture",
                UserType.ORGANIZER);
        accountController.createUser("Alex","Computer","100", "100", UserType.VIP);
        assertEquals(false, messageController.sendMessage("United Kingdom", "United Kingdom", "Hello"));
        assertEquals(false, messageController.sendMessage("CSC207", "CSC207", "Hello"));
        assertEquals(false, messageController.sendMessage("Toronto", "Toronto", "Hello"));
        assertEquals(false, messageController.sendMessage("Computer", "Computer", "Hello"));




    }


    @Test
    public void testSendMessageFromAndToInvalidUsernames() {
        MessageController messageController = new MessageController();
        AccountController accountController = new AccountController();
        accountController.createUser("David","CSC207","123", "123", UserType.ATTENDEE);
        accountController.createUser("Sam","United Kingdom","java program", "java program",
                UserType.SPEAKER);
        accountController.createUser("John","Toronto","Clean Architecture", "Clean Architecture",
                UserType.ORGANIZER);
        accountController.createUser("Alex","Computer","100", "100", UserType.VIP);
        assertNull(messageController.sendMessage("United Kingdom", "a", "Hello"));
        assertNull(messageController.sendMessage("CSC207", "b", "Hello"));
        assertNull(messageController.sendMessage("Toronto", "c", "Hello"));
        assertNull(messageController.sendMessage("Computer", "d", "Hello"));




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
        assertEquals(true, messageController.sendMessage("United Kingdom", "CSC207", "Hello"));
        assertNotNull(messageController.getConversation("United Kingdom", "CSC207"));

    }

    @Test
    public void testGetMessages2() {
        MessageController messageController = new MessageController();
        AccountController accountController = new AccountController();
        accountController.createUser("Bob","bob","123", "123", UserType.ATTENDEE);
        accountController.createUser("Baba","baba","java program", "java program",
                UserType.ATTENDEE);
        assertEquals(true, messageController.sendMessage("United Kingdom", "CSC207", "Hello"));
        assertNotNull(messageController.getConversation("United Kingdom", "CSC207"));

    }


    @Test
    public void testGetInbox() {
        MessageController messageController = new MessageController();
        AccountController accountController = new AccountController();
        accountController.createUser("Bob","bob","123", "123", UserType.ATTENDEE);
        accountController.createUser("Baba","baba","java program", "java program",
                UserType.ATTENDEE);
        assertNotNull(messageController.getInbox("bob"));
        assertNotNull(messageController.getInbox("baba"));

    }

    @Test
    public void testMarkAsRead() {
        MessageController messageController = new MessageController();
        AccountController accountController = new AccountController();
        accountController.createUser("Bob","bob","123", "123", UserType.ATTENDEE);
        accountController.createUser("Baba","baba","java program", "java program",
                UserType.ATTENDEE);
        messageController.sendMessage("bob", "baba", "Hi");
        messageController.markAsRead("bob", "baba");
        assertEquals(true, messageController.conversationIsRead("bob","baba"));

    }

    @Test
    public void testMarkAsReadDifferentOrder() {
        MessageController messageController = new MessageController();
        AccountController accountController = new AccountController();
        accountController.createUser("Bob","bob","123", "123", UserType.ATTENDEE);
        accountController.createUser("Baba","baba","java program", "java program",
                UserType.ATTENDEE);
        messageController.sendMessage("bob", "baba", "Hi");
        messageController.markAsRead("bob", "baba");
        assertEquals(true, messageController.conversationIsRead("baba","bob"));

    }

    @Test
    public void testMarkAsReadFalse() {
        MessageController messageController = new MessageController();
        AccountController accountController = new AccountController();
        accountController.createUser("Baba","baba","java program", "java program",
                UserType.ATTENDEE);
        accountController.createUser("Sam","United Kingdom","java program", "java program",
                UserType.SPEAKER);
        messageController.sendMessage("United Kingdom", "baba", "Hello");
        assertEquals(false, messageController.conversationIsRead("United Kingdom","baba"));

    }


}
