package ui.state;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A bundle class for editing events. Extends EventBundle. Includes a list of valid
 * speakers and rooms (for drop down menu). Not an entity; for UI use only.
 */
public class EventEditBundle extends EventBundle {
    private final List<String> roomOptions;
    private final List<String> speakerOptions;

    /**
     * Constructor.
     *
     * @param title          event title
     * @param description    event discription
     * @param speaker        event speaker(s) (username)
     * @param room           event room (id/name)
     * @param time           a calendar object of the event date and time
     * @param duration       the duration of the event as a string; hours and minutes (e.g. "2:30")
     * @param vipOnly        true if this event is VIP only.
     * @param roomOptions    list of valid rooms (for dropdowns); including room
     * @param speakerOptions list of valid speakers (for dropdowns); including speaker(s)
     */
    public EventEditBundle(String title, String description, List<String> speaker, String room, Calendar time, String duration, int capacity, boolean vipOnly, List<String> roomOptions, List<String> speakerOptions) {
        super(title, description, speaker, room, time, duration, capacity, vipOnly);
        if (!roomOptions.contains(room) && !room.equals(""))
            throw new IllegalArgumentException("Room must be in roomOptions");
        if (!speakerOptions.containsAll(speaker) && !speaker.isEmpty())
            throw new IllegalArgumentException("Speaker(s) must be in speakerOptions");
        this.roomOptions = roomOptions;
        this.speakerOptions = speakerOptions;
    }

    /**
     * Constructor from EventBundle
     *
     * @param bundle         event bundle
     * @param roomOptions    list of valid rooms; including room
     * @param speakerOptions list of valid speakers; including speaker
     */
    public EventEditBundle(EventBundle bundle, List<String> roomOptions, List<String> speakerOptions) {
        this(bundle.title, bundle.description, bundle.speaker, bundle.room, bundle.time, bundle.duration, bundle.capacity,bundle.vipOnly, roomOptions, speakerOptions);
    }

    /**
     * Constructor where all fields are empty except roomOptions and speakerOptions. Calendar is
     * the time of creation, capacity is 0, vipOnly is false.
     *
     * @param roomOptions    list of valid rooms
     * @param speakerOptions list of valid speakers
     */
    public EventEditBundle(List<String> roomOptions, List<String> speakerOptions) {
        this("", "", new ArrayList<>(), "", Calendar.getInstance(), "", 0,false, roomOptions, speakerOptions);
    }

    /**
     * Returns data of this object as EventBundle (for displaying)
     *
     * @return EventBundle
     */
    public EventBundle getEventBundle() {
        return new EventBundle(title, description, speaker, room, time, duration, capacity, vipOnly);
    }

    /**
     * Room options getter
     *
     * @return list of valid rooms
     */
    public List<String> getRoomOptions() {
        return roomOptions;
    }

    /**
     * Speaker options getter
     *
     * @return list of valid speakers
     */
    public List<String> getSpeakerOptions() {
        return speakerOptions;
    }
}
