import Entities.Schedule;
import Entities.User;
import Entities.Room;
import Util.ScheduleTime;
import org.junit.*;

import java.util.HashMap;

import static org.junit.Assert.*;
public class TestEntity {


    //TODO Test Cases for User Entity

    @Test(timeout=50)
    public void testGetNameUser() {
        User user = new User("David","CSC207","123", User.UserType.ATTENDEE);
        assertEquals("David", user.getName());
        User user2 = new User("Sam","United Kingdom","java program", User.UserType.SPEAKER);
        assertEquals("Sam", user2.getName());
        User user3 = new User("John","Toronto","Clean Architecture", User.UserType.ORGANIZER);
        assertEquals("John", user3.getName());
    }

    @Test(timeout=50)
    public void testGetUsername() {
        User user = new User("David","CSC207","123", User.UserType.ATTENDEE);
        assertEquals("CSC207", user.getUsername());
        User user2 = new User("Sam","United Kingdom","java program", User.UserType.SPEAKER);
        assertEquals("United Kingdom", user2.getUsername());
        User user3 = new User("John","Toronto","Clean Architecture", User.UserType.ORGANIZER);
        assertEquals("Toronto", user3.getUsername());
    }

    @Test(timeout=50)
    public void testGetPassword() {
        User user = new User("David","CSC207","123", User.UserType.ATTENDEE);
        assertEquals("123", user.getPassword());
        User user2 = new User("Sam","United Kingdom","java program", User.UserType.SPEAKER);
        assertEquals("java program", user2.getPassword());
        User user3 = new User("John","Toronto","Clean Architecture", User.UserType.ORGANIZER);
        assertEquals("Clean Architecture", user3.getPassword());
    }

    @Test(timeout=50)
    public void testGetUserType() {
        User user = new User("David","CSC207","123", User.UserType.ATTENDEE);
        assertEquals(User.UserType.ATTENDEE, user.getUserType());
        User user2 = new User("Sam","United Kingdom","java program", User.UserType.SPEAKER);
        assertEquals(User.UserType.SPEAKER, user2.getUserType());
        User user3 = new User("John","Toronto","Clean Architecture", User.UserType.ORGANIZER);
        assertEquals(User.UserType.ORGANIZER, user3.getUserType());
    }

    @Test(timeout=50)
    public void testSetUserType() {
        User user = new User("David","CSC207","123", User.UserType.ATTENDEE);
        user.setUserType(User.UserType.SPEAKER);
        assertEquals(User.UserType.SPEAKER, user.getUserType());
        user.setUserType(User.UserType.ORGANIZER);
        assertEquals(User.UserType.ORGANIZER, user.getUserType());
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
        assertEquals("Room 207", room.getString());
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


    //TODO Test Cases for Event Entity


}
