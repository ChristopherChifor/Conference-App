import Entities.User;
import org.junit.*;
import static org.junit.Assert.*;
public class TestEntity {

    @Test(timeout=50)
    public void testGetName() {
        User user = new User("David","CSC207","1234", User.UserType.ATTENDEE);
        assertEquals("David", user.getName());
        User user2 = new User("Sam","United Kingdom","java program", User.UserType.SPEAKER);
        assertEquals("Sam", user2.getName());
        User user3 = new User("John","Toronto","Clean Architecture", User.UserType.ORGANIZER);
        assertEquals("John", user3.getName());
    }

    @Test(timeout=50)
    public void testGetUsername() {
        User user = new User("David","CSC207","1234", User.UserType.ATTENDEE);
        assertEquals("CSC207", user.getUsername());
        User user2 = new User("Sam","United Kingdom","java program", User.UserType.SPEAKER);
        assertEquals("United Kingdom", user2.getUsername());
        User user3 = new User("John","Toronto","Clean Architecture", User.UserType.ORGANIZER);
        assertEquals("Toronto", user3.getUsername());
    }

    @Test(timeout=50)
    public void testGetPassword() {
        User user = new User("David","CSC207","1234", User.UserType.ATTENDEE);
        assertEquals("1234", user.getPassword());
        User user2 = new User("Sam","United Kingdom","java program", User.UserType.SPEAKER);
        assertEquals("java program", user2.getPassword());
        User user3 = new User("John","Toronto","Clean Architecture", User.UserType.ORGANIZER);
        assertEquals("Clean Architecture", user3.getPassword());
    }

    @Test(timeout=50)
    public void testGetUserType() {
        User user = new User("David","CSC207","1234", User.UserType.ATTENDEE);
        assertEquals(User.UserType.ATTENDEE, user.getUserType());
        User user2 = new User("Sam","United Kingdom","java program", User.UserType.SPEAKER);
        assertEquals(User.UserType.SPEAKER, user2.getUserType());
        User user3 = new User("John","Toronto","Clean Architecture", User.UserType.ORGANIZER);
        assertEquals(User.UserType.ORGANIZER, user3.getUserType());
    }

    @Test(timeout=50)
    public void testSetUserType() {
        User user = new User("paya","banter","1234", User.UserType.ATTENDEE);
        user.setUserType(User.UserType.SPEAKER);
        assertEquals(User.UserType.SPEAKER, user.getUserType());
        user.setUserType(User.UserType.ORGANIZER);
        assertEquals(User.UserType.ORGANIZER, user.getUserType());
    }


}
