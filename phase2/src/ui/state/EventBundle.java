package ui.state;

import java.util.Calendar;
import java.util.List;

/**
 * A data object of Event. This class contains all the information needed to display and event.
 * This class is non-mutable and should not be used as an entity; only for UI.
 */
public class EventBundle {
    protected final String title;
    protected final String description;
    protected final List<String> speaker;
    protected final String room;
    protected final Calendar time;
    protected final String duration;

    /**
     * Constructor.
     *
     * @param title       event title
     * @param description event discription
     * @param speaker     event speaker(s) (username)
     * @param room        event room (id/name)
     * @param time        a calendar object of the event date and time
     * @param duration    the duration of the event as a string; hours and minutes (e.g. "2:30")
     */
    public EventBundle(String title, String description, List<String> speaker, String room, Calendar time, String duration) {
        this.title = title;
        this.description = description;
        this.speaker = speaker;
        this.room = room;
        this.time = time;
        this.duration = duration;
    }

    /**
     * Title getter
     *
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Description getter
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Speaker's username getter
     *
     * @return speaker username
     */
    public List<String> getSpeaker() {
        return speaker;
    }

    /**
     * Room getter
     *
     * @return room
     */
    public String getRoom() {
        return room;
    }

    /**
     * Calendar getter
     *
     * @return time as a calender
     */
    public Calendar getTime() {
        return time;
    }

    /**
     * Duration getter
     *
     * @return duration as a string
     */
    public String getDuration() {
        return duration;
    }
}
