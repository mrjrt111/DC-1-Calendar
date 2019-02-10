package designchallenge1;

import java.util.ArrayList;

//Adapter Pattern for Interpreter with different date format,positioning
public interface DataInterpreterInterface
{
    public ArrayList<CalendarEvent> dataToCalendarEvents();

}
