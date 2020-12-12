package TestController;

import Controllers.RoomController;
import UseCases.RoomManager;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestRoomController {

    @Test
    public void testCreateRoom() {
        RoomController roomController = new RoomController();
        RoomManager roomManager = new RoomManager();
        roomController.createRoom("RoomRoom", 23);
        assertTrue(roomManager.getRoomNames().contains("RoomRoom"));
    }
}
