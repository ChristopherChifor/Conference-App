package TestController;

import Controllers.AccountController;
import Controllers.EventController;
import Controllers.RoomController;
import UseCases.AccountManager;
import UseCases.ScheduleManager;
import Util.UserType;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

public class TestEventController {

    @Test
    public void signUpEvent() {
        EventController eventController = new EventController();
        AccountController accountController = new AccountController();
        ScheduleManager scheduleManager = new ScheduleManager();
        RoomController roomController = new RoomController();

        roomController.createRoom("Room1", 50); //What if capacity of event is more than room
        eventController.createEvent("Event1", 50, "Room1",
                Calendar.getInstance(), 5);
        accountController.createUser("Jafar", "JJ", "pass", "pass", UserType.ATTENDEE);

        eventController.signUpEvent("JJ", "Event1");
        assertTrue(scheduleManager.inEvent("Event1", "JJ"));
    }

    @Test
    public void cancelEnrolment() {
        EventController eventController = new EventController();
        AccountController accountController = new AccountController();
        ScheduleManager scheduleManager = new ScheduleManager();
        RoomController roomController = new RoomController();

        roomController.createRoom("Room1", 50);
        eventController.createEvent("Event1", 50, "Room1",
                Calendar.getInstance(), 5);
        accountController.createUser("Jafar", "JJ", "pass", "pass", UserType.ATTENDEE);

        eventController.signUpEvent("Event1", "JJ");
        eventController.cancelEnrolment("Event1", "JJ");
        assertFalse(scheduleManager.getEvent("Event1").getAttendees().contains("JJ"));
    }

}
