package TestUseCase;

import Controllers.EventController;
import UseCases.ScheduleManager;
import org.junit.Test;

import java.util.Calendar;
import static org.junit.Assert.*;

public class TestScheduleManager {

    @Test
    public void testGetEventAttendees() {
        EventController eventController = new EventController();
        ScheduleManager scheduleManager = new ScheduleManager();
//        eventController.createEvent("EVENT", 50, "Room 3", Calendar.getInstance(),
//                50, null);
        eventController.signUpEvent("Jafar", "EVENT");
        eventController.signUpEvent("Jafar2", "EVENT");

        assertTrue(scheduleManager.getEventAttendees("EVENT").contains("Jafar"));

    }
}
