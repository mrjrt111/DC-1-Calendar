package designchallenge1;

import java.awt.*;
/*
    This class handles the loadedEvents on the calendar.
    This contains month, day, year in integer
    Color is stored as a Color Class
 */
public class CalendarEvent
{
    private int month;
    private int day;
    private int year;
    private String holiday;
    private Color color;

    public CalendarEvent(int month, int day, int year, String holiday, Color color)
    {
        this.month = month;
        this.day = day;
        this.year = year;
        this.holiday = holiday;
        this.color = color;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getHoliday() {
        return holiday;
    }

    public void setHoliday(String holiday) {
        this.holiday = holiday;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }


}
