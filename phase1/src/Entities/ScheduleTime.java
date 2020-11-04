package Entities;

/**
 * This class is Immutable
 *
 * @author Parssa
 */
public class ScheduleTime {
    private int day, month, year, hour, minute = 0;


    public ScheduleTime(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public ScheduleTime(int day, int month, int year, int hour, int minute) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }
}
