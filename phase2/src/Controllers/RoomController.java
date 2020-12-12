package Controllers;

import UseCases.RoomManager;

public class RoomController {
    private RoomManager roomManager;

    public RoomController() {
        this.roomManager = new RoomManager();
    }

    public boolean createRoom(String roomName, int capacity) {
        if (roomManager.roomExists(roomName)) return false;
        roomManager.createRoom(roomName, capacity);
        return true;
    }
}
