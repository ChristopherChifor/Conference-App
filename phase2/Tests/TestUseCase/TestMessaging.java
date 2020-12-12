package TestUseCase;

import Controllers.AccountController;
import Controllers.MessageController;
import Util.UserType;
import org.junit.*;
import static org.junit.Assert.*;

public class TestMessaging {


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
        assertFalse(messageController.sendMessage(null, "CSC207", "Hello"));
        assertFalse(messageController.sendMessage(null, null, "Hello"));
        assertFalse(messageController.sendMessage(null, null, null));
        assertFalse(messageController.sendMessage("United Kingdom", null, null));
        assertFalse(messageController.sendMessage("United Kingdom", null, "Hi"));
        assertFalse(messageController.sendMessage("Computer", null, "Hi"));
        assertFalse(messageController.sendMessage(null, "Computer", "Hi"));
        assertFalse(messageController.sendMessage("Toronto", null, "Hi"));
        assertFalse(messageController.sendMessage(null, "Toronto", "Hi"));
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
        assertTrue(messageController.sendMessage("CSC207", "United Kingdom", "Hello"));
        assertTrue(messageController.sendMessage("CSC207", "Computer", "Hello"));
        assertFalse(messageController.sendMessage("CSC207", "Toronto", "Hello"));
        assertTrue(messageController.sendMessage("CSC207", "baba", "Hello"));

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
        assertTrue(messageController.sendMessage("Computer", "United Kingdom", "Hello"));
        assertTrue(messageController.sendMessage("Computer", "CSC207", "Hello"));
        assertTrue(messageController.sendMessage("Computer", "baba", "Hello"));
        assertFalse(messageController.sendMessage("Computer", "Toronto", "Hello"));


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
        assertTrue(messageController.sendMessage("United Kingdom", "CSC207", "Hello"));
        assertTrue(messageController.sendMessage("United Kingdom", "Computer", "Hello"));
        assertTrue(messageController.sendMessage("United Kingdom", "Toronto", "Hello"));
        assertTrue(messageController.sendMessage("United Kingdom", "baba", "Hello"));
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
        assertTrue(messageController.sendMessage("Toronto", "United Kingdom", "Hello"));
        assertTrue(messageController.sendMessage("Toronto", "CSC207", "Hello"));
        assertTrue(messageController.sendMessage("Toronto", "Computer", "Hello"));
        assertTrue(messageController.sendMessage("Toronto", "baba", "Hello"));
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
        assertFalse(messageController.sendMessage("United Kingdom", "United Kingdom", "Hello"));
        assertFalse(messageController.sendMessage("CSC207", "CSC207", "Hello"));
        assertFalse(messageController.sendMessage("Toronto", "Toronto", "Hello"));
        assertFalse(messageController.sendMessage("Computer", "Computer", "Hello"));
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
        assertTrue(messageController.canMessage("United Kingdom", "CSC207"));
        assertTrue(messageController.canMessage("Toronto", "United Kingdom"));
        assertTrue(messageController.canMessage("Toronto", "CSC207"));
    }

    @Test
    public void testCanMessageNull() {
        MessageController messageController = new MessageController();
        AccountController accountController = new AccountController();
        accountController.createUser("David","CSC207","123", "123", UserType.ATTENDEE);
        accountController.createUser("Sam","United Kingdom","java program", "java program",
                UserType.SPEAKER);
        assertFalse(messageController.canMessage(null, "CSC207"));
        assertFalse(messageController.canMessage(null, null));
        assertFalse(messageController.canMessage("United Kingdom", null));
    }

    @Test
    public void testGetMessages() {
        MessageController messageController = new MessageController();
        AccountController accountController = new AccountController();
        accountController.createUser("David","CSC207","123", "123", UserType.ATTENDEE);
        accountController.createUser("Sam","United Kingdom","java program", "java program",
                UserType.SPEAKER);
        assertTrue(messageController.sendMessage("United Kingdom", "CSC207", "Hello"));
        assertNotNull(messageController.getConversation("United Kingdom", "CSC207"));
    }

    @Test
    public void testGetMessages2() {
        MessageController messageController = new MessageController();
        AccountController accountController = new AccountController();
        accountController.createUser("Bob","bob","123", "123", UserType.ATTENDEE);
        accountController.createUser("Baba","baba","java program", "java program",
                UserType.ATTENDEE);
        assertTrue(messageController.sendMessage("United Kingdom", "CSC207", "Hello"));
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
        assertTrue(messageController.conversationIsRead("bob", "baba"));
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
        assertTrue(messageController.conversationIsRead("baba", "bob"));
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
        assertFalse(messageController.conversationIsRead("United Kingdom", "baba"));
    }


}
