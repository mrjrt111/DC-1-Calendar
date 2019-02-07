package designchallenge1;

import java.util.ArrayList;

//Adapter Pattern for Interpreter with different date format,positioning
public interface DataInterpreterAdapter
{
    public ArrayList<CalendarEvent> dataToCalendarEvents();

}
