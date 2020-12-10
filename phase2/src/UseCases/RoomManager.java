package UseCases;


import Entities.Room;
import Gateways.IGateway;
import Gateways.JsonDatabase;

public class RoomManager {
   private IGateway<Room> roomJsonDatabase;

    /**
     * Constructor for EventManager
     */
    public RoomManager() {
        roomJsonDatabase = new JsonDatabase<Room>("Room", Room.class);
    }

    /**
     * Creates a new Room
     *
     * @param roomName     Name of Room that is to be created
     * @param roomCapacity Capacity of the room that is to be created
     * @return true if no other room has that name and new Room is created
     */
    public boolean createRoom(String roomName, int roomCapacity) {
        if (!roomExists(roomName)) return false;
        Room room = new Room(roomName);
        if (!room.setRoomCapacity(roomCapacity)) {
            return false;
        }
        roomJsonDatabase.write(room, roomName);
        return true;
    }

    /**
     * Checks if a room exists
     *
     * @param roomName Name of room that is being checked
     * @return true if room exists
     */
    public boolean roomExists(String roomName) {
        return roomJsonDatabase.getIds().contains(roomName);
    }

    /**
     * Gets Room from it's name
     *
     * @param roomName Name of Room that is to be taken
     * @return the room if it exists and null otherwise
     */
    public Room getRoom(String roomName) {
        return roomJsonDatabase.read(roomName);
    }
}