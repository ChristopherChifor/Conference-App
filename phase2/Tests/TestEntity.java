import Entities.*;
import Util.UserType;
import org.junit.*;

import java.util.ArrayList;
import java.util.Calendar;

import static org.junit.Assert.*;
public class TestEntity {



    @Test
    public void testGetNameUser() {
        User user = new User("David","CSC207","123", UserType.ATTENDEE);
        assertEquals("David", user.getName());
        User user2 = new User("Sam","United Kingdom","java program", UserType.SPEAKER);
        assertEquals("Sam", user2.getName());
        User user3 = new User("John","Toronto","Clean Architecture", UserType.ORGANIZER);
        assertEquals("John", user3.getName());
    }

    @Test
    public void testGetUsername() {
        User user = new User("David","CSC207","123", UserType.ATTENDEE);
        assertEquals("CSC207", user.getUsername());
        User user2 = new User("Sam","United Kingdom","java program", UserType.SPEAKER);
        assertEquals("United Kingdom", user2.getUsername());
        User user3 = new User("John","Toronto","Clean Architecture", UserType.ORGANIZER);
        assertEquals("Toronto", user3.getUsername());
    }

    @Test
    public void testGetPassword() {
        User user = new User("David","CSC207","123", UserType.ATTENDEE);
        assertEquals("123", user.getPassword());
        User user2 = new User("Sam","United Kingdom","java program", UserType.SPEAKER);
        assertEquals("java program", user2.getPassword());
        User user3 = new User("John","Toronto","Clean Architecture", UserType.ORGANIZER);
        assertEquals("Clean Architecture", user3.getPassword());
        User user4 = new User("Sam","United Kingdom","java program", UserType.SPEAKER);
        assertEquals("java program", user4.getPassword());
        User user5 = new User("Shashank","Toronto","boss", UserType.ORGANIZER);
        assertEquals("boss", user5.getPassword());
    }

    @Test
    public void testGetUserType() {
        User user = new User("David","CSC207","123", UserType.ATTENDEE);
        assertEquals(UserType.ATTENDEE, user.getUserType());
        User user2 = new User("Sam","United Kingdom","java program", UserType.SPEAKER);
        assertEquals(UserType.SPEAKER, user2.getUserType());
        User user3 = new User("John","Toronto","Clean Architecture", UserType.ORGANIZER);
        assertEquals(UserType.ORGANIZER, user3.getUserType());
    }

    @Test
    public void testSetUserType() {
        User user = new User("David","CSC207","123", UserType.ATTENDEE);
        user.setUserType(UserType.SPEAKER);
        assertEquals(UserType.SPEAKER, user.getUserType());
        user.setUserType(UserType.ORGANIZER);
        assertEquals(UserType.ORGANIZER, user.getUserType());
    }

    @Test
    public void testGetNameRoom() {
        Room room = new Room("Room 207");
        assertEquals("Room 207", room.getName());

    }

    @Test
    public void testGetStringRoom() {
        Room room = new Room("Room 207");
        assertEquals("Room 207", room.getName());
    }

    @Test
    public void testSetAndGetRoomCapacity() {
        Room room = new Room("Room 207");
        room.setRoomCapacity(20);
        assertEquals(20, room.getRoomCapacity());

    }

    @Test
    public void testSetAndGetRoomCapacityVersion2() {
        Room room = new Room("Room 207");
        room.setRoomCapacity(20);
        room.setRoomCapacity(45);
        assertEquals(45, room.getRoomCapacity());

    }

    @Test
    public void testSetAndGetRoomCapacityNegative() {
        Room room = new Room("Room 207");
        assertFalse(room.setRoomCapacity(-20));

    }

    @Test
    public void testMessageGetSender() {
        Message message = new Message("United Kingdom", "CSC207", "Hi");
        assertEquals("United Kingdom", message.getSender());

    }

    @Test
    public void testMessageGetRecipient() {
        Message message = new Message("United Kingdom", "CSC207", "Hi");
        assertEquals("CSC207", message.getRecipient());

    }

    @Test
    public void testMessageGetBody() {
        Message message = new Message("United Kingdom", "CSC207", "Hi");
        assertEquals("Hi", message.getBody());

    }

