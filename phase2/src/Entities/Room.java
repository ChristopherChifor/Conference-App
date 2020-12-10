package Entities;

import java.io.Serializable;

/**
 * @author Parssa
 */
public class Room implements Serializable {
    private String name;
    private int roomCapacity;

    /**
     * Creates new room (Room constructor)
     * @param name: name of the room
     */
    public Room(String name) {
        this.name = name;
    }

    /**
     * Getter for name
     * @return name of room
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the capacity of room
     *
     * @return capacity of room
     */
    public int getRoomCapacity() {
        return roomCapacity;
    }

    /**
     * Setter for the capacity of room
     *
     * @param capacity: capacity of room
     * @return true if capacity is positive and is successfully set
     */

    public boolean setRoomCapacity(int capacity) {
        if (capacity >= 1) {
            this.roomCapacity = capacity;
            return true;
        }
        return false;
    }
}
