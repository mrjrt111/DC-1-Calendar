package designchallenge1;

public class CalendarEvent
{
    private int month;
    private int day;
    private int year;
    private String holiday;
    private String color;

    public CalendarEvent(int month, int day, int year, String holiday, String color)
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


}
