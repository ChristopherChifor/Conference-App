package Entities;

import java.io.Serializable;

/**
 * @author Parssa
 */
public class Room implements Serializable {
    private String name;

    private int capacity;

    public Room(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getString() {
        return name;
    }
}
