package Entities;

/**
 * @author Parssa
 */
public class Room {
    private static int nextId = 0;

    private int id;

    private String name;

    private int capacity = 10;

    public Room(String name) {
        id = nextId ++;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }
}
