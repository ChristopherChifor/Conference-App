package Controllers;

import UseCases.RoomManager;

public class RoomController {
    private RoomManager roomManager;

    public RoomController() {
        this.roomManager = new RoomManager();
    }

    public boolean createRoom(String roomName, int capacity) {
        System.out.println("1111made it here!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        if (roomManager.roomExists(roomName)) return false;
        System.out.println("made it here!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        roomManager.createRoom(roomName, capacity);
        return true;
    }
}
