package TestController;

import Controllers.AccountController;
import Controllers.EventController;
import UseCases.AccountManager;
import UseCases.ScheduleManager;
import Util.UserType;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

public class TestEventController {

    @Test
    public void test_enrolAttendee() {
        EventController eventController = new EventController();
        AccountController accountController = new AccountController();
        ScheduleManager scheduleManager = new ScheduleManager();

        eventController.createEvent("Event1", 50, "Room 1",
                Calendar.getInstance(), 5);
        accountController.createUser("Jafar", "JJ", "pass", "pass", UserType.ATTENDEE);

        eventController.enrolAttendee("Event1", "JJ");
        assertTrue(scheduleManager.getEvent("Event1").getAttendees().contains("JJ"));

    }
}
