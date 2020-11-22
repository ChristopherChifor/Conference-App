package Entities;

import java.io.Serializable;

/**
 * @author Parssa
 */
public class Room implements Serializable {
    private String name;

    private int capacity = 10;

    public Room(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getString() {
        // TODO
        return "";
    }
}
