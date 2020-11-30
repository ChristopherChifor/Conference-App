package Entities;

import java.io.Serializable;

/**
 * @author Parssa
 */
public class Room implements Serializable {
    private String name;

    private int roomCapacity;

    public Room(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getRoomCapacity() {
        return roomCapacity;
    }

    public boolean setRoomCapacity(int capacity) {
        if (capacity >= 1) {
            this.roomCapacity = capacity;
            return true;
        }
        return false;
    }

    public String getString() {
        return name;
    }
}
