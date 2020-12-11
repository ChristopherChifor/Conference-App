import Entities.*;
import Util.UserType;
import org.junit.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import static org.junit.Assert.*;
public class TestEntity {


    //TODO Test Cases for User Entity

    @Test(timeout=50)
    public void testGetNameUser() {
        User user = new User("David","CSC207","123", UserType.ATTENDEE);
        assertEquals("David", user.getName());
        User user2 = new User("Sam","United Kingdom","java program", UserType.SPEAKER);
        assertEquals("Sam", user2.getName());
        User user3 = new User("John","Toronto","Clean Architecture", UserType.ORGANIZER);
        assertEquals("John", user3.getName());
    }

    @Test(timeout=50)
    public void testGetUsername() {
        User user = new User("David","CSC207","123", UserType.ATTENDEE);
        assertEquals("CSC207", user.getUsername());
        User user2 = new User("Sam","United Kingdom","java program", UserType.SPEAKER);
        assertEquals("United Kingdom", user2.getUsername());
        User user3 = new User("John","Toronto","Clean Architecture", UserType.ORGANIZER);
        assertEquals("Toronto", user3.getUsername());
    }

    @Test(timeout=50)
    public void testGetPassword() {
        User user = new User("David","CSC207","123", UserType.ATTENDEE);
        assertEquals("123", user.getPassword());
        User user2 = new User("Sam","United Kingdom","java program", UserType.SPEAKER);
        assertEquals("java program", user2.getPassword());
        User user3 = new User("John","Toronto","Clean Architecture", UserType.ORGANIZER);
        assertEquals("Clean Architecture", user3.getPassword());
    }

    @Test(timeout=50)
    public void testGetUserType() {
        User user = new User("David","CSC207","123", UserType.ATTENDEE);
        assertEquals(UserType.ATTENDEE, user.getUserType());
        User user2 = new User("Sam","United Kingdom","java program", UserType.SPEAKER);
        assertEquals(UserType.SPEAKER, user2.getUserType());
        User user3 = new User("John","Toronto","Clean Architecture", UserType.ORGANIZER);
        assertEquals(UserType.ORGANIZER, user3.getUserType());
    }

    @Test(timeout=50)
    public void testSetUserType() {
        User user = new User("David","CSC207","123", UserType.ATTENDEE);
        user.setUserType(UserType.SPEAKER);
        assertEquals(UserType.SPEAKER, user.getUserType());
        user.setUserType(UserType.ORGANIZER);
        assertEquals(UserType.ORGANIZER, user.getUserType());
    }

    //TODO Test Cases for Room Entity

    @Test(timeout=50)
    public void testGetNameRoom() {
        Room room = new Room("Room 207");
        assertEquals("Room 207", room.getName());

    }

    @Test(timeout=50)
    public void testGetStringRoom() {
        Room room = new Room("Room 207");
        assertEquals("Room 207", room.getName());
    }


    @Test(timeout=50)
    public void testSetAndGetRoomCapacity() {
        Room room = new Room("Room 207");
        room.setRoomCapacity(20);
        assertEquals(20, room.getRoomCapacity());

    }

    @Test(timeout=50)
    public void testSetAndGetRoomCapacityVersion2() {
        Room room = new Room("Room 207");
        room.setRoomCapacity(20);
        room.setRoomCapacity(45);
        assertEquals(45, room.getRoomCapacity());

    }

    @Test(timeout=50)
    public void testSetAndGetRoomCapacityNegative() {
        Room room = new Room("Room 207");
        assertEquals(false, room.setRoomCapacity(-20));

    }


    //TODO Test Cases for Conversation Entity


    //TODO Test Cases for Message Entity

    @Test(timeout=50)
    public void testMessageGetSender() {
        Message message = new Message("United Kingdom", "CSC207", "Hi");
        assertEquals("United Kingdom", message.getSender());

    }

