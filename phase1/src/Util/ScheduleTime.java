package Util;

import java.io.Serializable;

/**
 * This class is Immutable
 *
 * @author Parssa
 */
public class ScheduleTime implements Serializable {
    private int hour, minute = 0;
    private String referenceString;

    public ScheduleTime(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
        setReferenceString(hour, minute);
    }

    private void setReferenceString(Integer hour, Integer minute) {
        referenceString = hour.toString() + ":" + minute.toString();
    }

    @Override
    public String toString() {
        return referenceString;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public String getDateTime() { // YEAR : MONTH : DAY : HOUR : MINUTE
        return String.format("%d:%d", hour, minute);
    }

    public static ScheduleTime toScheduleTime(String time) {
        if (time.contains(":")) {
            int colonIndex = time.indexOf(':');
            String hours = time.substring(0, colonIndex);
            String minutes = time.substring(colonIndex + 1);
            return new ScheduleTime(Integer.parseInt(hours), Integer.parseInt(minutes));
        } else return null;
    }
}
