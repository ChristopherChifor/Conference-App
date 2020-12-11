package Controllers;

import UseCases.RoomManager;

public class RoomController {
    private RoomManager roomManager;

    public RoomController() {
        this.roomManager = new RoomManager();
    }
}
