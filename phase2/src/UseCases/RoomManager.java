package UseCases;

import Entities.Room;

import java.util.HashMap;

public class RoomManager {
    HashMap<String, Room> rooms; // should never be given out; its mutable

    /**
     * Constructor for EventManager
     */
    public RoomManager() {
        rooms = new HashMap<>();
    }
    /**
     * Creates a new Room
     *
     * @param roomName Name of Room that is to be created
     * @return true if no other room has that name and new Room is created
     */
    public boolean createRoom(String roomName, int roomCapacity) {
        if (getRoom(roomName) != null) return false;
        Room room = new Room(roomName);
        if (! room.setRoomCapacity(roomCapacity)) {
            return false;
        }
        rooms.put(roomName, room);
        return true;
    }
    /**
     * Gets Room from it's name
     *
     * @param roomName Name of Room that is to be taken
     * @return the room if it exists and null otherwise
     */
    public Room getRoom(String roomName) {
        return (rooms.containsKey(roomName)) ? rooms.get(roomName) : null;
    }
}