    @Test
    public void testMessageArchiveFalse() {
        Message message = new Message("United Kingdom", "CSC207", "Hi");
        assertFalse(message.getIsArchived());

    }

    @Test
    public void testMessageArchiveTrue() {
        Message message = new Message("United Kingdom", "CSC207", "Hi");
        message.markAsArchived();
        assertTrue(message.getIsArchived());

    }


    @Test
    public void testScheduleEntryGetEventName() {
        Calendar time = Calendar.getInstance();
        ScheduleEntry scheduleEntry = new ScheduleEntry("Conference", "1", time, 1);
        assertEquals("Conference", scheduleEntry.getEventName());

    }

    @Test
    public void testScheduleEntryGetRoomID() {
        Calendar time = Calendar.getInstance();
        ScheduleEntry scheduleEntry = new ScheduleEntry("Conference", "1", time, 1);
        assertEquals("1", scheduleEntry.getRoomID());

    }

    @Test
    public void testScheduleEntryGetStartTime() {
        Calendar time = Calendar.getInstance();
        ScheduleEntry scheduleEntry = new ScheduleEntry("Conference", "1", time, 1);
        assertEquals(time, scheduleEntry.getStartTime());

    }

    @Test
    public void testScheduleEntryGetDuration() {
        Calendar time = Calendar.getInstance();
        ScheduleEntry scheduleEntry = new ScheduleEntry("Conference", "1", time, 1);
        assertEquals(1, scheduleEntry.getDuration());

    }

    @Test
    public void testScheduleEntrySetRoomID() {
        Calendar time = Calendar.getInstance();
        ScheduleEntry scheduleEntry = new ScheduleEntry("Conference", "1", time, 1);
        scheduleEntry.setRoomID("2");
        assertEquals("2", scheduleEntry.getRoomID());

    }

    @Test
    public void testScheduleEntrySetDuration() {
        Calendar time = Calendar.getInstance();
        ScheduleEntry scheduleEntry = new ScheduleEntry("Conference", "1", time, 1);
        scheduleEntry.setDuration(2);
        assertEquals(2, scheduleEntry.getDuration());

    }

    @Test
    public void testScheduleEntrySetStartTime() {
        Calendar time = Calendar.getInstance();
        Calendar time2 = (Calendar) time.clone();
        ScheduleEntry scheduleEntry = new ScheduleEntry("Conference", "1", time, 1);
        time.add(Calendar.DATE,-1);
        scheduleEntry.setStartTime(time);
        assertNotEquals(time2, time);

    }

    @Test
    public void testScheduleEntrySetStartTime2() {
        Calendar time = Calendar.getInstance();
        Calendar time2 = (Calendar) time.clone();
        ScheduleEntry scheduleEntry = new ScheduleEntry("Conference", "1", time, 1);
        time.add(Calendar.DATE,-1);
        time2.add(Calendar.DATE,-1);
        scheduleEntry.setStartTime(time);
        assertEquals(time2, scheduleEntry.getStartTime());

    }

    @Test
    public void testConversationAddMessage() {
        Conversation conversation = new Conversation("United Kingdom", "CSC207");
        Message message = new Message("United Kingdom", "CSC207", "Hi");
        ArrayList <Message> messagesList = new ArrayList<>();
        messagesList.add(message);
        conversation.addMessage(message);
        assertEquals(messagesList, conversation.getMessages());

    }

    @Test
    public void testConversationGetUsers() {
        Conversation conversation = new Conversation("United Kingdom", "CSC207");
        assertEquals("United Kingdom", conversation.getUserOne());
        assertEquals("CSC207", conversation.getUserTwo());

    }

    @Test
    public void testConversationRead() {
        Conversation conversation = new Conversation("United Kingdom", "CSC207");
        assertFalse(conversation.getIsRead());
        conversation.markAsRead();
        assertTrue(conversation.getIsRead());

    }


    //TODO Test Cases for Event Entity

    @Test
    public void testSetEventType() {
        Event event = new Event("Event");
        event.setEventType(Event.EventType.TALK);
        assertTrue(event.getEventType() == Event.EventType.TALK);
        assertFalse(event.getEventType() == Event.EventType.PARTY);
    }




}
