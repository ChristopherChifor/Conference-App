package Controllers;

import UseCases.RoomManager;

public class RoomController {
    private RoomManager roomManager;

    public RoomController() {
        this.roomManager = new RoomManager();
    }

    /**
     * Creates a room
     * @param roomName name of room
     * @param capacity capacity of room
     * @return true iff a room is successfully made
     */
    public boolean createRoom(String roomName, int capacity) {
        if (roomManager.roomExists(roomName)) return false;
        roomManager.createRoom(roomName, capacity);
        return true;
    }
}