    @Test(timeout=50)
    public void testMessageGetRecipient() {
        Message message = new Message("United Kingdom", "CSC207", "Hi");
        assertEquals("CSC207", message.getRecipient());

    }

    @Test(timeout=50)
    public void testMessageGetBody() {
        Message message = new Message("United Kingdom", "CSC207", "Hi");
        assertEquals("Hi", message.getBody());

    }

    @Test(timeout=50)
    public void testMessageArchiveFalse() {
        Message message = new Message("United Kingdom", "CSC207", "Hi");
        assertEquals(false, message.getIsArchived());

    }

    @Test(timeout=50)
    public void testMessageArchiveTrue() {
        Message message = new Message("United Kingdom", "CSC207", "Hi");
        message.markAsArchived();
        assertEquals(true, message.getIsArchived());

    }


    @Test(timeout=50)
    public void testScheduleEntryGetEventName() {
        Calendar time = Calendar.getInstance();
        ScheduleEntry scheduleEntry = new ScheduleEntry("Conference", "1", time, 1);
        assertEquals("Conference", scheduleEntry.getEventName());

    }

    @Test(timeout=50)
    public void testScheduleEntryGetRoomID() {
        Calendar time = Calendar.getInstance();
        ScheduleEntry scheduleEntry = new ScheduleEntry("Conference", "1", time, 1);
        assertEquals("1", scheduleEntry.getRoomID());

    }

    @Test(timeout=50)
    public void testScheduleEntryGetStartTime() {
        Calendar time = Calendar.getInstance();
        ScheduleEntry scheduleEntry = new ScheduleEntry("Conference", "1", time, 1);
        assertEquals(time, scheduleEntry.getStartTime());

    }

    @Test(timeout=50)
    public void testScheduleEntryGetDuration() {
        Calendar time = Calendar.getInstance();
        ScheduleEntry scheduleEntry = new ScheduleEntry("Conference", "1", time, 1);
        assertEquals(1, scheduleEntry.getDuration());

    }

    @Test(timeout=50)
    public void testScheduleEntrySetRoomID() {
        Calendar time = Calendar.getInstance();
        ScheduleEntry scheduleEntry = new ScheduleEntry("Conference", "1", time, 1);
        scheduleEntry.setRoomID("2");
        assertEquals("2", scheduleEntry.getRoomID());

    }

    @Test(timeout=50)
    public void testScheduleEntrySetDuration() {
        Calendar time = Calendar.getInstance();
        ScheduleEntry scheduleEntry = new ScheduleEntry("Conference", "1", time, 1);
        scheduleEntry.setDuration(2);
        assertEquals(2, scheduleEntry.getDuration());

    }

    @Test(timeout=50)
    public void testScheduleEntrySetStartTime() {
        Calendar time = Calendar.getInstance();
        Calendar time2 = (Calendar) time.clone();
        ScheduleEntry scheduleEntry = new ScheduleEntry("Conference", "1", time, 1);
        time.add(Calendar.DATE,-1);
        scheduleEntry.setStartTime(time);
        assertEquals(false, time2.equals(time));

    }

    @Test(timeout=50)
    public void testScheduleEntrySetStartTime2() {
        Calendar time = Calendar.getInstance();
        Calendar time2 = (Calendar) time.clone();
        ScheduleEntry scheduleEntry = new ScheduleEntry("Conference", "1", time, 1);
        time.add(Calendar.DATE,-1);
        time2.add(Calendar.DATE,-1);
        scheduleEntry.setStartTime(time);
        assertEquals(time2, scheduleEntry.getStartTime());

    }

    @Test(timeout=50)
    public void testConversationAddMessage() {
        Conversation conversation = new Conversation("United Kingdom", "CSC207");
        Message message = new Message("United Kingdom", "CSC207", "Hi");
        ArrayList <Message> messagesList = new ArrayList<>();
        messagesList.add(message);
        conversation.addMessage(message);
        assertEquals(messagesList, conversation.getMessages());

    }


    //TODO Test Cases for Event Entity


}
