package Entities;

/**
 * @author Parssa
 */
public class Room {
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
}
